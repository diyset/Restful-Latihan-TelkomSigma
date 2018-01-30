package com.diansetiyadi.latihanspringboot.dto;

import java.math.BigDecimal;
import java.util.Date;

public class HistoryTransactionDto {

	private String transactionType;
	private Date transactionDate;
	private BigDecimal amount;
	private Long userAccount;
	private long receipentAccount;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Long userAccount) {
		this.userAccount = userAccount;
	}

	public long getReceipentAccount() {
		return receipentAccount;
	}

	public void setReceipentAccount(long receipentAccount) {
		this.receipentAccount = receipentAccount;
	}

}
