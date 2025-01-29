package edu.school21.Printer;

import edu.school21.Renderer.Renderer;

import java.time.LocalDateTime;


public class PrinterWithDateTimeImpl implements Printer {

   private Renderer render;
   String time = LocalDateTime.now().toString();

   public PrinterWithDateTimeImpl(Renderer render) {
       this.render = render;
   }

    @Override
    public void print(String message) {
        System.out.println(message + " " + time);
    }
}
