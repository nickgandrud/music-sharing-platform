package com.nickgandrud.music_sharing_platform.dto;

import com.nickgandrud.music_sharing_platform.model.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

//CreateContentRequest DTO so things like ID are not sent from the client.
public record CreateContentRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Artist is required")
        String artist,

        @NotNull(message = "Content Type is required")
        Type contentType,

        @NotNull(message = "Date created is required")
        LocalDateTime dateCreated,

        @NotBlank(message = "URL is required")
        String url
) {

}
