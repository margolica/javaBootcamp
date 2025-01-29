package repository;

import object.User;

public interface UsersList {
    void addUser(User user);

    User getUserById(int id);

    User getUserByIndex(int index);

    int getNumberUsers();
}
