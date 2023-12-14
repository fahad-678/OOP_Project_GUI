import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Teacher implements Serializable {

  private int id;
  static int idCounter = 0;
  private String name;
  private String password;
  private int createdBy;
  private ArrayList<Integer> teachCourse = new ArrayList<>();

  private int temp;
  transient Helper hp = new Helper("Print");

  int getId() {
    return id;
  }

  String getName() {
    return name;
  }

  String getPassword() {
    return password;
  }

  int getCreatedBy() {
    return createdBy;
  }

  ArrayList<Integer> getTeachCourse() {
    return (ArrayList<Integer>) teachCourse;
  }

  Teacher() {
    this.id = -1;
    this.name = "";
    this.password = "";
  }

  Teacher(boolean n) {
    this.addTeacher();
  }

  void addTeacher() {
    name = hp.inputValidatorString("Enter Name:", null);
    password = hp.inputValidatorString("Enter Password:", null);
    createdBy = Main.activeId;
    id = idCounter++;
  }

  void editTeacher() {
    hp = new Helper("Print");
    name = hp.inputValidatorString("Enter Name:", name);
    password = hp.inputValidatorString("Enter Password:", password);
  }

  void printTeacher() {
    System.out.print("Id: " + id + ", Name: " + name + ", TeachCourses [");
    for (Integer i : teachCourse) {
      System.out.print(i + " ");
    }
    System.out.println("]\n");
  }

  void addTeachCourse() {
    hp = new Helper("Print");
    hp.printCourse(true, null);
    boolean check = false;
    boolean check1 = false;
    do {
      temp = hp.inputValidatorInt("Enter Course to Add. Press -1 to exit.");
      if (temp != -1) {
        for (Course ls : Helper.cl) {
          if (ls.getId() == temp) {
            for (Integer il : teachCourse) {
              if (il == temp) {
                System.out.println("Already exist");
                check1 = true;
                check = true;
              }
            }
            if (!check1) {
              teachCourse.add(temp);
              check = true;
              break;
            }
          }
        }
        if (!check) {
          System.out.println("No Course Found with this id");
        }
      }
    } while (temp != -1);
  }

  void deleteTeachCourse() {
    printTeacher();
    Iterator<Integer> iterator = teachCourse.iterator();
    while (true) {
      temp = hp.inputValidatorInt("Enter Course to Delete Press -1 to exit");
      if (temp == -1) {
        break;
      }

      boolean found = false;
      while (iterator.hasNext()) {
        Integer tc = iterator.next();
        if (tc == temp) {
          iterator.remove();
          System.out.println("Removed");
          found = true;
          break;
        }
      }

      if (!found) {
        System.out.println("Course not found.");
      }
    }
  }
}
