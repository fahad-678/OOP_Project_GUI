import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Assignment implements Serializable {

  private int courseId;
  private int teacherId;
  private String section;
  private int assignmentNo;
  private boolean check;
  private boolean check1;
  private int temp;
  private Map<Integer, LocalDateTime> assignmentTime;
  private Map<Integer, String> assignment;
  private Map<Integer, String> studentIdLs;
  private Map<Integer, Map<Integer, String>> studentAssignment;
  transient Helper hp = new Helper("Validation");

  String getSection() {
    return section;
  }

  int getTeacherId() {
    return teacherId;
  }

  int getCourseId() {
    return courseId;
  }

  Assignment() {
    courseId = -1;
    teacherId = -1;
    assignmentNo = 0;
    section = "";
    temp = -1;
    check = false;
    assignmentTime = new HashMap<>();
    assignment = new HashMap<>();
    studentIdLs = new HashMap<>();
    studentAssignment = new HashMap<>();
    this.addAssignment();
  }

  void addAssignment() {
    teacherId = Main.activeId;
    section = hp.inputValidatorString("Enter Section", null);
    hp.printCourse(false, section);
    this.validateCourse();
  }

  void updateAssignment() {
    hp = new Helper("Validation");
    while (true) {
      assignmentNo = hp.inputValidatorInt("Assignment no :");
      if (assignment.containsKey(assignmentNo)) {
        System.out.println("This Assignment Already exist");
      } else {
        break;
      }
    }

    System.out.println("Assignment No : " + assignmentNo);
    assignment.put(
      assignmentNo,
      hp.inputValidatorString("Enter Assignment", null)
    );
    assignmentTime.put(assignmentNo, hp.setTime());
  }

  void deleteAssignment() {
    hp = new Helper("Validation");
    temp = hp.inputValidatorInt("Enter an assignment no : ");
    if (assignment.containsKey(temp)) {
      assignment.remove(temp);
    } else {
      System.out.println("Assignment does not exist");
    }
  }

  void printAvailableAssignment() {
    hp = new Helper("Validation");
    for (Map.Entry<Integer, LocalDateTime> outerEntry : assignmentTime.entrySet()) {
      if (outerEntry.getValue().isAfter(LocalDateTime.now())) {
        for (Map.Entry<Integer, String> innerEntry : assignment.entrySet()) {
          if (innerEntry.getKey() == outerEntry.getKey()) {
            hp.print(
              "Assignment No : " +
              outerEntry.getKey() +
              ", Assignment Last Date : " +
              outerEntry.getValue() +
              ", Assignment :\n" +
              innerEntry.getValue()
            );
          }
        }
      }
    }
  }

  void printSubmittedAssignment() {
    hp = new Helper("Validate");
    temp = hp.inputValidatorInt("Enter Assignment No : ");
    check = false;
    for (Map.Entry<Integer, Map<Integer, String>> outerEntry : studentAssignment.entrySet()) {
      if (outerEntry.getKey() == temp) {
        System.out.println("Assignment No : " + outerEntry.getKey());
        Map<Integer, String> innerMap = outerEntry.getValue();
        for (Map.Entry<Integer, String> innerEntry : innerMap.entrySet()) {
          System.out.println(
            "Student Id: " +
            innerEntry.getKey() +
            ", Assignment: " +
            innerEntry.getValue()
          );
        }
        check = true;
      }
    }
    if (!check) {
      hp.print("No assignment found with this id");
    }
  }

  void printAssignment() {
    hp = new Helper("Validation");
    for (Map.Entry<Integer, LocalDateTime> outerEntry : assignmentTime.entrySet()) {
      for (Map.Entry<Integer, String> innerEntry : assignment.entrySet()) {
        if (outerEntry.getKey() == innerEntry.getKey()) {
          hp.print(
            "Assignment No : " +
            innerEntry.getKey() +
            ", Assignment Last Date : " +
            outerEntry.getValue() +
            ", Assignment :\n" +
            innerEntry.getValue()
          );
        }
      }
    }
  }

  void uploadAssignment() {
    hp = new Helper("Validation");
    this.printAvailableAssignment();
    temp = hp.inputValidatorInt("Input Assignment NO :");

    check = false;
    check1 = false;
    for (Map.Entry<Integer, LocalDateTime> outerEntry : assignmentTime.entrySet()) {
      if (
        outerEntry.getKey() == temp &&
        outerEntry.getValue().isAfter(LocalDateTime.now())
      ) {
        for (Map.Entry<Integer, Map<Integer, String>> innerEntry : studentAssignment.entrySet()) {
          for (Map.Entry<Integer, String> inEntry : studentIdLs.entrySet()) {
            if (
              innerEntry.getKey() == outerEntry.getKey() &&
              inEntry.getKey() == Main.activeId
            ) {
              hp.print("Already Submitted the Assignment");
              check = true;
            }
          }
        }
        if (!check) {
          studentIdLs.put(
            Main.activeId,
            hp.inputValidatorString("Enter Assignment :", null)
          );
          studentAssignment.put(outerEntry.getKey(), studentIdLs);
          check1 = true;
        }
      }
    }
    if (!check1) {
      System.out.println("Wrong Assignment NO");
    }
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
}
