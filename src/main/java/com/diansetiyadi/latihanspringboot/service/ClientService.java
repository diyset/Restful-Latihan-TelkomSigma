package com.diansetiyadi.latihanspringboot.service;

import java.util.List;

import com.diansetiyadi.latihanspringboot.dto.UserDto;
import com.diansetiyadi.latihanspringboot.model.Client;

public interface ClientService {

	public List<Client> findAllClients();
	
	public Client findOneClientById(Long clientId);
	
	public Client saveClient(Client client);
	
	public void deleteClient(Client client);
	
	public void updateClient(Client client);
	
	public List<UserDto> findAllUserDto();
	
}
