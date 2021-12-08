package com.iuh.CartService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	private String cartId;

	private String cartName;

	private int soLuong;
	
	private String thongTin;

	private String userId;
}
