package service;

import object.Transaction;
import object.User;
import repository.TransactionsLinkedList;
import repository.UsersArrayList;

import java.util.UUID;

public class TransactionsService {
    public UsersArrayList userList = new UsersArrayList();

    public void addUser(User user) {

        userList.addUser(user);
    }

    public double balanceUser(int id) {
        return userList.getUserById(id).getBalance();
    }

    public void creatingTranslation(int recipient, int sender, double amountMoney) {
        User user_00 = this.userList.getUserById(recipient);
        User user_01 = this.userList.getUserById(sender);

        Transaction Transaction_CREDIT = new Transaction(user_01, user_00, Transaction.TypeTransfer.CREDIT, -amountMoney);
        Transaction Transaction_DEBIT = new Transaction(user_01, user_00, Transaction.TypeTransfer.DEBIT, amountMoney);

        Transaction_DEBIT.setIdentifier(Transaction_CREDIT.getIdentifier());

        user_01.getTransactionList().addTransaction(Transaction_CREDIT);
        user_00.getTransactionList().addTransaction(Transaction_DEBIT);
    }

    public Transaction[] getTransactionListUser(User user) {
        return user.getTransactionList().transformIntoArray();
    }

    public void removeTransactionUser(int userId, UUID transactionId) {
        this.userList.getUserById(userId).getTransactionList().removeTransactionByUUID(transactionId);
    }

    public Transaction[] unpairedOperations() {
        TransactionsLinkedList bufferList = new TransactionsLinkedList();
        int i = 0;
        for (; i < userList.getNumberUsers(); i++) {
            for (int j = 0; j < userList.getNumberUsers(); j++) {
                for (int k = 0; k < userList.getUserByIndex(i).getTransactionList().size(); ++k) {
                    boolean flag_stop = false;
                    for (int m = 0; m < userList.getUserByIndex(j).getTransactionList().size(); ++m) {
                        if (i != j && userList.getUserByIndex(i).getTransactionList().getTransactionByIndex(k).getIdentifier() == userList.getUserByIndex(j).getTransactionList().getTransactionByIndex(m).getIdentifier())
                            flag_stop = true;
                    }
                    if (!flag_stop)
                        bufferList.addTransaction(userList.getUserByIndex(i).getTransactionList().getTransactionByIndex(k));
                    else break;
                }
            }
        }
        Transaction[] arrayTransaction = new Transaction[bufferList.size()];
        arrayTransaction = bufferList.transformIntoArray();
        return arrayTransaction;
    }

    public void printInformationUser(int id) {
        this.userList.getUserById(id).printUser();
        this.userList.getUserById(id).getTransactionList().printTransactionsList();
    }
}
