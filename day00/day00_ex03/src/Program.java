import java.util.Scanner;

public class Program {
  static int iteration = 0;
  public static void main(String[] argc) {
    int numWeek;
    StringBuilder diagram = new StringBuilder();
    Scanner input = new Scanner(System.in);
    while ((numWeek = readWeek(input)) != 42)
      diagram.append("Week " + numWeek + paintDiagram(readScore(input)) + ">" + '\n');
    System.out.println(diagram);
  }

  public static int readWeek(Scanner input) {
    int numWeek = 1;
    iteration++;
    System.out.print("Введите неделю:\n");
    String week = input.nextLine();
    if (!(week.matches("Week [0-9]{1,3}") || week.matches("42")))
      errorExit(input, "Invalid data format");
    if (week.length() > 6)
      numWeek = ((week.charAt(5) - '0') * 10) + (week.charAt(6) - '0');
    else if (week.length() > 5)
      numWeek = week.charAt(5) - '0';
    else if (week.length() > 1)
      numWeek = 42;
    else if (numWeek != iteration && numWeek != 42)
      errorExit(input, "The order of data entry has been violated");
    return numWeek;
  }

  public static double readScore(Scanner input) {
    System.out.print("Введите баллы за неделю:\n");
    String sourceOfDay = new Scanner(System.in).nextLine();
    if (!sourceOfDay.matches("[1-9] [1-9] [1-9] [1-9] [1-9]"))
      errorExit(input, "Гарантированное количество тестов в неделю — 5");
    return (double) ((sourceOfDay.charAt(0) + sourceOfDay.charAt(2) + sourceOfDay.charAt(4)
                         + sourceOfDay.charAt(6) + sourceOfDay.charAt(8))
               - (5 * '0'))
        / 5;
  }

  public static String paintDiagram(double avg) {
    StringBuilder builderStr = new StringBuilder();
    for (int i = 0; i < avg; i++) builderStr.append("=");
    return builderStr.toString();
  }

  public static void errorExit(Scanner input, String error) {
    System.err.println(error);
    input.close();
    System.exit(-1);
  }
}
