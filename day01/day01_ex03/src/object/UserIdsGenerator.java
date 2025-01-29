package object;

public class UserIdsGenerator {
    private static UserIdsGenerator userIdsGenerator;
    private static int idCounter = 0;
    private int userId;

    private UserIdsGenerator() {
        userId = idCounter;
    }

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null)
            userIdsGenerator = new UserIdsGenerator();
        return userIdsGenerator;
    }

    public UserIdsGenerator generateId() {
        userId = ++idCounter;
        return userIdsGenerator;
    }

    public void readId() {
        System.out.format("UserID: %d", userId);
    }
}