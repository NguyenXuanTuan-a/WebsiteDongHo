package com.nguyenxuantuan.shopdongho.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenxuantuan.shopdongho.project.dao.SubCategoriesDao;
import com.nguyenxuantuan.shopdongho.project.entity.CategoriesEntity;
import com.nguyenxuantuan.shopdongho.project.entity.ProductEntity;
import com.nguyenxuantuan.shopdongho.project.entity.SubCategoriesEntity;
import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.ProductDTO;
import com.nguyenxuantuan.shopdongho.project.model.SubCategoriesDTO;

public interface SubCategoriesService {
	public List<SubCategoriesDTO> getAll();
	public SubCategoriesDTO getSubById(int id);
	public void update(SubCategoriesDTO subCategoriesDTO);
	public void add(SubCategoriesDTO subCategoriesDTO);
	public void delete(SubCategoriesDTO subCategoriesDTO);
	public SubCategoriesDTO getListProduct(int id_subcategories);
	public List<SubCategoriesDTO> getByIdCategories(int id_categories);
}

@Service
@Transactional
class SubCategoriesServiceImpl implements SubCategoriesService{
	@Autowired
	private SubCategoriesDao subCategoriesDao;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoriesService categoriesService;
	@Override
	public List<SubCategoriesDTO> getAll() {
		List<SubCategoriesEntity> subcaE = subCategoriesDao.getAll();
		List<SubCategoriesDTO> subDtos = new ArrayList<SubCategoriesDTO>();
		for(SubCategoriesEntity subca : subcaE) {
			SubCategoriesDTO subdto = new SubCategoriesDTO();
			subdto.setId(subca.getId());
			subdto.setId_categories(subca.getId_categories());
			subdto.setSub_categories(subca.getSub_categories());
			subdto.setStatus(subca.getStatus());
			subdto.setCategoriesDTO(categoriesService.getCategoriesById(subca.getId_categories()));
			subDtos.add(subdto);
			
		}
		return subDtos;
	}

	@Override
	public SubCategoriesDTO getSubById(int id) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(id);
		SubCategoriesDTO subCategoriesDTO = new SubCategoriesDTO();
		subCategoriesDTO.setId(subCategoriesEntity.getId());
		subCategoriesDTO.setCategoriesDTO(categoriesService.getCategoriesById(subCategoriesEntity.getId_categories()));
		subCategoriesDTO.setStatus(subCategoriesEntity.getStatus());
		subCategoriesDTO.setSub_categories(subCategoriesEntity.getSub_categories());
		return subCategoriesDTO;
	}

	@Override
	public void update(SubCategoriesDTO subCategoriesDTO) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(subCategoriesDTO.getId());
		if(subCategoriesDTO != null) {
			subCategoriesEntity.setId_categories(subCategoriesDTO.getCategoriesDTO().getId());
			subCategoriesEntity.setStatus(subCategoriesDTO.getStatus());
			subCategoriesEntity.setSub_categories(subCategoriesDTO.getSub_categories());
			subCategoriesDao.update(subCategoriesEntity);
		}
		
	}

	@Override
	public void add(SubCategoriesDTO subCategoriesDTO) {
		SubCategoriesEntity subCategoriesEntity = new SubCategoriesEntity();
		subCategoriesEntity.setSub_categories(subCategoriesDTO.getSub_categories());
		subCategoriesEntity.setStatus(subCategoriesDTO.getStatus());
		subCategoriesEntity.setId_categories(subCategoriesDTO.getId_categories());
		subCategoriesDao.add(subCategoriesEntity); 
		
		
	}

	@Override
	public void delete(SubCategoriesDTO subCategoriesDTO) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(subCategoriesDTO.getId());
		if(subCategoriesEntity != null) {
			subCategoriesDao.delete(subCategoriesEntity);
		}
		
	}

	@Override
	public SubCategoriesDTO getListProduct(int id_subcategories) {
		SubCategoriesEntity subCategoriesEntity = subCategoriesDao.getSubById(id_subcategories);
		SubCategoriesDTO subCategoriesDTO = new SubCategoriesDTO();
		subCategoriesDTO.setId(subCategoriesEntity.getId());
		subCategoriesDTO.setId_categories(subCategoriesEntity.getId_categories());
		subCategoriesDTO.setStatus(subCategoriesEntity.getStatus());
		subCategoriesDTO.setSub_categories(subCategoriesEntity.getSub_categories());
		subCategoriesDTO.setProductDTOs(productService.getByIdSubCategories(subCategoriesEntity.getId()));
		return subCategoriesDTO;
	}

	@Override
	public List<SubCategoriesDTO> getByIdCategories(int id_categories) {
		List<SubCategoriesEntity> subCategoriesEntities = subCategoriesDao.getByIdCategories(id_categories);
		List<SubCategoriesDTO> subCategoriesDTOs = new ArrayList<SubCategoriesDTO>();
		for(SubCategoriesEntity subCategoriesEntity : subCategoriesEntities) {
			SubCategoriesDTO subCategoriesDTO = new SubCategoriesDTO();
			subCategoriesDTO.setId(subCategoriesEntity.getId());
			subCategoriesDTO.setId_categories(subCategoriesEntity.getId_categories());
			subCategoriesDTO.setSub_categories(subCategoriesEntity.getSub_categories());
			subCategoriesDTO.setStatus(subCategoriesEntity.getStatus());
			subCategoriesDTOs.add(subCategoriesDTO);
		}
		return subCategoriesDTOs;
	}
}