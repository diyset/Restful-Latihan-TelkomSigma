package com.diansetiyadi.latihanspringboot.service;

import java.util.List;

import com.diansetiyadi.latihanspringboot.dto.AccountDto;
import com.diansetiyadi.latihanspringboot.dto.AccountDtoLogin;
import com.diansetiyadi.latihanspringboot.model.*;

public interface AccountService {
	
	public List<Account> findAllAccounts();
	
	public Account findOneAccountById(Long accountId);
	
	public void deleteAccount(Account account);
	
	public void updateAccount(Account account);
	
	public Account saveAccount(Account account);
	
	public Account saveAccountObject(Account account);

	public Object[] saveAccountObjects(Account account);
	
	public List<AccountDto> findAllAccountsByClientId(Long clientId);
	
	public Long findCountAccount();
	
	public Account createAccountByLogin(AccountDtoLogin accountDtoLogin);
}
