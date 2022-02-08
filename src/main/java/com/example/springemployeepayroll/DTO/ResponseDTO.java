package com.example.springemployeepayroll.DTO;
import lombok.Data;

// UC1 : Use Lombok to auto generate getters and setters for DTO
public @Data class ResponseDTO {
    private String message;
    private Object data;

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
