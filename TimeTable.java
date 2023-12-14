import java.io.Serializable;

public class TimeTable implements Serializable {

  private String section;
  private int[] per = { -1, -1, -1, -1, -1 };
  private Object[][] day = {
    { "mon", per },
    { "tue", per },
    { "wed", per },
    { "thur", per },
    { "fri", per },
  };
  transient Helper hp = new Helper("Print");

  TimeTable() {
    section = "";
  }

  TimeTable(boolean n) {
    this.addTimetable();
  }

  String getSection() {
    return section;
  }

  void addTimetable() {
    section = hp.inputValidatorString("Enter the Section: ", null);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        String n =
          "Enter Course for Slot " +
          (j + 1) +
          " ON " +
          day[i][0].toString().toUpperCase() +
          ", Enter -1 For Empty";
        per[j] = hp.inputValidatorInt(n);
      }
      day[i][1] = per;
      per = new int[] { -1, -1, -1, -1, -1 };
    }
  }

  void editTimetable() {
    hp = new Helper("Print");
    section = hp.inputValidatorString("Enter the Section: ", null);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        String n =
          "Enter Course for Slot " +
          (i + 1) +
          " ON " +
          day[i][0].toString().toUpperCase() +
          ", Enter -1 For Empty";
        per[j] = hp.inputValidatorInt(n);
      }
      day[i][1] = per;
      per = new int[] { -1, -1, -1, -1, -1 };
    }
  }

  void printTimetable() {
    System.out.print("Section " + section);
    for (int i = 0; i < 5; i++) {
      System.out.println("\nDay : " + day[i][0].toString().toUpperCase());
      for (int j = 0; j < 5; j++) {
        per = (int[]) day[i][1];
        System.out.print("-- Period " + (j + 1) + " -- " + per[j] + " ---");
      }
    }
    System.out.println("\n");
  }
}
