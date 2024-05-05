package com.example.bookingticket.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GenreRequest {
    private int id;
    private String name;
    private String code;
    private String description;
    private int status;
}
