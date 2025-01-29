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

        HikariConfig hikariConfig = new HikariConfig("/hikari.properties");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        MessagesRepositoryJdbcImpl messagesRepositoryJdbcImpl = new MessagesRepositoryJdbcImpl(dataSource);
        Scanner scanner = new Scanner(System.in);

        Optional<Message> message = messagesRepositoryJdbcImpl.findById(Integer.parseInt(scanner.next()));
        if (message.isPresent()) {
            Message savedMessage = message.get();
            savedMessage.setText("UPDATE");
            messagesRepository.update(savedMessage);
        } else {
            System.out.print("Объекта с запрошенным ID отсутствует");
        }
    }

}
