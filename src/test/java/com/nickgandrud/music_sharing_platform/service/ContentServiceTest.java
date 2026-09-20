package com.nickgandrud.music_sharing_platform.service;

import com.nickgandrud.music_sharing_platform.dto.ContentResponse;
import com.nickgandrud.music_sharing_platform.dto.CreateContentRequest;
import com.nickgandrud.music_sharing_platform.model.Content;
import com.nickgandrud.music_sharing_platform.model.Type;
import com.nickgandrud.music_sharing_platform.model.User;
import com.nickgandrud.music_sharing_platform.repository.ContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ContentServiceTest {

    ContentService contentService;
    UserService userService;
    ContentRepository contentRepository;


    @BeforeEach
    void setUp() {
        contentRepository = mock(ContentRepository.class);
        userService = mock(UserService.class);
        contentService = new ContentService(contentRepository, userService);

    }

    @Test
    void createForUser_existingUserContentCreation(){
        Integer userId = 1;
        User user = new User(
                1,
                "nick",
                "nick@gmail.com",
                LocalDateTime.now()
        );

        CreateContentRequest request = new CreateContentRequest(
                "Selected Ambient Works 85-92",
                "Aphex Twin",
                Type.ALBUM,
                LocalDateTime.of(1992, 11, 9, 0, 0),
                "https://example.com/album"
        );

        Content content = new Content(
                null,
                userId,
                request.title(),
                request.artist(),
                request.contentType(),
                request.dateCreated(),
                request.url()
        );

        Content savedContent = new Content(
                5,
                user.id(),
                request.title(),
                request.artist(),
                request.contentType(),
                request.dateCreated(),
                request.url()
        );

        when(userService.requireUserById(userId)).thenReturn(user);

        when(contentRepository.save(content)).thenReturn(savedContent);

        ContentResponse response = contentService.createForUser(request,userId);

        assertEquals(5,response.id());
        assertEquals(userId,response.userId());
        assertEquals(request.title(),response.title());

        verify(userService).requireUserById(userId);
        verify(contentRepository).save(content);
    }

    @Test void createForUser_userNotFound(){
        Integer userId = 100;

        CreateContentRequest request = new CreateContentRequest(
                "Selected Ambient Works 85-92",
                "Aphex Twin",
                Type.ALBUM,
                LocalDateTime.of(1992, 11, 9, 0, 0),
                "https://example.com/album"
        );

        when(userService.requireUserById(userId)).thenThrow(
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                )
        );


        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> contentService.createForUser(request,userId)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(contentRepository, never()).save(any());

    }

    @Test void findAllByUserId_returnUserContentOnly(){
        Integer userId = 1;
        User user = new User(
                1,
                "nick",
                "nick@gmail.com",
                LocalDateTime.now()
        );

        Content contentAlbum = new Content(
                10,
                userId,
                "Selected Ambient Works 85-92",
                "Aphex Twin",
                Type.ALBUM,
                LocalDateTime.of(1992, 11, 9, 0, 0),
                "https://example.com/album"
        );

        Content contentMix = new Content(
                11,
                userId,
                "Untitled A",
                "Samo Dj",
                Type.MIX,
                LocalDateTime.of(2016, 01, 05, 0, 0),
                "https://example.com/mix"
        );

        when(userService.requireUserById(userId)).thenReturn(user);

        when(contentRepository.findAllByUserId(userId)).thenReturn(List.of(contentAlbum,contentMix));

        List<ContentResponse> response = contentService.findAllByUserId(userId);

        assertEquals(2,response.size());

        assertEquals(userId, response.get(0).userId());
        assertEquals(userId, response.get(1).userId());

        verify(userService).requireUserById(userId);
        verify(contentRepository).findAllByUserId(userId);





    }

}
