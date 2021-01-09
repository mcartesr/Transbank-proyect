package com.transbank.transbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.transbank.transbank.model.CreateTransaction;
import com.transbank.transbank.model.RequestCommit;
import com.transbank.transbank.model.RequestTransactionTB;
import com.transbank.transbank.model.ResponseUI;
import com.transbank.transbank.service.TransbankService;

@RestController
public class TransbankController {
	
	@Autowired
	TransbankService transbank;
	
	@GetMapping("/test")
	public String holaMundo() {
		return "SI ESTA CONECTADO";
	}
	
	
	@PostMapping(path="/insert",consumes="application/json")
	public ResponseUI returnTokenTransaction(@RequestBody CreateTransaction createTransaction) {
		ResponseUI response = null;
		response = transbank.responseCreateTransaction(createTransaction);
		return response;
	}
	
	@PostMapping(path="/query-installments",consumes="application/json")
	public ResponseUI returnInstallmentsResponse(@RequestBody RequestTransactionTB requestInstallments) {
		ResponseUI response = null;
		response = transbank.queryInstallments(requestInstallments.getToken(), requestInstallments.getCuotas());
		return response;
	}
	
	@PostMapping(path="/commit",consumes="application/json")
	public ResponseUI commitTransactionResponse(@RequestBody RequestCommit request) {
		ResponseUI response = null;
		response = transbank.commitTransaction(request.getToken(), request.getIdQueryInstallments());
		return response;
	}
	
	@GetMapping(path="/status-transaction",consumes="application/json")
	public ResponseUI TransactionQueryResponse(@RequestBody RequestCommit token) {
		ResponseUI response = null;
		response = transbank.StatusTransactionQuery(token.getToken());
		return response;
	}
	
	@PutMapping(path="/reserve-or-cancel",consumes="application/json")
	public ResponseUI reserveOrCancelResponse(@RequestBody RequestTransactionTB token) {
		ResponseUI response = null;
		response = transbank.reserveOrCancelTransaction(token.getToken(), token.getAmount());
		return response;
	}
	
	
}
