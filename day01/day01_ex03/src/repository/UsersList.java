package repository;

import object.User;
import object.UserIdsGenerator;

public interface UsersList {
    void addUser(User user);

    User getUserById(UserIdsGenerator id);

    User getUserByIndex(int index);

    int getNumberUsers();
}
