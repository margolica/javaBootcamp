package edu.school21.service.application;

import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");


        User user_01 = new User(1L, "margolica@mail.ru");
        User user_02 = new User(1L, "reset@mail.ru");

        UsersRepository usersRepository_01 = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        usersRepository_01.save(user_01);
        System.out.println(usersRepository_01.findAll());
        usersRepository_01.findAll().forEach(System.out::println);
        System.out.println(usersRepository_01.findByEmail(user_01.getEmail()));
        usersRepository_01.update(user_02);
        System.out.println(usersRepository_01.findByEmail(user_02.getEmail()));
        usersRepository_01.delete(1L);
        System.out.println(usersRepository_01.findAll());


        UsersRepository usersRepository_02 = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        usersRepository_02.save(user_01);
        System.out.println(usersRepository_02.findAll());
        usersRepository_02.findAll().forEach(System.out::println);
        System.out.println(usersRepository_02.findByEmail("margolica"));
        usersRepository_02.update(user_02);
        System.out.println(usersRepository_02.findByEmail("reset"));
        usersRepository_02.delete(1L);
        System.out.println(usersRepository_02.findAll());
    }
}