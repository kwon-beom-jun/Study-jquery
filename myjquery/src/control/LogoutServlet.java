package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		//session.invalidate(); 세션 제거 
		session.removeAttribute("loginInfo"); // 세션의 속성만 제거.
		
		String path = "/logoutresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request,response);
				
	}

}
