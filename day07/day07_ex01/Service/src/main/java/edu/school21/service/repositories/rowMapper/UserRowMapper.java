package edu.school21.service.repositories.rowMapper;

import edu.school21.service.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(1));
        user.setEmail(rs.getString(2));
        return user;
    }
}
