import object.Transaction;
import object.User;
import repository.TransactionsLinkedList;

import java.util.UUID;

public class Program {
    public
    static void main(String[] argc) {
        User user_01 = new User("danil", 800);
        TransactionsLinkedList List_00 = new TransactionsLinkedList();
        for (int i = 0; i < 3; ++i) {
            Transaction transaction = new Transaction(user_01, user_01, Transaction.TypeTransfer.DEBIT, -500 + i);
            List_00.addTransaction(transaction);
        }
        List_00.removeTransactionByUUID(UUID.randomUUID());
    }
}
