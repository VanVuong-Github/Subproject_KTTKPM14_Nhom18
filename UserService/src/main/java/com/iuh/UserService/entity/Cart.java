package com.iuh.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	private Long cartId;

	private String cartName;

	private int soLuong;
	
	private String thongTin;
}
