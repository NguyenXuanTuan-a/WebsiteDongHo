package com.nguyenxuantuan.shopdongho.project.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
	private int id;
	private UserDTO user;
	private CouponDTO couponDTO;
	private String phone;
	private String address;
	private String city;
	private float total_price;
	private Date added_on;
	
	
}

