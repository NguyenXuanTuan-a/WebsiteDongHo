package com.nguyenxuantuan.shopdongho.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenxuantuan.shopdongho.project.dao.ProductDao;
import com.nguyenxuantuan.shopdongho.project.entity.CategoriesEntity;
import com.nguyenxuantuan.shopdongho.project.entity.ProductEntity;
import com.nguyenxuantuan.shopdongho.project.entity.SubCategoriesEntity;
import com.nguyenxuantuan.shopdongho.project.model.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getAll();
	public void add(ProductDTO productDTO);
	public void delete(ProductDTO productDTO);
	public ProductDTO getById(int id);
	public void update(ProductDTO productDTO);
	public List<ProductDTO> getByIdCategories(int id_categories);
	public List<ProductDTO> getByIdSubCategories(int id_subcategories);
	public List<ProductDTO> searchProductName(String name);
}

@Service
@Transactional
class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	@Override
	public List<ProductDTO> getAll() {
		List<ProductEntity> productEntities = productDao.getAll();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(ProductEntity pro : productEntities) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(pro.getId());
			productDTO.setId_categories(pro.getId_categories());
			productDTO.setId_subcategories(pro.getId_subcategories());
			productDTO.setName(pro.getName());
			productDTO.setImage(pro.getImage());
			productDTO.setMrp(pro.getMrp());
			productDTO.setPrice(pro.getPrice());
			productDTO.setQty(pro.getQty());
			productDTO.setShort_desc(pro.getShort_desc());
			productDTO.setDescription(pro.getDescription());
			productDTO.setBest_seller(pro.getBest_seller());
			productDTO.setMeta_desc(pro.getMeta_desc());
			productDTO.setMeta_keyword(pro.getMeta_keyword());
			productDTO.setMeta_title(pro.getMeta_title());
			productDTO.setStatus(pro.getStatus());
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	@Override
	public void add(ProductDTO productDTO) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId_categories(productDTO.getId_categories());
		productEntity.setId_subcategories(productDTO.getId_subcategories());
		productEntity.setName(productDTO.getName());
		productEntity.setImage(productDTO.getImage());
		productEntity.setMrp(productDTO.getMrp());
		productEntity.setPrice(productDTO.getPrice());
		productEntity.setQty(productDTO.getQty());
		productEntity.setShort_desc(productDTO.getShort_desc());
		productEntity.setDescription(productDTO.getDescription());
		productEntity.setBest_seller(productDTO.getBest_seller());
		productEntity.setMeta_desc(productDTO.getMeta_desc());
		productEntity.setMeta_keyword(productDTO.getMeta_keyword());
		productEntity.setMeta_title(productDTO.getMeta_title());
		productEntity.setStatus(productDTO.getStatus());
		productDao.add(productEntity);
	}
	@Override
	public void delete(ProductDTO productDTO) {
		ProductEntity productEntity = productDao.getProductById(productDTO.getId());
		if(productEntity != null) {
			productDao.delete(productEntity);
		}
		
	}
	@Override
	public ProductDTO getById(int id) {
		ProductEntity productEntity = productDao.getProductById(id);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productEntity.getId());
		productDTO.setId_categories(productEntity.getId_categories());
		productDTO.setId_subcategories(productEntity.getId_subcategories());
		productDTO.setName(productEntity.getName());
		productDTO.setImage(productEntity.getImage());
		productDTO.setMrp(productEntity.getMrp());
		productDTO.setPrice(productEntity.getPrice());
		productDTO.setQty(productEntity.getQty());
		productDTO.setShort_desc(productEntity.getShort_desc());
		productDTO.setDescription(productEntity.getDescription());
		productDTO.setBest_seller(productEntity.getBest_seller());
		productDTO.setMeta_desc(productEntity.getMeta_desc());
		productDTO.setMeta_keyword(productEntity.getMeta_keyword());
		productDTO.setMeta_title(productEntity.getMeta_title());
		productDTO.setStatus(productEntity.getStatus());
		return productDTO;
	}
	@Override
	public void update(ProductDTO productDTO) {
		ProductEntity productEntity = productDao.getProductById(productDTO.getId());
		if(productEntity != null) {
			productEntity.setId_categories(productDTO.getId_categories());
			productEntity.setId_subcategories(productDTO.getId_subcategories());
			productEntity.setName(productDTO.getName());
			productEntity.setImage(productDTO.getImage());
			productEntity.setMrp(productDTO.getMrp());
			productEntity.setPrice(productDTO.getPrice());
			productEntity.setQty(productDTO.getQty());
			productEntity.setShort_desc(productDTO.getShort_desc());
			productEntity.setDescription(productDTO.getDescription());
			productEntity.setBest_seller(productDTO.getBest_seller());
			productEntity.setMeta_desc(productDTO.getMeta_desc());
			productEntity.setMeta_keyword(productDTO.getMeta_keyword());
			productEntity.setMeta_title(productDTO.getMeta_title());
			productEntity.setStatus(productDTO.getStatus());
			productDao.update(productEntity);
		}
		
	}
	@Override
	public List<ProductDTO> getByIdCategories(int id_categories) {
		List<ProductEntity> productEntities = productDao.getByIdCategories(id_categories);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(ProductEntity pro : productEntities) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(pro.getId());
			productDTO.setId_categories(pro.getId_categories());
			productDTO.setId_subcategories(pro.getId_subcategories());
			productDTO.setName(pro.getName());
			productDTO.setImage(pro.getImage());
			productDTO.setMrp(pro.getMrp());
			productDTO.setPrice(pro.getPrice());
			productDTO.setQty(pro.getQty());
			productDTO.setShort_desc(pro.getShort_desc());
			productDTO.setDescription(pro.getDescription());
			productDTO.setBest_seller(pro.getBest_seller());
			productDTO.setMeta_desc(pro.getMeta_desc());
			productDTO.setMeta_keyword(pro.getMeta_keyword());
			productDTO.setMeta_title(pro.getMeta_title());
			productDTO.setStatus(pro.getStatus());
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	@Override
	public List<ProductDTO> getByIdSubCategories(int id_subcategories) {
		List<ProductEntity> productEntities = productDao.getByIdSubCategories(id_subcategories);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(ProductEntity pro : productEntities) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(pro.getId());
			productDTO.setId_categories(pro.getId_categories());
			productDTO.setId_subcategories(pro.getId_subcategories());
			productDTO.setName(pro.getName());
			productDTO.setImage(pro.getImage());
			productDTO.setMrp(pro.getMrp());
			productDTO.setPrice(pro.getPrice());
			productDTO.setQty(pro.getQty());
			productDTO.setShort_desc(pro.getShort_desc());
			productDTO.setDescription(pro.getDescription());
			productDTO.setBest_seller(pro.getBest_seller());
			productDTO.setMeta_desc(pro.getMeta_desc());
			productDTO.setMeta_keyword(pro.getMeta_keyword());
			productDTO.setMeta_title(pro.getMeta_title());
			productDTO.setStatus(pro.getStatus());
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	@Override
	public List<ProductDTO> searchProductName(String name) {
		List<ProductEntity> productEntities = productDao.searchProductByNam(name);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(ProductEntity pro : productEntities) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(pro.getId());
			productDTO.setId_categories(pro.getId_categories());
			productDTO.setId_subcategories(pro.getId_subcategories());
			productDTO.setName(pro.getName());
			productDTO.setImage(pro.getImage());
			productDTO.setMrp(pro.getMrp());
			productDTO.setPrice(pro.getPrice());
			productDTO.setQty(pro.getQty());
			productDTO.setShort_desc(pro.getShort_desc());
			productDTO.setDescription(pro.getDescription());
			productDTO.setBest_seller(pro.getBest_seller());
			productDTO.setMeta_desc(pro.getMeta_desc());
			productDTO.setMeta_keyword(pro.getMeta_keyword());
			productDTO.setMeta_title(pro.getMeta_title());
			productDTO.setStatus(pro.getStatus());
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
}