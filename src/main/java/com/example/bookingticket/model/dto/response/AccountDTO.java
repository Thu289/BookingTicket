package com.example.bookingticket.model.dto.response;

import com.example.bookingticket.model.entity.Role;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountDTO {
    private int id;
    private String name;
    private String nickName;
    private String pass;
    private Timestamp dob;
    private String email;
    private boolean gender;
    private String phone;
    private int roleId;
    private Role role;
}
