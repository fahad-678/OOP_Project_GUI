import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Student implements Serializable {

  private int id;
  static int idCounter = 0;

  private String name;
  private String f_name;
  private int age;
  private String address;
  private String section;
  private String password;
  private int createdBy;
  private int temp;
  private ArrayList<Integer> scl = new ArrayList<>();
  transient Helper hp = new Helper("Validation");

  Student() {
    id = -1;
    name = "";
    f_name = "";
    age = -1;
    address = "";
    password = "";
    section = "";
    createdBy = -1;
  }

  Student(boolean n) {
    this.addStudent();
  }

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

  String getSection() {
    return section;
  }

  int getSCL(int n) {
    for (Integer ls : scl) {
      if (ls == n) {
        return ls;
      }
    }
    return -1;
  }

  void addStudent() {
    name = hp.inputValidatorString("Enter Name:", null);
    f_name = hp.inputValidatorString("Enter Father Name:", null);
    age = hp.inputValidatorInt("Enter Age:");
    address = hp.inputValidatorString("Enter Address:", null);
    section = hp.inputValidatorString("Enter Section:", null);
    password = hp.inputValidatorString("Enter Password:", null);
    createdBy = Main.activeId;
    id = idCounter++;
  }

  void editStudent() {
    hp = new Helper("Print");
    name = hp.inputValidatorString("Enter Name:", name);
    f_name = hp.inputValidatorString("Enter Father Name:", f_name);
    temp = hp.inputValidatorInt("Enter Age:");
    age = temp == -1 ? age : temp;
    address = hp.inputValidatorString("Enter Address:", address);
    section = hp.inputValidatorString("Enter Section:", section);
    password = hp.inputValidatorString("Enter Password:", password);
  }

  void printStudent() {
    System.out.print(
      "Id: " +
      id +
      ", Name: " +
      name +
      ", Father Name: " +
      f_name +
      ", Age: " +
      age +
      ", Address: " +
      address +
      ", Section: " +
      section +
      ", Courses: ["
    );
    for (Integer i : scl) {
      System.out.print(i + " ");
    }
    System.out.print("]\n");
  }

  void addCourse() {
    hp = new Helper("Print");
    hp.printCourse(false, section);
    boolean check1 = false;
    do {
      boolean check = false;
      temp = hp.inputValidatorInt("Enter Course Id\nPress -1 exit");
      if (temp != -1) {
        for (Course ls : Helper.cl) {
          if (temp == ls.getId() && section.equals(ls.getSection())) {
            for (Integer il : scl) {
              if (il == temp) {
                System.out.println("Already exist");
                check1 = true;
                check = true;
              }
            }
            if (!check1) {
              scl.add(temp);
              check = true;
              break;
            }
          }
        }
        if (!check) {
          System.out.println("No Course Found With This Id");
        }
      }
    } while (temp != -1);
  }

  void deleteCourse() {
    printStudent();
    Iterator<Integer> iterator = scl.iterator();
    hp = new Helper("Print");
    while (true) {
      temp = hp.inputValidatorInt("Enter Course to Delete, Press -1 to exit");
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
