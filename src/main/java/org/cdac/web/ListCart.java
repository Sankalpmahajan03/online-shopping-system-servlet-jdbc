package org.cdac.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ListCart
 */
@WebServlet("/ListCart")
public class ListCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("login.html");
			return;
		}
		PrintWriter out = response.getWriter();
		
		ArrayList<Products> objCart = (ArrayList<Products>) session.getAttribute("cart");
		
		out.println("<html>");
		out.println("<body>");
		out.println("<a href = Logout>Logout</a><br/>");
		out.println("Welcome<b>" + session.getAttribute("username")+ "</b><br/>");
		
		if(objCart==null || objCart.isEmpty()) {
			out.println("No products in your cart");
		}else {
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<td>Name</td>");
			out.println("<td>Description</td>");
			out.println("<td>Image</td>");
			out.println("</tr>");
			
			Iterator<Products> iter = objCart.iterator();
			
			double total = 0.0;
			while(iter.hasNext()) {
				Products objProduct =iter.next();
				out.println("<tr>");
				out.println("<td>"+objProduct.getCategoryId()+"</td>");
				out.println("<td>"+objProduct.getProductId()+"</td>");
				out.println("<td>"+objProduct.getProductPrice()+"</td>");
				out.println("</tr");
				total +=objProduct.getProductPrice();
			}
			out.println("</table>");
			out.println("<b>Total :"+total);
			out.println("<br/><a href='Category'>Continue Shopping</a>");
			out.println("<br/><a href='ListCreditCards'>See Saved Cards</a>");
			
			out.println("<form action='PlaceOrder' method='post'>");
			out.println("<input type='submit' value='Place Order'/>");
			out.println("</form>");

			out.println("</body>");
			out.println("</html>");
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
