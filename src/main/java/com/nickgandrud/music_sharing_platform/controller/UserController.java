package com.nickgandrud.music_sharing_platform.controller;

import com.nickgandrud.music_sharing_platform.dto.CreateUserRequest;
import com.nickgandrud.music_sharing_platform.dto.UserResponse;
import com.nickgandrud.music_sharing_platform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserResponse> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findUserById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping("")
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return userService.create(createUserRequest);
    }

}
