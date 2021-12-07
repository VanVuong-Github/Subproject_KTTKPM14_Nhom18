package com.iuh.CartService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    Cart findCart(@PathVariable("id") Long id){
        return cartService.findCart(id);
    }

	@GetMapping("/")
    List<Cart> findAllCarts(){
    	return cartService.findAllCarts();
    }
}
