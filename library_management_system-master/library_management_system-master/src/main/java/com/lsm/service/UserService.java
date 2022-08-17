package com.lsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lsm.model.User;
import com.lsm.repository.UserRepository;

import ch.qos.logback.core.joran.action.ActionUtil.Scope;

@Service()
public class UserService implements UserRepository {
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String ADD_USER= "INSERT INTO user (id,name, email, password,role,about) VALUES (?,?,?,?,?,?)";
	private static final String GET_USER_BY_USERNAME = "SELECT * FROM user WHERE email=?";
	private static final String GET_ALL_USER = "SELECT * FROM user WHERE role='ROLE_USER'";
	private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id=?";
	private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id=?";
	private static final String UPDATE_USER = "UPDATE user SET name = ?,  about = ? WHERE id = ?";

	@Override
	public User addUser(User user) {
		int update = jdbcTemplate.update(ADD_USER, new Object[] {user.getId(),user.getName(),
															user.getEmail(),
															user.getPassword(),
															user.getRole(),
															user.getAbout()
														  });
		if(update == 1) {
			System.out.println("User inserted ..");
			return user;
		}
		return user;
	}

	@Override
	public User getUserByuserName(String email) {
		User retriveUser = jdbcTemplate.queryForObject(GET_USER_BY_USERNAME, 
				               new BeanPropertyRowMapper<User>(User.class), email);
		System.out.println("retrive user in service method--"+retriveUser);
		return retriveUser;
	}

	@Override
	public List<User> getAll() {
		
		List<User> users = jdbcTemplate.query(GET_ALL_USER,
				            new BeanPropertyRowMapper<User>(User.class));
		return users;
	}

	@Override
	public User getById(int id) {
		
		User retriveUser = jdbcTemplate.queryForObject(GET_USER_BY_ID, 
	                       new BeanPropertyRowMapper<User>(User.class), id);
		return retriveUser;
	}

	@Override
	public boolean deleteById(int id) {
		
		int update = jdbcTemplate.update(DELETE_USER_BY_ID, id);
		if(update==1)return true;
		return false;
	}

	@Override
	public boolean update(User e, int id) {
		
		int update = jdbcTemplate.update(UPDATE_USER, new Object[] {
				                  e.getName(), e.getAbout(), id});
		if(update==1)return true;
		return false;
	}

}
