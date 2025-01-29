package repository;

import object.Transaction;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);

    public boolean removeTransactionByUUID(UUID identifier);

    Transaction[] transformIntoArray();
}
