import java.util.Scanner;

public class Program {
  public static final int MAX_STUDENTS = 10;
  public static final int MAX_COUNT_LESSON = 10;
  public static final int MAX_RECORD_VISIT = MAX_STUDENTS * MAX_COUNT_LESSON;
  public static final int DAY_MONTH = 30;

  public static void main(String[] argc) {
    String[] listStudents = new String[MAX_STUDENTS];
    String[] timeTable = new String[MAX_COUNT_LESSON];
    String[] visitTable = new String[MAX_RECORD_VISIT];
    String[][] calendar = new String[3][100];

    Scanner input = new Scanner(System.in);

    creatingListStudents(input, listStudents);
    creatingTimeTable(input, timeTable);
    creatingVisitTable(input, visitTable, listStudents, timeTable);

    input.close();

    creatingCalendar(calendar, timeTable);
    printCalendar(listStudents, calendar, visitTable);
  }

  public static void creatingListStudents(Scanner input, String[] listStudents) {
    System.out.println("Enter the list of students:");
    for (int i = 0; i < MAX_STUDENTS; ++i) {
      String name = input.nextLine();
      if (name.equals("."))
        break;
      if (name.length() > 10)
        errorExit(input, "Name length exceeded");
      listStudents[i] = name;
    }
  }

  public static void creatingTimeTable(Scanner input, String[] timeTable) {
    System.out.println("Enter the time and day of the week:");
    for (int i = 0; i < MAX_COUNT_LESSON; ++i) {
      String dataLesson = input.nextLine();
      if (dataLesson.equals("."))
        break;
      if (!dataLesson.matches("[1-6] \\b(SU|MO|TU|WE|TH|FR|SA)\\b"))
        errorExit(input, "Incorrect time and day of the week format");
      timeTable[i] = dataLesson;
    }
  }

  public static void creatingVisitTable(
      Scanner input, String[] visitTable, String[] listStudents, String[] timeTable) {
    System.out.println("Enter the information about the visits:");
    boolean statusDataVisit = true;
    for (int i = 0; i < MAX_RECORD_VISIT && statusDataVisit == true; ++i) {
      String dataVisit = input.nextLine();
      if (dataVisit.equals("."))
        break;
      String[] items = dataVisit.split(" ");
      if (items.length != 4)
        errorExit(input, "Invalid session format");
      for (int a = 0; a < items.length && statusDataVisit == true; ++a) {
        if (a == 0)
          statusDataVisit = searchEntry(items[a], listStudents);
        else if (a == 1)
          statusDataVisit = searchEntry(items[a] + " " + items[a + 1], listStudents);
        else if (a == 3)
          statusDataVisit = items[a].matches("NOT_HERE|HERE");
      }
    }
  }

  public static void printCalendar(
      String[] listStudents, String[][] calendar, String[] visitTable) {
    System.out.print("\t\t  ");
    for (int student = -1; student < MAX_STUDENTS; ++student) {
      if (student > -1)
        System.out.format("%-10s", listStudents[student]);
      for (int lesson = 0; calendar[0][lesson] != null; ++lesson) {
        if (student == -1)
          System.out.format(
              "%-10s%-2s%2s|", calendar[0][lesson], calendar[1][lesson], calendar[2][lesson]);
        else {
          for (int visit = 0; visit < 10 && visitTable[visit] != null; ++visit) {
            String[] items = visitTable[visit].split(" ");
            if (listStudents[student].equals(items[0]) && calendar[1][lesson].equals(items[1])
                && calendar[2][lesson].equals(items[2]))
              System.out.format("%14s|", (items[3].equals("HERE") ? 1 : -1));
          }
          System.out.format("%14s|", "");
        }
      }
      System.out.format("\n");
    }
  }

  public static void creatingCalendar(String[][] calendar, String[] timeTable) {
    String str = "MO TU WE TH FR SA SU";
    for (int day = 0; day < DAY_MONTH; ++day) {
      for (int i = 0; i < MAX_COUNT_LESSON && timeTable[i] != null; ++i) {
        String[] items = timeTable[i].split(" ");
        int x = (str.indexOf(items[1]) / 3) - 1, y = day % 7;
        if (x == y || (x == -1 && y == 6)) {
          calendar[0][day] = items[1];
          calendar[1][day] = items[0];
          calendar[2][day] = String.valueOf(day);
        }
      }
    }
  }

  public static boolean searchEntry(String str, String[] array) {
    boolean result = false;
    for (String i : array) {
      if (str.equals(str)) {
        result = true;
        break;
      }
    }
    return result;
  }

  public static void errorExit(Scanner input, String error) {
    System.err.println(error);
    input.close();
    System.exit(-1);
  }
}
