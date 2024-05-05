package com.example.bookingticket.model.dto.response;

import lombok.Data;

@Data
public class GenreDTO {
    private int id;
    private String name;
    private String description;
    private String code;
    private int status;
}
