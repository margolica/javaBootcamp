package edu.school21.service.services;

import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    public UsersServiceImpl(@Qualifier("usersRepository") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public String SignUp(String email) {
        User user = new User(usersRepository.maxUserId() + 1, UUID.randomUUID().toString(), email);
        usersRepository.save(user);
        return user.getPassword();
    }
}
