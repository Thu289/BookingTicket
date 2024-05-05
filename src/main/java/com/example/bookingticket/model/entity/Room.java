package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "room")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @Column(name = "status")
    private int status;

    @Column(name = "row")
    private int row;

    @Column(name = "column")
    private int column;

    @Column(name = "chair")
    private String chairs;
}
