package com.capgemini.service;

import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService{

	AccountRepository accountRepository ;
	public AccountServiceImpl(AccountRepository accountRepository){
		this.accountRepository=accountRepository;
		
	}
	
	public Account createAccount(int account_number, int amount) throws InsufficientInitialBalanceException {
		
		if(amount<500){
			throw new InsufficientInitialBalanceException();
		}
		
		Account account = new Account();
		account.setAccount_number(account_number);
		account.setAmount(amount);
		
		if(accountRepository.save(account)){
			return account;
		}
		return null;
	}

	@Override
	public int showBalance(int account_number) throws InvalidAccountNumberException {
		
	   
		
		
		System.out.println(account_number);
		System.out.println("1)---"+accountRepository.searchAccount(account_number));
		System.out.println("1)---"+accountRepository.searchAccount(account_number).getAccount_number());
	   if(accountRepository.searchAccount(account_number).getAccount_number()==account_number){
		   return accountRepository.searchAccount(account_number).getAmount();
	   }else{
		   throw new InvalidAccountNumberException();
	   }
	   
	}

	@Override
	public int withdrawAmount(int account_number,int amount) throws InvalidAccountNumberException, InsufficientBalanceException {
		Account account = accountRepository.searchAccount(account_number);
		if(account.getAccount_number()==account_number){
		   if(account.getAmount()>=amount){
			   account.setAmount(account.getAmount()-amount);
			  
			   	   
		   }
		   return amount;
		}else{
			throw new InvalidAccountNumberException();
		}
		
	}

	@Override
	public int depositAmount(int account_number) throws InvalidAccountNumberException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String fundTransfer(int account_number) throws InvalidAccountNumberException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
