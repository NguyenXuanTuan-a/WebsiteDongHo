package com.nguyenxuantuan.shopdongho.project.admin.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.ProductDTO;
import com.nguyenxuantuan.shopdongho.project.model.SubCategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.ProductService;
import com.nguyenxuantuan.shopdongho.project.service.SubCategoriesService;

@Controller
@RequestMapping("/admin")
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CategoriesService categoriesService;
	
	@Autowired
	private SubCategoriesService subCategoriesService;
	
	@GetMapping("/product")
	public String getProduct(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAll();
		request.setAttribute("product",productDTOs);
		return "admin/product";
	}
	
	@GetMapping("/add-product")
	public String addProduct(Model model, HttpServletRequest request) {
		model.addAttribute("product", new ProductDTO());
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		List<SubCategoriesDTO>subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		request.setAttribute("subCategoriesDTOs", subCategoriesDTOs);
		return "admin/add-product";
	}
	@PostMapping("/addpro")
	public String addProduct(@ModelAttribute()ProductDTO productDTO ) {
		productDTO.setImage(productDTO.getMultipartFile().getOriginalFilename());
		MultipartFile file = productDTO.getMultipartFile();
		File dir = new File("C:\\Users\\PC\\Downloads\\shop\\shop\\src\\main\\resources\\static\\images\\flags\\" /*+ productDTO.getCategoriesDTO().getCategories() */);
		
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		File newFile = new File("C:\\Users\\PC\\Downloads\\shop\\shop\\src\\main\\resources\\static\\images\\flags\\" /*+ productDTO.getCategoriesDTO().getCategories()*/ + "\\" + file.getOriginalFilename());
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			
			fileOutputStream.write(file.getBytes());
			
			fileOutputStream.close();
			
			productService.add(productDTO);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		CategoriesDTO categoriesDTO = categoriesService.getCategoriesById(productDTO.getId_categories());
		productDTO.setId_categories(categoriesDTO.getId());
		
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getSubById(productDTO.getId_subcategories());
		productDTO.set
		*/
		return "redirect:/admin/product";
	}
	@GetMapping("/delete-product/{id}")
	public String deleteProduct(@PathVariable()int id) {
		ProductDTO productDTO = productService.getById(id);
		productService.delete(productDTO);
		return "redirect:/admin/product";
	}
	
	@GetMapping("/check-product/{id}")
	public String checkActive(@PathVariable(name = "id")int id) {
		ProductDTO productDTO = productService.getById(id);
		int a = productDTO.getStatus();
		if(a==1) {
			a=0;
		}else {
			a=1;
		}
		productDTO.setStatus(a);
		productService.update(productDTO);
		return "redirect:/admin/product";
	}
	@GetMapping("/update-product/{id}")
	public String updateProduct(@PathVariable()int id,Model model ,HttpServletRequest request) {
		ProductDTO productDTO = productService.getById(id);
		model.addAttribute("product", productDTO);
		
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		List<SubCategoriesDTO>subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		request.setAttribute("subCategoriesDTOs", subCategoriesDTOs);
		return "admin/update-product";
	}
	
}
