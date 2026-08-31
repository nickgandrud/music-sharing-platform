package com.nickgandrud.music_sharing_platform.service;

import com.nickgandrud.music_sharing_platform.dto.ContentResponse;
import com.nickgandrud.music_sharing_platform.dto.CreateContentRequest;
import com.nickgandrud.music_sharing_platform.dto.UpdateContentRequest;
import com.nickgandrud.music_sharing_platform.model.Content;
import com.nickgandrud.music_sharing_platform.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final ContentRepository repository;

    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }

    public List<ContentResponse> findAll(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ContentResponse findById(Integer id){

        //Check if content exists by given id
        Content content = findContentById(id);

        //Converts the database content into the ContentResponse format for the client.
        return toResponse(content);

    }

    public ContentResponse create(CreateContentRequest request){
        /*Creates a new Content object for the database. Id is kept null since postgres handles it. All other values are
        filled in through the request object. */
        Content content = new Content(
                null,
                request.title(),
                request.artist(),
                request.contentType(),
                request.dateCreated(),
                request.url()
            );

        //The content object is saved in the database and we save the content to the savedContent object.
        Content savedContent =  repository.save(content);
        //Converts the database content into the ContentResponse format for the client.
        return toResponse(savedContent);

    }

    public ContentResponse update(UpdateContentRequest request, Integer id){

        // Check if content exists by given id
        Content content = findContentById(id);

         /*Creates a new Content object for the database. Note that id is passed in. All other values are
        filled in through the request object. */
        Content updatedContent = new Content(
                id,
                request.title(),
                request.artist(),
                request.contentType(),
                request.dateCreated(),
                request.url()
        );


        Content savedContent = repository.save(updatedContent);
        return toResponse(savedContent);
    }

    public void deleteById(Integer id){
        findContentById(id);
        repository.deleteById(id);
    }

    public List<ContentResponse> findByTitle(String keyword) {

        return repository.findAllByTitleContains(keyword)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //Helper method that checks if content exists by the supplied id in the database. Returns the content in the database if found
    private Content findContentById(Integer id){
        return repository.findById(id)
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
                content.title(),
                content.artist(),
                content.contentType(),
                content.dateCreated(),
                content.url()
        );
    }

}
