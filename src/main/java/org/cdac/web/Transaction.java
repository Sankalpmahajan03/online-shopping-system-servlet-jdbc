package org.cdac.web;

import java.sql.Timestamp;

public class Transaction {
	 private int transactionId;
	    private String username;
	    private int orderId;
	    private double amount;
	    private String status;
	    private Timestamp transactionDate;
	    
	    public Transaction() {
	    	
	    }
		public int getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Timestamp getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(Timestamp transactionDate) {
			this.transactionDate = transactionDate;
		}
		public Transaction(int transactionId, String username, int orderId, double amount, String status,
				Timestamp transactionDate) {
			super();
			this.transactionId = transactionId;
			this.username = username;
			this.orderId = orderId;
			this.amount = amount;
			this.status = status;
			this.transactionDate = transactionDate;
		}

}
