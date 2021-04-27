package com.nguyenxuantuan.shopdongho.project.user;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.OrderDTO;
import com.nguyenxuantuan.shopdongho.project.model.OrderDetailDTO;
import com.nguyenxuantuan.shopdongho.project.model.ProductDTO;
import com.nguyenxuantuan.shopdongho.project.model.UserDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.OrderDetailService;
import com.nguyenxuantuan.shopdongho.project.service.OrderService;
import com.nguyenxuantuan.shopdongho.project.service.ProductService;
import com.nguyenxuantuan.shopdongho.project.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class CheckOutController {
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/checkout")
	public String getChetOut( HttpServletRequest request, HttpSession httpSession) {
		List<CategoriesDTO>categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		
		Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) httpSession.getAttribute("cart");
		UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
		if(map != null && userDetails != null) {
			return "user/checkout";
		}else if(map == null && userDetails != null) {
			return "redirect:/index";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/order")
	public String order(HttpServletRequest request,HttpSession httpSession,@RequestParam(name = "city")String city,@RequestParam(name = "phone")String phone, @RequestParam(name = "address")String address) {
		Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) httpSession.getAttribute("cart");
		UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
		if(userDetails == null && map == null) {
			return "redirect:/login";
		}else {
			UserDTO userDTO = userServiceImpl.getByUserName(userDetails.getUsername());
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setAddress(address);
			orderDetailDTO.setPhone(phone);
			orderDetailDTO.setCity(city);
			orderDetailDTO.setUser(userDTO);
			
			int total =  0;
			for(Map.Entry<Integer, OrderDTO> entry : map.entrySet()) {
					total += entry.getValue().getQty() * entry.getValue().getPrice();
				}
			
			orderDetailDTO.setTotal_price(total);
			
			try {
				orderDetailService.add(orderDetailDTO);
				for(Map.Entry<Integer, OrderDTO> entry : map.entrySet()) {
					OrderDTO orderDTO = entry.getValue();
					orderDTO.setOrderDetailDTO(orderDetailDTO);
					orderService.addOrder(orderDTO);
					//System.out.println(orderDTO.getOrderDetailDTO().getId());
					ProductDTO productDTO = orderDTO.getProductDTO();
					productDTO.setQty(productDTO.getQty() - orderDTO.getQty());
					productService.update(productDTO);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		httpSession.removeAttribute("cart");
		httpSession.removeAttribute("coupon");
		return "redirect:/user/order-detail";
	}
}
