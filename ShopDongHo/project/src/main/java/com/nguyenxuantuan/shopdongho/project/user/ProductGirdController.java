package com.nguyenxuantuan.shopdongho.project.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.SubCategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.ProductService;
import com.nguyenxuantuan.shopdongho.project.service.SubCategoriesService;

@Controller
public class ProductGirdController {
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private SubCategoriesService subCategoriesService;
	
	@Autowired 
	private ProductService productService;
	
	@GetMapping("/product-grid-categories/{id}")
	public String getListProductByIdCategories(@PathVariable(name = "id")int id_categories , HttpServletRequest request) {
		
		List<CategoriesDTO>categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		
		CategoriesDTO categoriesDTO = categoriesService.getListProduct(id_categories) ;
		request.setAttribute("categoriesDTO", categoriesDTO);
		
		request.setAttribute("productDTO", productService.getAll());
		return "user/product-grid";
	}
	
	@GetMapping("/product-grid-subcategories/{id_subcategories}")
	public String getListProductByIdSubCategories(@PathVariable(name = "id_subcategories")int id_subcategories , HttpServletRequest request) {
		
		List<CategoriesDTO>categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getListProduct(id_subcategories);
		request.setAttribute("subcategoriesDTO", subCategoriesDTO);
		
		request.setAttribute("productDTO", productService.getAll());
		
		return "user/product-grid";
	}
}
