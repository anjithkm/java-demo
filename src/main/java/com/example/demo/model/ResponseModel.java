package com.example.demo.model;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// import com.example.demo.model.response.FailedResponseModel;
// import com.example.demo.model.response.SuccessResponseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseModel {
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
    public static class Success<T> {
        private String status = "SUCCESS"; // SUCCESS
        private T data;
        private String message;

        // Constructor that sets default status
        public Success(T data, String message) {
            this.data = data;
            this.message = message;
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Failed<T> {
        private String status = "FAILED"; // FAILED
        private String errorType; // BAD_REQUEST, NOT_FOUND, etc.
        private T error;
        private String message;

        // Constructor that sets default status
        public Failed(T error, String errorType, String message) {
            this.errorType = errorType;
            this.error = error;
            this.message = message;
        }
    }

    public static <T> ResponseEntity<Success<T>> Success(T data, String message) {
        Success<T> successResponse = new Success<T>(data, message);
        return new ResponseEntity<Success<T>>(successResponse, HttpStatus.OK);
    }

    public ResponseEntity<Failed<List<FieldError>>> BadRequest(List<FieldError> error, String msg) {
        String errorType = "BAD REQUEST";
        String message = (msg == null) ? "Please check your requested payload." : msg;
        Failed<List<FieldError>> failedResponse = new Failed<List<FieldError>>(error, errorType, message);
        return new ResponseEntity<Failed<List<FieldError>>>(failedResponse, HttpStatus.BAD_REQUEST);
    }

    public <T> ResponseEntity<Failed<T>> InternalError(T error) {
        String errorType = "INTERNAL SERVER ERROR";
        String message = "Something went wrong.";
        Failed<T> failedResponse = new Failed<T>(error, errorType, message);
        return new ResponseEntity<Failed<T>>(failedResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // public <T> Failed<T> UnAuthorizedError(T error) {
    // String errorType = "UNAUTHORIZED";
    // String message = "Access denied.";
    // return new Failed<T>(FailedStatus, errorType, error, message);
    // }

    // public <T> Failed<T> InValidTokenError(T error) {
    // String errorType = "INVALID TOKEN";
    // String message = "Not acceptable due to invalid token.";

    // return new Failed<T>(FailedStatus, errorType, error, message);
    // }

    // public <T> Failed<T> ExpiredTokenError(T error) {
    // String errorType = "INVALID TOKEN";
    // String message = "Not acceptable due to invalid token.";

    // return new Failed<T>(FailedStatus, errorType, error, message);
    // }

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
