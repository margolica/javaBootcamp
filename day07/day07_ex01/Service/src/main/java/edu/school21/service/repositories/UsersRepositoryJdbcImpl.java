package edu.school21.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.service.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;

    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = " + "'" + email + "'";
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = + id";
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.executeQuery(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void save(Object entity) {
        String sql = "INSERT INTO users (id, email) VALUES (?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, ((User) entity).getId());
            preparedStatement.setString(2, ((User) entity).getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Object entity) {
        Long id = ((User) entity).getId();
        String sql = "UPDATE users SET email = ? WHERE id = + id";
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, ((User) entity).getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = + id";
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
