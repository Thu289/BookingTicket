package com.example.bookingticket.service;

import com.example.bookingticket.common.constant.Status;
import com.example.bookingticket.common.util.ResponseUtils;
import com.example.bookingticket.config.security.AuthenticationResponse;
import com.example.bookingticket.exception.NotFoundException;
import com.example.bookingticket.model.dto.request.AuthenticationRequest;
import com.example.bookingticket.model.dto.request.RegisterRequest;
import com.example.bookingticket.model.dto.response.AccountDTO;
import com.example.bookingticket.model.dto.response.ResponseDTO;
import com.example.bookingticket.model.entity.Account;
import com.example.bookingticket.model.entity.Role;
import com.example.bookingticket.repository.AccountRepository;
import com.example.bookingticket.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    public ResponseDTO getAccountDetailById(Integer id) {
        log.info("----- Get user Start------");
        try {
            Account account = getAccountById(id);
            log.info("----- Get User End ------");
            return ResponseUtils.success(account, "Get user success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Get user fail");
        }
    }

    protected Account getAccountById(Integer id){
        return accountRepository.findById(id)
                .orElseThrow(()->new NotFoundException("account with id "+ id));
    }

    public Account findByNameOrEmail(String nameOrEmail) {
        return nameOrEmail.contains("@")
                ? accountRepository.findByEmail(nameOrEmail)
                : accountRepository.findByName(nameOrEmail);
    }

    public String passwordCoder(String password) {
        return bcryptEncoder.encode(password);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        Account user = new Account();
        user.setName(request.getName());
        user.setPass(bcryptEncoder.encode(request.getPassword()));
        user.setNickName(request.getNickName());
        user.setDob(request.getDob());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
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

    public String validatePasswordResetToken(String token) {
        return jwtService.validatePasswordResetToken(token);
    }

    public Account findUserByPasswordToken(String passwordResetToken) {
        return jwtService.findUserByPasswordToken(passwordResetToken).get();
    }

    public ResponseDTO changePassword(int id, String oldPassword, String newPassword) {
        log.info("----- Change Password Start------");
        try {
            Account user = getAccountById(id);
            if (bcryptEncoder.matches(oldPassword, user.getPass())) {
                user.setPass(passwordCoder(newPassword));
                accountRepository.save(user);
                log.info("----- Change Password End ------");
                return ResponseUtils.success("Thay đổi mật khẩu thành công");
            }

            log.info("----- Change Password Error ------");
            return ResponseUtils.error("Thay đổi mật khẩu thất bại");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Thay đổi mật khẩu thất bại");
        }
    }

    public ResponseDTO createAndUpdateStaff(AccountDTO accountDTO) {
        log.info("----- Create And Update Staff Start------");
        try {
            Account user = new Account();
            if (accountDTO.getId() != 0) {
                user = getAccountById(user.getId());
            }
            if (
                    findByEmail(accountDTO.getEmail()) != null
                            && !accountDTO.getEmail().equals(user.getEmail()))
            {
                log.info("----- Create And Update Staff End ------");
                return ResponseUtils.error("Email đã tồn tại");

            }
            if(!accountDTO.getName().replace(" ", "").equals(""))
            {
                if (findByName(accountDTO.getName()) != null  && !accountDTO.getName().equals(user.getName()))
                {
                    log.info("----- Create And Update Staff End ------");
                    return ResponseUtils.error("Username đã tồn tại");
                }
            }
            if (!validateDob(accountDTO.getDob())) {

                log.info("----- Create And Update Staff End ------");
                return ResponseUtils.error("Ngày sinh nhỏ hơn ngày hiện tại!");
            }
            if (!accountDTO.getPhone().replace(" ", "").equals("")) {
                if (!accountRepository.findByPhoneNumber(accountDTO.getPhone()).isEmpty() && !accountDTO.getPhone().equals(user.getPhone())) {
                    log.info("----- Create And Update Staff End ------");
                    return ResponseUtils.error("Số điện thoại đã tồn tại!");
                }
            }

            commonMapping(user, accountDTO);
            accountRepository.save(user);

            log.info("----- Create And Update Staff End success------");
            return ResponseUtils.success(user.getId(), "Lưu thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lưu thất bại");
        }
    }

    private void commonMapping(Account user, AccountDTO accountDTO) {
        user.setId(accountDTO.getId() != 0 ? accountDTO.getId() : user.getId());
        user.setName(accountDTO.getName() == null ? user.getName() : accountDTO.getName());
        user.setNickName(accountDTO.getNickName() == null ? user.getNickName() : accountDTO.getNickName());
        user.setPhone(accountDTO.getPhone() == null ? user.getPhone() : accountDTO.getPhone());
        user.setDob(accountDTO.getDob());
        user.setEmail(accountDTO.getEmail() == null ? user.getEmail() : accountDTO.getEmail());
        user.setStatus(Status.ACTIVATE);
        Role role = user.getRole();
        if (accountDTO.getRoleId() != 0) {
            role = roleRepository.findById(accountDTO.getId()).orElseThrow(() -> new IllegalStateException("Department not found"));
        }
        user.setRole(role);
    }

    private boolean validateDob(Timestamp dob) {
        try {
            Date currentDate = new Date();
            return dob.before(currentDate);
        } catch (Exception e) {
            return false;
        }
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account findByName(String name) {
        return accountRepository.findByName(name);
    }
}
