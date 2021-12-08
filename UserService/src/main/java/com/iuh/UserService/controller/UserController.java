package com.iuh.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import com.iuh.UserService.entity.User;
import com.iuh.UserService.service.UserService;
import com.iuh.UserService.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
    private UserService userService ;

    @PostMapping("/saveUser")
    User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/")
    @Cacheable(value = "user")
    List<User> findAllUsers(){
        return userService.findAllUsers();
    }
    
//    @GetMapping("/{id}")
//    public ResponseTemplateVO getUserWithCart(@PathVariable("id") Long id){
//        return userService.getUserWithCart(id);
//    }

  //  @GetMapping("/{id}")
   // public ResponseTemplateVO getUserWithCartbyId(@PathVariable("id") Long id){
  //      return userService.getUserWithCartbyId(id);
 //   }
    @GetMapping("/{taiKhoan}")
    @Cacheable(value="user",key = "#taiKhoan")
    public ResponseTemplateVO getUserWithCartbyTK(@PathVariable("taiKhoan") String taiKhoan){
        return userService.getUserWithCartbyTK(taiKhoan);
    }
}
