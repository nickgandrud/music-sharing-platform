package com.nickgandrud.music_sharing_platform.controller;

import com.nickgandrud.music_sharing_platform.config.ContentProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    private final ContentProperties contentProperties;

    public HomeController(ContentProperties contentProperties) {
        this.contentProperties = contentProperties;
    }

    @Value("${content.properties.welcomeMessage: Default Welcome Message}")
    private String welcomeMessage;

    @Value("${content.properties.about:Hello}")
    private String about;

    @GetMapping("/")
    public ContentProperties home(){
        return contentProperties;
    }
}
