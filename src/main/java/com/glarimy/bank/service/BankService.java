package com.glarimy.bank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.glarimy.bank.api.Bank;
import com.glarimy.bank.data.AccountRepository;
import com.glarimy.bank.domain.Account;
import com.glarimy.bank.domain.Transaction;
import com.glarimy.bank.exceptions.AccountNotFoundException;
import com.glarimy.bank.exceptions.BankException;
import com.glarimy.bank.exceptions.InsufficientBalanceException;
import com.glarimy.bank.exceptions.InvalidAccountException;

@Service
@EnableTransactionManagement
public class BankService implements Bank {
	@Autowired
	private AccountRepository repo;

	@Override
	@Transactional
	public Account open(Account account) throws InvalidAccountException, BankException {
		repo.save(account);
		return account;
	}

	@Override
	@Transactional
	public Transaction doTransaction(int number, Transaction tx)
			throws InsufficientBalanceException, AccountNotFoundException, BankException {
		Account account = repo.findOne(number);
		if (tx.getType().equalsIgnoreCase("CR")) {
			double balance = account.getBalance() + tx.getAmount();
			tx.setBalance(balance);
			account.setBalance(balance);
			account.getTransactions().add(tx);
			return tx;
		} else {
			double balance = account.getBalance() - tx.getAmount();
			tx.setBalance(balance);
			account.setBalance(balance);
			account.getTransactions().add(tx);
			return tx;
		}

	}

	@Override
	public List<Transaction> getTransactions(int number) throws AccountNotFoundException {
		return repo.findTrasnactionsByNumber(number);
	}

	@Override
	public Account getAccount(int number) throws AccountNotFoundException, BankException {
		return repo.findOne(number);
	}

}
