package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "movie")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "duration")
    private float duration;

    @Column(name = "public_time")
    private Timestamp publicTime;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private int status;

    @Column(name = "float")
    private float rating;

    @Column(name = "censorship")
    private String censorship;

    @Column(name = "type_id")
    private String type;
}
