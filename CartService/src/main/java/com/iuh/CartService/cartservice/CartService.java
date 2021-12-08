package com.iuh.CartService.cartservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.iuh.CartService.entity.Cart;
import com.iuh.CartService.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	@CachePut(value = "redis_cart")
	public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Cacheable(value = "redis_cart")
    public Cart findCart(String id){
        return cartRepository.findById(id).get();
    }

    @Cacheable(value = "redis_cart")
    public List<Cart> findAllCarts(){
        return cartRepository.findAll();
    }

    @Cacheable(value = "redis_cart",key = "#id")
    public List<Cart> findByUserId(String id){
        List<Cart> carts = findAllCarts();
        List<Cart> kq =new ArrayList<>();
        for (Cart i : carts){
            if (i.getUserId().equals(id)){
                kq.add(i);
            }
        }
        return kq;
    }
}
