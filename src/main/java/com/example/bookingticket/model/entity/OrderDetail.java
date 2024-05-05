package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Table(name = "order_detail")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "showing_id")
    private ShowingMovie showing;

    @Column(name = "chair")
    private String chair;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private float totalPrice;
}
