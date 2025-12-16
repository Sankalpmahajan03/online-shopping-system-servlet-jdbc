package org.cdac.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.cdac.web.dao.TransactionDAO;
import org.cdac.web.dao.TransactionDaoImpl;

@WebServlet("/ListTransactions")
public class ListTransactions extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body>");
        out.println("<a href='Logout'>Logout</a><br/>");
        out.println("Welcome <b>" + username + "</b><br/>");

        try {
            Connection conn = (Connection) getServletContext().getAttribute("globalConnection"); 
            TransactionDAO txnDao = new TransactionDaoImpl(conn);
            List<Transaction> transactions = txnDao.findByUser(username);

            out.println("<h3>Your Transactions</h3>");
            if (transactions.isEmpty()) {
                out.println("No transactions found.");
            } else {
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Order</th><th>Amount</th><th>Status</th><th>Date</th></tr>");
                for (Transaction txn : transactions) {
                    out.println("<tr>");
                    out.println("<td>" + txn.getTransactionId() + "</td>");
                    out.println("<td>" + txn.getOrderId() + "</td>");
                    out.println("<td>" + txn.getAmount() + "</td>");
                    out.println("<td>" + txn.getStatus() + "</td>");
                    out.println("<td>" + txn.getTransactionDate() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } catch (SQLException e) {
            e.printStackTrace(out);
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}