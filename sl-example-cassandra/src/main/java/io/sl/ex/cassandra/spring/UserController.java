package io.sl.ex.cassandra.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.sl.ex.cassandra.spring.services.UserService;

@Controller
public class UserController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/users")
	public String listUsers() {
		return userService.getAll().toString();
	}
	

}
