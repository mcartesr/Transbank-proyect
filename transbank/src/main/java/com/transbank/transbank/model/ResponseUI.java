package com.transbank.transbank.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseUI {
	/**
	 *  mensaje de error
	 */
	public static final String ERROR_KEY = "error";

	private boolean success;
	@JsonIgnore
	private HttpStatus httpCode;
	private Map<String, Object> data = new HashMap<String, Object>(1);
	
	public ResponseUI() {}
	
	public ResponseUI(boolean success, HttpStatus httpCode) {
		this.success = success;
		this.httpCode = httpCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(HttpStatus httpCode) {
		this.httpCode = httpCode;
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void setError(String message) {
		List<String> errors = new ArrayList<>(3);
		errors.add(message);
		data.put(ERROR_KEY, errors);
	}
	
	@SuppressWarnings("unchecked")
	public void addError(String message) {
		if (data.containsKey(ERROR_KEY)) {
			((List<String>) data.get(ERROR_KEY)).add(message);
		} else {
			setError(message);
		}
		
	}
	
}
