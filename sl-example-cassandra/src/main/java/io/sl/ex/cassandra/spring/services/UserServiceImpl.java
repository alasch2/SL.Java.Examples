package io.sl.ex.cassandra.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sl.ex.cassandra.spring.User;
import io.sl.ex.cassandra.spring.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		//userRepository.findAll().forEach(users::add);
		for (User u: userRepository.findAll()) {
			users.add(u);
		}
		return users;
	}

	@Override
	public User getById(String id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User saveOrUpdate(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);		
	}

}
