package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "order")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private Timestamp createdTime;

    @Column(name = "status")
    private int status;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Account customer;

    @Column(name = "type")
    private int type;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "discount")
    private String discount;

    @Column(name = "final_price")
    private float finalPrice;
}
