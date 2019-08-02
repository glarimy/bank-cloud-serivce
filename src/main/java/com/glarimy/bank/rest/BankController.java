package com.glarimy.bank.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.glarimy.bank.api.Bank;
import com.glarimy.bank.domain.Account;
import com.glarimy.bank.domain.Transaction;

@RestController
public class BankController {

	@Autowired
	private Bank bank;

	@RequestMapping(path = "/account", method = RequestMethod.POST)
	public ResponseEntity<Account> addNewAccount(@RequestBody Account account, UriComponentsBuilder builder) {
		account = bank.open(account);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/account/{number}").buildAndExpand(account.getNumber()).toUri());
		return new ResponseEntity<Account>(account, headers, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/account/{number}/transaction", method = RequestMethod.POST)
	public ResponseEntity<Transaction> doTransaction(@RequestBody Transaction tx, @PathVariable("number") int number,
			UriComponentsBuilder builder) {
		tx = bank.doTransaction(number, tx);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/account/{number}/tx/{tid}").buildAndExpand(number, tx.getId()).toUri());
		return new ResponseEntity<Transaction>(tx, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/account/{number}/transaction")
	public ResponseEntity<List<Transaction>> history(@PathVariable("number") int number) {
		return new ResponseEntity<List<Transaction>>(bank.getTransactions(number), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/account/{number}")
	public ResponseEntity<Account> find(@PathVariable("number") int number) {
		return new ResponseEntity<Account>(bank.getAccount(number), HttpStatus.OK);
	}

}
