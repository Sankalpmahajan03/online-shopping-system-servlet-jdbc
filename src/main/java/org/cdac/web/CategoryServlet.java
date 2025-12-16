package org.cdac.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;

import org.cdac.web.dao.CategoryDAO;
import org.cdac.web.dao.CategoryDAOImpl;
import org.cdac.web.entity.Category;
import org.cdac.web.exception.CategoryException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   CategoryDAO categoryDao;
   
   @Override
   public void init(ServletConfig config) throws ServletException{
	   super.init(config);
	   ServletContext application = getServletContext();
	   Connection connection = (Connection)application.getAttribute("globalConnection");
	   
	   categoryDao = new CategoryDAOImpl(connection);
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession(false);
			if(session==null) {
				response.sendRedirect("login.html");
				return;
			}
			Iterator<Category>iter = categoryDao.findAll();
			
			PrintWriter out = response.getWriter();
			String username = (String)session.getAttribute("username");
			out.println("<html>");
			out.println("<body>");
			out.println("Welcome<b>"+username+"</b><br/>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<td>Name</td>");
			out.println("<td>Description</td>");
			out.println("<td>Image</td>");
			out.println("</tr>");
			
			while(iter.hasNext()) {
				out.println("<tr>");
				Category objCategory = iter.next();
				out.println("<td><a href='Products?catId="+objCategory.getCategoryId()+"'>"+objCategory.getCategoryName()+"</a></td>");
				out.println("<td>"+objCategory.getCategoryDescription()+"</td>");
				out.println("<td><img src ='Images/"+objCategory.getCategoryImage()+"'height='60px' width='60px'/></td>");
			}
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
		}catch(IOException e) {
			e.printStackTrace();
		}catch(CategoryException e) {
			e.printStackTrace();
		}finally {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
