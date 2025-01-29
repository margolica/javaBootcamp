import java.util.Scanner;

public class Program {
  public static final int MAXIMUM_CODE_VALUE = 65534;
  public static final int TOP = 10;
  public static int j = 0;

  public static void main(String[] argc) {
    readData();
  }

  public static void readData() {
    System.out.print("Введите данные:\n");
    String input = new Scanner(System.in).nextLine();
    try {
      if (input.length() > 999)
        throw new IllegalArgumentException("Превышен размер входных данных");

      int[] characterCounter = new int[MAXIMUM_CODE_VALUE];
      for (char ch : input.toCharArray()) characterCounter[(int) ch] += 1;

      char[] topChars = new char[TOP];
      int[] topCounts = new int[TOP];

      fillingTop(characterCounter, topCounts, topChars);

      char[][] printArray = new char[12][11];
      fillingArray(printArray, topCounts, topChars);
      printResult(printArray, topCounts);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static void fillingTop(int[] characterCounter, int[] topCounts, char[] topChars) {
    for (int i = 0; i < characterCounter.length; ++i) {
      int minValueTopCounts = searchMinValue(topCounts),
          maxValueTopCounts = searchMaxValue(topCounts);
      if (characterCounter[i] > minValueTopCounts
          || (isEmptyPlace(topCounts) && characterCounter[i] > 0)) {
        if (characterCounter[i] >= maxValueTopCounts) {
          int pos = shiftRightInt(topCounts, 0);
          topCounts[pos] = characterCounter[i];
          shiftRightChar(topChars, 0);
          topChars[pos] = (char) i;
        } else if (characterCounter[i] < maxValueTopCounts
            && characterCounter[i] >= minValueTopCounts) {
          int pos = searchPos(topCounts, characterCounter[i]);
          topCounts[pos] = characterCounter[i];
          topChars[pos] = (char) i;
        } else if (characterCounter[i] < minValueTopCounts && isEmptyPlace(topCounts)) {
          int pos = searchPosMinValue(topCounts);
          topCounts[pos] = characterCounter[i];
          topChars[pos] = (char) i;
        }
      }
    }
  }

  public static void fillingArray(char[][] printArray, int[] topCounts, char[] topChars) {
    int minValueTopCounts = searchMinValue(topCounts),
        maxValueTopCounts = searchMaxValue(topCounts),
        delta = maxValueTopCounts - minValueTopCounts;
    for (int i = 0; i < 12; ++i)
      for (int j = 0; j < 10; ++j) {
        if (i == 11)
          printArray[i][j] = topChars[j];
        else if (i > 0
            && i < (Math.round((float) (topCounts[j] - minValueTopCounts) / ((float) delta / 10))))
          printArray[11 - i][j] = '#';
      }
  }

  public static void printResult(char[][] printArray, int[] topCounts) {
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 10; j++) {
        if (((i != 11 && j != 9) && (printArray[i][j] == 0 && printArray[i + 1][j] == '#'))
            || (i == 10 && topCounts[j] == searchMinValue(topCounts))) {
          System.out.print(topCounts[j]);
          System.out.print("   ");
          if ((i != 10 && topCounts[j] == searchMinValue(topCounts))
              && printArray[i + 1][j + 1] != '#')
            break;
        } else if (printArray[i][j] != 0)
          System.out.print(printArray[i][j] + "   ");
      }
      System.out.println();
    }
  }

  public static int shiftRightInt(int[] array, int pos) {
    for (int i = array.length - 1; i != pos; --i) array[i] = array[i - 1];
    return pos;
  }

  public static int shiftRightChar(char[] array, int pos) {
    for (int i = array.length - 1; i != pos; --i) array[i] = array[i - 1];
    return pos;
  }

  public static int searchPos(int[] array, int value) {
    int i = 0;
    for (; i < array.length; ++i) {
      if (value > array[i])
        break;
    }
    return i;
  }

  public static int searchPosMaxValue(int[] array) {
    int position = 0;
    for (int i : array)
      if (array[position] < array[i])
        position = i;
    return position;
  }

  public static int searchPosMinValue(int[] array) {
    int position = 0;
    for (int i = 0; i < array.length; ++i)
      if (array[position] > array[i])
        position = i;
    return position;
  }

  public static int searchMinValue(int[] array) {
    int value = array[0];
    for (int i = 0; i < array.length; ++i)
      if (value > array[i] && array[i] != 0)
        value = array[i];
    return value;
  }

  public static int searchMaxValue(int[] array) {
    int value = array[0];
    for (int i = 0; i < array.length; ++i)
      if (value < array[i])
        value = array[i];
    return value;
  }

  public static boolean isEmptyPlace(int[] array) {
    boolean result = false;
    for (int i = 0; i < array.length; ++i)
      if (array[i] == 0)
        result = true;
    return result;
  }
}