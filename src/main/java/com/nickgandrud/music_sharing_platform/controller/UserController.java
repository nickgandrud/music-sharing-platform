package com.nickgandrud.music_sharing_platform.controller;

import com.nickgandrud.music_sharing_platform.dto.ContentResponse;
import com.nickgandrud.music_sharing_platform.dto.CreateContentRequest;
import com.nickgandrud.music_sharing_platform.dto.CreateUserRequest;
import com.nickgandrud.music_sharing_platform.dto.UserResponse;
import com.nickgandrud.music_sharing_platform.service.ContentService;
import com.nickgandrud.music_sharing_platform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin

public class UserController {

    private final UserService userService;
    private final ContentService contentService;

    public UserController(UserService userService, ContentService contentService) {
        this.userService = userService;
        this.contentService = contentService;

    }

    @GetMapping("")
    public List<UserResponse> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserResponse findUserById(@PathVariable Integer userId){
        return userService.findById(userId);
    }

    @PostMapping("")
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return userService.create(createUserRequest);
    }

    @PostMapping("/{userId}/content")
    public ContentResponse createContentForUser(@PathVariable Integer userId, @Valid @RequestBody CreateContentRequest createContentRequest){
        return contentService.createForUser(createContentRequest, userId);
    }

    @GetMapping("/{userId}/content")
    public List<ContentResponse> findContentForUser(@PathVariable Integer userId){
        return contentService.findAllByUserId(userId);
    }


}
