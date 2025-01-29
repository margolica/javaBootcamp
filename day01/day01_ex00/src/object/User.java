package object;

public class User {
    private static int identifierCount = 0;
    private int identifier;
    private String userName;
    private double balance;

    public User(String userName, double balance) {
        if (balance < 0)
            System.out.println("Ошибка: баланс нового пользователя не может быть отрицательным");
        else {
            setBalance(balance);
            this.userName = userName;
            this.balance = balance;
            this.identifier = identifierCount++;
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
}
