package com.nickgandrud.music_sharing_platform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "content.properties")
public record ContentProperties(String welcomeMessage, String about) {
}
