package com.nguyenxuantuan.shopdongho.project.admin.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;



@Controller
@RequestMapping("/admin")
public class AdminCategoriesController {
	
	@Autowired
	private	CategoriesService categoriesService;
	
	@GetMapping("/categories")
	public String getCategories(HttpServletRequest request) {
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("listcategories", categoriesDTOs);
		return "admin/categories";
	}
	@GetMapping("/categories-delete/{id}")
	public String deleteCategories(@PathVariable(name = "id")int id) {
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(id);
		categoriesService.delete(categoriesDTO);
		return "redirect:/admin/categories";
	}
	@GetMapping("/categories-update/{id}")
	public String updateCategories(@PathVariable(name = "id")int id, HttpServletRequest request) {
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(id);
		request.setAttribute("categories", categoriesDTO);
		return "admin/update-categories";
	}
	@PostMapping("/updatecategories")
	public String updateCategories(@ModelAttribute()CategoriesDTO categoriesDTO) {
		categoriesService.update(categoriesDTO);
		return "redirect:/admin/categories";
	}
	@GetMapping("/add-categories")
	public String addCategories() {
		return "admin/add-categories";
	}
	@PostMapping("/addcategories")
	public String addCategories(@ModelAttribute(name="cadto")CategoriesDTO categoriesDTO) {
		categoriesService.add(categoriesDTO);
		return "redirect:/admin/categories";
	}
	@GetMapping("/check/{id}")
	public String checkActive(HttpServletRequest request,@PathVariable(name="id")int id) {
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(id);
		int a = categoriesDTO.getStatus();
		if(a==1) {
			a=0;
		}else {
			a=1;
		}
		categoriesDTO.setStatus(a);
		categoriesService.update(categoriesDTO);
		return "redirect:/admin/categories";
	}
	
}
