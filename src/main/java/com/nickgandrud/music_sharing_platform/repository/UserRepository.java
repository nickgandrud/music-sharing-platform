package com.nickgandrud.music_sharing_platform.repository;

import com.nickgandrud.music_sharing_platform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Integer> {

}
