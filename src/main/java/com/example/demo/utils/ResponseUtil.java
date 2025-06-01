package com.example.demo.utils;
// import org.springframework.web.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// import org.springframework.http.HttpStatus;

import com.example.demo.model.response.*;

public class ResponseUtil {

    public <T> SuccessResponseModel<T> success(T data, String message) {
        return new SuccessResponseModel<T>("SUCCESS", data, message);
    }

    public <T> FailedResponseModel<T> error(String errorType, String message, T error) {
        return new FailedResponseModel<T>("FAILED", errorType, error, message);
    }

    public ResponseEntity<FailedResponseModel<Object>> internalServerError(Object details) {
        Object error = details == null ? new Object() : details;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error("INTERNAL SERVER ERROR", "Something went wrong", error));
    }

    public ResponseEntity<FailedResponseModel<Object>> unAuthorized(Object details) {
        Object error = details == null ? new Object() : details;
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error("UNAUTHORIZED", "Access denied", error));
    }

    public ResponseEntity<FailedResponseModel<Object>> inValidToken(Object details) {
        Object error = details == null ? new Object() : details;
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(error("INVALID TOKEN", "Not acceptable due to invalid token", error));
    }

    public ResponseEntity<FailedResponseModel<Object>> expiredToken(Object details) {
        Object error = details == null ? new Object() : details;
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(error("EXPIRED TOKEN", "Not acceptable due to expired token", error));
    }

    public ResponseEntity<FailedResponseModel<Object>> DataError(Object details) {
        Object error = details == null ? new Object() : details;
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error("DATA ERROR", "Access denied", error));
    }

    public ResponseEntity<FailedResponseModel<Object>> badRequest(Object details) {
        Object error = details == null ? new Object() : details;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error("BAD REQUEST", "Pls check requested payload", error));
    }

}
