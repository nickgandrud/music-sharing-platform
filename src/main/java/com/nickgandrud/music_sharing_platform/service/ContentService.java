package com.nickgandrud.music_sharing_platform.service;

import com.nickgandrud.music_sharing_platform.dto.ContentResponse;
import com.nickgandrud.music_sharing_platform.dto.CreateContentRequest;
import com.nickgandrud.music_sharing_platform.dto.UpdateContentRequest;
import com.nickgandrud.music_sharing_platform.model.Content;
import com.nickgandrud.music_sharing_platform.model.User;
import com.nickgandrud.music_sharing_platform.repository.ContentRepository;
import com.nickgandrud.music_sharing_platform.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final UserService userService;

    public ContentService(ContentRepository repository, UserService userService) {
        this.contentRepository = repository;
        this.userService = userService;
    }

    public List<ContentResponse> findAll(){
        return contentRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ContentResponse findById(Integer id){

        //Check if content exists by given id
        Content content = findContentById(id);

        //Converts the database content into the ContentResponse format for the client.
        return toResponse(content);

    }

    public ContentResponse create(CreateContentRequest request, Integer userId){
        /*Creates a new Content object for the database. Id is kept null since postgres handles it. All other values are
        filled in through the request object. */


        Content content = new Content(
                null,
                userId,
                request.title(),
                request.artist(),
                request.contentType(),
                request.dateCreated(),
                request.url()
            );

        //The content object is saved in the database and we save the content to the savedContent object.
        Content savedContent =  contentRepository.save(content);
        //Converts the database content into the ContentResponse format for the client.
        return toResponse(savedContent);
    }

    public ContentResponse createForUser(CreateContentRequest request, Integer userId){
        /*Creates a new Content object for the database. Id is kept null since postgres handles it. All other values are
        filled in through the request object. */

        //Validate User exists
        User user = userService.requireUserById(userId);
        Content content = new Content(
                null,
                userId,
                request.title(),
                request.artist(),
                request.contentType(),
                request.dateCreated(),
                request.url()
        );

        //The content object is saved in the database and we save the content to the savedContent object.
        Content savedContent =  contentRepository.save(content);
        //Converts the database content into the ContentResponse format for the client.
        return toResponse(savedContent);
    }

    public ContentResponse update(UpdateContentRequest request, Integer id){

        // Check if content exists by given id
        Content existingContent = findContentById(id);

         /*Creates a new Content object for the database. Note that id is passed in. All other values are
        filled in through the request object. */
        Content updatedContent = new Content(
                existingContent.id(),
                existingContent.userId(),
                request.title(),
                request.artist(),
                request.contentType(),
                request.dateCreated(),
                request.url()
        );


        Content savedContent = contentRepository.save(updatedContent);
        return toResponse(savedContent);
    }

    public void deleteById(Integer id){
        findContentById(id);
        contentRepository.deleteById(id);
    }

    public List<ContentResponse> findByTitle(String keyword) {

        return contentRepository.findAllByTitleContains(keyword)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //Helper method that checks if content exists by the supplied id in the database. Returns the content in the database if found
    private Content findContentById(Integer id){
        return contentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Content not found"
                        )
                );

    }


  
    private ContentResponse toResponse(Content content) {
        return new ContentResponse(
                content.id(),
                content.userId(),
                content.title(),
                content.artist(),
                content.contentType(),
                content.dateCreated(),
                content.url()
        );
    }

    public List<ContentResponse>  findAllByUserId(Integer userId){
        userService.requireUserById(userId);
        return contentRepository.findAllByUserId(userId).stream().map(this::toResponse).toList();
    }


}
