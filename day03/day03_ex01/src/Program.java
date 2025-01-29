import object.Consumer;
import object.Object;
import object.Producer;

public class Program {
    public static void main(String[] argc) {
        try {
            int countThread = parserInputString(argc);

            Object obj = new Object();

            Producer threadEge = new Producer(obj, countThread);
            Consumer threadHen = new Consumer(obj, countThread);

            new Thread(threadEge).start();
            new Thread(threadHen).start();

        } catch (AssertionError | NumberFormatException ex) {
            System.out.println("Ошибка: Недопустимый аргумент командной строки");
        }
    }

    private static int parserInputString(String[] argc) throws AssertionError {
        int countThread = 0;
        assert argc.length == 1;
        if (argc[0].contains("--count="))
            countThread = Integer.parseInt((argc[0].split("="))[1]);
        else
            throw new NumberFormatException();
        assert countThread > 0;
        return countThread;
    }
}
