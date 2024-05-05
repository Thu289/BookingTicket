package com.example.bookingticket.service;

import com.example.bookingticket.config.security.AuthenticationResponse;
import com.example.bookingticket.config.security.RegistrationCompleteEventListener;
import com.example.bookingticket.model.dto.request.AuthenticationRequest;
import com.example.bookingticket.model.dto.request.PasswordResetRequest;
import com.example.bookingticket.model.dto.request.RegisterRequest;
import com.example.bookingticket.model.entity.Account;
import com.example.bookingticket.repository.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RegistrationCompleteEventListener eventListener;

    @Value("${spring.config.link}")
    private String link;
    @Autowired
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AccountService accountService;

    public AuthenticationResponse register(RegisterRequest request) {
        Account user = new Account();
        user.setName(request.getName());
        user.setPass(bcryptEncoder.encode(request.getPassword()));
        user.setDob(request.getDob());
        user.setPhone(request.getPhone());
        user.setNickName(request.getNickName());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        accountRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().response(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Account user = accountService.findByNameOrEmail(request.getNameOrEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getNameOrEmail(),
                            request.getPassword()
                    )
            );
            var jwtToken = jwtService.generateToken(user);
            jwtService.revokeAllUserTokens(user);
            jwtService.createTokenForUser(user, jwtToken);

            log.info("Login successful");
            return AuthenticationResponse.builder().response(jwtToken).build();
        } catch (Exception e) {
            if (user == null) {
                log.error("Username is wrong");
            } else {
                log.error("Password is wrong");
            }
            return AuthenticationResponse.builder().response("Tên người dùng hoặc mật khẩu sai").build();
        }
    }

    public void ResetPassword(Account user, String newPassword) {
        user.setPass(bcryptEncoder.encode(newPassword));
        accountRepository.save(user);

    }

    public String applicationUrl(HttpServletRequest request) {
        return link;
    }

    public String passwordResetEmailLink(Account user, String applicationUrl, String passwordResetToken, PasswordResetRequest passwordResetRequest) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
        String url = "";
        if (passwordResetRequest.getType() == 1) {
            url = applicationUrl + "/login";

            user.setPass(accountService.passwordCoder(""));
            accountRepository.save(user);
            eventListener.sendVerificationEmail(url, user);
        } else {
            url = applicationUrl + "/resetPassword?" + passwordResetToken;
            eventListener.sendPasswordResetVerificationEmail(url, user);
        }
        log.info("Click the link to reset your password : ", url);
        return url;
    }
}
