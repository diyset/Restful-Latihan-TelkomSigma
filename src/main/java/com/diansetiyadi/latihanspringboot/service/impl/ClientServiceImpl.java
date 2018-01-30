package com.diansetiyadi.latihanspringboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diansetiyadi.latihanspringboot.dto.UserDto;
import com.diansetiyadi.latihanspringboot.model.Client;
import com.diansetiyadi.latihanspringboot.model.User;
import com.diansetiyadi.latihanspringboot.repository.ClientRepository;
import com.diansetiyadi.latihanspringboot.repository.UserRepository;
import com.diansetiyadi.latihanspringboot.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public Client findOneClientById(Long clientId) {
		// TODO Auto-generated method stub
		return clientRepository.findOne(clientId);
	}

	@Override
	public Client saveClient(Client client) {
		// TODO Auto-generated method stub

		return clientRepository.save(client);
	}

	@Override
	public void deleteClient(Client client) {
		// TODO Auto-generated method stub
		clientRepository.delete(client);
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		clientRepository.save(client);
	}

	@Override
	public List<UserDto> findAllUserDto() {
		List<Object[]> listObject = clientRepository.findAllUserDtoObject();

		List<UserDto> userDtosList = new ArrayList<>();
		for (Object[] objects : listObject) {
			UserDto userDto = new UserDto();
			Client client = (Client) objects[0];
			User user = (User) objects[1];
			userDto.setAddress(client.getAddress());
			userDto.setClientName(client.getClientName());
			userDto.setUsername(user.getUsername());
			userDto.setPassword(user.getPassword());
			userDto.setClientId(client.getClientId());
			userDtosList.add(userDto);
		}

		return userDtosList;
	}

}
