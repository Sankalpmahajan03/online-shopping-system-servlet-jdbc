package org.cdac.web;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCart() {
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
		String tmp = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(tmp);
		
		tmp = request.getParameter("productsId");
		int productId = Integer.parseInt(tmp);
		
		tmp = request.getParameter("productPrice");
		float productPrice = Float.parseFloat(tmp);
		
		Products objProduct = new Products(categoryId,productId,productPrice);
		
		ArrayList<Products> objCart = (ArrayList<Products>)session.getAttribute("cart");
		
		if(objCart==null) {
			objCart = new ArrayList<>();
			session.setAttribute("cart", objCart);
		}
		objCart.add(objProduct);
		response.sendRedirect("ListCart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
