package day00_ex01.src;

import java.util.Scanner;

public class Program {

  public static void main(String[] argc) {
    System.out.print("Enter an integer: ");
    Scanner input = new Scanner(System.in);

    int value = input.nextInt(), codeError = 0, countIter = 0;

    if (value < 2)
      errorExit(input, "Illegal Argument");
    else {
      for (int i = 2; i <= Math.sqrt(value); ++i, ++countIter)
        if (value % i == 0)
          errorExit(input, "Statement: false\n" + "Number of iterations: " + countIter);
        else
          System.out.print("Statement: true\n" + "Number of iterations: " + countIter);
    }
  }

   static void errorExit(Scanner input, String error) {
    System.err.println(error);
    input.close();
    System.exit(-1);
  }
}
