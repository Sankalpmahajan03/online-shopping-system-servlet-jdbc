package org.cdac.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.cdac.web.dao.TransactionDAO;
import org.cdac.web.dao.TransactionDaoImpl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        ArrayList<Products> cart = (ArrayList<Products>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.getWriter().println("Cart is empty.");
            return;
        }

        double totalAmount = 0.0;
        for (Products p : cart) {
            totalAmount += p.getProductPrice();
        }

        try {
            ServletContext context = getServletContext();
            Connection connection = (Connection) context.getAttribute("globalConnection");

            // ✅ Insert line items into orders table
            PreparedStatement psOrder = connection.prepareStatement(
                "INSERT INTO orders(username, productId, categoryId, price) VALUES(?,?,?,?)"
            );
            for (Products p : cart) {
                psOrder.setString(1, username);
                psOrder.setInt(2, p.getProductId());
                psOrder.setInt(3, p.getCategoryId());
                psOrder.setFloat(4, p.getProductPrice());
                psOrder.executeUpdate();
            }

            // ✅ Save overall transaction using DAO
            Transaction txn = new Transaction();
            txn.setUsername(username);
            txn.setOrderId((int)(Math.random() * 10000)); // generate orderId
            txn.setAmount(totalAmount);
            txn.setStatus("Success");
            txn.setTransactionDate(new java.sql.Timestamp(System.currentTimeMillis()));

            TransactionDAO txnDao = new TransactionDaoImpl(connection);
            txnDao.save(txn);

            // clear cart
            cart.clear();

            // redirect to transaction history
            response.sendRedirect("ListTransactions");

        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}