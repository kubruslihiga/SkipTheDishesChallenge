package br.challenge.skipthedishes.service;

import br.challenge.skipthedishes.exception.SkiptheDishesException;

public interface LoginService {

	String login(String login, String password) throws SkiptheDishesException;
}
