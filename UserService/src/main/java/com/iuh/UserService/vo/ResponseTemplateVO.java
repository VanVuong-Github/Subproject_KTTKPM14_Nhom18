package com.iuh.UserService.vo;

import java.util.List;

import com.iuh.UserService.entity.Cart;
import com.iuh.UserService.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	private User user;
    private List<Cart> cart;
}
