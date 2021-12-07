package com.iuh.UserService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iuh.UserService.entity.Cart;
import com.iuh.UserService.entity.User;
import com.iuh.UserService.repository.UserRepository;
import com.iuh.UserService.vo.ResponseTemplateVO;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User order) {
		return userRepository.save(order);
	}

	public User findUserById(long id) {
		return userRepository.findById(id).get();
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

//    @Retry(name = "basic")
//	public ResponseTemplateVO getUserWithCart(Long userId) {
//        ResponseTemplateVO vo = new ResponseTemplateVO();
//        User user = userRepository.findById(userId).get();
//        vo.setUser(user);
//        Cart cart = restTemplate.getForObject("http://localhost:8000/cart/"
//                                + user.getCartId(),
//                                Cart.class);
//
//        vo.setCart(cart);
//        return vo;
//    }

	@Retry(name = "basic")
	public ResponseTemplateVO getUserWithCartbyTK(String taiKhoan) {
		System.out.println(taiKhoan);
		ResponseTemplateVO vo = new ResponseTemplateVO();
		List<User> users = userRepository.findByTaiKhoan(taiKhoan);
		System.out.println(users);
		List<Cart> carts = new ArrayList<>();
		// vo.setUser(user);
		for (User i : users) {
			Cart cart = restTemplate.getForObject("http://localhost:8000/cart/" + i.getCartId(), Cart.class);

			carts.add(cart);
		}
		System.out.println(carts);
		vo.setCart(carts);
		return vo;
	}
}
