package org.cdac.web;

public class CreditCard {
	
	    private int cardId;
	    private String cardNumber;
	    private String cardHolderName;
	    private String expiryDate;
	    private String cvv;
	    private String username; 

	    public CreditCard() {
	    	
	    }
	    
	    public CreditCard(int cardId,String cardNumber,String cardHolderName,String expiryDate,String cvv,String username) {
	    	this.cardId=cardId;
	    	this.cardNumber=cardNumber;
	    	this.cardHolderName=cardHolderName;
	    	this.expiryDate=expiryDate;
	    	this.cvv=cvv;
	    	this.username=username;
	    }

		public int getCardId() {
			return cardId;
		}

		public void setCardId(int cardId) {
			this.cardId = cardId;
		}

		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}

		public String getCardHolderName() {
			return cardHolderName;
		}

		public void setCardHolderName(String cardHolderName) {
			this.cardHolderName = cardHolderName;
		}

		public String getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
		}

		public String getCvv() {
			return cvv;
		}

		public void setCvv(String cvv) {
			this.cvv = cvv;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

}
