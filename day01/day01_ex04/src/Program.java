import object.Transaction;
import service.TransactionsService;

public class Program {
    public
    static void main(String[] argc) {
        TransactionsService interface_00 = new TransactionsService();
        interface_00.addUser("00", 600);
        interface_00.addUser("01", 700);

        interface_00.creatingTranslation(interface_00.userList.getUserByIndex(0).getUserId(), interface_00.userList.getUserByIndex(1).getUserId(), -300);

        interface_00.removeTransactionUser(interface_00.userList.getUserByIndex(0).getUserId(), interface_00.userList.getUserByIndex(0).getTransactionList().getTransactionByIndex(0).getIdentifier());

        System.out.println(interface_00.balanceUser(0));
        System.out.println(interface_00.balanceUser(1));

        interface_00.printInformationUser(0);
        interface_00.printInformationUser(1);

        Transaction[] array = interface_00.unpairedOperations();

    }
}
