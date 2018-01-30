package com.diansetiyadi.latihanspringboot.service;

import java.util.List;

import com.diansetiyadi.latihanspringboot.model.TransactionType;

public interface TransactionTypeService {

	
	public List<TransactionType> findAllTransactionType();
	
	public TransactionType findOneByCodeType(String transactionType);
	
	public TransactionType createTransactionType(TransactionType transactionType);
}
