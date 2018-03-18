package br.challenge.skipthedishes.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.User;
import br.challenge.skipthedishes.repository.UserRepository;
import br.challenge.skipthedishes.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String login(String login, String password) throws SkiptheDishesException {
		User user = userRepository.findByLoginAndPassword(login, password);
		if (user == null) {
			throw new SkiptheDishesException("Invalid login or password.");
		}
		//save UUID on memory. Dont know how....
		return UUID.randomUUID().toString();
	}

}
