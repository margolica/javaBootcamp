package edu.school21;

import edu.school21.Printer.PrinterWithDateTimeImpl;
import edu.school21.Printer.PrinterWithPrefixImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PrinterWithDateTimeImpl printerWithDateTimeImpl = context.getBean("PrinterWithDateTimeImplErrToUpperImpl", PrinterWithDateTimeImpl.class);
		printerWithDateTimeImpl
				.print("Hello World");
	}

}
