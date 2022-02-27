package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// this is the custom user details service that implements the actual and default details service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	// injecting user repository for getting user by username
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// getting user by username
		Optional<UserModel> user = userRepo.findByUsername(username);
		
		// if user not found then throws an exception
		user.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
		
		// returns a user details object based on my custom user details model
		return user.map(MyUserDetails::new).get();
	}
	
}
