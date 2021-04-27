package com.nguyenxuantuan.shopdongho.project.admin.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyenxuantuan.shopdongho.project.model.OrderDTO;
import com.nguyenxuantuan.shopdongho.project.model.OrderDetailDTO;
import com.nguyenxuantuan.shopdongho.project.service.OrderDetailService;
import com.nguyenxuantuan.shopdongho.project.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order")
	public String getOrder(HttpServletRequest request) {
		List<OrderDetailDTO> orderDetailDTOs = orderDetailService.getAll();
		request.setAttribute("order", orderDetailDTOs);
		return "admin/order";
	}
	@GetMapping("/order-detail/{id}")
	public String getOrderDetail(HttpServletRequest request, @PathVariable()int id) {
		List<OrderDTO> orderDTOs = orderService.getOrderById_OrderDeatil(id);
		
		float total=0;
		for(OrderDTO orderDTO : orderDTOs) {
			total += orderDTO.getPrice();
		}
		request.setAttribute("order", orderDTOs);
		request.setAttribute("total", total);
		return "admin/order-detail";
	}
}
