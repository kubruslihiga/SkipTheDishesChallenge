package br.challenge.skipthedishes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.challenge.skipthedishes.model.User;
import br.challenge.skipthedishes.repository.UserRepository;
import br.challenge.skipthedishes.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

//	@Override
//	public void saveUser(UserSample user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(1);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//		userRepository.save(user);
//	}

}
