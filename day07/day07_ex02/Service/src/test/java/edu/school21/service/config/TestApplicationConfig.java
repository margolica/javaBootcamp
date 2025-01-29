package edu.school21.service.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.repositories.UsersRepositoryJdbcImpl;
import edu.school21.service.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class TestApplicationConfig {

    @Bean
    public HikariDataSource hikariDataSource() throws SQLException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:Service");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public UsersRepositoryJdbcImpl usersRepositoryJdbcImpl(HikariDataSource hikariDataSource) {
        return new UsersRepositoryJdbcImpl(hikariDataSource);
    }

    @Bean
    public UsersServiceImpl usersServiceImpl(UsersRepository usersRepository) {
        return new UsersServiceImpl(usersRepository);
    }

}
