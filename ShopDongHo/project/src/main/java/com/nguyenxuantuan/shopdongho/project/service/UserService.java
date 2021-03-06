package com.nguyenxuantuan.shopdongho.project.service;

import java.util.List;
import com.nguyenxuantuan.shopdongho.project.model.UserDTO;

public interface UserService {
	public UserDTO getByUserName(String username);
	public void add(UserDTO userDTO);
	public void delete(UserDTO userDTO);
	public UserDTO getUserById(int id);
	public List<UserDTO> getAll();
	public List<UserDTO> searchUserMember();
	public List<UserDTO> searchUserAdmin();
}

