package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exception.NotSavedSubEntityException;
import edu.school21.chat.models.chatroom.Chatroom;
import edu.school21.chat.models.message.Message;
import edu.school21.chat.models.user.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;

public class Program {
    public static void main(String[] args) {

        HikariConfig hikariConfig = new HikariConfig("/hikari.properties");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        User user = new User(1, "login", "0000");
        Chatroom chatroom = new Chatroom(1, "chatroom_01", user, new ArrayList<>());
        Message message = new Message(null, user, chatroom, "Hello", new Date());

        MessagesRepositoryJdbcImpl messagesRepositoryJdbcImpl = new MessagesRepositoryJdbcImpl(dataSource);

        try {
            messagesRepositoryJdbcImpl.save(message);
        } catch (NotSavedSubEntityException e) {
            System.out.println("NotSavedSubEntityException caught: " + e.getMessage());
            throw e;
        }
        System.out.println(message.toString());
    }
}
