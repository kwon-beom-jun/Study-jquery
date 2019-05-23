package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.*;
import com.kitri.exception.AddException;
import com.kitri.service.OrderService;

@WebServlet("/addorder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private OrderService service;
	
    public AddOrderServlet() {
    	service = new OrderService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("AddOrderServlet으로 들오옴~");
		//장바구니정보가 주문테이블에 저장.
		OrderInfo info = new OrderInfo();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginInfo");
		Customer c = new Customer();
		c.setId(id);
		info.setCustomer(c); //주문자 ID설정
		
		//장바구니 상품번호, 수량 -> OrderLine에 설정
		//line.setProder_prod_no(proder_prod_no);
		Map<Product,Integer> cart = (Map)session.getAttribute("cart");
		
		
		
		
		List<OrderLine> lines = new ArrayList<>();
		for(Product p: cart.keySet()) {
//			String no = p.getProd_no();
			int quantity = (Integer)cart.get(p);
			OrderLine line = new OrderLine();
			line.setProduct(p);
			line.setOrder_quantity(quantity);
			
			lines.add(line);
			
		}
		info.setLines(lines);
		
		String result = "";

		try {
			service.addOrder(info); // try catch 안나오미.
			session.removeAttribute("cart"); // 장바구니 비우기.
			result = "1";
		} catch (AddException e) {
			e.printStackTrace();
			result = "-1";
		}
		//session의 저장되있는 cart내용 빼오기.
		//아이디는 세션의 로그인 인포에서 빼오기

		String path = "/addorderresult.jsp";
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
