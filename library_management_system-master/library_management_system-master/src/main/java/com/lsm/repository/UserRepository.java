package com.lsm.repository;


import java.util.List;

import com.lsm.model.User;




public interface UserRepository {
	
	public User addUser(User user);
	
	public User getUserByuserName(String email);
	
    public List<User> getAll();
	
	public User getById(int id);
	
	public boolean deleteById(int id);
	
	public boolean update(User e, int id);
	

}
