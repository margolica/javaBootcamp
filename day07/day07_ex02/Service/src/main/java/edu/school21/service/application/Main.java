package edu.school21.service.application;

import edu.school21.service.models.User;
import edu.school21.service.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.service");

        User user_01 = new User(1L, "margolica@mail.ru", UUID.randomUUID().toString());
        User user_02 = new User(1L, "reset@mail.ru", UUID.randomUUID().toString());

//        UsersRepository usersRepository_01 = context.getBean("usersRepositoryJdbcImpl", UsersRepository.class);
//        usersRepository_01.save(user_01);
//        System.out.println(usersRepository_01.findAll());
//        usersRepository_01.findAll().forEach(System.out::println);
//        System.out.println(usersRepository_01.findByEmail(user_01.getEmail()));
//        usersRepository_01.update(user_02);
//        System.out.println(usersRepository_01.findByEmail(user_02.getEmail()));
//        usersRepository_01.delete(1L);
//        System.out.println(usersRepository_01.findAll());
//
//
//        UsersRepository usersRepository_02 = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepository.class);
//
//        usersRepository_02.save(user_01);
//        System.out.println(usersRepository_02.findAll());
//        usersRepository_02.findAll().forEach(System.out::println);
//        System.out.println(usersRepository_02.findByEmail(user_01.getEmail()));
//        usersRepository_02.update(user_02);
//        System.out.println(usersRepository_02.findByEmail(user_02.getEmail()));
//        usersRepository_02.delete(1L);
//        System.out.println(usersRepository_02.findAll());

        UsersService usersService = context.getBean(UsersService.class);
        System.out.println(usersService.SignUp("margolica@mail.ru").toString());
    }
}