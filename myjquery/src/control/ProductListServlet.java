package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategory;
import com.kitri.service.ProductService;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("들어옴");
		request.setCharacterEncoding("UTF-8");

		ProductService productService = new ProductService();
		List<Product> list = productService.findAll();
		
		System.out.println(list);
		
//		private String prod_no;
//		private String prod_name;
//		private int prod_price;
//		private String prod_detail;
//		private String prod_cate;
//		private ProductCategory productCategory;
		
		/*
		String prod_no = "";
		String prod_name = "";
		int prod_price = 0;
		String prod_detail = "";
		String prod_cate = "";
		
		
		
		for (int i = 0; i < list.size(); i++) {
			prod_no = list.get(i).getProd_no();
			prod_name = list.get(i).getProd_name();
			prod_price = list.get(i).getProd_price();
			prod_detail = list.get(i).getProd_detail();
			prod_cate = list.get(i).getProd_cate();
			System.out.println(prod_no + "|" + prod_name + "|" + prod_price + "|" + prod_detail + "|" + prod_cate);
		}
		*/
		
		String path = "/product/productlistresult.jsp";
		
		
	    
		request.setAttribute("productlist", list);
	    RequestDispatcher rd = request.getRequestDispatcher(path);
	    rd.forward(request, response);
		
	}

	

}
