package io.sl.ex.cassandra.spring;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@SpringBootApplication
//@EnableAutoConfiguration
public class CassandraApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CassandraApplication.class, args);

		useCassandraDirectly();

		LOGGER.info("@@@@ STARTED @@@@");
	}

	protected static User newUser(String uname, String fname, String lname) {
		return newUser(UUID.randomUUID(), uname, fname, lname);
	}

	protected static User newUser(UUID id, String uname, String fname, String lname) {
		return new User(id, uname, fname, lname);
	}

	protected static void useCassandraDirectly() {
		Cluster cluster = Cluster.builder().addContactPoints("176.34.157.212").withPort(9042).build();
		Session session = cluster.connect("example");

//		CassandraOperations template = new CassandraTemplate(session);
//
//		User jonDoe = newUser("Jon Doe", "Jon", "Doe");
//		template.insert(jonDoe);
//
//		LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getId());
//
//		template.truncate(User.class);
		session.close();
		cluster.close();		  
	}
}


