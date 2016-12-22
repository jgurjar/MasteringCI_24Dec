package com.capgemini.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountTest {
	
	@Mock
	AccountRepository accountRepository;
	
	AccountService accountService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService=new AccountServiceImpl(accountRepository);
	}

	@Test(expected=com.capgemini.exception.InsufficientInitialBalanceException.class)
	public void whenInitialBalanceIsLessThan500ThenThrowException() throws InsufficientInitialBalanceException {
		accountService.createAccount(101, 400);		
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException{
		
		Account account = new Account() ;
		account.setAccount_number(101);
		account.setAmount(501);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account,accountService.createAccount(101, 501));		
	}
	
	/*
	 * when accountNumber is invalid then throw exception 
	 * when accountNumber is valid then retuen balance of given account number
	 * 
	 * */

	@Test(expected=com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenAccountNumIsInvalidThenThrowException() throws InvalidAccountNumberException {
		accountService.showBalance(112);		
	}
	
	@Test 
	public void whenAccountNumIsInvalidThenShowBalance() throws InvalidAccountNumberException {

		Account account = new Account() ;
		account.setAccount_number(101);
		account.setAmount(70000);
		
		when(accountRepository.searchAccount(account.getAccount_number())).thenReturn(account);
	   
		assertEquals(account.getAmount(),accountService.showBalance(account.getAccount_number()));
		
	}
}
