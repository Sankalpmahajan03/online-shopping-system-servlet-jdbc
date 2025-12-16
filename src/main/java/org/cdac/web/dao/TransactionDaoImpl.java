package org.cdac.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cdac.web.Transaction;

public class TransactionDaoImpl implements TransactionDAO{
	 private Connection connection;

	    public TransactionDaoImpl(Connection connection) {
	        this.connection = connection;
	    }

	    @Override
	    public void save(Transaction txn) throws SQLException {
	        PreparedStatement ps = connection.prepareStatement(
	            "INSERT INTO transactions(username, orderId, amount, status, transactionDate) VALUES(?,?,?,?,?)"
	        );
	        ps.setString(1, txn.getUsername());
	        ps.setInt(2, txn.getOrderId());
	        ps.setDouble(3, txn.getAmount());
	        ps.setString(4, txn.getStatus());
	        ps.setTimestamp(5, txn.getTransactionDate());
	        ps.executeUpdate();
	    }

	    @Override
	    public List<Transaction> findByUser(String username) throws SQLException {
	        List<Transaction> txns = new ArrayList<>();
	        PreparedStatement ps = connection.prepareStatement(
	            "SELECT * FROM transactions WHERE username=?"
	        );
	        ps.setString(1, username);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Transaction txn = new Transaction();
	            txn.setTransactionId(rs.getInt("transactionId"));   // assuming PK column
	            txn.setUsername(rs.getString("username"));
	            txn.setOrderId(rs.getInt("orderId"));
	            txn.setAmount(rs.getDouble("amount"));
	            txn.setStatus(rs.getString("status"));
	            txn.setTransactionDate(rs.getTimestamp("transactionDate"));

	            txns.add(txn);   // ✅ add to list
	        }

	        return txns;
	    }
}
