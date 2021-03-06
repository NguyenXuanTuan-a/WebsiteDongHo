package com.nguyenxuantuan.shopdongho.project.admin.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;

@RestController
public class RestAdminCategories {
	
	@Autowired
	private CategoriesService categoriesService;
	
	@GetMapping("/api/admin/categories")
	public List<CategoriesDTO> getAll(){
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		return categoriesDTOs;
	}
	
	@GetMapping("/api/admin/categories/{id}")
	public CategoriesDTO getCategoriesById(@PathVariable(name = "id")int id){
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(id);
		return categoriesDTO;
	}
	
	@PostMapping("/api/admin/add-categories")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addCategories (@RequestBody CategoriesDTO categoriesDTO) {
		try {
			categoriesService.add(categoriesDTO);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@PutMapping("/api/admin/update-categories")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoriesDTO updateCategories(@RequestBody CategoriesDTO categoriesDTO) {
		try {
			categoriesService.update(categoriesDTO);
			 return categoriesDTO;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	@DeleteMapping("/api/admin/delete-categories/{id}")
	public void deleteCategories(@PathVariable(name="id")int id) {
		try {
			CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(id);
			categoriesService.delete(categoriesDTO);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
