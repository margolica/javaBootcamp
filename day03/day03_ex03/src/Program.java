import ui.Menu;

public class Program {
    private static final String PATH_FILE_URLS = "day03/day03_ex03/src/materials/files_urls.txt";

    public static void main(String[] argc) {
        int countThread = parserInputString(argc);
        Menu menu = new Menu(PATH_FILE_URLS, countThread);
        menu.start(PATH_FILE_URLS);
    }

    private static int parserInputString(String[] argc) {
        int countThread = 0;
        try {
            assert argc.length == 1;
            if (argc[0].contains("--threadsCount=")) {
                countThread = Integer.parseInt(argc[0].split("=")[1]);
                assert countThread > 0;
            }
        } catch (AssertionError ex) {
            System.out.println("Ошибка: Недопустимый аргумент командной строки");
        }
        return countThread;
    }
    // TODO Проверить исключения и assert
}

