package com.cp.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.Counter;

import com.cp.model.Login;
import com.cp.model.User;
import com.cp.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {

		User user1 = userRepository.saveUser(user);
		return user1;

	}

	@Override
	public List<User> findAllUser() {
		List<User> user = userRepository.findAllUser();
		return user;

	}

	@Override
	public List<User> findUserById(int id) {
		List<User> user1 = userRepository.findUserById(id);
		return user1;

	}

	@Override
	public int deleteUserById(int id) {
		return userRepository.deleteUserById(id);

	}

	@Override
	public User updateUserById(User user) {
		User user1 = userRepository.updateUserIdBy(user);
		return user1;

	}

	@Override
	public Login findUserByNameandPassword(Login login) {
		 
	Login login1 = userRepository.findUserByNameandPassword(login);
	return login1;
	
	}

	
	

}
