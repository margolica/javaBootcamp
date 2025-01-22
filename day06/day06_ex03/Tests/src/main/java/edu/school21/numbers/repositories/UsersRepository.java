package edu.school21.numbers.repositories;

import edu.school21.numbers.model.User;

public interface UsersRepository {
    User findByLogin(String login);

    void update(User user);
}
