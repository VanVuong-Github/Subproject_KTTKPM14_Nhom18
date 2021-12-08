package com.iuh.UserService.service;

import java.util.ArrayList;
import java.util.List;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
	@CachePut(value = "redis_user")
	public User saveUser(User order) {
		return userRepository.save(order);
	}
	@Cacheable(value = "redis_user")
	public User findUserById(String id) {
		return userRepository.findById(id).get();
	}

	@Cacheable(value = "redis_user")
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
/*
   @Retry(name = "basic")
	public ResponseTemplateVO getUserWithCart(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findById(userId).get();
        vo.setUser(user);
        Cart cart = restTemplate.getForObject("http://localhost:8000/cart/"
                                + user.getCartId(),
                                Cart.class);

        vo.setCart(cart);
        return vo;
    }
*/

	@Retry(name = "basic")
	@RateLimiter(name="basicExample")
	@Cacheable(value="redis_user",key = "#id")
	public ResponseTemplateVO getUserWithCartbyId(String id) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = findUserById(id);
		List<Cart> carts = new ArrayList<>();
		vo.setUser(user);
		List<Cart> listcart =  restTemplate.getForObject("http://localhost:8000/cart/user/" + user.getUserId(), List.class);
		vo.setCart(listcart );
		return vo;
	}

//	@Cacheable(value="redis_user")
	public ResponseTemplateVO getUserWithCartbyTK(String taiKhoan) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		List<User> users = userRepository.findAll();
		for (User i : users){
			if (i.getTaiKhoan().equals(taiKhoan)){
				return getUserWithCartbyId(i.getUserId());
			}
		}

		return vo;
	}

}
