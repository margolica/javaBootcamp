package edu.school21.numbers.service;

import edu.school21.numbers.exception.AlwaysAuthenticatedException;
import edu.school21.numbers.model.User;
import edu.school21.numbers.repositories.UsersRepository;

public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) {
        User user = usersRepository.findByLogin(login);
        if (user == null)
            return false;
        if (user.isAuthenticationSuccessStatus())
            throw new AlwaysAuthenticatedException("Always authenticated");
        if (!user.getPassword().equals(password))
            throw new RuntimeException("Wrong password");
        user.setAuthenticationSuccessStatus(true);
        usersRepository.update(user);
        return true;
    }
}