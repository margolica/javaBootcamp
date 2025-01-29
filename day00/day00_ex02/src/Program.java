 import java.util.Scanner;

public class Program {
  public static void main(String[] argc) {
    int inputValue = 0, countCoffee = 0;
    System.out.print("Enter an integer: ");
    while ((inputValue = (new Scanner(System.in).nextInt())) != 42)
      if (naturalNumber(sumDigit(inputValue)))
        countCoffee++;
    System.out.print("Count of coffee-request – " + countCoffee);
  }

  public static int sumDigit(int value) {
    int sumNumber = 0;
    for (int i = 0; i < String.valueOf(value).length();
         sumNumber = (String.valueOf(value).charAt(i++) - '0'))
      ;
    return sumNumber;
  }

  public static boolean naturalNumber(int value) {
    for (int i = 2; i <= Math.sqrt(value); ++i)
      if (value % i == 0)
        return false;
    return true;
  }
}
