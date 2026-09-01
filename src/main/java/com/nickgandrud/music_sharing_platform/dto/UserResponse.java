package com.nickgandrud.music_sharing_platform.dto;

import com.nickgandrud.music_sharing_platform.model.Type;

import java.time.LocalDateTime;

//Sends back this to the clients.
public record UserResponse(
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
