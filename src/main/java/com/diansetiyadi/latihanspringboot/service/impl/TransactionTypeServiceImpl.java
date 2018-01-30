package com.diansetiyadi.latihanspringboot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diansetiyadi.latihanspringboot.model.TransactionType;
import com.diansetiyadi.latihanspringboot.repository.TransactionTypeRepository;
import com.diansetiyadi.latihanspringboot.service.TransactionTypeService;

@Service
@Transactional
public class TransactionTypeServiceImpl implements TransactionTypeService {

	@Autowired
	TransactionTypeRepository transactionTypeRepository;
	
	@Override
	public List<TransactionType> findAllTransactionType() {
		// TODO Auto-generated method stub
		return transactionTypeRepository.findAll();
	}

	@Override
	public TransactionType findOneByCodeType(String transactionType) {
		// TODO Auto-generated method stub
		return transactionTypeRepository.findOneByTypeCode(transactionType);
	}

	@Override
	public TransactionType createTransactionType(TransactionType transactionType) {
		// TODO Auto-generated method stub
		
		transactionType.setCreatedOn(new Date());
		return transactionTypeRepository.save(transactionType);
	}

}
