package io.sl.ex.cassandra.spring;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

	/**
	 * Sample method annotated with {@link Query}. This method executes the CQL from the {@link Query} value.
	 *
	 * @param id
	 * @return
	 */
	@Query("SELECT * from users where user_id in(?0)")
	User findUserByIdIn(String id);

	/**
	 * Derived query method. This query corresponds with {@code SELECT * FROM users WHERE uname = ?0}.
	 * {@link User#username} is not part of the primary so it requires a secondary index.
	 *
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);

	/**
	 * Derived query method using SASI (SSTable Attached Secondary Index) features through the {@code LIKE} keyword. This
	 * query corresponds with {@code SELECT * FROM users WHERE lname LIKE '?0'}. {@link User#lastname} is not part of the
	 * primary key so it requires a secondary index.
	 *
	 * @param lastnamePrefix
	 * @return
	 */
	List<User> findUsersByLastnameStartsWith(String lastnamePrefix);
}