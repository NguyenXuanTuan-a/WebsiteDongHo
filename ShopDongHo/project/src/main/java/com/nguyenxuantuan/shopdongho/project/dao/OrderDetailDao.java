package com.nguyenxuantuan.shopdongho.project.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenxuantuan.shopdongho.project.entity.OrderDetailEntity;



public interface OrderDetailDao {
	public List<OrderDetailEntity> getAll();
	public void add(OrderDetailEntity orderDetailEntity);
	public void update(OrderDetailEntity orderDetailEntity);
	public void delete(OrderDetailEntity orderDetailEntity);
	public OrderDetailEntity getOrderDetailById(int id);
	public List<OrderDetailEntity> getOrderDetailByIdUser (int user_id);
	public OrderDetailEntity getOrderDetailById_User(int user_id);
}

@Repository
@Transactional
class OrderDetailDaoImpl implements OrderDetailDao{
	
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<OrderDetailEntity> getAll() {
		String jql="Select c from OrderDetailEntity c";
		return entityManager.createQuery(jql,OrderDetailEntity.class).getResultList();
	}

	@Override
	public void add(OrderDetailEntity orderDetailEntity) {
		entityManager.persist(orderDetailEntity);
		
	}

	@Override
	public void update(OrderDetailEntity orderDetailEntity) {
		entityManager.merge(orderDetailEntity);
		
	}

	@Override
	public void delete(OrderDetailEntity orderDetailEntity) {
		entityManager.remove(orderDetailEntity);
		
	}

	@Override
	public OrderDetailEntity getOrderDetailById(int id) {
		return entityManager.find(OrderDetailEntity.class, id);
	}

	@Override
	public List<OrderDetailEntity> getOrderDetailByIdUser(int user_id) {
		String jql="Select c from OrderDetailEntity c where c.user_id = :user_id";
		return entityManager.createQuery(jql,OrderDetailEntity.class).setParameter("user_id",user_id).getResultList();
	}

	@Override
	public OrderDetailEntity getOrderDetailById_User(int user_id) {
		return entityManager.find(OrderDetailEntity.class, user_id);
	}
	
}

