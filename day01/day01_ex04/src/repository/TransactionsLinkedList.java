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

        public Transaction getData() {
            return this.data;
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
        Node node = this.head;
        for (int i = 0; i < this.size(); i++) {
            if (node.getData().getIdentifier() == identifier) {
                result = true;
                break;
            } else node = node.next;
        }
        if (!result) throw new TransactionNotFoundException("Транзакции с таким ID не существует");
        if (node.next == null && node.prev == null)
            head = tail = null;
        else if (node.prev == null) {
            head = node.next;
            node.next.prev = null;
        } else if (node.next == null) {
            tail = node.prev;
            node.prev.next = null;
        }
        return result;
    }

    public Transaction getTransactionByIndex(int index) {
        if (index > size()) throw new TransactionNotFoundException("Транзакции с таким index не существует");
        Node node = this.head;
        for (int i = 0; i != index; ++i)
            node = node.next;
        return node.getData();
    }

    @Override
    public Transaction[] transformIntoArray() {
        int i = 0;
        Transaction[] arrayTransaction = new Transaction[this.size()];
        for (Node node = this.tail; node != null; node = node.next)
            arrayTransaction[i] = node.data;
        return arrayTransaction;
    }

    public void printTransactionsList() {
        for (TransactionsLinkedList.Node node = this.head; node != null; node = node.next) {
            System.out.format("ID Transaction: %s | Amount Transfer %s\n" +
                            "Recipient: %s   Sender: %s   Type Transfer: %s   Amount: %s",
                    node.data.getIdentifier(), node.data.getAmountTransfer(),
                    node.data.getRecipient().getUserName(),
                    node.data.getSender().getUserName(),
                    node.data.getTypeTransfer(),
                    node.data.getAmountTransfer());
        }
    }
}
