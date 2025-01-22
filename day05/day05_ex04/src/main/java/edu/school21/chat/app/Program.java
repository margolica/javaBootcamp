package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.user.User;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {

        int page = 0, size = 0;

        System.out.println("Введите номер и размер страницы");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String strPage = (str.split(" "))[0], strSize = (str.split(" "))[1];

        page = Integer.parseInt(strPage);
        size = Integer.parseInt(strSize);

        HikariConfig hikariConfig = new HikariConfig("/hikari.properties");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(dataSource);

        List<User> arrayUser = usersRepositoryJdbc.findAll(page, size);

        for (User user : arrayUser) {
            System.out.println(user.toString());
        }


    }

}
