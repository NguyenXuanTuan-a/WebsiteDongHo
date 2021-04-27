package com.nguyenxuantuan.shopdongho.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nguyenxuantuan.shopdongho.project.entity.UserEntity;

public interface UserDao {
	public UserEntity getUser(String username);
	public void add(UserEntity userEntity);
	public void update(UserEntity userEntity);
	public void delete(UserEntity userEntity);
	public UserEntity getUserById(int id);
	public List<UserEntity> getAll();
	public List<UserEntity> searchUserMember();
	public List<UserEntity> searchUserAdmin();
}

@Repository
@Transactional
class UserDaoImpl implements UserDao{
	
	@Autowired
	private EntityManager entityManager;
	@Override
	public UserEntity getUser(String username) {
		String jql = "select u from UserEntity u where u.username= :username";
		return entityManager.createQuery(jql,UserEntity.class).setParameter("username", username).getSingleResult();
	}

	@Override
	public void add(UserEntity userEntity) {
		entityManager.persist(userEntity);
	}

	@Override
	public void delete(UserEntity userEntity) {
		entityManager.remove(userEntity);
	}

	@Override
	public UserEntity getUserById(int id) {
		return entityManager.find(UserEntity.class, id);
	}

	@Override
	public List<UserEntity> getAll() {
		String jql = "select u from UserEntity u";
		return entityManager.createQuery(jql,UserEntity.class).getResultList();
	}

	@Override
	public void update(UserEntity userEntity) {
		entityManager.merge(userEntity);
	}
	
	@Override
	public List<UserEntity> searchUserMember() {
		String jql="select u from UserEntity u where u.role = :string";
		return entityManager.createQuery(jql, UserEntity.class).setParameter("string", "ROLE_USER").getResultList();
	}
	
	@Override
	public List<UserEntity> searchUserAdmin() {
		String jql="select u from UserEntity u where u.role = :string";
		return entityManager.createQuery(jql, UserEntity.class).setParameter("string", "ROLE_ADMIN").getResultList();
	}
	
}