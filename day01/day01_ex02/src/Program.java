import object.User;
import repository.UsersArrayList;

public class Program {

    public static void main(String[] argc) {
        UsersArrayList List_00 = new UsersArrayList();

        for (int i = 0; i < 15; ++i) {
            User user = new User("0" + i, i * 100);
            List_00.addUser(user);
        }

        User user = new User("20", -500);
        List_00.addUser(user);

        System.out.println(List_00.getSizeAllocatedMemory());
        System.out.println(List_00.getSizeUserMemoryUsed());

        System.out.println(List_00.getNumberUsers());

        if (List_00.getUserById(List_00.getUserByIndex(0).getUserId()).getUserName().equals("00"))
            System.out.println("The user search was completed successfully");

        User[] arrayUser = new User[40];
        arrayUser = List_00.getArrayUser();
    }

}
