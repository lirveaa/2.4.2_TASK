package org.java.web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "org.java.web")
@EnableTransactionManagement
public class JpaConfig {
    @Autowired
    private Environment env;

    @Bean("entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean en
                = new LocalContainerEntityManagerFactoryBean();
        //en.setPersistenceUnitName(env.getRequiredProperty("db.name"));

        en.setDataSource(dataSource());
        en.setPackagesToScan("org.java.web");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        en.setJpaVendorAdapter(vendorAdapter);
        en.setJpaProperties(getHibernateProperties());
        return en;
    }

    @Bean
    public JpaTransactionManager platformTransactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean
    public DataSource dataSource(){
        BasicDataSource db = new BasicDataSource();
        db.setUrl(env.getRequiredProperty("db.url"));
        db.setDriverClassName(env.getRequiredProperty("db.driver"));
        db.setUsername(env.getRequiredProperty("db.username"));
        db.setPassword(env.getRequiredProperty("db.password"));

        db.setInitialSize(Integer.parseInt((env.getRequiredProperty("db.initialSize"))));
        db.setMinIdle(Integer.parseInt(env.getRequiredProperty("db.minIdle")));
        db.setMaxIdle(Integer.parseInt(env.getRequiredProperty("db.maxIdle")));
        db.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        db.setMinEvictableIdleTimeMillis(Long.parseLong(env.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        db.setTestOnBorrow(Boolean.parseBoolean(env.getRequiredProperty("db.testOnBorrow")));
        db.setValidationQuery(env.getRequiredProperty("db.validationQuery"));

        return db;
    }

    public Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto","create");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }
}
