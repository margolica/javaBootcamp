import ui.Menu;

import static java.lang.System.exit;

public class Program {
    public static void main(String[] argc) {
        if (argc.length == 1) {
            Menu menu = new Menu();
            menu.start(argc);
        } else {
            System.out.println("Количество аргументов командной строки неверно");
            exit(-1);
        }
    }
}
