package com.test.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.test.business.dao.ds2", 
    entityManagerFactoryRef = "emf2", 
    transactionManagerRef = "tm2"
)
public class DS2Config {
	
	@Value("${spring.ds2.hibernate.dialect}")
    private String dialect;
	
//	@Primary
	@Bean(name = "ds2")
	@ConfigurationProperties(prefix = "spring.ds2")
	DataSource ds() {
		return DataSourceBuilder.create().build();
	}
	
//	@Primary
	@Bean(name = "jdbc2")
	public JdbcTemplate mssqlJdbcTemplate(@Qualifier("ds2") DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
//	@Primary
	@PersistenceContext(unitName = "pu2")
    @Bean(name = "emf2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    		EntityManagerFactoryBuilder builder,
    		@Qualifier("ds2") DataSource ds) {
		
		Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
		
        LocalContainerEntityManagerFactoryBean emf = builder
                .dataSource(ds)
                .packages("com.test.business.model.ds2")
                .persistenceUnit("pu2")
                .build();
        
        emf.setJpaProperties(properties);
        
        return emf;
    }
	
//	@Primary
    @Bean(name = "tm2")
    public PlatformTransactionManager transactionManager(
    		@Qualifier("emf2") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
