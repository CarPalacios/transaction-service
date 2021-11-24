package com.nttdata.service.impl;

import com.nttdata.model.Transaction;
import com.nttdata.repository.Repository;
import com.nttdata.repository.TransactionRepository;
import com.nttdata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**La clase TransactionServiceImpl se extiende CrudServiceImpl y se implementa TransactionService.*/
@Service
public class TransactionServiceImpl 
    extends CrudServiceImpl<Transaction, String> implements TransactionService {

  @Autowired
  private TransactionRepository repository;

  @Override
  protected Repository<Transaction, String> getRepository() {
    return repository;
  }

}
