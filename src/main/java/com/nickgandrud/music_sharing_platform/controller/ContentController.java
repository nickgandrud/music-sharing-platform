package com.nickgandrud.music_sharing_platform.controller;

import com.nickgandrud.music_sharing_platform.dto.ContentResponse;
import com.nickgandrud.music_sharing_platform.dto.CreateContentRequest;
import com.nickgandrud.music_sharing_platform.dto.UpdateContentRequest;
import com.nickgandrud.music_sharing_platform.model.Content;
import com.nickgandrud.music_sharing_platform.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin

public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {this.contentService = contentService;}

    @GetMapping("")
    public List<ContentResponse> findAll(){
        return contentService.findAll();
    }

    @GetMapping("/{id}")
    public ContentResponse findById(@PathVariable Integer id){
        return contentService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ContentResponse create(@Valid @RequestBody CreateContentRequest content){
        return contentService.create(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ContentResponse update(@RequestBody UpdateContentRequest content, @PathVariable Integer id){
       return contentService.update(content, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        contentService.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<ContentResponse> findByTitle(@PathVariable String keyword){
        return contentService.findByTitle(keyword);
    }

}
