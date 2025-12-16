package org.cdac.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Products")
public class ProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String tmpId = request.getParameter("catId");
        int categoryId = Integer.parseInt(tmpId);

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ServletContext context = getServletContext();
            connection = (Connection) context.getAttribute("globalConnection");

            ps = connection.prepareStatement("SELECT * FROM products WHERE categoryId=?");
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Product List</h3>");
            out.println("<table border='1'>");
            out.println("<tr><th>Name</th><th>Description</th><th>Price</th><th>Image</th><th>Action</th></tr>");

            while (rs.next()) {
                int productId = rs.getInt("productId");
                float price = rs.getFloat("productprice");

                out.println("<tr>");
                out.println("<td>" + rs.getString("productName") + "</td>");
                out.println("<td>" + rs.getString("productDescription") + "</td>");
                out.println("<td>" + price + "</td>");
                out.println("<td><img src='Images/" + rs.getString("productimageUrl") + "' height='60' width='60'/></td>");
                out.println("<td><a href='AddCart?categoryId=" + categoryId +
                            "&productsId=" + productId +
                            "&productPrice=" + price + "'>Add to Cart</a></td>");
                out.println("</tr>");
            }

            out.println("</table></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); } catch (SQLException e) {}
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}