package repository;

import object.Transaction;
import exception.TransactionNotFoundException;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private static class Node {
        Node prev, next;
        Transaction data;

        private Node(Transaction transaction, Node prev, Node next) {
            this.next = next;
            this.prev = prev;
            this.data = transaction;
        }
    }

    Node head = null;
    Node tail = null;

    public int size() {
        int count = 0;
        for (Node node = this.head; node != null; node = node.next, ++count) ;
        return count;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (head == null)
            head = tail = new Node(transaction, null, null);
        else {
            Node node = new Node(transaction, this.tail, null);
            this.tail.next = node;
            this.tail = node;
        }
    }

    @Override
    public boolean removeTransactionByUUID(UUID identifier) {
        boolean result = false;
        for (Node node = this.head; this.head.data.getIdentifier() != identifier && node != null; node = node.next)
            if (this.head.data.getIdentifier() == identifier) result = true;
        if (result == false) throw new TransactionNotFoundException("Транзакции с таким ID не существует");
        return result;
    }

    @Override
    public Transaction[] transformIntoArray() {
        int i = 0;
        Transaction[] arrayTransaction = new Transaction[this.size()];
        for (Node node = this.head; node != null; node = node.next)
            arrayTransaction[i] = node.data;
        return arrayTransaction;
    }
}
