package com.nguyenxuantuan.shopdongho.project.user;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.OrderDTO;
import com.nguyenxuantuan.shopdongho.project.model.ProductDTO;
import com.nguyenxuantuan.shopdongho.project.model.SubCategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.UserDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.ProductService;
import com.nguyenxuantuan.shopdongho.project.service.SubCategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.UserService;

@Controller
public class IndexUserController {
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private SubCategoriesService subCategoriesService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@GetMapping({ "/index", "/", "/user" })
	public String getIndex(HttpServletRequest request, HttpSession httpSession) {

		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);

		List<ProductDTO> productDTOs = productService.getAll();
		request.setAttribute("productDTOs", productDTOs);

		return "user/index";
	}

	@GetMapping("/login")
	public String getLogin(HttpServletRequest request, @RequestParam(name = "e", required = false) String string) {
		if (string != null) {
			request.setAttribute("e", string);
		}
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		return "user/login";
	}

	@GetMapping("/member")
	public String getMember(HttpServletRequest request, HttpSession httpSession) {
		if (request.isUserInRole("ADMIN")) {
			return "redirect:/admin/";
		}
		else {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			httpSession.setAttribute("user", userDetails);
			return "redirect:/";
		}
	}

	@PostMapping("/add-user")
	public String addUser(@ModelAttribute() UserDTO userDTO) {
		userDTO.setRole("ROLE_USER");
		userService.add(userDTO);
		return "redirect:/login";
	}
}
