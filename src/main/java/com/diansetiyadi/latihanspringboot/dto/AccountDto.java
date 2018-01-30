package com.diansetiyadi.latihanspringboot.dto;

import java.math.BigDecimal;

public class AccountDto {
	private String accountDesc;
	private BigDecimal balance;

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	
}
