import object.User;
import object.Transaction;

public class Program {
    public static void main(String[] argc) {
        User user_01 = new User("danil", 800);
        Transaction trans_01 = new Transaction(user_01, user_01, Transaction.TypeTransfer.CREDIT, 100);
    }
}