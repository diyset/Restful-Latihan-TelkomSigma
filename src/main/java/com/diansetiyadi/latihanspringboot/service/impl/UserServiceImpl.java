package com.diansetiyadi.latihanspringboot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diansetiyadi.latihanspringboot.dto.UserDto;
import com.diansetiyadi.latihanspringboot.model.Client;
import com.diansetiyadi.latihanspringboot.model.User;
import com.diansetiyadi.latihanspringboot.repository.ClientRepository;
import com.diansetiyadi.latihanspringboot.repository.UserRepository;
import com.diansetiyadi.latihanspringboot.service.UserService;
import com.diansetiyadi.latihanspringboot.util.ShaDigest;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		List<User> listUser = userRepository.findAll();
		return listUser;
	}

	@Override
	public User findOneUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public List<Object> findAllUserAndClient() {
		// TODO Auto-generated method stub

		return findAllUserAndClient();
	}

	@Override
	public User saveUserApi(User user) {
		// TODO Auto-generated method stub

		user.setPassword(ShaDigest.get_SHA_1_SecurePassword(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findOneUserByClientId(Long clientId) {
		// TODO Auto-generated method stub
		return userRepository.findByClientId(clientId);
	}

	@Override
	public User saveUserAndClient(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = new User();
		Client client = new Client();
		client.setAddress(userDto.getAddress());
		client.setClientName(userDto.getClientName());
		client.setRegisteredOn(new Date());
		clientRepository.save(client);
		user.setUsername(userDto.getUsername());
		user.setPassword(ShaDigest.get_SHA_1_SecurePassword(userDto.getPassword()));
		user.setClientId(client.getClientId());
		userRepository.save(user);

		return user;
	}

}
