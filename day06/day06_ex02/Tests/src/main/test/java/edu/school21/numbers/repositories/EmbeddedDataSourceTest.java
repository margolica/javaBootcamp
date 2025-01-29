package edu.school21.numbers.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.Connection;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {

    public EmbeddedDatabase dataBase = null;

    @BeforeEach
    void init() {
        dataBase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .continueOnError(true)
                .ignoreFailedDrops(true)
                .build();
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataBase.getConnection();
        Assertions.assertNotNull(connection);
    }
}
