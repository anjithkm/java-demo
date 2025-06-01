package com.example.demo.model.response;
// import org.springframework.web.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FailedResponseModel<T> {
    private String status; // FAILED
    private String errorType;  // BAD_REQUEST, NOT_FOUND, etc.
    private T error;
    private String message;
}
