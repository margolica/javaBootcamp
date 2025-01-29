package object;

import repository.TransactionsLinkedList;

public class User {
    private int identifier;
    private String userName;
    private double balance;
    private TransactionsLinkedList transactionList;

    public User(String userName, double balance) {
        if (balance < 0)
            System.out.println("Ошибка: баланс нового пользователя не может быть отрицательным");
        else {
            setIdentifier(UserIdsGenerator.getInstance().generateId());
            setBalance(balance);
            this.userName = userName;
            transactionList = new TransactionsLinkedList();
        }
    }

    public int getUserId() {
        return this.identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionsLinkedList getTransactionList() {
        return this.transactionList;
    }

    public void printUser() {
        System.out.printf("ID USER: %d | NameUser: %s \n", this.getUserId(), this.userName);
    }
}