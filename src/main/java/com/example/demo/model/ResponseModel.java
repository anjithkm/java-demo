package com.example.demo.model;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.response.FailedResponseModel;
import com.example.demo.model.response.SuccessResponseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseModel {
    private String SuccessStatus = "SUCCESS";
    private String FailedStatus = "FAILED";
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Success<T> {
        private String status; // SUCCESS
        private T data;
        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Failed<T> {
        private String status; // FAILED
        private String errorType; // BAD_REQUEST, NOT_FOUND, etc.
        private T error;
        private String message;
    }

    public ResponseEntity<Object> Ok(T data, String message) {
        Success<T> successResponse = new Success<T>(SuccessStatus, data, message);
        return new ResponseEntity<Object>(successResponse, HttpStatus.OK);
    }

    public ResponseEntity<> BadRequestError(List<FieldError> error) {
        String errorType = "BAD REQUEST";
        String message = "Pls check requested payload.";
        Failed<List<FieldError>> failedResponse = new Failed<List<FieldError>>(FailedStatus, errorType, error, message);
        return new ResponseEntity<>(failedResponse, HttpStatus.BAD_REQUEST);
    }

    public <T> Failed<T> InternalServerError(T error) {
        String errorType = "INTERNAL SERVER ERROR";
        String message = "Something went wrong.";
        return new Failed<T>(FailedStatus, errorType, error, message);
    }

    public <T> Failed<T> UnAuthorizedError(T error) {
        String errorType = "UNAUTHORIZED";
        String message = "Access denied.";
        return new Failed<T>(FailedStatus, errorType, error, message);
    }

    public <T> Failed<T> InValidTokenError(T error) {
        String errorType = "INVALID TOKEN";
        String message = "Not acceptable due to invalid token.";

        return new Failed<T>(FailedStatus, errorType, error, message);
    }

    public <T> Failed<T> ExpiredTokenError(T error) {
        String errorType = "INVALID TOKEN";
        String message = "Not acceptable due to invalid token.";

        return new Failed<T>(FailedStatus, errorType, error, message);
    }

    // public ResponseEntity<FailedResponseModel<Object>> internalServerError(Object
    // details) {
    // Object error = details == null ? new Object() : details;
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error("INTERNAL
    // SERVER ERROR", "Something went wrong",error));
    // }

    // public ResponseEntity<FailedResponseModel<Object>> unAuthorized(Object
    // details) {
    // Object error = details == null ? new Object() : details;
    // return
    // ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error("UNAUTHORIZED",
    // "Access denied",error));
    // }

    // public ResponseEntity<FailedResponseModel<Object>> inValidToken(Object
    // details) {
    // Object error = details == null ? new Object() : details;
    // return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error("INVALID
    // TOKEN", "Not acceptable due to invalid token",error));
    // }

    // public ResponseEntity<FailedResponseModel<Object>> expiredToken(Object
    // details) {
    // Object error = details == null ? new Object() : details;
    // return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error("EXPIRED
    // TOKEN", "Not acceptable due to expired token",error));
    // }

    // public ResponseEntity<FailedResponseModel<Object>> DataError(Object details)
    // {
    // Object error = details == null ? new Object() : details;
    // return
    // ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error("DATA
    // ERROR", "Access denied",error));
    // }

    // public ResponseEntity<FailedResponseModel<Object>> badRequest(Object details)
    // {
    // Object error = details == null ? new Object() : details;
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error("BAD
    // REQUEST", "Pls check requested payload",error));
    // }

}
