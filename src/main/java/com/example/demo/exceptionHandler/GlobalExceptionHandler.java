package com.example.demo.exceptionHandler;

import com.example.demo.model.ResponseModel;
// import com.example.demo.model.ResponseModel.Failed;K
// import com.example.demo.model.response.ResponseModel;
// ResponseModel
// import com.example.demo.model.response.FailedResponseModel;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
// import org.springframework.web.ErrorResponse;
// import org.springframework.validation.FieldError;
// import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    ResponseModel Response = new ResponseModel();

    private String extractRejectedValue(String message) {
        // Example message format: "Cannot deserialize value of type `java.lang.Integer`
        // from String \"string\""
        Pattern pattern = Pattern.compile("from String \\\"(.*?)\\\"");
        Matcher matcher = pattern.matcher(message);

        return matcher.find() ? matcher.group(1) : "Unknown value";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid argument: " + ex.getClass().getSimpleName());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleTypeMismatchException(HttpMessageNotReadableException ex) {
        String message = "Invalid input type: Ensure all fields match the expected data type";
        List<ResponseModel.FieldError> errors = new ArrayList<>();

        // Extract specific cause from Jackson exception
        Throwable cause = ex.getCause();
        if (cause instanceof MismatchedInputException) {
            MismatchedInputException mismatchEx = (MismatchedInputException) cause;
            String exMsg = ex.getMessage();
            String rejectedValue = extractRejectedValue(exMsg);
            String fieldName = mismatchEx.getPath().get(0).getFieldName();
            String expectedType = mismatchEx.getTargetType().getSimpleName();
            String errorMessage = "Invalid type for field '" + fieldName + "', expected " + expectedType;

            // Construct the error object in the same format
            errors.add(new ResponseModel.FieldError(fieldName, errorMessage, rejectedValue));
        }
        return Response.BadRequest(errors, message);
    }

    // Handle validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = "Validation failed";
        List<ResponseModel.FieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ResponseModel.FieldError(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue()))
                .collect(Collectors.toList());
        return Response.BadRequest(errors, message);
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralExceptions(Exception ex) {
        StackTraceElement[] errors = ex.getStackTrace();
        return Response.InternalError(errors);
    }
}
