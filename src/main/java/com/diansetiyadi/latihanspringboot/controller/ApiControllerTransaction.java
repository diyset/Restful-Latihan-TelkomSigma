package com.diansetiyadi.latihanspringboot.controller;


import java.util.Date;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diansetiyadi.latihanspringboot.dto.AccountDto;
import com.diansetiyadi.latihanspringboot.dto.AccountDtoLogin;

import com.diansetiyadi.latihanspringboot.dto.TransactionDto;
import com.diansetiyadi.latihanspringboot.dto.TransactionLoginDto;
import com.diansetiyadi.latihanspringboot.dto.UserDto;

import com.diansetiyadi.latihanspringboot.model.Account;
import com.diansetiyadi.latihanspringboot.model.Client;
import com.diansetiyadi.latihanspringboot.model.TransactionHistory;
import com.diansetiyadi.latihanspringboot.model.TransactionType;
import com.diansetiyadi.latihanspringboot.model.User;
import com.diansetiyadi.latihanspringboot.service.AccountService;
import com.diansetiyadi.latihanspringboot.service.ClientService;
import com.diansetiyadi.latihanspringboot.service.TransactionHistoryService;
import com.diansetiyadi.latihanspringboot.service.TransactionTypeService;
import com.diansetiyadi.latihanspringboot.service.UserService;
import com.diansetiyadi.latihanspringboot.util.ShaDigest;

@RestController
@RequestMapping("/api")
public class ApiControllerTransaction {

	@Autowired
	TransactionHistoryService transactionHistoryService;
	@Autowired
	AccountService accountService;
	@Autowired
	TransactionTypeService transactionTypeService;
	@Autowired
	UserService userService;
	@Autowired
	ClientService clientService;

	// Transaction History
	@GetMapping("/transactions")
	public List<TransactionHistory> getAllTransaction() {
		return transactionHistoryService.findAllTransactionHistory();
	}

	@PutMapping("/transactions")
	public TransactionHistory createTransaction(@Valid @RequestBody TransactionHistory transactionHistory) {

		transactionHistory = transactionHistoryService.saveTransaction(transactionHistory);
		return transactionHistory;
	}

	// Accounts
	@GetMapping("/accounts")
	public List<Account> getAllAccount() {
		return accountService.findAllAccounts();
	}

	@GetMapping("/accounts/{clientId}")
	public List<AccountDto> getAllAccountByClientId(@PathVariable("clientId") Long clientId) {
		return accountService.findAllAccountsByClientId(clientId);
	}

	@PutMapping("/accounts")
	public Account createAccount(@Valid @RequestBody Account account) {

		// account.setClientId((long) 1);
		return accountService.saveAccount(account);
	}

	// Create Account By Client Id PathVariable
	@PutMapping("/accounts/{clientId}")
	public Account createAccountFromClientId(@PathVariable("clientId") Long clientId,
			@Valid @RequestBody AccountDto accountDto) {
		Client client = clientService.findOneClientById(clientId);

		Account account = new Account();
		account.setBalance(accountDto.getBalance());
		account.setAccountDesc(accountDto.getAccountDesc());
		account.setCreatedOn(new Date());
		account.setClientId(client.getClientId());

		return accountService.saveAccount(account);
	}

	// Create Account Without Implements
	@PutMapping("/accounts/login")
	public ResponseEntity<AccountDtoLogin> createAccountFromUsernameAndPassword(
			@Valid @RequestBody AccountDtoLogin accountDtoLogin) {
		User user = userService.findOneUserByUsername(accountDtoLogin.getUsername());
		if (user == null
				|| !user.getPassword().equals(ShaDigest.get_SHA_1_SecurePassword(accountDtoLogin.getPassword()))) {
			return null;
		}
		Account account = new Account();
		account.setAccountDesc(accountDtoLogin.getAccountDesc());
		account.setClientId(user.getClientId());
		account.setBalance(accountDtoLogin.getBalance());
		account.setCreatedOn(new Date());
		accountService.saveAccount(account);
		return ResponseEntity.ok().body(accountDtoLogin);

	}

