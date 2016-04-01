package com.stockontrol.application.configuration;

import java.util.Properties;

import javax.sql.DataSource;
import javax.validation.ValidatorFactory;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableJpaRepositories("com.stockontrol.domain.repository")
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class JpaConfig
{
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JpaTransactionManager transactionManager()
	{
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(entityManagerFactory().getObject());
		return jtm;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		Properties props = new Properties();
		props.setProperty("org.hibernate.envers.store_data_at_delete", "true");
		emf.setJpaProperties(props);
		
		return emf;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter()
	{
		HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();
		jva.setShowSql(true);
		return jva;
	}
	
	@Bean
	public ValidatorFactory validatorFactory()
	{
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public Flyway flyway()
	{
		Flyway f = new Flyway();
		f.setBaselineOnMigrate(false);
		f.setLocations("classpath:/db/migrate");
		f.setDataSource(dataSource);
		f.migrate();
		return f;
	}
}
