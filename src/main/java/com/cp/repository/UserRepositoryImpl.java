package com.cp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cp.model.Login;
import com.cp.model.User;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User saveUser(User user) {

		String sql = "insert into register(id, firstname, lastname, username,  password, contact) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {user.getId(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword(),user.getPassword()});
		
		return user;
	}

	@Override
	public List<User> findAllUser() {

		String sql = "select * from register";

		return jdbcTemplate.query(sql, new UserMapper());

	}

	private static final class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			
			user.setId(rs.getInt("id"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setContact(rs.getString("contact"));

		return user;

	}
	}

	@Override
	public List<User> findUserById(int id) {
		String sql = "select id, firstname, lastname, username, password, contact from register where id = ?";
		return jdbcTemplate.query(sql, new UserMapper(), id);

	}

	@Override
	public int deleteUserById(int id) {
		String sql = "delete from register where id = ?";
		return jdbcTemplate.update(sql, new Object[] { id });

	}

	@Override
	public User updateUserIdBy(User user) {
		String sql = "update register set firstname = ?, lastname = ?, username = ?, password = ?, contact = ?  where id = ?";
		jdbcTemplate.update(sql, new Object[] { user.getFirstname(), user.getLastname(), user.getUsername(),
				user.getPassword(), user.getContact(), user.getId() });
		System.out.println("data successfully updated");
		return user;
	}

	@Override
	public Login findUserByNameandPassword(Login login) {
	
		String sql = "select * from register where username = '" + login.getUsername()+"' and password = '" + login.getPassword()+"' ";
		jdbcTemplate.query(sql, new UserMapper());
		return login;
		
		
	}

	

	

}
