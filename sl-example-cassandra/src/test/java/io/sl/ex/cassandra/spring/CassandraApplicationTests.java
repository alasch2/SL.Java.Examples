package io.sl.ex.cassandra.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.sl.ex.cassandra.spring.CassandraApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CassandraApplication.class)
public class CassandraApplicationTests {

	@Test
	public void contextLoads() {
	}

}
