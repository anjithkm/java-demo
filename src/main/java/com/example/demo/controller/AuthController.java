package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // @PostMapping("/signup")
    // public String signup(@RequestBody User user) {
    //     // Save user to database (implement your logic)
    //     return "User registered successfully!";
    // }

    // @PostMapping("/login")
    // public String login(@RequestBody User user) {
    //     // Validate user credentials (implement your logic)
    //     String token = JwtUtil.generateToken(user.getUsername());
    //     return "Bearer " + token;
    // }
}
