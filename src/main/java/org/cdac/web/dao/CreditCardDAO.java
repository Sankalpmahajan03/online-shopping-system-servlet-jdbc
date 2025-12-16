package org.cdac.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.cdac.web.CreditCard;

public interface CreditCardDAO {
    void save(CreditCard card) throws SQLException;
    CreditCard findByNumber(String cardNumber) throws SQLException;
    List<CreditCard> findByUser(String username) throws SQLException;
}
