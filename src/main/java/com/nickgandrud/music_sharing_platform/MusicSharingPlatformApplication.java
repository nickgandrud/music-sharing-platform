package com.nickgandrud.music_sharing_platform;

import com.nickgandrud.music_sharing_platform.config.ContentProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ContentProperties.class)
@SpringBootApplication
public class MusicSharingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicSharingPlatformApplication.class, args);
	}

}
