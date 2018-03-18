package br.challenge.skipthedishes.service;

import br.challenge.skipthedishes.model.User;

public interface UserService {
	public User findUserByLogin(String login);
}
