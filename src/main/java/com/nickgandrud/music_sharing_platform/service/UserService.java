package com.nickgandrud.music_sharing_platform.service;

import com.nickgandrud.music_sharing_platform.dto.ContentResponse;
import com.nickgandrud.music_sharing_platform.dto.CreateUserRequest;
import com.nickgandrud.music_sharing_platform.dto.UserResponse;
import com.nickgandrud.music_sharing_platform.model.Content;
import com.nickgandrud.music_sharing_platform.model.User;
import com.nickgandrud.music_sharing_platform.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> findAll(){
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Integer id){
        User user = requireUserById(id);
        return toResponse(user);
    }

    public UserResponse create(CreateUserRequest createUserRequest ){
        User user = new User(
                null,
                createUserRequest.username(),
                createUserRequest.email(),
                LocalDateTime.now()
        );
        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    //Public Method to search the user
    public User requireUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        )
                );
    }

    public UserResponse toResponse(User user){
        return new UserResponse(
                user.id(),
                user.username(),
                user.email(),
                user.createdAt()
        );
    }

}
