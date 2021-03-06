package com.nguyenxuantuan.shopdongho.project.admin.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenxuantuan.shopdongho.project.model.UserDTO;
import com.nguyenxuantuan.shopdongho.project.service.UserServiceImpl;

@RestController
public class RestAdminUserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@GetMapping("/api/admin/user")
	public List<UserDTO> getAllUser(){
		List<UserDTO> lisDtos = userServiceImpl.getAll();
		return lisDtos;
	}
	
	@GetMapping("/api/admin/list-member")
	public List<UserDTO> getUserMember(){
		List<UserDTO> lisDtos = userServiceImpl.searchUserMember();
		return lisDtos;
	}
	
	@GetMapping("/api/admin/list-admin")
	public List<UserDTO> getUserAdmin(){
		List<UserDTO> lisDtos = userServiceImpl.searchUserAdmin();
		return lisDtos;
	}
	
	@PostMapping("/api/admin/add-user")
	@ResponseStatus(code= HttpStatus.CREATED)
	public void addUser(@RequestBody UserDTO userDTO) {
		userServiceImpl.add(userDTO);
	}
	
	@GetMapping("/api/admin/user/{id}")
	public UserDTO getUserById(@PathVariable(name = "id")int id) {
		UserDTO userDTO = userServiceImpl.getUserById(id);
		return userDTO;
	}
	
}
