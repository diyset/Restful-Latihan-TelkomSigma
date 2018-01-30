package com.diansetiyadi.latihanspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diansetiyadi.latihanspringboot.model.TransactionType;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

	@Query("Select a from TransactionType a where a.transactionType = :typeCode")
	public TransactionType findOneByTypeCode(@Param("typeCode") String typeCode);

	@Query("select a,b from TransactionType a , TransactionHistory b where a.transactionType=b.transactionTypeCode")
	public List<Object[]> getTypeCodeNameObject();

}
