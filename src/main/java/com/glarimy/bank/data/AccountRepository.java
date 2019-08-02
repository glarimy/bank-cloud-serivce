package com.glarimy.bank.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glarimy.bank.domain.Account;
import com.glarimy.bank.domain.Transaction;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	List<Transaction> findTrasnactionsByNumber(int id);
}
