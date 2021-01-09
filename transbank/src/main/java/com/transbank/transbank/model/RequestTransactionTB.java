package com.transbank.transbank.model;

public class RequestTransactionTB {

	private String token;
	private Byte cuotas;
	private Double amount;
	
	public RequestTransactionTB() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Byte getCuotas() {
		return cuotas;
	}

	public void setCuotas(Byte cuotas) {
		this.cuotas = cuotas;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	
	
	
	
	
}
