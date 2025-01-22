package edu.school21.numbers.services;

import edu.school21.numbers.model.User;
import edu.school21.numbers.repositories.UsersRepository;
import edu.school21.numbers.service.UsersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersServiceImplTest {
    UsersServiceImpl usersService = null;
    UsersRepository mokUsersRepository = null;

    @BeforeEach
    void setUp() {
        this.mokUsersRepository = Mockito.mock(UsersRepository.class);
        usersService = new UsersServiceImpl(this.mokUsersRepository);
    }

    @Test
    void correctlyAuntificationUser() {
        User user = new User(1L, "user", "user", false);
        Mockito.when(mokUsersRepository.findByLogin("user")).thenReturn(user);
        Assertions.assertTrue(usersService.authenticate("user", "user"));
        Mockito.verify(mokUsersRepository, Mockito.times(1)).update(user);
    }

    @Test
    void errorLogin() {
        Mockito.when(mokUsersRepository.findByLogin("users")).thenReturn(null);
        Assertions.assertFalse(usersService.authenticate("users", "users"));
    }

    @Test
    void errorPassword() {
        User user = new User(1L, "user", "user", false);
        Mockito.when(mokUsersRepository.findByLogin("user")).thenReturn(user);
        Assertions.assertThrows(RuntimeException.class, () -> usersService.authenticate("user", "users"));
    }
}
