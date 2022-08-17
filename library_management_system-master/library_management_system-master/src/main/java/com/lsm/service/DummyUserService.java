package com.lsm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lsm.model.Book;
import com.lsm.model.User;
import com.lsm.repository.UserRepository;


public class DummyUserService implements UserRepository {

	public List<User> users = new ArrayList<User>(
			Arrays.asList(
							new User(1, "user1", "user1@mail", "user1password", "librarian", "user1 about"),
							new User(2, "user2", "user2@mail", "user2password", "librarian", "user2 about"),
							new User(3, "user3", "user3@mail", "user3password", "librarian", "user3 about"),
							new User(4, "user4", "user4@mail", "user4password", "librarian", "user4 about"),
							new User(5, "user5", "user5@mail", "user5password", "librarian", "user5 about"))
						);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User addUser(User u1) {

		if (users.add(u1))
			return u1;
		return null;
	}

	@Override
	public User getUserByuserName(String name) {
		Optional<User> matchingObject = users.stream().filter(p -> p.getEmail().equals(name)).findFirst();
		User user = matchingObject.get();
		if (user != null)
			return user;
		return null;
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public User getById(int id) {
		Optional<User> matchingObject = users.stream().filter(p -> p.getId() == id).findFirst();
		User user = matchingObject.get();
		System.out.println(user);
		if (user != null)
			return user;
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		return users.removeIf(e -> e.getId() == id);
	}

	@Override
	public boolean update(User e, int id) {
		System.out.println(e);
		System.out.println("user------------------------------------------------------- id" + id);
		User b = getById(id);

		if (b != null) {
			b.setId(e.getId());
			b.setName(e.getName());
			b.setEmail(e.getEmail());
			b.setAbout(e.getAbout());
			b.setPassword(e.getPassword());
			b.setRole(e.getRole());

			users.set(users.indexOf(b), b);
			return true;
		}
		return false;
	}

}
