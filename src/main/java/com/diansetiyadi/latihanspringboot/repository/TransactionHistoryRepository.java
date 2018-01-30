package com.diansetiyadi.latihanspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diansetiyadi.latihanspringboot.model.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

	@Query("select a from TransactionHistory a where a.userAccount = :userAccount")
	public List<TransactionHistory> findAllTransactionByUserAccount(@Param("userAccount") Long userAccount);
}
