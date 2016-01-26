package se.arole.webapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import se.arole.api.controller.TeamController;
import se.arole.api.controller.UserController;
import se.arole.datalayer.service.TeamService;
import se.arole.datalayer.serviceImp.TeamServiceImpl;
import se.arole.datalayer.serviceImp.UserServiceImp;
import se.arole.webapi.resources.UserResource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("se.arole.datalayer.repository")
@EnableTransactionManagement
public class Config {

	@Bean
	public TeamController teamController() {
		return new TeamController();
	}

	@Bean
	public UserController userController() {
		return new UserController(userServce());
	}

	@Bean
	public TeamServiceImpl TeamService() {
		return new TeamServiceImpl();
	}

	@Bean
	public UserServiceImp userServce() {
		return new UserServiceImp();
	}

	// Data Source
	@Bean
	public DataSource dataSource() {

		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/arole");
		config.setUsername("root");
		config.setPassword("letmein");

		return new HikariDataSource(config);
	}

	// Transaction Manager
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

	// JPA Vendor
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setGenerateDdl(true);

		return adapter;
	}

	// Entity Manager Factory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setPackagesToScan("se.arole.datalayer");

		return factory;
	}

}
