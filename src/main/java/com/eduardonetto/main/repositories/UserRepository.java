package com.eduardonetto.main.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eduardonetto.main.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}
