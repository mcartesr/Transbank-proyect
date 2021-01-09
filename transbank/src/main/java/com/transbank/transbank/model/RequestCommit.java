package com.transbank.transbank.model;

public class RequestCommit {

	private String token;
	private Long idQueryInstallments;
	
	public RequestCommit() {
		super();
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getIdQueryInstallments() {
		return idQueryInstallments;
	}
	public void setIdQueryInstallments(Long idQueryInstallments) {
		this.idQueryInstallments = idQueryInstallments;
	}
	
	
}
