package com.diansetiyadi.latihanspringboot.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diansetiyadi.latihanspringboot.dto.AccountDto;
import com.diansetiyadi.latihanspringboot.dto.AccountDtoLogin;
import com.diansetiyadi.latihanspringboot.model.Account;
import com.diansetiyadi.latihanspringboot.model.User;
import com.diansetiyadi.latihanspringboot.repository.AccountRepository;
import com.diansetiyadi.latihanspringboot.repository.UserRepository;
import com.diansetiyadi.latihanspringboot.service.AccountService;
import com.diansetiyadi.latihanspringboot.util.ShaDigest;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public Account findOneAccountById(Long accountId) {
		// TODO Auto-generated method stub
		return accountRepository.findOne(accountId);
	}

	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		accountRepository.delete(account);
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		accountRepository.save(account);
	}

	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMYYYY");

		Long increments = accountRepository.count();
		increments += 1;
		String padding = String.format(simpleDateFormat.format(new Date()))
				+ padLeftZeros(findCountAccount().toString(), 4);
		String dateFormat = simpleDateFormat.format(new Date());
		Long generatedAccountId = Long.parseLong(simpleDateFormat.format(new Date()));
		Long finalGeneratorAccountId = Long.parseLong(padding);

		account.setAccountId(finalGeneratorAccountId);

		return accountRepository.save(account);
	}

	@Override
	public Account saveAccountObject(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] saveAccountObjects(Account account) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<AccountDto> findAllAccountsByClientId(Long clientId) {
		// TODO Auto-generated method stub
		List<Account> listAccount = accountRepository.findOneAccountByIdClient(clientId);
		List<AccountDto> listAccountDto = new ArrayList<>();
		for (Account objects : listAccount) {

			AccountDto accountDto = new AccountDto();
			accountDto.setAccountDesc(objects.getAccountDesc());
			accountDto.setBalance(objects.getBalance());
			listAccountDto.add(accountDto);
		}

		return listAccountDto;
	}

	private static String padLeftZeros(String str, int n) {
		return String.format("%1$" + n + "s", str).replace(' ', '0');
	}

	@Override
	public Long findCountAccount() {
		// TODO Auto-generated method stub

		return accountRepository.count() + 1;
	}

	@Override
	public Account createAccountByLogin(AccountDtoLogin accountDtoLogin) {
		// TODO Auto-generated method stub
		User user = userRepository.findOne(accountDtoLogin.getUsername());
		if (user == null
				|| !user.getPassword().equals(ShaDigest.get_SHA_1_SecurePassword(accountDtoLogin.getPassword()))) {
			return null;
		}
		Account account = new Account();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMYYYY");

		Long increments = accountRepository.count();
		increments += 1;
		String padding = String.format(simpleDateFormat.format(new Date()))
				+ padLeftZeros(findCountAccount().toString(), 4);

		Long finalGeneratorAccountId = Long.parseLong(padding);

		account.setAccountId(finalGeneratorAccountId);

		account.setAccountDesc(accountDtoLogin.getAccountDesc());
		account.setClientId(user.getClientId());
		account.setBalance(accountDtoLogin.getBalance());
		account.setCreatedOn(new Date());
		accountRepository.save(account);
		return account;
	}
}
