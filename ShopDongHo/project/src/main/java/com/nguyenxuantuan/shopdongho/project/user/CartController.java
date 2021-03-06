package com.nguyenxuantuan.shopdongho.project.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.OrderDTO;
import com.nguyenxuantuan.shopdongho.project.model.ProductDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.ProductService;

@Controller
public class CartController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoriesService categoriesService;
	
	@GetMapping("/add-cart-session/{id}")
	public String addCartSession(@PathVariable("id") int id, HttpSession httpSession, HttpServletRequest request) {
		ProductDTO productDTO = productService.getById(id);
			Object object = httpSession.getAttribute("cart");
			
			if(productDTO.getQty() ==0) {
				return "hết hàng";
			}else {
				if(object == null) {
					Map<Integer, OrderDTO> map = new HashMap<Integer, OrderDTO>();
					
					OrderDTO orderDTO = new OrderDTO();
					
					orderDTO.setProductDTO(productDTO);
					orderDTO.setQty(1);
					orderDTO.setPrice(productDTO.getPrice());
					
					map.put(productDTO.getId(), orderDTO);
					
					httpSession.setAttribute("cart", map);
				}else{
					Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) object;
					
					OrderDTO orderDTO = map.get(id);
					
					if(orderDTO == null) {
						orderDTO = new OrderDTO();
						
						orderDTO.setProductDTO(productDTO);
						orderDTO.setQty(1);
						orderDTO.setPrice(productDTO.getPrice());
						
						map.put(productDTO.getId(), orderDTO);
				}else {
					orderDTO.setQty(orderDTO.getQty() + 1);
					
					map.put(productDTO.getId(), orderDTO);
				}
				
				httpSession.setAttribute("cart", map);
				}
			}
			return "redirect:/cart";
	}
	@PostMapping("/add-cart/{id}")
	public String addCart(@PathVariable("id") int id,@RequestParam(name="qty")int qty, HttpSession httpSession, HttpServletRequest request) {
		ProductDTO productDTO = productService.getById(id);
		if(qty > productDTO.getQty()) {
			return "redirect:/client/productdetail?id=" + id + "&e=error" ;
		}else {
			Object object = httpSession.getAttribute("cart");
			if(object == null) {
				Map<Integer, OrderDTO> map = new HashMap<Integer, OrderDTO>();
				
				OrderDTO orderDTO = new OrderDTO();
				
				orderDTO.setProductDTO(productDTO);
				orderDTO.setQty(qty);
				orderDTO.setPrice(productDTO.getPrice());
				
				map.put(productDTO.getId(), orderDTO);
				
				httpSession.setAttribute("cart", map);
			}else{
				Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) object;
				
				OrderDTO orderDTO = map.get(id);
				
				if(orderDTO == null) {
					orderDTO = new OrderDTO();
					
					orderDTO.setProductDTO(productDTO);
					orderDTO.setQty(qty);
					orderDTO.setPrice(productDTO.getPrice());
					
					map.put(productDTO.getId(), orderDTO);
			}else {
				orderDTO.setQty(orderDTO.getQty() + qty);
				
				map.put(productDTO.getId(), orderDTO);
			}
			
			httpSession.setAttribute("cart", map);
			}
		}
			
			return "redirect:/cart";
	}
	@GetMapping("/cart")
	public String getCart(HttpServletRequest request, HttpSession httpSession) {
		
		List<CategoriesDTO>categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		
		Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) httpSession.getAttribute("cart");
		int total =  0;
		
		if(map != null) {
			for(Map.Entry<Integer, OrderDTO> entry : map.entrySet()) {
				total += entry.getValue().getQty() * entry.getValue().getPrice();
			}
		}
		
		httpSession.setAttribute("total", total);
		
		return "user/cart";
	}
	
	@GetMapping("/delete-cart/{id}")
	public String deleteCart(@PathVariable()int id ,HttpSession httpSession) {
		Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) httpSession.getAttribute("cart");
		
		if(map != null) {
				map.remove(id);
		}
		
		httpSession.setAttribute("cart", map);
		return "redirect:/cart";
	}
}
