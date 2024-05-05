package com.example.bookingticket.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;
    private String email;
    private Timestamp dob;
    private String phone;
    private String nickName;
    private int gender;
}
