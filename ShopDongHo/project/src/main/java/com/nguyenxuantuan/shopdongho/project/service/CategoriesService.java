package com.nguyenxuantuan.shopdongho.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenxuantuan.shopdongho.project.dao.CategoriesDao;
import com.nguyenxuantuan.shopdongho.project.entity.CategoriesEntity;
import com.nguyenxuantuan.shopdongho.project.entity.ProductEntity;
import com.nguyenxuantuan.shopdongho.project.entity.SubCategoriesEntity;
import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.ProductDTO;
import com.nguyenxuantuan.shopdongho.project.model.SubCategoriesDTO;

public interface CategoriesService {
	public List<CategoriesDTO> getAll();
	public void add(CategoriesDTO categoriesDTO);
	public void update(CategoriesDTO categoriesDTO);
	public void delete(CategoriesDTO categoriesDTO);
	public CategoriesDTO getCategoriesById(int id);
	public CategoriesDTO getListProduct(int id_categories);
}

@Service
@Transactional
class CategoriesServiceImpl implements CategoriesService{
	@Autowired
	private CategoriesDao categoriesDao; 
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SubCategoriesService subCategoriesService;
	@Override
	public List<CategoriesDTO> getAll() {
		List<CategoriesEntity> caE = categoriesDao.getAll();
		List<CategoriesDTO>cadtos = new ArrayList<CategoriesDTO>();
		for(CategoriesEntity ca : caE) {
			CategoriesDTO cadto = new CategoriesDTO();
			cadto.setId(ca.getId());
			cadto.setCategories(ca.getCategories());
			cadto.setStatus(ca.getStatus());
			cadto.setSubCategoriesDTOs(subCategoriesService.getByIdCategories(ca.getId()));
			cadtos.add(cadto);
		}
		
		return cadtos;
	}
	@Override
	public void add(CategoriesDTO categoriesDTO) {
		CategoriesEntity caE = new CategoriesEntity();
		caE.setCategories(categoriesDTO.getCategories());
		caE.setStatus(categoriesDTO.getStatus());
		categoriesDao.addCa(caE);
		
	}
	@Override
	public void update(CategoriesDTO categoriesDTO) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(categoriesDTO.getId());
		if(cae != null) {
			cae.setCategories(categoriesDTO.getCategories());
			cae.setStatus(categoriesDTO.getStatus());
			categoriesDao.update(cae);
		}
		
	}
	@Override
	public void delete(CategoriesDTO categoriesDTO) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(categoriesDTO.getId());
		if(cae != null) {
			categoriesDao.delete(cae);
		}
		
	}
	@Override
	public CategoriesDTO getCategoriesById(int id) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(id);
		CategoriesDTO cadto = new CategoriesDTO();
		cadto.setId(cae.getId());
		cadto.setCategories(cae.getCategories());
		cadto.setStatus(cae.getStatus());
		return cadto;
	}
	@Override
	public CategoriesDTO getListProduct(int id_categories) {
		CategoriesEntity cae = categoriesDao.getCategoriesById(id_categories);
		CategoriesDTO cadto = new CategoriesDTO();
		cadto.setId(cae.getId());
		cadto.setCategories(cae.getCategories());
		cadto.setStatus(cae.getStatus());
		cadto.setProductDTOs(productService.getByIdCategories(cae.getId()));
		return cadto;
	}
	
}
