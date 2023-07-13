package com.eduardonetto.main.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eduardonetto.main.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
