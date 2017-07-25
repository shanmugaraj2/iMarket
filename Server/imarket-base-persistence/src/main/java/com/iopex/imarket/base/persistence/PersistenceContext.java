package com.iopex.imarket.base.persistence;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.iopex")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.iopex")
@EnableScheduling
@EnableCaching

public class PersistenceContext {
	
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_DATABASE_MAX_POOL_SIZE = "db.maxPoolSize";
	private static final String PROPERTY_NAME_DATABASE_MIN_POOL_SIZE = "db.minPoolSize";
	private static final String PROPERTY_NAME_DATABASE_ACQUIRE_INCREMENT = "db.acquireIncrement";
	private static final String PROPERTY_NAME_DATABASE_INITIAL_POOL_SIZE = "db.initialPoolSize";
	private static final String PROPERTY_NAME_DATABASE_CONNECTION_TIMEOUT = "db.connectionTimeOut";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_ENVERS_CATALOG = "org.hibernate.envers.default_catalog";

	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";


	@Resource
	private Environment env;

	/**
	 * use DriverManagerDataSource  build for MySql,
	 * ComboPooledDataSource  for Oracle
	 * @return
	 * @throws IllegalStateException
	 * @throws PropertyVetoException
	 */
	@Bean
	public DataSource dataSource() throws IllegalStateException, PropertyVetoException {
		
		if(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER).contains("mysql")){
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
			dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
			dataSource.setUsername(env
					.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
			dataSource.setPassword(env
					.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
			return dataSource;
		} else if(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER).contains("oracle")){
			ComboPooledDataSource dataSource = new ComboPooledDataSource();

			dataSource.setDriverClass(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
			dataSource.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
			dataSource.setUser(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
			dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

			dataSource.setMaxPoolSize(Integer.valueOf(env.getRequiredProperty(PROPERTY_NAME_DATABASE_MAX_POOL_SIZE)));
			dataSource.setMinPoolSize(Integer.valueOf(env.getRequiredProperty(PROPERTY_NAME_DATABASE_MIN_POOL_SIZE)));
			dataSource.setAcquireIncrement(Integer.valueOf(env.getRequiredProperty(PROPERTY_NAME_DATABASE_ACQUIRE_INCREMENT)));
			dataSource.setInitialPoolSize(Integer.valueOf(env.getRequiredProperty(PROPERTY_NAME_DATABASE_INITIAL_POOL_SIZE)));
			dataSource.setCheckoutTimeout(Integer.valueOf(env.getRequiredProperty(PROPERTY_NAME_DATABASE_CONNECTION_TIMEOUT)));
			
			dataSource.setAutoCommitOnClose(Boolean.TRUE);
			return dataSource;
		} else {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			return dataSource;
		}

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IllegalStateException, PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass((Class<? extends PersistenceProvider>) HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		
		entityManagerFactoryBean.setJpaProperties(hibProperties());

		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,	env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_ENVERS_CATALOG, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_ENVERS_CATALOG));

		return properties;
	}


	@Bean
	public JpaTransactionManager transactionManager() throws IllegalStateException, PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager();
	}

}
