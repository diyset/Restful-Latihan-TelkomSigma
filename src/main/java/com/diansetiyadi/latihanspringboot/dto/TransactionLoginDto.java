package com.diansetiyadi.latihanspringboot.dto;

public class TransactionLoginDto {

	private String password;
	private String username;
	private Long userAccount;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Long userAccount) {
		this.userAccount = userAccount;
	}

}
