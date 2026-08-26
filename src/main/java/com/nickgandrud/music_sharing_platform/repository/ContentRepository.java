package com.nickgandrud.music_sharing_platform.repository;

import com.nickgandrud.music_sharing_platform.model.Content;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {

    List<Content> findAllByTitleContains(String keyword);

}
