package com.diansetiyadi.latihanspringboot.service;

import java.util.List;

import com.diansetiyadi.latihanspringboot.dto.HistoryTransactionDto;
import com.diansetiyadi.latihanspringboot.dto.TransactionDto;
import com.diansetiyadi.latihanspringboot.dto.TransactionLoginDto;
import com.diansetiyadi.latihanspringboot.dto.UserLoginDto;
import com.diansetiyadi.latihanspringboot.model.TransactionHistory;

public interface TransactionHistoryService {

	public List<TransactionHistory> findAllTransactionHistory();

	public TransactionHistory findOneTransactionById(Long transactionId);

	public TransactionHistory saveTransaction(TransactionHistory transactionHistory);

	public void updateTransaction(TransactionHistory transactionHistory);

	public void deleteTransaction(TransactionHistory transactionHistory);
	
	public Long findCountTransaction();
	
	public List<TransactionDto> getOneListTransaction(TransactionLoginDto transactionLoginDto);
	
	public TransactionHistory transactionTransfer(TransactionDto transactionDto);
	
	public TransactionHistory transactionDeposit(TransactionDto transactionDto);
	
	public TransactionHistory transactionWithdrawl(TransactionDto transactionDto);
	
}
