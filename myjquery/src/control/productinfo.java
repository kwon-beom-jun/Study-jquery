package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;

@WebServlet("/productinfo")
public class productinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProductService productService;
	
	public void init() {
		productService = new ProductService();
		// doget 할때마다 생성되는것보다 따로 빼줌.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("productinfo 왔습니다요~");
		
		String no = request.getParameter("no");
		

		Product product = productService.findByNo(no);
//		System.out.println(product.getProd_name());
		request.setAttribute("product", product);
		String path = "productdetail.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
	}
	

}
