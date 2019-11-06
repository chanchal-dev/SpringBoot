package com.cp.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cp.model.Login;
import com.cp.model.User;
import com.cp.repository.UserRepository;
import com.cp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootExampleApplicationTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void findAllUserTest()
	{
	
		
		when(userRepository.findAllUser()).thenReturn(Stream.of(new User(5,"chinki", "joshi","chinkijoshi@123", "chinki@12", "7896542310")).collect(Collectors.toList()));
		assertEquals(1, userService.findAllUser().size());
		
		
	
	}
	
	@Test
	public void findUserByIdTest()
	{
	int id = 1;
		when(userRepository.findUserById(id)).thenReturn( Stream.of(new User(1,"kaju", "agrwal","kajuagrwal@123", "kaju@12", "7896542310")).collect(Collectors.toList()));
		assertEquals(1, userService.findUserById(id).size());
		
	}
	
	@Test
	public void saveUserTest()
	{
		User user = new User(3, "sonu", "sharma", "sonusharma@123", "sonu@123", "6789054321");
		when(userRepository.saveUser(user)).thenReturn(user);
		assertEquals(user, userService.saveUser(user));
		
		
	}
	
	@Test 
	public void updateUserByIdTest()
	{
		User user = new User(3, "pankaj", "tomer", "pankajtomer@123", "pankaj@123", "9874561230");
		when(userRepository.updateUserIdBy(user)).thenReturn(user);
		assertEquals(user, userService.updateUserById(user));
	}
	
	
	@Test
	public void deleteUserByIdTest()
	{
		int id = 3;
		
		when(userRepository.deleteUserById(id)).thenReturn(id);
		assertEquals(3, userService.deleteUserById(id));
		
	}
	
	
	@Test
	public void findUserByNameandPassword()
	{
		Login login = new Login("chanchal", "chinki@345");
		when(userRepository.findUserByNameandPassword(login)).thenReturn(login);
		assertEquals(login, userService.findUserByNameandPassword(login));
	}
	
	
}
