package edu.school21.chat.models.chatroom;

import edu.school21.chat.models.message.Message;
import edu.school21.chat.models.user.User;

import java.util.ArrayList;

public class Chatroom {
    private int id;
    private String name;
    private User owner;
    private ArrayList<Message> listMessages;

    public Chatroom(int id, String name, User owner, ArrayList<Message> listMessages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.listMessages = listMessages;
    }

    @Override
    public String toString() {
        return "Chatroom [id=" + id
                + " name=" + name
                + " id=" + owner.getId() + "]\n";
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + owner.hashCode();
        result = 31 * result + (listMessages != null ? listMessages.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Chatroom c = (Chatroom) obj;
        return Integer.compare(id, c.id) == 0
                && name.equals(c.name)
                && owner.equals(c.owner)
                && listMessagesEqual(c.listMessages);
    }

    private boolean listMessagesEqual(ArrayList<Message> otherList) {
        if (listMessages.size() != otherList.size()) return false;

        for (int i = 0; i < listMessages.size(); i++) {
            if (!listMessages.get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }
}