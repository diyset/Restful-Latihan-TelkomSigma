package com.diansetiyadi.latihanspringboot.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {

	private String password;
	private Long pengirimId;
	private Long penerimaId;
	private BigDecimal amount;
	private String codeTypeName;
	private String namaClient;
	private Date transactionDate;

	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}

	public Long getPengirimId() {
		return pengirimId;
	}

	public void setPengirimId(Long pengirimId) {
		this.pengirimId = pengirimId;
	}

	public Long getPenerimaId() {
		return penerimaId;
	}

	public void setPenerimaId(Long penerimaId) {
		this.penerimaId = penerimaId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getNamaClient() {
		return namaClient;
	}

	public void setNamaClient(String namaClient) {
		this.namaClient = namaClient;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
