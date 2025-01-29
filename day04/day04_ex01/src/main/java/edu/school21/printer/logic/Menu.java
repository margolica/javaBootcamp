package edu.school21.printer.logic;

public class Menu
{
    private String [] argc;

    public Menu(String[] argc) {
        this.argc = argc;
    }

    public void start()
    {
        parserArgs(argc);
        drawFile(argc);
    }
    private static void drawFile(String[] argc)
    {
        DrawFile drawFile = new DrawFile(argc);
        drawFile.draw();
    }

    private void parserArgs(String [] argc)
    {
        if (argc.length != 3 || argc[0].length() != 1 || argc[1].length() != 1)
            throw new IllegalArgumentException("Введено недопустимое количество аргументов или аргумент введен не верно");
    }

}
