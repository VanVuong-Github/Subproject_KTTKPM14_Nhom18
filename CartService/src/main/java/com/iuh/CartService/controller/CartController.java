package com.iuh.CartService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iuh.CartService.cartservice.CartService;
import com.iuh.CartService.entity.Cart;
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/user/{id}")
    List<Cart> findAllCarts(@PathVariable("id") Long id){
        return cartService.findByUserId(id);
    }
}
