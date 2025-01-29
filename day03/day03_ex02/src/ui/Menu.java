package ui;

import model.SumCellsArray;

public class Menu {

    private static String[] argc;

    public Menu(String[] argc) {
        try {
            parserInputString(argc);
        } catch (AssertionError ex) {
            System.out.println("Ошибка: Недопустимый аргумент командной строки");
        }
        this.argc = argc;
    }

    public void sumCellsArray() {
        SumCellsArray sumCellsArray = new SumCellsArray(argc);
        sumCellsArray.start();
    }

    private static void parserInputString(String[] argc) throws AssertionError {
        assert argc.length == 2;
        if (!argc[0].contains("--arraySize=") && !argc[0].contains("--threadsCount="))
            throw new AssertionError();
    }
}
