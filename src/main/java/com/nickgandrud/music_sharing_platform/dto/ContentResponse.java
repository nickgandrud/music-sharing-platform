package com.nickgandrud.music_sharing_platform.dto;

import com.nickgandrud.music_sharing_platform.model.Type;

import java.time.LocalDateTime;

//Sends back this to the clients.
public record ContentResponse(
        Integer id,
        String title,
        String artist,
        Type contentType,
        LocalDateTime dateCreated,
        String url
) {
}
