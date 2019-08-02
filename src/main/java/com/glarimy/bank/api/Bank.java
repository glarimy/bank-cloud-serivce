package com.glarimy.bank.api;

import java.util.List;

import com.glarimy.bank.domain.Account;
import com.glarimy.bank.domain.Transaction;
import com.glarimy.bank.exceptions.AccountNotFoundException;
import com.glarimy.bank.exceptions.BankException;
import com.glarimy.bank.exceptions.InsufficientBalanceException;
import com.glarimy.bank.exceptions.InvalidAccountException;

public interface Bank {
	public Account open(Account account) throws InvalidAccountException, BankException;

	public Transaction doTransaction(int number, Transaction tx)
			throws InsufficientBalanceException, AccountNotFoundException, BankException;

	public List<Transaction> getTransactions(int number) throws AccountNotFoundException;

	public Account getAccount(int number) throws AccountNotFoundException, BankException;
}
