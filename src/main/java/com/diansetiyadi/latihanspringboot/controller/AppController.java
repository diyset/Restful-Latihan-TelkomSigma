package com.diansetiyadi.latihanspringboot.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.diansetiyadi.latihanspringboot.dto.UserDto;
import com.diansetiyadi.latihanspringboot.model.Client;
import com.diansetiyadi.latihanspringboot.model.User;
import com.diansetiyadi.latihanspringboot.service.ClientService;
import com.diansetiyadi.latihanspringboot.service.UserService;
import com.diansetiyadi.latihanspringboot.util.ShaDigest;
import com.diansetiyadi.latihanspringboot.validator.UserValidator;

@Controller
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	ClientService clientService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping("/")
	public String indexPrimary() {
		return "Index";
	}

	// @RequestMapping(value = { "/index" })
	// public String index(Model model) {
	// return "Index";
	// }

	@RequestMapping(value = { "/login" })
	public String loginForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("formlogin", userDto);
		return "login";
	}

	@RequestMapping(value = { "/logincheck" })
	public String login(HttpSession session, @ModelAttribute("formlogin") UserDto userDto, Model model) {
		System.out.println(userDto.getUsername());
		User userCheck = userService.findOneUserByUsername(userDto.getUsername());
		if (userCheck == null) {
			model.addAttribute("message", "Username Tidak Ada");
			return "redirect:/login";

		}
		if (!userCheck.getPassword().equals(ShaDigest.get_SHA_1_SecurePassword(userDto.getPassword()))) {
			model.addAttribute("message", "Password anda salah");
			return "redirect:/login";
		}
		session.setAttribute("userSession", userCheck);
		return "redirect:/";
	}

	@RequestMapping(value = { "/register" })
	public String register(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("formregis", userDto);
		return "register";
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public String register(@ModelAttribute("formregis") UserDto userDto, Model model) {
		// Object Client New
		// Object User New
		User userCheck = userService.findOneUserByUsername(userDto.getUsername());
		if (userCheck != null) {
			model.addAttribute("message", "Sudah Ada");
			return "register";
		}
		Client client = new Client();
		User user = new User();
		client.setRegisteredOn(new Date());
		client.setAddress(userDto.getAddress());
		client.setClientName(userDto.getClientName());
		clientService.saveClient(client);

		user.setPassword(ShaDigest.get_SHA_1_SecurePassword(userDto.getPassword()));
		user.setUsername(userDto.getUsername());
		user.setClientId(client.getClientId());

		userService.saveUser(user);

		return "Index";
	}

	// User All
	@RequestMapping(value = { "/user/all" })
	public String user(Model model) {
		// User user = (User) userService.findAllUsers();

		List<User> users = userService.findAllUser();

		model.addAttribute("users", users);
		return "user";
	}

	@RequestMapping(value = { "/edit" })
	public String editUser(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("formedit", userDto);
		return "edituser";
	}

	@RequestMapping(value = { "/edit/{id}" })
	public String editUserValidation(@ModelAttribute("formedit") UserDto userDto, @PathVariable("id") long id,
			BindingResult result, Model model) {
		User userCheck = userService.findOneUserByUsername(userDto.getUsername());
		Client clientCheck = clientService.findOneClientById(userCheck.getClientId());
		if (userCheck == null) {
			model.addAttribute("message", "Id User Tidak Ditemukan");
			return "redirect:/user/all";
		}

		model.addAttribute("user", userCheck);
		model.addAttribute("client", clientCheck);

		return "edituser";
	}

	@RequestMapping(value = { "/user/{clientId}" })
	public String userById(Model model, @PathVariable("clientId") long clientId) {
		User user = userService.findOneUserByClientId(clientId);
		model.addAttribute("user", user);
		return "oneuser";
	}

}
