package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
public class Token {
    private static final int EXPIRATION_TIME = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;
    private String token;
    private Date expirationTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Account user;

    public Token(String token, Account user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public Token(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }

}