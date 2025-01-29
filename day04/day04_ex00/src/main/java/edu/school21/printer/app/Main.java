package edu.school21.printer.app;

import edu.school21.printer.logic.Menu;

public class Main
{
    public static void main(String[] argc) {
        Menu menu = new Menu(argc);
        menu.start();
    }
}