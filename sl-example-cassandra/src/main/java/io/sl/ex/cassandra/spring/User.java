package io.sl.ex.cassandra.spring;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "users")
public class User {

	@PrimaryKey("user_id")
	private String id;

	@Column("uname") private String username;
	@Column("fname") private String firstname;
	@Column("lname") private String lastname;
	
	public User() {
		id = UUID.randomUUID().toString();
	}

	public User(UUID id, String username, String firstname, String lastname) {
		setId(id.toString());
		setUsername(username);
		setFirstname(firstname);
		setLastname(lastname);
	}
}