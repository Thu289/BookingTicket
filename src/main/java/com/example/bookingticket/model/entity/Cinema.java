package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cinema")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private int status;
}
