package com.iuh.CartService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import com.iuh.CartService.cartservice.CartService;
import com.iuh.CartService.entity.Cart;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/")
	Cart saveCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @GetMapping("/{id}")
    Cart findCart(@PathVariable("id") String id){
        return cartService.findCart(id);
    }

	@GetMapping("/")
    List<Cart> findAllCarts(){
    	return cartService.findAllCarts();
    }

    /**
     * find all carts with userId
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    List<Cart> findAllCarts(@PathVariable("id") String id){
        return cartService.findByUserId(id);
    }
}
