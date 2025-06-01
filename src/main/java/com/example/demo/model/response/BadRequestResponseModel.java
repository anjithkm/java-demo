package com.example.demo.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BadRequestResponseModel {
    private String message;
    private List<FieldError> errors;

    // Constructors, getters, and setters
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldError {
        private String field;
        private String message;
        private Object rejectedValue;

        // Constructors, getters, and setters
    }
}
