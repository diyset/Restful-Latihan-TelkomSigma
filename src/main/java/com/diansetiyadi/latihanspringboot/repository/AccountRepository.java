package com.diansetiyadi.latihanspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diansetiyadi.latihanspringboot.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("Select a,b.clientName from Account a , Client b where a.clientId = b.clientId")
	public List<Object[]> findAllAccountByIdClientId();

	@Query("Select a,b from Account a , Client b where a.clientId = b.clientId")
	public Object[] findAccountByIdClientId();

	@Query("select a from Account a where a.clientId = :clientId")
	public List<Account> findOneAccountByIdClient(@Param("clientId") Long clientId);

	@Query("select a from Account a where a.accountId =:accountId")
	public Account findOneAccountByAccountId(@Param("accountId") Long accountId);

	@Query("select a from Account a where a.accountId = :accountId")
	public List<Account> findAllAccountByAccountId(@Param("accountId") Long accountId);

}
