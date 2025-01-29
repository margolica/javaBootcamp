public class Program {
  public static void main(String[] argc) {
    int number = 479598, result = 0;
    for (int i = 0; i < String.valueOf(number).length(); ++i)
      result += (String.valueOf(number).charAt(i) - '0');
    System.out.print(result);
  }
}
