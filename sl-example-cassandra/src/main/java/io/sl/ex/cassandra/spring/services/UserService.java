package io.sl.ex.cassandra.spring.services;

import java.util.List;

import io.sl.ex.cassandra.spring.User;

public interface UserService {
	List<User> getAll();
	User getById(String id);
	User saveOrUpdate(User user);
	void delete(String id);	
}