	// Create Account With Login Implements Clear
	@PutMapping("/accounts/loginv2")
	public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDtoLogin accountDtoLogin) {
		Account account = accountService.createAccountByLogin(accountDtoLogin);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(account);

	}

	@PutMapping("/transactiontype")
	public TransactionType createTransactionType(@Valid @RequestBody TransactionType transactionType) {

		return transactionTypeService.createTransactionType(transactionType);
	}

	@GetMapping("/transactiontype")
	public List<TransactionType> getAllTransactionType() {
		return transactionTypeService.findAllTransactionType();
	}

	@GetMapping("/transactiontype/{codeType}")
	public ResponseEntity<TransactionType> getTransactionType(@PathVariable(value = "codeType") String codeType) {
		TransactionType transactionType = transactionTypeService.findOneByCodeType(codeType);
		if (transactionType == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(transactionType);
	}

	// Client
	@GetMapping("/clients")
	public List<Client> getAllClient() {
		return clientService.findAllClients();
	}

	@PutMapping("/clients")
	public Client createClient(@Valid @RequestBody Client client) {
		return clientService.saveClient(client);
	}

	// User
	@GetMapping("/users")
	public List<User> getAllUser() {
		return userService.findAllUser();
	}

	// Create User
	@PutMapping("/users")
	public User createUser(@Valid @RequestBody User userDetails) {

		return userService.saveUserApi(userDetails);
	}

	// Update User
	@PutMapping("/users/{clientId}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "clientId") Long clientId,
			@Valid @RequestBody User userDetails) {
		User user = userService.findOneUserByClientId(clientId);
		if (user == null) {
			return ResponseEntity.notFound().build();

		}
		user.setPassword(ShaDigest.get_SHA_1_SecurePassword(userDetails.getPassword()));
		user.setUsername(userDetails.getUsername());

		User updatedUser = userService.saveUserApi(user);
		return ResponseEntity.ok(updatedUser);
	}

	// Client and User Dto
	@GetMapping("/clientsuser")
	public List<UserDto> getAllClientAndUser() {
		return clientService.findAllUserDto();
	}

	// Create Client and User Without Implements
	@PutMapping("/clientsuser")
	public UserDto createClientAndUser(@Valid @RequestBody UserDto userDtoDetails) {
		User user = new User();
		Client client = new Client();
		client.setAddress(userDtoDetails.getAddress());
		client.setClientName(userDtoDetails.getClientName());
		client.setRegisteredOn(new Date());
		clientService.saveClient(client);
		user.setUsername(userDtoDetails.getUsername());
		user.setPassword(ShaDigest.get_SHA_1_SecurePassword(userDtoDetails.getPassword()));
		user.setClientId(client.getClientId());
		userService.saveUser(user);

		return userDtoDetails;
	}

	// Create Client And User Implements Clear
	@PutMapping("/clientsuserv2")
	public ResponseEntity<User> createClientAndUserV2(@Valid @RequestBody UserDto userDto) {
		User user = userService.saveUserAndClient(userDto);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);

	}

	@PutMapping("/transactionTransfer")
	public ResponseEntity<TransactionHistory> transactionTransfer(@Valid @RequestBody TransactionDto transactionDto) {
		TransactionHistory transactionHistory = transactionHistoryService.transactionTransfer(transactionDto);
		if (transactionHistory == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(transactionHistory);
	}

	@PutMapping("/transactionDeposit")
	public ResponseEntity<TransactionHistory> transcationDeposit(@Valid @RequestBody TransactionDto transactionDto) {
		TransactionHistory transactionHistory = transactionHistoryService.transactionDeposit(transactionDto);
		if (transactionHistory == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(transactionHistory);
	}

	@PutMapping("/transactionWithdrawl")
	public ResponseEntity<TransactionHistory> transactionWithdrawl(@Valid @RequestBody TransactionDto transactionDto) {
		TransactionHistory transactionHistory = transactionHistoryService.transactionWithdrawl(transactionDto);
		if (transactionHistory == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(transactionHistory);
	}

	@PutMapping("/transactionsgetall")
	public ResponseEntity<List<TransactionDto>> transactionGetAllByAccountAndUser(
			@RequestBody TransactionLoginDto transactionLoginDto) {
		List<TransactionDto> transactionDto = transactionHistoryService.getOneListTransaction(transactionLoginDto);
		if (transactionDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(transactionDto);
	}

}
