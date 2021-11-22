package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.model.Transaction;
import com.nttdata.repository.IRepository;
import com.nttdata.repository.ITransactionRepository;
import com.nttdata.service.ITransactionService;

public class TransactionService extends CRUDServiceImpl<Transaction, String> implements ITransactionService {

	@Autowired
	private ITransactionRepository repository;

	@Override
	protected IRepository<Transaction, String> getRepository() {
		
		return repository;
		
	}

}