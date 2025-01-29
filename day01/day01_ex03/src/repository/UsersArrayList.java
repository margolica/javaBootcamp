package repository;

import object.User;
import object.UserIdsGenerator;
import exception.UserNotFoundException;

public class UsersArrayList implements UsersList {
    private int sizeAllocatedMemory;
    private int sizeUserMemoryUsed;
    User[] arrayUser;

    public UsersArrayList() {
        sizeAllocatedMemory = 10;
        sizeUserMemoryUsed = 0;
        arrayUser = new User[sizeAllocatedMemory];
    }

    public int getSizeAllocatedMemory() {
        return this.sizeAllocatedMemory;
    }

    public int getSizeUserMemoryUsed() {
        return this.sizeUserMemoryUsed;
    }

    public User[] getArrayUser() {
        return this.arrayUser;
    }

    @Override
    public void addUser(User user) {
        if (sizeUserMemoryUsed == sizeAllocatedMemory - 1)
            reserve(sizeAllocatedMemory * 2);
        arrayUser[sizeUserMemoryUsed++] = user;
    }

    private void reserve(int new_cap) {
        User[] bufferArrayUser = new User[new_cap];
        System.arraycopy(this.arrayUser, 0, bufferArrayUser, 0, new_cap / 2);
        sizeAllocatedMemory = new_cap;
        this.arrayUser = bufferArrayUser;
        bufferArrayUser = null;
    }

    @Override
    public User getUserById(UserIdsGenerator id) {
        int i = 0;
        for (; i < this.sizeUserMemoryUsed && this.getUserByIndex(i).getUserId() != id; ++i) ;
        return (this.getUserByIndex(i).getUserId() == id) ? this.getUserByIndex(i) : null;
    }

    @Override
    public User getUserByIndex(int index) {
        if (index > -1 && index < sizeUserMemoryUsed)
            return arrayUser[index];
        else
            throw new UserNotFoundException("Error");
    }

    @Override
    public int getNumberUsers() {
        return sizeUserMemoryUsed;
    }
}
