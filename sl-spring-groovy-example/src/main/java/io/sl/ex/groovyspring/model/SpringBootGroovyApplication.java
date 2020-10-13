package io.sl.ex.groovyspring.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"io.sl.ex.groovyspring.controller", "io.sl.ex.groovyspring.component"})
public class SpringBootGroovyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGroovyApplication.class, args);
	}

}
