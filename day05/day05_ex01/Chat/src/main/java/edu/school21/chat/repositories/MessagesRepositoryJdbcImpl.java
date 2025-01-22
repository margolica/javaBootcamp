package edu.school21.chat.repositories;

import edu.school21.chat.models.chatroom.Chatroom;
import edu.school21.chat.models.message.Message;
import edu.school21.chat.models.user.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    final static int INDEX_COLUMN_ID = 1;

    DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(int id) {
        Optional<Message> messageOptional = Optional.ofNullable(selectDataBase(dataSource, id));
        return messageOptional;
    }

    private Message selectDataBase(DataSource dataSource, int searchId) {
        Message message = null;
        PreparedStatement pstmtMessage = null, pstmtChatroom = null, pstmtUser = null;
        ResultSet resultMessage = null, resultChatroom = null, resultUser = null;
        try {
            String sqlMessage = "SELECT * FROM message WHERE id=?";
            String sqlChatroom = "SELECT * FROM chatroom WHERE id=?";
            String sqlUser = "SELECT * FROM \"user\" WHERE id=?";

            pstmtMessage = dataSource.getConnection().prepareStatement(sqlMessage);
            pstmtUser = dataSource.getConnection().prepareStatement(sqlUser);
            pstmtChatroom = dataSource.getConnection().prepareStatement(sqlChatroom);

            pstmtMessage.setInt(INDEX_COLUMN_ID, searchId);
            resultMessage = pstmtMessage.executeQuery();

            if (resultMessage.next()) {
                pstmtChatroom.setInt(INDEX_COLUMN_ID, resultMessage.getInt(3));
                resultChatroom = pstmtChatroom.executeQuery();

                pstmtUser.setInt(INDEX_COLUMN_ID, resultMessage.getInt(2));
                resultUser = pstmtUser.executeQuery();

                if (resultChatroom.next() && resultUser.next()) {
                    User user = new User(resultUser.getInt(1), resultUser.getString(2), resultUser.getString(3));
                    Chatroom chatroom = new Chatroom(resultChatroom.getInt(1), resultChatroom.getString(2), user, new ArrayList<>());
                    message = new Message(resultMessage.getInt(1), user, chatroom, resultMessage.getString(4), resultMessage.getTimestamp(5));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
}
