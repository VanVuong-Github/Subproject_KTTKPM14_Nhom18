package com.iuh.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuh.UserService.entity.User;
import com.iuh.UserService.service.UserService;
import com.iuh.UserService.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService userService ;

    @PostMapping("/")
    User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/")
    List<User> findAllUsers(){
        return userService.findAllUsers();
    }
    
//    @GetMapping("/{id}")
//    public ResponseTemplateVO getUserWithCart(@PathVariable("id") Long id){
//        return userService.getUserWithCart(id);
//    }
    
    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithCartbyTK(@PathVariable("id") Long id){
        return userService.getUserWithCartbyId(id);
    }
}
