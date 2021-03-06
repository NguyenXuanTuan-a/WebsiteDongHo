package com.nguyenxuantuan.shopdongho.project.admin.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyenxuantuan.shopdongho.project.model.UserDTO;
import com.nguyenxuantuan.shopdongho.project.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getIndex(HttpServletRequest request) {
		return "admin/index";
	}
	@GetMapping("/user")
	public String getuserList(HttpServletRequest request) {
		List<UserDTO> userDTOs = userService.getAll();
		request.setAttribute("listuser", userDTOs);
		return "admin/user";
	}
	@GetMapping("/add-user")
	public String add() {
		return "admin/add-user";
	}
	@PostMapping("/adduser")
	public String addUser(@ModelAttribute() UserDTO userDTO) {
		userDTO.setRole("ROLE_ADMIN");
		userService.add(userDTO);
		return "redirect:/admin/user";
	}
	
}
