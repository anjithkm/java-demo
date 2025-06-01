package com.example.demo.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SuccessResponseModel<T> {
    private String status;  // SUCCESS
     private T data;
     private String message;
}

