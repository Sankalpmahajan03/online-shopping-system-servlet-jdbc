package org.cdac.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection connection =null;
	PreparedStatement psAuthenticateUser = null;
	
   @Override
   public void init(ServletConfig config) throws ServletException{
	   super.init(config);
	   try {
		   System.out.println("Init of Authenticate");
		   ServletContext application = getServletContext();
		   String dbUser = application.getInitParameter("dbUsername");
		   String dbUrl = application.getInitParameter("dbUrl");
		   String password = application.getInitParameter("dbPassword");
		   String driverClass = application.getInitParameter("driverClass");
		   
		   Class.forName(driverClass);
		   
		   connection = DriverManager.getConnection(dbUrl,dbUser,password);
		   
		   application.setAttribute("globalConnection",connection);
		   
		   psAuthenticateUser = connection.prepareStatement("select * from userdetails where Username=? and password=?");
		   
	   }catch(ClassNotFoundException e) {
		   e.printStackTrace();
		   throw new ServletException ("Failed to initilaze AUthenticate",e);
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
   }
   
   @Override
   public void destroy() {
	   super.destroy();
	   try {
		   if(psAuthenticateUser!=null) psAuthenticateUser.close();
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }
   }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		ResultSet resultAuthenticate = null;
		    try {
		        String username = request.getParameter("username");
		        String password = request.getParameter("password");

		        psAuthenticateUser.clearParameters();
		        psAuthenticateUser.setString(1, username);
		        psAuthenticateUser.setString(2, password);

		        resultAuthenticate = psAuthenticateUser.executeQuery();

		        if (resultAuthenticate.next()) {
		            HttpSession session = request.getSession();
		            session.setAttribute("username", username);

		            // ✅ Redirect to CategoryServlet after login
		            response.sendRedirect("Category");
		        } else {
		            response.getWriter().println("Authentication Failure");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (resultAuthenticate != null) resultAuthenticate.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
}
}
