package com.diansetiyadi.latihanspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diansetiyadi.latihanspringboot.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("select a,b from Client a , User b where a.clientId = b.clientId")
	public List<Object[]> findAllUserDtoObject();

	@Query("select a from Client a where a.clientId = :clientId")
	public Client findOneByClientId(@Param("clientId") Long clientId);

	@Query("select a from Client a where a.clientId= :clientId")
	public List<Client> findAllClients(@Param("clientId") Long clientId);
}
