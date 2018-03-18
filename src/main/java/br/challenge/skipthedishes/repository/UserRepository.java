package br.challenge.skipthedishes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.challenge.skipthedishes.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByLogin(String login);
	 User findByLoginAndPassword(String login, String password);
}
