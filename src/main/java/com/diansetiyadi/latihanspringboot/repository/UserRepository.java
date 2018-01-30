package com.diansetiyadi.latihanspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diansetiyadi.latihanspringboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("select a from User a where a.username = :username")
	public User findByUserName(@Param("username") String username);

	public User findByUsername(String username);

	@Query("select a from User a where a.clientId=:clientId")
	public User findByClientId(@Param("clientId") Long clientId);
	


}
