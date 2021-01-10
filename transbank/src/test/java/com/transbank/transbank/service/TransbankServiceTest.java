package com.transbank.transbank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.transbank.transbank.model.CreateTransaction;
import com.transbank.transbank.model.ResponseUI;

import cl.transbank.transaccioncompleta.model.FullTransactionCreateResponse;
import cl.transbank.webpay.exception.TransactionCreateException;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransbankServiceTest {

	@Autowired
	private TransbankService transbankService;
	
	
	@Test
	public void testCreateTransaction() throws TransactionCreateException, IOException {
		FullTransactionCreateResponse fullTransaction = new FullTransactionCreateResponse();
		fullTransaction.setToken("01ab57c40861c139c32769d6d91147591d72348e76c27587da7e63d4aaebbc3b");
		
		CreateTransaction createT = new CreateTransaction();
		createT.setBuyOrder("ordenCompra12345678");
		createT.setSessionId("sesion1234564");
		createT.setAmount((double) 10000);
		createT.setCardNumber("4051885600446623");
		createT.setCardExpirationDate("22/10");
		createT.setCvv((short) 123);
		
		ResponseUI response = new ResponseUI(true,HttpStatus.OK);
		response.getData().put("tokenId",fullTransaction.getToken());
				
		assertThat(transbankService.responseCreateTransaction(createT)).isNotNull();
		assertThat(transbankService.responseCreateTransaction(createT)).isInstanceOf(ResponseUI.class);
	}
}
