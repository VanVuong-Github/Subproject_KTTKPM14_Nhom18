package com.iuh.UserService.vo;

import java.io.Serializable;
import java.util.List;

import com.iuh.UserService.entity.Cart;
import com.iuh.UserService.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO implements Serializable {
	private User user;
    private List<Cart> cart;
}
