package com.example.bookingticket.controller;

import com.example.bookingticket.config.security.AuthenticationResponse;
import com.example.bookingticket.model.dto.request.AuthenticationRequest;
import com.example.bookingticket.model.dto.request.ChangePasswordRequest;
import com.example.bookingticket.model.dto.request.RegisterRequest;
import com.example.bookingticket.model.dto.response.ResponseDTO;
import com.example.bookingticket.model.entity.Account;
import com.example.bookingticket.service.AccountService;
import com.example.bookingticket.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(accountService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(accountService.authenticate(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ChangePasswordRequest passwordResetRequest,
                                                @RequestParam("token") String passwordResetToken) {
        String tokenValidationResult = accountService.validatePasswordResetToken(passwordResetToken);
        if (!tokenValidationResult.equalsIgnoreCase("valid")) {
            return new ResponseEntity<>("Mã thay đổi không hợp lệ", HttpStatus.OK);
        }

        Account user = accountService.findUserByPasswordToken(passwordResetToken);
        if (user != null) {
            if (passwordResetRequest.getType() == 1) {
                ResponseDTO responseDTO = accountService.changePassword(user.getId(), passwordResetRequest.getOldPassword(), passwordResetRequest.getNewPassword());
                if (responseDTO.isSuccess()) {
                    return new ResponseEntity<>(responseDTO.getDisplayMessage(), HttpStatus.OK);
                }
                return new ResponseEntity<>(responseDTO.getDisplayMessage(), HttpStatus.OK);
            }
            accountService.ResetPassword(user, passwordResetRequest.getNewPassword());
            return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("Mã thay đổi không hợp lệ", HttpStatus.OK);

    }
}
