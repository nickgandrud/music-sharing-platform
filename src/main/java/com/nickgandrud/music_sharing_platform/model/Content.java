package com.nickgandrud.music_sharing_platform.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public record Content(
        @Id
        Integer id,
        String title,
        String artist,
        Type contentType,
        LocalDateTime dateCreated,
        String url
) {
}
