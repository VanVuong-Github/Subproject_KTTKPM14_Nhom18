package com.iuh.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	@Id
	private String userId;
	private String taiKhoan;
	private String matKhau;
	private String email;
	private String ten;
	private int gioiTinh;
	
}
