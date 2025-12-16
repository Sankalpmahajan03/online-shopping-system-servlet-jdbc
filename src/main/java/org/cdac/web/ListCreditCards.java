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

import org.cdac.web.dao.CreditCardDAO;
import org.cdac.web.dao.CreditCardDaoImpl;

@WebServlet("/ListCreditCards")
public class ListCreditCards extends HttpServlet {
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
            CreditCardDAO cardDao = new CreditCardDaoImpl(conn);
            List<CreditCard> cards = cardDao.findByUser(username);

            out.println("<h3>Your Saved Credit Cards</h3>");
            out.println("<a href='addCard.html'>Add New Card</a><br/><br/>");
            if (cards.isEmpty()) {
                out.println("No credit cards found.");
            } else {
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Number</th><th>Holder</th><th>Expiry</th></tr>");
                for (CreditCard card : cards) {
                    out.println("<tr>");
                    out.println("<td>" + card.getCardId() + "</td>");
                    out.println("<td>" + card.getCardNumber() + "</td>");
                    out.println("<td>" + card.getCardHolderName() + "</td>");
                    out.println("<td>" + card.getExpiryDate() + "</td>");
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