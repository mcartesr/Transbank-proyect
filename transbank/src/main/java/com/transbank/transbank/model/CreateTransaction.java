package com.transbank.transbank.model;

public class CreateTransaction {

	private String buyOrder;
	private String sessionId;
	private Double amount;
	private String cardNumber;
	private String cardExpirationDate;
	private Short cvv;
	
	public CreateTransaction() {
		super();
	}
	
	public String getBuyOrder() {
		return buyOrder;
	}
	public void setBuyOrder(String buyOrder) {
		this.buyOrder = buyOrder;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpirationDate() {
		return cardExpirationDate;
	}
	public void setCardExpirationDate(String cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}
	public Short getCvv() {
		return cvv;
	}
	public void setCvv(Short cvv) {
		this.cvv = cvv;
	}
	
	
}
