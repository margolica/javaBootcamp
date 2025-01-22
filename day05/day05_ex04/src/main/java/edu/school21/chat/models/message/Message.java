package edu.school21.chat.models.message;

import edu.school21.chat.models.chatroom.Chatroom;
import edu.school21.chat.models.user.User;

import java.util.Date;

public class Message {

    private Integer id;
    private User author;
    private Chatroom room;
    private String text;
    private Date time;

    public Message(Integer id, User author, Chatroom room, String text, Date time) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.time = Date.from(time.toInstant());
    }

    @Override
    public String toString() {
        return "Message [id=" + id + "\n"
                + author.toString()
                + room.toString()
                + " text=" + text + "\n"
                + " time=" + time + "]";
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + author.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Message c = (Message) obj;

        return Integer.compare(id, c.id) == 0 &&
                author.equals(c.author) &&
                room.equals(c.room) &&
                text.equals(c.text) &&
                time.equals(c.time);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}