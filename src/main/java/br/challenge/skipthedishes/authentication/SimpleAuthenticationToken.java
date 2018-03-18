package br.challenge.skipthedishes.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import br.challenge.skipthedishes.model.User;

public class SimpleAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private Collection<GrantedAuthority> authorities;

	private String authToken;

	private User user;

	public SimpleAuthenticationToken(User user) {
		super(user, "");
	}

	public String getAuthToken() {
		return authToken;
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public List<String> getAuthoritiesAsStringList() {
		List<String> result = new ArrayList<>();
		for (GrantedAuthority auth : authorities) {
			result.add(auth.getAuthority());
		}
		return result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
