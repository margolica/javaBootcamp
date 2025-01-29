package edu.school21.printer.logic;

import com.beust.jcommander.JCommander;


public class Menu {
    private Args args;

    public Menu(String[] argc) {
        this.args = new Args();
        parserArgs(argc);
    }

    public void start() {
         drawFile(args);
    }

    private static void drawFile(Args args) {
        DrawFile drawFile = new DrawFile(args);
        drawFile.draw();
    }

    private void parserArgs(String[] argc) {
        JCommander.newBuilder().addObject(this.args).build().parse(argc);
    }
}