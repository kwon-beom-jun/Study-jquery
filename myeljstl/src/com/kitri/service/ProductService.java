package com.kitri.service;

import java.util.List;

import com.kitri.dao.ProductDAO;
import com.kitri.dto.Product;

public class ProductService {

	private ProductDAO dao;

	public ProductService() {
		dao = new ProductDAO();
	}
	
	public List<Product> findAll(){
		
		return dao.selectAll();
	}
	
	public Product findByNo(String no){
		
		Product p = dao.SelectByNo(no);
		
		return p;
	}
	
//	1)요청전달데이터 얻기
//	2)model호출
//	3)결과를 Request Attribute로 추가
//	4)view로 이동
	
}
