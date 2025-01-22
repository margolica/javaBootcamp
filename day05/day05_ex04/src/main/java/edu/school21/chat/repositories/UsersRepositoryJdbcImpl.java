package edu.school21.chat.repositories;

import edu.school21.chat.models.chatroom.Chatroom;
import edu.school21.chat.models.message.Message;
import edu.school21.chat.models.user.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;
    String sql = "WITH table_0 AS (\n" +
            "    SELECT \n" +
            "        \"user\".id AS user_id, \n" +
            "        \"user\".login AS user_login, \n" +
            "        \"user\".password AS user_password,\n" +
            "\t\tchatroom.id AS user_create_room_id,\n" +
            "\t\tchatroom.name AS user_create_room_name\n" +
            "    FROM \"user\" \n" +
            "    LEFT OUTER JOIN chatroom ON chatroom.owner_id = \"user\".id \n" +
            "\n" +
            "),\n" +
            "table_1 AS (\n" +
            "\tSELECT \n" +
            "\t\tuser_id, \n" +
            "\t\tuser_login, \n" +
            "\t\tuser_password,\n" +
            "\t\tuser_create_room_id,\n" +
            "\t\tuser_create_room_name\n" +
            "\tFROM table_0\n" +
            "\tLEFT OUTER JOIN chatroom ON chatroom.id = user_create_room_id\n" +
            "),\n" +
            "table_2 AS ( \n" +
            "    SELECT\n" +
            "        user_id, \n" +
            "        user_login, \n" +
            "        user_password, \n" +
            "\t\tuser_create_room_id,\n" +
            "\t\tuser_create_room_name,\n" +
            "        message.room_id, \n" +
            "        message.date\n" +
            "    FROM table_1\n" +
            "    LEFT OUTER JOIN message ON message.author_id = user_id\n" +
            ")\n" +
            "SELECT \n" +
            "    user_id, \n" +
            "    user_login, \n" +
            "    user_password, \n" +
            "    STRING_AGG(DISTINCT CAST(room_id AS TEXT), ', ') AS add_chat, \n" +
            "\tSTRING_AGG(DISTINCT CAST(chatroom.name AS TEXT), ', ') AS add_chat_name, \n" +
            "    STRING_AGG(DISTINCT CAST(user_create_room_id AS TEXT), ', ') AS user_create_room_id, \n" +
            "    STRING_AGG(DISTINCT CAST(user_create_room_name AS TEXT), ', ') AS user_create_room_name\n" +
            "FROM table_2 \n" +
            "LEFT OUTER JOIN chatroom ON chatroom.id = room_id\n" +
            "GROUP BY user_id, user_login, user_password\n" +
            "ORDER BY user_id;\n";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static ArrayList<User> completionArrayListUser(int page, int size, ResultSet result) throws SQLException {
        ArrayList<User> arrayList = new ArrayList<>();
        while (true) {
            if (!result.next())
                break;
            int userId = result.getInt("user_id");
            String login = result.getString("user_login");
            String password = result.getString("user_password");
            User user = new User(userId, login, password);
            ArrayList<Chatroom> chatroomsCreate = completionArrayListChatrooms(userId, result.getString("user_create_room_id"), result.getString("user_create_room_name"), result);
            ArrayList<Chatroom> chatroomsAdd = completionArrayListChatrooms(userId, result.getString("add_chat"), result.getString("add_chat_name"), result);
            user.setCreateRooms(chatroomsCreate);
            user.setChatroomsUser(chatroomsAdd);
            arrayList.add(user);
        }
        return arrayList;
    }

    private static ArrayList<Chatroom> completionArrayListChatrooms(int user_id, String room_id, String room_name, ResultSet result) throws SQLException {
        if (result.wasNull())
            return null;
        String[] arrayRoomId = room_id.split(", ");
        String[] arrayRoomName = room_name.split(", ");
        if (arrayRoomId.length != arrayRoomName.length) {
            System.out.println("Error: room_id and room_name are not the same");
            return null;
        }
        ArrayList<Chatroom> chatrooms = new ArrayList<>();
        for (int i = 0; i < arrayRoomId.length; i++) {
            chatrooms.add(new Chatroom(Integer.parseInt(arrayRoomId[i]), arrayRoomName[i], new User(null, null), new ArrayList<Message>()));
        }
        return chatrooms;
    }

    @Override
    public List<User> findAll(int page, int size) {
        Statement stem = null;
        ResultSet result = null;
        ArrayList<User> arrayList = null;
        try {
            stem = dataSource.getConnection().createStatement();
            result = stem.executeQuery(sql);
            arrayList = completionArrayListUser(page, size, result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int numberPage = arrayList.size() / size;
        ArrayList<User> pageList = new ArrayList<>();
        for (int i = 0; i <= numberPage; i++)
            for (int j = 0; j < size && i == page && ((i * size) + j) < arrayList.size(); j++)
                pageList.add(arrayList.get((i * size) + j));
        return pageList;
    }
}



