package com.diansetiyadi.latihanspringboot.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.diansetiyadi.latihanspringboot.dto.TransactionDto;
import com.diansetiyadi.latihanspringboot.dto.TransactionLoginDto;

import com.diansetiyadi.latihanspringboot.model.Account;
import com.diansetiyadi.latihanspringboot.model.Client;
import com.diansetiyadi.latihanspringboot.model.TransactionHistory;
import com.diansetiyadi.latihanspringboot.model.TransactionType;
import com.diansetiyadi.latihanspringboot.model.User;
import com.diansetiyadi.latihanspringboot.repository.*;
import com.diansetiyadi.latihanspringboot.service.TransactionHistoryService;

import com.diansetiyadi.latihanspringboot.util.PaddingString;
import com.diansetiyadi.latihanspringboot.util.ShaDigest;

@Service
@Transactional
public class TransctionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionTypeRepository transactionTypeRepository;
	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<TransactionHistory> findAllTransactionHistory() {
		// TODO Auto-generated method stub
		return transactionHistoryRepository.findAll();
	}

	@Override
	public TransactionHistory findOneTransactionById(Long transactionId) {
		// TODO Auto-generated method stub
		return transactionHistoryRepository.findOne(transactionId);
	}

	@Override
	public TransactionHistory saveTransaction(TransactionHistory transactionHistory) {
		// TODO Auto-generated method stub

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DDMMYYYY");

		String padding = String.format(simpleDateFormat.format(new Date()))
				+ PaddingString.PAD_LEFT_ZEROS(findCountTransaction().toString(), 7);
		Long finalGeneratorTransactionId = Long.parseLong(padding);
		transactionHistory.setTransactionId(finalGeneratorTransactionId);
		return transactionHistoryRepository.save(transactionHistory);
	}

	@Override
	public void updateTransaction(TransactionHistory transactionHistory) {
		// TODO Auto-generated method stub
		transactionHistoryRepository.save(transactionHistory);
	}

	@Override
	public void deleteTransaction(TransactionHistory transactionHistory) {
		// TODO Auto-generated method stub
		transactionHistoryRepository.delete(transactionHistory);
	}

	@Override
	public Long findCountTransaction() {
		// TODO Auto-generated method stub
		Long i = transactionHistoryRepository.count() + 1;
		return i;
	}

	@Override
	public TransactionHistory transactionTransfer(TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		Account pengirim = accountRepository.findOne(transactionDto.getPengirimId());
		Account penerima = accountRepository.findOne(transactionDto.getPenerimaId());

		if (pengirim == null & penerima == null) {
			return null;
		}

		User user = userRepository.findByClientId(pengirim.getClientId());
		if (user == null
				|| !user.getPassword().equals(ShaDigest.get_SHA_1_SecurePassword(transactionDto.getPassword()))) {
			return null;
		}

		TransactionHistory transactionHistory = new TransactionHistory();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DDMMYYYY");

		double amountPengirim = pengirim.getBalance().doubleValue() - transactionDto.getAmount().doubleValue();
		double amountPenerima = penerima.getBalance().doubleValue() + transactionDto.getAmount().doubleValue();
		if (amountPengirim < 0) {
			return null;

		}
		String padding = String.format(simpleDateFormat.format(new Date()))
				+ PaddingString.PAD_LEFT_ZEROS(findCountTransaction().toString(), 4);
		Long finalGeneratorTransactionId = Long.parseLong(padding);
		penerima.setBalance(new BigDecimal(amountPenerima));
		pengirim.setBalance(new BigDecimal(amountPengirim));
		transactionHistory.setAmount(transactionDto.getAmount());
		transactionHistory.setTransactionDate(new Date());
		transactionHistory.setTransactionId(finalGeneratorTransactionId);
		transactionHistory.setTransactionTypeCode("a");
		transactionHistory.setReceipentAccount(penerima.getAccountId());
		transactionHistory.setUserAccount(pengirim.getAccountId());
		transactionDto.setPassword(user.getPassword());
		transactionHistoryRepository.save(transactionHistory);
		return transactionHistory;
	}

	@Override
	public TransactionHistory transactionDeposit(TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findOne(transactionDto.getPengirimId());
		if (account == null) {
			return null;
		}

		User user = userRepository.findByClientId(account.getClientId());
		if (user == null
				|| !user.getPassword().equals(ShaDigest.get_SHA_1_SecurePassword(transactionDto.getPassword()))) {
			return null;
		}

		TransactionHistory transactionHistory = new TransactionHistory();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DDMMYYYY");

		double amountDeposit = account.getBalance().doubleValue() + transactionDto.getAmount().doubleValue();
		if (amountDeposit < 0) {
			return null;
		}
		String padding = String.format(simpleDateFormat.format(new Date()))
				+ PaddingString.PAD_LEFT_ZEROS(findCountTransaction().toString(), 7);
		Long finalGeneratorTransactionId = Long.parseLong(padding);
		transactionHistory.setAmount(transactionDto.getAmount());
		account.setBalance(new BigDecimal(amountDeposit));
		transactionHistory.setUserAccount(account.getAccountId());
		transactionHistory.setTransactionId(finalGeneratorTransactionId);
		transactionHistory.setTransactionTypeCode("b");
		transactionHistory.setTransactionDate(new Date());
		transactionHistory.setReceipentAccount(0);
		transactionDto.setPassword(user.getPassword());
		transactionHistoryRepository.save(transactionHistory);
		return transactionHistory;
	}

	@Override
	public TransactionHistory transactionWithdrawl(TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findOne(transactionDto.getPengirimId());
		if (account == null) {
			return null;
		}

		User user = userRepository.findByClientId(account.getClientId());
		if (user == null
				|| !user.getPassword().equals(ShaDigest.get_SHA_1_SecurePassword(transactionDto.getPassword()))) {
			return null;
		}

		TransactionHistory transactionHistory = new TransactionHistory();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DDMMYYYY");

		double amountWithdrawl = account.getBalance().doubleValue() - transactionDto.getAmount().doubleValue();
		if (amountWithdrawl > account.getBalance().doubleValue()) {
			return null;
		}
		String padding = String.format(simpleDateFormat.format(new Date()))
				+ PaddingString.PAD_LEFT_ZEROS(findCountTransaction().toString(), 7);
		Long finalGeneratorTransactionId = Long.parseLong(padding);
		transactionHistory.setAmount(transactionDto.getAmount());
		account.setBalance(new BigDecimal(amountWithdrawl));
		transactionHistory.setUserAccount(account.getAccountId());
		transactionHistory.setTransactionId(finalGeneratorTransactionId);
		transactionHistory.setTransactionTypeCode("c");
		transactionHistory.setTransactionDate(new Date());
		transactionHistory.setReceipentAccount(0);
		transactionDto.setPassword(user.getPassword());
		transactionHistoryRepository.save(transactionHistory);
		return transactionHistory;
	}

	@Override
	public List<TransactionDto> getOneListTransaction(TransactionLoginDto transactionLoginDto) {
		// TODO Auto-generated method stub
		User user = userRepository.findOne(transactionLoginDto.getUsername());
		if (user == null
				|| !user.getPassword().equals(ShaDigest.get_SHA_1_SecurePassword(transactionLoginDto.getPassword()))) {
			return null;
		}

		List<TransactionHistory> transactions = transactionHistoryRepository
				.findAllTransactionByUserAccount(transactionLoginDto.getUserAccount());
		Client clientPengirim = clientRepository.findOneByClientId(user.getClientId());
		List<TransactionDto> listTransaction = new ArrayList<>();
		for (TransactionHistory transactionHistory : transactions) {

			TransactionType transactionType = transactionTypeRepository
					.findOneByTypeCode(transactionHistory.getTransactionTypeCode());
			TransactionDto trnDto = new TransactionDto();
			trnDto.setAmount(transactionHistory.getAmount());
			trnDto.setPengirimId(transactionHistory.getUserAccount());
			trnDto.setTransactionDate(transactionHistory.getTransactionDate());
			trnDto.setPenerimaId(transactionHistory.getReceipentAccount());
			trnDto.setCodeTypeName(transactionType.getTransactionTypeName());
			trnDto.setNamaClient(clientPengirim.getClientName());
			trnDto.setPassword(ShaDigest.get_SHA_1_SecurePassword(transactionLoginDto.getPassword()));

			listTransaction.add(trnDto);

		}

		return listTransaction;

	}

}
