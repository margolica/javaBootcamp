package ui;

import object.User;
import service.TransactionsService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService inter;
    private String[] argc;
    private Scanner input = new Scanner(System.in);

    public Menu(String[] argc) {
        inter = new TransactionsService();
        this.argc = argc;
    }

    public void startProgram() {
        boolean developerMode = argc[2].equals("--profile=dev");
        while (true) {
            printMenu(developerMode);
            menuNavigation(input, developerMode, inter);
        }
    }

    private static void menuNavigation(Scanner input, boolean developerMode, TransactionsService inter) {
        if (input.hasNextInt()) {
            int menuItem = input.nextInt();
            input.nextLine();
            switch (menuItem) {
                case (1):
                    addUser(input, inter);
                    break;
                case (2):
                    viewUserBalances(input, inter);
                    break;
                case (3):
                    performTransfer(input, inter);
                    break;
                case (4):
                    viewAllTransactionsSpecificUser(input, inter);
                    break;
                case (5):
                    if (developerMode) removeTransferById(input, inter);
                    else exitProgram(input);
                    break;
                case (6):
                    if (developerMode) checkTransferValidity(input, inter);
                    else System.out.println("Команды нет");
                    break;
                case (7):
                    if (developerMode) exitProgram(input);
                    else System.out.println("Команды нет");
                    break;
            }
        } else {
            System.out.println("Invalid choice. Please enter a valid number");
            input.next();
        }
    }

    private static void addUser(Scanner input, TransactionsService inter) {
        System.out.println("Enter a user name and a balance");
        String arrayInput[] = input.nextLine().split(" ");
        User user = new User(arrayInput[0], Integer.parseInt(arrayInput[1]));
        inter.addUser(user);
        System.out.format("User with id = %d is added\n", user.getUserId());
        printLine();
    }

    private static void viewUserBalances(Scanner input, TransactionsService inter) {
        System.out.println("Enter a user ID");
        int userId = input.nextInt();
        System.out.format("%s - %f\n", inter.userList.getUserById(userId).getUserName(),
                inter.userList.getUserById(userId).getBalance());
        printLine();
    }

    private static void performTransfer(Scanner input, TransactionsService inter) {
        System.out.println("Enter a recipient ID, a sender ID, and a transfer amount");
        String[] arrayInput = input.nextLine().split(" ");
        inter.creatingTranslation(Integer.parseInt(arrayInput[0]),
                Integer.parseInt(arrayInput[1]),
                Integer.parseInt(arrayInput[2]));
        System.out.println("The transfer is completed");
        printLine();
    }

    private static void viewAllTransactionsSpecificUser(Scanner input, TransactionsService inter) {
        System.out.println("Enter a user ID");
        inter.userList.getUserById(input.nextInt()).getTransactionList().printTransactionsList();
        printLine();
    }

    private static void removeTransferById(Scanner input, TransactionsService inter) {
        System.out.println("Enter a user ID and a transfer ID");
        String[] arrayInput = input.nextLine().split(" ");
        inter.removeTransactionUser(Integer.parseInt(arrayInput[0]), UUID.fromString(arrayInput[1]));
        System.out.format("Transfer To %s(id = %d) %f removed\n", inter.userList.getUserById(Integer.parseInt(arrayInput[0])),
                Integer.parseInt(arrayInput[0]),
                inter.userList.getUserById(Integer.parseInt(arrayInput[0])).getBalance());
        printLine();
    }

    private static void checkTransferValidity(Scanner input, TransactionsService inter) {
        System.out.println("Enter a user ID");
        inter.unpairedOperations();
    }


    private static void exitProgram(Scanner input) {
        input.close();
        System.exit(0);
    }

    private static void printMenu(boolean developerMode) {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (developerMode) {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
            System.out.println("7. Finish execution");
        } else
            System.out.println("5. Finish execution");
    }

    private static void printLine() {
        System.out.println("---------------------------------------------------------\n");
    }
}



