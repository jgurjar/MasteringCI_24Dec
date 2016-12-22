package com.capgemini.service;

import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {

	public Account createAccount(int account_number, int amount) throws InsufficientInitialBalanceException ;
	
	 int showBalance(int account_number) throws InvalidAccountNumberException;
	
	 int withdrawAmount(int account_number, int amount) throws InvalidAccountNumberException,InsufficientBalanceException;
	
	 int depositAmount(int account_number) throws InvalidAccountNumberException;
	
	 String fundTransfer(int account_number) throws InvalidAccountNumberException; 
}
