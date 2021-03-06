package com.nguyenxuantuan.shopdongho.project.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private int id;
	private int id_categories;
	private int id_subcategories;
	private String name;
	private String image;
	private MultipartFile multipartFile;
	private float mrp;
	private float price;
	private int qty;
	private String short_desc;
	private String description;
	private int best_seller;
	private String meta_title;
	private String meta_desc;
	private String meta_keyword;
	private int status;
}