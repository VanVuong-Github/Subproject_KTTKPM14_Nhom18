package com.iuh.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
	private String cartId;

	private String cartName;

	private int soLuong;
	
	private String thongTin;

	private String userId;
}
