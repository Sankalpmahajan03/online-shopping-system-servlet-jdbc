package org.cdac.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.cdac.web.Transaction;

public interface TransactionDAO {
	void save(Transaction txn) throws SQLException;
    List<Transaction> findByUser(String username) throws SQLException;

}
