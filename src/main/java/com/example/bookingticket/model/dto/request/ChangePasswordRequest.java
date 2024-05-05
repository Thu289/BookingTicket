package com.example.bookingticket.model.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private int type;
    private String oldPassword;
    private String newPassword;
}
