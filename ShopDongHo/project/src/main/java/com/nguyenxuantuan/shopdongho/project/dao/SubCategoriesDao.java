package com.nguyenxuantuan.shopdongho.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nguyenxuantuan.shopdongho.project.entity.SubCategoriesEntity;

public interface SubCategoriesDao {
	public List<SubCategoriesEntity> getAll();
	public SubCategoriesEntity getSubById(int id);
	public void add(SubCategoriesEntity subCategoriesEntity);
	public void update(SubCategoriesEntity subCategoriesEntity);
	public void delete(SubCategoriesEntity subCategoriesEntity);
	public SubCategoriesEntity getListProduct(int id_subcategories);
	public List<SubCategoriesEntity> getByIdCategories(int id_categories);
}
@Repository
@Transactional
class SubCategoriesDaoImpl implements SubCategoriesDao{
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<SubCategoriesEntity> getAll() {
		String jql = "Select s from SubCategoriesEntity s";
		return entityManager.createQuery(jql,SubCategoriesEntity.class).getResultList();
	}
	@Override
	public SubCategoriesEntity getSubById(int id) {
		return entityManager.find(SubCategoriesEntity.class, id);
	}
	@Override
	public void add(SubCategoriesEntity subCategoriesEntity) {
		entityManager.persist(subCategoriesEntity);
		
	}
	@Override
	public void update(SubCategoriesEntity subCategoriesEntity) {
		entityManager.merge(subCategoriesEntity);
		
	}
	@Override
	public void delete(SubCategoriesEntity subCategoriesEntity) {
		entityManager.remove(subCategoriesEntity);
		
	}
	@Override
	public SubCategoriesEntity getListProduct(int id_subcategories) {
		return entityManager.find(SubCategoriesEntity.class, id_subcategories);
	}
	@Override
	public List<SubCategoriesEntity> getByIdCategories(int id_categories) {
		String jql ="Select s from SubCategoriesEntity s where s.id_categories =: id_categories";
		return entityManager.createQuery(jql,SubCategoriesEntity.class).setParameter("id_categories", id_categories).getResultList();
	}
}
