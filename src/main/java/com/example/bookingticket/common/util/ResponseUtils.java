package com.example.bookingticket.common.util;

import com.example.bookingticket.model.dto.response.ResponseDTO;

public class ResponseUtils {

    public static ResponseDTO success(
            Object result,
            String displayMessage
    ) {
        return new ResponseDTO(true, result, displayMessage);
    }

    public static ResponseDTO success(String displayMessage) {
        return new ResponseDTO(true, null, displayMessage);
    }

    public static ResponseDTO error(String errorMessage) {
        return new ResponseDTO(false, null, errorMessage);
    }

    public static ResponseDTO error(Object result, String errorMessage) {
        return new ResponseDTO(false, result, errorMessage);
    }
}
