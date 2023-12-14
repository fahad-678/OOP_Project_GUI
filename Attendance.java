import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Attendance implements Serializable {

  private int teacherId;
  private String section;
  private int courseId;
  private int lectureNo;
  private Map<Integer, Boolean> studentIdLs;
  private Map<Integer, Map<Integer, Boolean>> attendance;
  private int temp;
  private boolean check;
  transient Helper hp = new Helper("Print");

  String getSection() {
    return section;
  }

  int getTeacherId() {
    return teacherId;
  }

  int getCourseId() {
    return courseId;
  }

  Attendance() {
    teacherId = -1;
    section = "";
    courseId = -1;
    lectureNo = 0;
    studentIdLs = new HashMap<>();
    attendance = new HashMap<>();
    this.addAttendance();
  }

  void addAttendance() {
    teacherId = Main.activeId;
    section = hp.inputValidatorString("Enter Section", null);
    hp.printCourse(false, section);
    this.validateCourse();
  }

  void updateAttendance() {
    System.out.println("Lecture No : " + lectureNo);
    for (Student ls : Helper.sl) {
      if (section.equals(ls.getSection()) && ls.getSCL(courseId) != -1) {
        hp.print("Enter id : " + ls.getId() + ", Name : " + ls.getName());
        studentIdLs.put(ls.getId(), hp.inputValidatorBool("Enter Attendance"));
      }
    }
    attendance.put(++lectureNo, studentIdLs);
  }

  void validateCourse() {
    check = false;
    while (true) {
      temp = hp.inputValidatorInt("Enter Course id");
      for (Course ls : Helper.cl) {
        if (
          ls.getId() == temp &&
          section.equals(ls.getSection()) &&
          hp.checkCourse(temp)
        ) {
          courseId = ls.getId();
          check = true;
          break;
        }
      }
      if (!check) {
        hp.print("Course not found or Section does not Match");
      } else {
        hp.print(
          "Added New Attendance Section : " +
          section +
          ", Course Id : " +
          courseId +
          " By Teacher Id : " +
          teacherId
        );
        break;
      }
    }
  }

  void printAttendance() {
    for (Map.Entry<Integer, Map<Integer, Boolean>> outerEntry : attendance.entrySet()) {
      int outerKey = outerEntry.getKey();
      Map<Integer, Boolean> innerMap = outerEntry.getValue();
      System.out.println("Lecture No : " + outerKey);
      for (Map.Entry<Integer, Boolean> innerEntry : innerMap.entrySet()) {
        System.out.println(
          "Student Id: " +
          innerEntry.getKey() +
          ", Present: " +
          innerEntry.getValue()
        );
      }
    }
  }

  void printAttendanceStudent() {
    for (Map.Entry<Integer, Map<Integer, Boolean>> outerEntry : attendance.entrySet()) {
      int outerKey = outerEntry.getKey();
      Map<Integer, Boolean> innerMap = outerEntry.getValue();
      System.out.println("Lecture No : " + outerKey);
      for (Map.Entry<Integer, Boolean> innerEntry : innerMap.entrySet()) {
        if (innerEntry.getKey() == Main.activeId) {
          System.out.println(
            "Student Id: " +
            innerEntry.getKey() +
            ", Present: " +
            innerEntry.getValue()
          );
        }
      }
    }
  }
}
