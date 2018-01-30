package com.diansetiyadi.latihanspringboot.service;

import java.util.List;

import com.diansetiyadi.latihanspringboot.dto.UserDto;
import com.diansetiyadi.latihanspringboot.model.User;

public interface UserService {

	public List<User> findAllUser();
	
	public List<Object> findAllUserAndClient();

	public User findOneUserByUsername(String username);

	public User saveUser(User user);
	
	public User saveUserAndClient(UserDto userDto);

	public void updateUser(User user);

	
	public User saveUserApi(User user);
	
	public User findOneUserByClientId(Long clientId);
	
}
