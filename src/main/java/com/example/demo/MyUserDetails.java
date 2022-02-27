package com.example.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// my custom user details model that implements the actual/default user details model
public class MyUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	// declaring property based on my user model which will be used in DB
	private String username;
	private String password;
	private boolean isActive;
	private List<? extends GrantedAuthority> authorities;

	public MyUserDetails(UserModel user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.isActive = user.isActive();
		
		// getting the roles string from UserModel and making an array by splitting it using comma(,)
		// then making a new SimpleGrantedAuthoritiy instance using that role
		// then collecting those roles to list
		this.authorities = Arrays.stream(user.getRoles().split(","))
								.map(SimpleGrantedAuthority::new)
								.collect(Collectors.toList());
	}
	
	public MyUserDetails() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}
	
	
}
