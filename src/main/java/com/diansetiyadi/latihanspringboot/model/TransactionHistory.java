package com.diansetiyadi.latihanspringboot.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long transactionId;
	private String transactionTypeCode;
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	private BigDecimal amount;
	private Long userAccount;
	private long receipentAccount;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
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
