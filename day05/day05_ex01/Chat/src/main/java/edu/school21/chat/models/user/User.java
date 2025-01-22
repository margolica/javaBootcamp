package edu.school21.chat.models.user;

import edu.school21.chat.models.chatroom.Chatroom;


import java.util.ArrayList;
import java.util.Objects;

public class User {

    private int id;
    private String login;
    private String password;
    private ArrayList<Chatroom> createRooms;
    private ArrayList<Chatroom> chatroomsUser;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id
                + " login=" + login
                + " password=" + password
                + " createRooms=" + createRooms
                + " chatroomsUser=" + chatroomsUser + "]\n";
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + createRooms.hashCode();
        result = 31 * result + chatroomsUser.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        User c = (User) obj;
        return login.equals(c.login) &&
                password.equals(c.password) &&
                Objects.equals(createRooms, c.createRooms) &&
                chatroomsUser.equals(c.chatroomsUser);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Chatroom> getCreateRooms() {
        return createRooms;
    }

    public void setCreateRooms(ArrayList<Chatroom> createRooms) {
        this.createRooms = createRooms;
    }

    public ArrayList<Chatroom> getChatroomsUser() {
        return chatroomsUser;
    }

    public void setChatroomsUser(ArrayList<Chatroom> chatroomsUser) {
        this.chatroomsUser = chatroomsUser;
    }
}