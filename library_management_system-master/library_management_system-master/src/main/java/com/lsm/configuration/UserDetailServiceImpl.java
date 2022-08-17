package com.lsm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lsm.model.User;
import com.lsm.repository.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByuserName(username);

		System.out.println(user);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid User");
		}

		CustomUserDetails customUserDetails = new CustomUserDetails(user);

		return customUserDetails;
	}

}
