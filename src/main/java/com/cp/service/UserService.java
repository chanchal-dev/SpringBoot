package com.cp.service;

import java.util.List;

import com.cp.model.Login;
import com.cp.model.User;

public interface UserService {

	
	public User saveUser(User user);
	public List<User> findAllUser();
	public List<User> findUserById(int id);
	public User updateUserById(User user);
	public int deleteUserById(int id);
	public Login findUserByNameandPassword(Login login);
	}
