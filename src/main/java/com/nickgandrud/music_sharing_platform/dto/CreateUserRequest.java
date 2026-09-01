package com.nickgandrud.music_sharing_platform.dto;

import com.nickgandrud.music_sharing_platform.model.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

//CreateContentRequest DTO so things like ID are not sent from the client.
public record CreateUserRequest(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Email is required")
        String email,

        @NotNull(message = "Date created is required")
        LocalDateTime createdAt
) {

}
