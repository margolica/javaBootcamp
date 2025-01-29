package object;

public class UserIdsGenerator {
    private static UserIdsGenerator userIdsGenerator;
    private static int idCounter = 0;

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null)
            userIdsGenerator = new UserIdsGenerator();
        return userIdsGenerator;
    }

    public int generateId() {
        return idCounter++;
    }
}