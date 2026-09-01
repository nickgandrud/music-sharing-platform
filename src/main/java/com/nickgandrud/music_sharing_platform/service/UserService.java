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
        User user = findUserById(id);
        return toResponse(user);
    }

    public UserResponse create(CreateUserRequest createUserRequest ){
        User user = new User(
                null,
                createUserRequest.username(),
                createUserRequest.email(),
                createUserRequest.createdAt()
        );
        userRepository.save(user);
        return toResponse(user);
    }

    //Helper method that checks if content exists by the supplied id in the database. Returns the user in the database if found
    private User findUserById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Content not found"
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
