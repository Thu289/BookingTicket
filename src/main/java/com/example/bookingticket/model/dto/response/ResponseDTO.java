package com.example.bookingticket.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private boolean isSuccess = true;
    private Object result;
    private String displayMessage;
}
