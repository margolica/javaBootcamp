package edu.school21.Printer;

import edu.school21.Renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private Renderer render;
    String prefix = "PREFIX";

    public PrinterWithPrefixImpl(Renderer render) {
        this.render = render;
    }

    @Override
    public void print(String message) {
        System.out.println(prefix + " " + message);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
