package io.sl.ex.cassandra.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("/")
	public String index() {
		LOGGER.info("Invoked index()");
		return "SPring boot-cassandra application is running!!";
	}

	@RequestMapping("/hello")
	public String hello() {
		LOGGER.info("Invoked hello()");
		return "Pressed hello :)";
	}

}