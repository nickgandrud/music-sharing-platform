package com.nickgandrud.music_sharing_platform.controller;

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
    public List<Content> findAll(){
        return contentService.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return contentService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        contentService.create(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        contentService.update(content, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        contentService.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword){
        return contentService.findByTitle(keyword);
    }

}
