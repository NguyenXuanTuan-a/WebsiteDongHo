package com.nguyenxuantuan.shopdongho.project.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyenxuantuan.shopdongho.project.model.CategoriesDTO;
import com.nguyenxuantuan.shopdongho.project.model.OrderDTO;
import com.nguyenxuantuan.shopdongho.project.model.OrderDetailDTO;
import com.nguyenxuantuan.shopdongho.project.model.UserDTO;
import com.nguyenxuantuan.shopdongho.project.service.CategoriesService;
import com.nguyenxuantuan.shopdongho.project.service.OrderDetailService;
import com.nguyenxuantuan.shopdongho.project.service.OrderService;
import com.nguyenxuantuan.shopdongho.project.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@GetMapping("/order-detail")
	public String getOrderDetail(HttpServletRequest request, HttpSession httpSession) {
		List<CategoriesDTO>categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		
		 if(httpSession.getAttribute("user") != null) {
			 UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
			 UserDTO userDTO = userServiceImpl.getByUserName(userDetails.getUsername());
			 List<OrderDetailDTO> orderDetailDTOs = orderDetailService.getOrderDetailByIdUser(userDTO.getId());
			 request.setAttribute("order", orderDetailDTOs);
		 }else { 
			 return "redirect:/login"; 
		 }
		 
		return "user/order-detail";
	}
	
	@GetMapping("/order-details/{id}")
	public String getOrderDetailId(@PathVariable()int id , HttpServletRequest request) {
		List<CategoriesDTO>categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categoriesDTOs", categoriesDTOs);
		
		List<OrderDTO> orderDTOs = orderService.getOrderById_OrderDeatil(id);
		request.setAttribute("listorder", orderDTOs);
		
		return "user/order-detail-id";
	}
}
