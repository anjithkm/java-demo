package com.example.demo.dto.request;

import jakarta.validation.constraints.*;

public class UserDto {
    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must be at most 60")
    private int age;

    @Email(message = "Invalid email format")
    private String email;

       // Constructors, Getters & Setters
    public UserDto(Integer id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    // public UserDto(String name, String email) {
    //     this.name = name;
    //     this.email = email;
    // }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
