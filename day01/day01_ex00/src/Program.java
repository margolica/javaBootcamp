import object.Transaction;
import object.User;

public class Program {
    public static void main(String[] argc) {
        User user_00 = new User("danil", -100);
        User user_01 = new User("danil", 800);
        User user_02 = new User("denis", 300);

        Transaction trans_00 = new Transaction(user_01, user_02, Transaction.TypeTransfer.CREDIT, -100);
        Transaction trans_01 = new Transaction(user_02, user_01, Transaction.TypeTransfer.DEBIT, 100);
        Transaction trans_02 = new Transaction(user_02, user_01, Transaction.TypeTransfer.CREDIT, 100);
    }
}
