package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "type_showing")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeShowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private int status;
}
