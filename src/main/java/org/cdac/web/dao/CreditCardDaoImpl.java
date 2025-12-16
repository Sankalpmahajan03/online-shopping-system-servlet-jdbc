package org.cdac.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cdac.web.CreditCard;

public class CreditCardDaoImpl implements CreditCardDAO {
    private Connection connection;

    public CreditCardDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(CreditCard card) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "INSERT INTO credit_cards(cardNumber, cardHolderName, expiryDate, cvv, username) VALUES(?,?,?,?,?)"
        );
        ps.setString(1, card.getCardNumber());
        ps.setString(2, card.getCardHolderName());
        ps.setString(3, card.getExpiryDate());
        ps.setString(4, card.getCvv());
        ps.setString(5, card.getUsername());
        ps.executeUpdate();
    }

    @Override
    public CreditCard findByNumber(String cardNumber) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "SELECT * FROM credit_cards WHERE cardNumber=?"
        );
        ps.setString(1, cardNumber);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            CreditCard card = new CreditCard();
            card.setCardId(rs.getInt("cardId"));
            card.setCardNumber(rs.getString("cardNumber"));
            card.setCardHolderName(rs.getString("cardHolderName"));
            card.setExpiryDate(rs.getString("expiryDate"));
            card.setCvv(rs.getString("cvv"));
            card.setUsername(rs.getString("username"));
            return card;
        }
        return null;
    }

    @Override
    public List<CreditCard> findByUser(String username) throws SQLException {
        List<CreditCard> cards = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(
            "SELECT * FROM credit_cards WHERE username=?"
        );
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CreditCard card = new CreditCard();
            card.setCardId(rs.getInt("cardId"));
            card.setCardNumber(rs.getString("cardNumber"));
            card.setCardHolderName(rs.getString("cardHolderName"));
            card.setExpiryDate(rs.getString("expiryDate"));
            card.setCvv(rs.getString("cvv"));
            card.setUsername(rs.getString("username"));
            cards.add(card);
        }
        return cards;
    }
}
