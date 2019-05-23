package com.kitri.service;

import java.util.List;

import com.kitri.dao.OrderDAO;
import com.kitri.dto.OrderInfo;
import com.kitri.exception.AddException;

public class OrderService {

	private OrderDAO dao;
	
	public OrderService() {
		dao = new OrderDAO();
	}
	
	public void addOrder(OrderInfo info) throws AddException{
		System.out.println("서비스 호출 - 주문 insert");
		dao.insert(info);
	}
	
	public List<OrderInfo> findById(String id){
		return dao.selectById(id);
	}
	
	
}
