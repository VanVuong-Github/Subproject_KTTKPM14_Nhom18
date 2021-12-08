package com.iuh.CartService.cartservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.CartService.entity.Cart;
import com.iuh.CartService.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
    
    public Cart findCart(String id){
        return cartRepository.findById(id).get();
    }

    public List<Cart> findAllCarts(){
        return cartRepository.findAll();
    }

    public List<Cart> findByUserId(String id){
        List<Cart> carts = cartRepository.findAll();
        List<Cart> kq =new ArrayList<>();
        for (Cart i : carts){
            if (i.getUserId().equals(id)){
                kq.add(i);
            }
        }
        return kq;
    }
}
