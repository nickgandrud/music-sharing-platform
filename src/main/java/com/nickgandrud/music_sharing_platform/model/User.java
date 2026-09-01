package com.nickgandrud.music_sharing_platform.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("users")
public record User(
        @Id
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
