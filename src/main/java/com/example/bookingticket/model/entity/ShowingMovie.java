package com.example.bookingticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "showing_movie")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowingMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @ManyToOne
    @JoinColumn(name = "type")
    private TypeShowing type;

    @Column(name = "status")
    private int status;

    @Column(name = "chair")
    private String chair;

    @Column(name = "booked_chair")
    private String bookedChair;


}
