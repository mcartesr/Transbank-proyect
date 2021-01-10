package com.transbank.transbank.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.transbank.transbank.model.*;

import java.io.IOException;

import javax.transaction.Transactional;



import cl.transbank.transaccioncompleta.FullTransaction;
import cl.transbank.transaccioncompleta.model.FullTransactionCommitResponse;
import cl.transbank.transaccioncompleta.model.FullTransactionCreateResponse;
import cl.transbank.transaccioncompleta.model.FullTransactionInstallmentResponse;
import cl.transbank.transaccioncompleta.model.FullTransactionRefundResponse;
import cl.transbank.transaccioncompleta.model.FullTransactionStatusResponse;
import cl.transbank.webpay.exception.TransactionCommitException;
import cl.transbank.webpay.exception.TransactionCreateException;
import cl.transbank.webpay.exception.TransactionInstallmentException;
import cl.transbank.webpay.exception.TransactionRefundException;
import cl.transbank.webpay.exception.TransactionStatusException;

@Service
public class TransbankService {

	@Transactional
	public ResponseUI responseCreateTransaction(CreateTransaction createTransaction) {
		ResponseUI responseUI = null;
		try {
			final FullTransactionCreateResponse response =  FullTransaction.Transaction.create(
					  createTransaction.getBuyOrder(),
					  createTransaction.getSessionId(),
					  createTransaction.getAmount(),
					  createTransaction.getCardNumber(),
					  createTransaction.getCardExpirationDate(),
					  createTransaction.getCvv()                               
					);
			responseUI = new ResponseUI(true, HttpStatus.OK);
			responseUI.getData().put("tokenId",response.getToken());
		} catch (TransactionCreateException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseUI;
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	public ResponseUI queryInstallments(String token, Byte cuotas) {
		ResponseUI responseUI = null;
		FullTransactionInstallmentResponse response;		
			try {
				response = FullTransaction.Transaction.installment(token, cuotas);
				responseUI = new ResponseUI(true, HttpStatus.OK);
				responseUI.getData().put("installmentsAmount",response.getInstallmentsAmount());
				responseUI.getData().put("idQueryInstallments",response.getIdQueryInstallments());
				responseUI.getData().put("deferredPeriod",response.getDeferredPeriods());


			}catch (TransactionInstallmentException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		return responseUI;
	}
	
	public ResponseUI commitTransaction(String token, Long id) {
		ResponseUI responseUI = null;
		try {
			final FullTransactionCommitResponse response = FullTransaction.Transaction.commit(token, id, null, null);
			responseUI = new ResponseUI(true, HttpStatus.OK);
			responseUI.getData().put("accountingDate",response.getAccountingDate());
			responseUI.getData().put("amount",response.getAmount());
			responseUI.getData().put("authorizationCode",response.getAuthorizationCode());
			responseUI.getData().put("buyOrder",response.getBuyOrder());
			responseUI.getData().put("cardNumber",response.getCardNumber());
			responseUI.getData().put("installmentsNumber",response.getInstallmentsNumber());
			responseUI.getData().put("paymentTypeCode",response.getPaymentTypeCode());
			responseUI.getData().put("responseCode",response.getResponseCode());
			responseUI.getData().put("sessionId",response.getSessionId());
			responseUI.getData().put("transactionDate",response.getTransactionDate());

		} catch (TransactionCommitException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseUI;

	}
	
	public ResponseUI StatusTransactionQuery(String token) {
		ResponseUI responseUI = null;
		try {
			final FullTransactionStatusResponse response = FullTransaction.Transaction.status(token);
			responseUI = new ResponseUI(true, HttpStatus.OK);
			responseUI.getData().put("accountingDate",response.getAccountingDate());
			responseUI.getData().put("amount",response.getAmount());
			responseUI.getData().put("authorizationCode",response.getAuthorizationCode());
			responseUI.getData().put("buyOrder",response.getBuyOrder());
			responseUI.getData().put("cardNumber",response.getCardNumber());
			responseUI.getData().put("installmentsNumber",response.getInstallmentsNumber());
			responseUI.getData().put("paymentTypeCode",response.getPaymentTypeCode());
			responseUI.getData().put("responseCode",response.getResponseCode());
			responseUI.getData().put("sessionId",response.getSessionId());
			responseUI.getData().put("transactionDate",response.getTransactionDate());
		} catch (TransactionStatusException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseUI;
	}
	

	public ResponseUI reserveOrCancelTransaction(String token, Double amount) {
		ResponseUI responseUI = null;
		try {
			final FullTransactionRefundResponse response = FullTransaction.Transaction.refund(token,amount);
			responseUI = new ResponseUI(true, HttpStatus.OK);
			responseUI.getData().put("Type",response.getType());
			responseUI.getData().put("authorizationDate",response.getAuthorizationDate());
			responseUI.getData().put("authorizationCode",response.getAuthorizationCode());
			responseUI.getData().put("nullifiedAmount",response.getNullifiedAmount());
			responseUI.getData().put("balance",response.getBalance());
			responseUI.getData().put("responseCode",response.getResponseCode());
		} catch (TransactionRefundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseUI;
	}
	
	

}
