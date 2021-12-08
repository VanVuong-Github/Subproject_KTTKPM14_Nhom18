package com.iuh.CartService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iuh.CartService.entity.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

}
