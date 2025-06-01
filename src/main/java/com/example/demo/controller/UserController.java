package com.example.demo.controller;

// import com.example.demo.dto.UserDTO;
import com.example.demo.dto.request.UserDto;
import com.example.demo.entity.User;
// import com.example.demo.model.FailedResponse;
// import com.example.demo.model.SuccessResponse;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResponseUtil;

import jakarta.validation.Valid;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.ObjectError;
// // import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping
    // public ResponseEntity<SuccessResponse<List<User>>> getAllUsers() {
    // List<User> data = userService.getAllUsers();
    // return ResponseEntity.ok(ResponseUtil.success(data,"Data fetched
    // successfully"));
    // }

    // @GetMapping("/success")
    // public ResponseEntity<SuccessResponse<List<User>>> getSuccessUsers() {
    // List<User> data = userService.getAllUsers();
    // return ResponseEntity.ok(ResponseUtil.success(data,"Data fetched
    // successfully"));
    // }

    @GetMapping("/badRequest")
    public Object getBadRequestUsers() {
        ResponseUtil response = new ResponseUtil();
        return response.badRequest("");
    }

    @PostMapping
    public Object createUser(@Valid @RequestBody UserDto userDto) {
        ResponseUtil response = new ResponseUtil();
        User user = new User("", "");
        User result = userService.createUser(user);
        return response.success(result, "User Created Successfully");
    }
}