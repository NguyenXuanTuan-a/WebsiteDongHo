package com.nguyenxuantuan.shopdongho.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nguyenxuantuan.shopdongho.project.dao.UserDao;
import com.nguyenxuantuan.shopdongho.project.entity.UserEntity;
import com.nguyenxuantuan.shopdongho.project.model.UserDTO;
import com.nguyenxuantuan.shopdongho.project.model.UserPrincipal;
import com.nguyenxuantuan.shopdongho.project.security.PasswordGenerator;

@Service
@Transactional
public class UserServiceImpl implements UserService,UserDetailsService{
	@Autowired
	private PasswordGenerator passwordGenerator;
	@Autowired
	private UserDao userDao;
	@Override
	public UserDTO getByUserName(String username) {
		UserEntity userEntity = userDao.getUser(username);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setRole(userEntity.getRole());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setName(userEntity.getName());
		userDTO.setAdded_on(userEntity.getAdded_on());
		userDTO.setMobile(userEntity.getMobile());
		return userDTO;
	}

	@Override
	public void add(UserDTO userDTO) {
		Date date = new Date();
		UserEntity userEntity = new UserEntity();
		userEntity.setName(userDTO.getName());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setAdded_on(date);
		userEntity.setMobile(userDTO.getMobile());
		userEntity.setRole(userDTO.getRole());
		userEntity.setPassword(passwordGenerator.getHashString(userDTO.getPassword()));
		userDao.add(userEntity);
		
	}

	@Override
	public void delete(UserDTO userDTO) {
		UserEntity userEntity = userDao.getUserById(userDTO.getId());
		if(userEntity != null) {
			userDao.delete(userEntity);
		}
	}

	@Override
	public UserDTO getUserById(int id) {
		UserEntity userEntity = userDao.getUserById(id);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setRole(userEntity.getRole());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setName(userEntity.getName());
		userDTO.setAdded_on(userEntity.getAdded_on());
		userDTO.setMobile(userEntity.getMobile());
		return userDTO;
	}

	@Override
	public List<UserDTO> getAll() {
		List<UserEntity> userEntity = userDao.getAll();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for(UserEntity adminE : userEntity) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(adminE.getId());
			userDTO.setUsername(adminE.getUsername());
			userDTO.setPassword(adminE.getPassword());
			userDTO.setRole(adminE.getRole());
			userDTO.setEmail(adminE.getEmail());
			userDTO.setName(adminE.getName());
			userDTO.setAdded_on(adminE.getAdded_on());
			userDTO.setMobile(adminE.getMobile());
			
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userDao.getUser(username);

		if (userEntity == null) {
			throw new UsernameNotFoundException("Not User");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));

		UserPrincipal userPrincipal = new UserPrincipal(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
				authorities);

		userPrincipal.setId(userEntity.getId());
		userPrincipal.setUserName(userEntity.getUsername());
		userPrincipal.setPassword(userEntity.getPassword());
		userPrincipal.setName(userEntity.getName());
		userPrincipal.setRole(userEntity.getRole());
		userPrincipal.setMail(userEntity.getEmail());
		userPrincipal.setPhone(userEntity.getMobile());

		return userPrincipal;
	}

	@Override
	public List<UserDTO> searchUserMember() {
		List<UserEntity> userMember = userDao.searchUserMember();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for(UserEntity userEntity : userMember) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(userEntity.getId());
			userDTO.setUsername(userEntity.getUsername());
			userDTO.setPassword(userEntity.getPassword());
			userDTO.setRole(userEntity.getRole());
			userDTO.setEmail(userEntity.getEmail());
			userDTO.setName(userEntity.getName());
			userDTO.setAdded_on(userEntity.getAdded_on());
			userDTO.setMobile(userEntity.getMobile());
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}
	
	@Override
	public List<UserDTO> searchUserAdmin() {
		List<UserEntity> userAdmin = userDao.searchUserAdmin();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for(UserEntity userEntity : userAdmin) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(userEntity.getId());
			userDTO.setUsername(userEntity.getUsername());
			userDTO.setPassword(userEntity.getPassword());
			userDTO.setRole(userEntity.getRole());
			userDTO.setEmail(userEntity.getEmail());
			userDTO.setName(userEntity.getName());
			userDTO.setAdded_on(userEntity.getAdded_on());
			userDTO.setMobile(userEntity.getMobile());
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}
}
