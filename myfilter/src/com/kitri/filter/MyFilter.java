package com.kitri.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter(description = "Test", urlPatterns = { "*" })// * = 모든 요청이 들어올때 필터를 거쳐라.
public class MyFilter implements Filter {

	
//	http://localhost/myfilter/first
	
	
    /**
     * Default constructor. 
     */
    public MyFilter() { // 객체생성시 자동생성
    	System.out.println("MyFilter객체생성됨");
    }
    
    public void init(FilterConfig fConfig) throws ServletException { // 객체생성시 자동생성   	
		System.out.println("MyFilter의 init()호출");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("MyFilter의 doFilter()호출됨");
		chain.doFilter(request, response); // 없으면 서블릿에게 보내지않고 여기서 끝냄.
	}

	public void destroy() {
		System.out.println("MyFilter객체소멸됨");
	}
	
	

}
