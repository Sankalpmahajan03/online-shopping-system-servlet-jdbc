package org.cdac.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.cdac.web.dao.CreditCardDAO;
import org.cdac.web.dao.CreditCardDaoImpl;

@WebServlet("/SaveCreditCard")
public class SaveCreditCardServlet extends HttpServlet {
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
        String cardNumber = request.getParameter("cardNumber");
        String holderName = request.getParameter("cardHolderName");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        try {
            ServletContext context = getServletContext();
            Connection connection = (Connection) context.getAttribute("globalConnection");

            CreditCardDAO cardDao = new CreditCardDaoImpl(connection);

            CreditCard card = new CreditCard(0, cardNumber, holderName, expiryDate, cvv, username);
            cardDao.save(card);

            // ✅ Redirect to list of saved cards
            response.sendRedirect("ListCreditCards");

        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}