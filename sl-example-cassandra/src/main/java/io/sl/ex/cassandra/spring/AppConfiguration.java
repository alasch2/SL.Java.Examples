package io.sl.ex.cassandra.spring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;

@Configuration
@EnableAutoConfiguration
public class AppConfiguration {

	@Configuration
	@EnableCassandraRepositories
	static class CassandraConfiguration extends AbstractCassandraConfiguration {

		@Override
		public SchemaAction getSchemaAction() {
			return SchemaAction.CREATE_IF_NOT_EXISTS;
		}

		@Bean
		public CassandraTemplate cassandraTemplate(Session session) {
			return new CassandraTemplate(session);
		}

		@Override
		protected String getContactPoints() {
			return "176.34.157.212";
		}		

		@Override
		protected int getPort() {
			return 9042;
		}

		@Override
		protected ProtocolVersion getProtocolVersion() {
			//return super.getProtocolVersion();
			return ProtocolVersion.V3;			
		}

		@Override
		public String[] getEntityBasePackages() {
			return new String[] { User.class.getPackage().getName() };
		}

		@Override
		protected String getKeyspaceName() {
			return "example";
		}

	}
}
