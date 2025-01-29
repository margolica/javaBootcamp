import object.Egg;
import object.Hen;

public class Program {
    public static void main(String[] argc) {
        try {
            int countThread = parserInputString(argc);

            Egg threadEge = new Egg(countThread);
            Hen threadHen = new Hen(countThread);

            threadEge.getThreadEgg().start();
            threadHen.getThreadHen().start();

            for (int i = 0; i < countThread; ++i) {
                Thread.sleep(0, 1);
                System.out.println("Human");
            }
        } catch (AssertionError | NumberFormatException ex) {
            System.out.println("Ошибка: Недопустимый аргумент командной строки");
        } catch (InterruptedException ex) {
            System.out.println("Ошибка: Основной поток прерван");
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

