package edu.school21.service.repositories;

import edu.school21.service.models.User;
import edu.school21.service.repositories.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long maxUserId() {
        String sql = "SELECT * FROM users WHERE MAX(id)";
        Optional<User> optionalUser = Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserRowMapper()));
        if (optionalUser.isPresent()) {
            return optionalUser.get().getId();
        } else {
            return 0L;
        }
    }

    @Override
    public Optional findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        Optional<User> optionalUser = Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserRowMapper(), email));
        return optionalUser;
    }

    @Override
    public Optional findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        Optional<User> optionalUser = Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserRowMapper(), id));
        return optionalUser;
    }

    @Override
    public List findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    @Override
    public void save(Object entity) {
        jdbcTemplate.update(
                "INSERT INTO users VALUES (?, ?)",
                ((User) entity).getId(),
                ((User) entity).getEmail());
    }

    @Override
    public void update(Object entity) {
        jdbcTemplate.update(
                "UPDATE users SET email = ? WHERE id = ?",
                ((User) entity).getEmail(),
                ((User) entity).getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}
