
package com.cp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cp.model.Login;
import com.cp.model.User;
import com.cp.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

//-------------------------------savedata--------------------------------

//---------------------------------getall-------------------------------
	@RequestMapping(value = "/getAll/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAllUser() {
		List<User> user = userService.findAllUser();
		if (user.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);

	}

//------------------------------------getdatabyid-----------------------
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<User>> findUserById(@PathVariable("id") int id, @RequestBody User user) {
		List<User> user1 = userService.findUserById(id);
		if (user1 == null) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<List<User>>(user1, HttpStatus.OK);

	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUserById(@PathVariable("id") int id, @RequestBody User user) {
		user.setId(id);
		User user1 = userService.updateUserById(user);

		if (user1 == null) {

			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		user1.setId(user.getId());
		user1.setFirstname(user.getFirstname());
		user1.setLastname(user.getLastname());
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		user1.setContact(user.getContact());

		return new ResponseEntity<User>(user1, HttpStatus.OK);

	}

//-------------------------------deleteuserbyid----------------------

	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUserById(@PathVariable("id") int id) {

		List<User> user1 = userService.findUserById(id);
		if (user1 == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		}

		userService.deleteUserById(id);

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

	}

//---------------------------register---------------------------------------
	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);

		System.out.println("user created" + user);

		if (user1 != null) {
			user1.setFirstname(user.getFirstname());
			user1.setLastname(user.getLastname());
			user1.setUsername(user.getUsername());
			user1.setPassword(user.getPassword());
			user1.setContact(user.getContact());

		}

		return new ResponseEntity<User>(user1, HttpStatus.CREATED);

	}

//----------------------------login----------------------------------
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<Login> findUserByNameandPassword(@RequestBody Login login) {
		Login login1 = userService.findUserByNameandPassword(login);

		if (login1 != null) {
			login.setUsername(login.getUsername());
			login.setPassword(login.getPassword());

		}

		return new ResponseEntity<Login>(login1, HttpStatus.OK);

	}

}
