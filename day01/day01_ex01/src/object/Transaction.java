package object;

import java.util.UUID;

public class Transaction {
    private UUID identifier = UUID.randomUUID();
    private User recipient;
    private User sender;
    private TypeTransfer type;
    private double amountTransfer;

    public enum TypeTransfer {
        DEBIT,
        CREDIT
    }

    ;

    public Transaction(User recipient, User sender, TypeTransfer type, double amountTransfer) {
        if ((type == TypeTransfer.DEBIT && amountTransfer > 0) || (type == TypeTransfer.CREDIT && amountTransfer < 0))
            System.out.println("Сумма перевода указана неверно");
        else {
            setRecipient(recipient);
            setSender(sender);
            setTypeTransfer(type);
            setAmountTransfer(amountTransfer);
        }
    }

    public UUID getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getRecipient() {
        return this.recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public TypeTransfer getTypeTransfer() {
        return this.type;
    }

    public void setTypeTransfer(TypeTransfer type) {
        this.type = type;
    }

    public double getAmountTransfer() {
        return this.amountTransfer;
    }

    public void setAmountTransfer(double amountTransfer) {
        this.amountTransfer = amountTransfer;
    }
}
