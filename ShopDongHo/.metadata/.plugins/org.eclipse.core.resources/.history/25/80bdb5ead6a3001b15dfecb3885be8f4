package com.nguyenxuantuan.shopdongho.project.admin.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.SubCategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.SubCategoriesService;

@Controller
@RequestMapping("/admin")
public class AdminSubCategories {
	@Autowired
	private SubCategoriesService subCategoriesService;
	@Autowired
	private CategoriesService categoriesService;
	@GetMapping("/subcategories")
	public String getSubCategories(HttpServletRequest request) {
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("listsubCategoriesDTOs", subCategoriesDTOs);
		return "admin/subcategories";
	}
	
	@GetMapping("/add-subcategories")
	public String addSubCategories(HttpServletRequest request , Model model) {
		model.addAttribute("subcate", new SubCategoriesDTO());
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("cate", categoriesDTOs);
		return "admin/add-subcategories";
	}
	
	@PostMapping("/addsubcate")
	public String addSubCategories(@ModelAttribute()SubCategoriesDTO subCategoriesDTO) {
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(subCategoriesDTO.getCategoriesDTO().getId());
		subCategoriesDTO.setCategoriesDTO(categoriesDTO);
		subCategoriesService.add(subCategoriesDTO);
		return "redirect:/admin/subcategories";
	}
	
	@GetMapping("/update-subcategories/{id}")
	public String updateSubCategories(HttpServletRequest request, @PathVariable(name = "id")int id , Model model) {
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getSubById(id);
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		model.addAttribute("subCategoriesDTO",subCategoriesDTO);
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		return "admin/update-subcategories";
	}
	
	@PostMapping("/updatesubcate")
	public String updateSubCategories(@ModelAttribute()SubCategoriesDTO subCategoriesDTO ) {
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(subCategoriesDTO.getCategoriesDTO().getId());
		System.out.println(categoriesDTO.getId());
		subCategoriesDTO.setCategoriesDTO(categoriesDTO);
		subCategoriesService.update(subCategoriesDTO);
		return "redirect:/admin/subcategories";
	}
	
	@GetMapping("/delete-subcategories/{id}")
	public String deleteSubCategories(HttpServletRequest request,@PathVariable(name = "id")int id) {
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getSubById(id);
		subCategoriesService.delete(subCategoriesDTO);
		return "redirect:/admin/subcategories";
	}
	
	@GetMapping("/checksub/{id}")
	public String checkActive(HttpServletRequest request,@PathVariable(name="id")int id) {
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getSubById(id);
		int a = subCategoriesDTO.getStatus();
		if(a==0) {
			a=1;
		}else {
			a=0;
		}
		subCategoriesDTO.setStatus(a);
		subCategoriesService.update(subCategoriesDTO);
		return "redirect:/admin/subcategories";
	}
}
