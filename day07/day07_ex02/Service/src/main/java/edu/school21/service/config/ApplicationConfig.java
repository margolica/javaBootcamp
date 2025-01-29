package edu.school21.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class ApplicationConfig {

    Properties properties = new Properties();


    @Bean
    public HikariDataSource hikariDataSource() throws IOException {
        properties.load(new FileInputStream("src/ex02/Service/src/main/resources/db.properties"));
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(properties.getProperty("db.url"));
        hikariDataSource.setUsername(properties.getProperty("db.user"));
        hikariDataSource.setPassword(properties.getProperty("db.password"));
        return hikariDataSource;
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource() throws IOException {
        properties.load(new FileInputStream("src/ex02/Service/src/main/resources/db.properties"));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getProperty("db.driver.name"));
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.user"));
        dataSource.setPassword(properties.getProperty("db.password"));
        return dataSource;
    }


}
