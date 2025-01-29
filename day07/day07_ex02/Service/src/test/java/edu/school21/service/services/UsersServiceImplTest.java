package edu.school21.service.services;

import edu.school21.service.config.TestApplicationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest(classes = {TestApplicationConfig.class})
@ActiveProfiles("test")
class UsersServiceImplTest {

    @Autowired
    private static DataSource dataSource;

    @Autowired
    private static UsersService usersService;

    @BeforeAll
    static void beforeAll() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersService = context.getBean("usersServiceImpl", UsersService.class);
        TestApplicationConfig testApplicationConfig = new TestApplicationConfig();
        dataSource = testApplicationConfig.hikariDataSource();
        Statement statement = dataSource.getConnection().createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS USERS");
        statement.executeUpdate("CREATE TABLE USERS (" +
                "id BIGINT, " +
                "email VARCHAR, " +
                "password VARCHAR)");
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM USERS");
    }

    @Test
    public void testSignUp() {
        Assertions.assertNotNull(usersService.SignUp("margolica@mail.ru"));
    }
}