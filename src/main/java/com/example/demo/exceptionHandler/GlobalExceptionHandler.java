package com.example.demo.exceptionHandler;

import com.example.demo.model.response.BadRequestResponseModel;
// BadRequestResponseModel
import com.example.demo.model.response.FailedResponseModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
// import org.springframework.validation.FieldError;
// import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestResponseModel> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<BadRequestResponseModel.FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new BadRequestResponseModel.FieldError(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue()))
                .collect(Collectors.toList());
        BadRequestResponseModel errorResponse = new BadRequestResponseModel("Validation failed", fieldErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BadRequestResponseModel> handleGeneralExceptions(Exception ex) {
        BadRequestResponseModel errorResponse = new BadRequestResponseModel("Something went wrong", null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
