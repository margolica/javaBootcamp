package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.message.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Enter a message ID:\n");
        String inputId = new Scanner(System.in).nextLine();

        HikariConfig hikariConfig = new HikariConfig("/hikari.properties");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        Optional<Message> objectMessage = messagesRepository.findById(Integer.parseInt(inputId));
        if (objectMessage.isPresent()) {
            System.out.print(objectMessage.get().toString());
        } else {
            System.out.print("Объекта с запрошенным ID отсутствует");
        }
    }
}
