package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "account")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nickname", length = 100)
    private String nickName;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name="email", length = 100)
    private String email;

    @Column(name="dob")
    private Timestamp dob;

    @Column(name="gender")
    private int gender;

    @Column(name="status")
    private int status;

    @Column(name="phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    @Column(name="password")
    private String pass;

    @Column(name="joindate")
    private Timestamp joinDate;

    @Column(name="avatar")
    private String avatar;
}
