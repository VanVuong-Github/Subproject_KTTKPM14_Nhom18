package com.iuh.UserService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iuh.UserService.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
