package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "price")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "showing_id")
    private ShowingMovie showing;

    @Column(name = "chairs")
    private String chairs;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private int status;
}
