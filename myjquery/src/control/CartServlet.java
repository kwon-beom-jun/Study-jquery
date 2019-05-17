package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.websocket.Session;

import com.kitri.dto.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("CartServlet에 들어옴 핳핳!");
		
		String no = request.getParameter("no"); // 숫자
		String quantity = request.getParameter("quantity"); // 수량
		HttpSession session = request.getSession();
		
		//       상품번호         수량
		//	Map<Product, Integer> c = new HashMap<>();
		Map<Product, Integer> c = (Map)session.getAttribute("cart"); // 장바구니 세션을 해쉬맵에 넣어줌.
		
		if( c == null ) { // 있을때는 장바구니를 만들지말고 없으면 만들자.
		
		c = new HashMap<>();
		session.setAttribute("cart",c);
		
		}	
		
// ------------------------------------------------------------
		
		Product p = new Product();
		p.setProd_no(no);
		
		int intQuantity = Integer.parseInt(quantity);
		
		//상품 존재 확인
		Integer inte = c.get(p);
		if (inte != null) { // 존재하면 수량 합산.
			intQuantity += inte.intValue();
		}
		
		
		c.put(p, intQuantity);
		
		/*
		System.out.println("--장바구니 내용--");
		Set<Product> keys = c.keySet();
		for(Product key:keys) {
			int q = c.get(key);
			
			System.out.println("상품번호 : " + key.getProd_no() + ", 수량 : " + q);
			
		}
		*/
		
		//request.setAttribute("chartproduct", c);
		String path = "/addcartresult.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
