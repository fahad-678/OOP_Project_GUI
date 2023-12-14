import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Helper {

  private String count;
  private String counts;
  private int id;
  private boolean check;
  static List<Student> sl;
  static List<Admin> al;
  static List<Teacher> tl;
  static List<Course> cl;
  static List<Announcement> acl;
  static List<TimeTable> til;
  static List<Attendance> atl;
  static List<Assignment> asl;
  static Scanner sc = new Scanner(System.in);
  FileHandling fh = new FileHandling();
  Session sn = new Session();

  Helper() {
    counts = "";
    sl = new ArrayList<>();
    cl = new ArrayList<>();
    al = new ArrayList<>();
    tl = new ArrayList<>();
    acl = new ArrayList<>();
    til = new ArrayList<>();
    atl = new ArrayList<>();
    asl = new ArrayList<>();
    this.retrieveData();
  }

  Helper(String n) {}

  void retrieveData() {
    sl = fh.retrieveStudent();
    cl = fh.retrieveCourse();
    al = fh.retrieveAdmin();
    tl = fh.retrieveTeacher();
    acl = fh.retrieveAnnouncement();
    til = fh.retrieveTimetable();
    atl = fh.retrieveAttendance();
    asl = fh.retrieveAssignment();
  }

  boolean mainFn() {
    do {
      switch (Main.activeRole != 0 ? Main.activeRole : 1) {
        case 1:
          if (al.isEmpty()) {
            System.out.println("No Admin Found Add Admin");
            al.add(new Admin(true));
            Main.activeId = al.get(0).getId();
            Main.activeName = al.get(0).getName();
            Main.activeRole = 1;
            fh.writeAdmin(al);
            break;
          }
          do {
            count =
              this.inputValidatorString(
                  "1. Profile \n2. Course\n3. UserManagement\n4. StudentsRecords\n5. Announcement\n6. TimeTable\n7. Logout\n8. Exit",
                  null
                );
            switch (count) {
              case "1":
                this.profile();
                break;
              case "2":
                if (cl.isEmpty()) {
                  System.out.println("No Course Found Add Course");
                  cl.add(new Course(true));
                  fh.writeCourse(cl);
                  break;
                }
                this.courseDetails();
                break;
              case "3":
                this.userManagement();
                break;
              case "4":
                if (sl.isEmpty()) {
                  System.out.println("No Student Found Add Student");
                  sl.add(new Student(true));
                  fh.writeStudent(sl);
                  break;
                }
                this.printStudents(null);
                break;
              case "5":
                if (acl.isEmpty()) {
                  System.out.println("No Announcement Found Add Announcement");
                  acl.add(new Announcement(true));
                  fh.writeAnnouncement(acl);
                  break;
                }
                this.announcement();
                break;
              case "6":
                this.timetable();
                break;
              case "7":
                sn.deleteSession();
                return true;
              case "8":
                this.printExit("Admin");
                return true;
              default:
                this.printInvalid();
            }
          } while (!count.equals("8"));
          break;
        case 2:
          do {
            count =
              this.inputValidatorString(
                  "1. Profile\n2. Course\n3. Attendance\n4. Announcement\n5. Assignment\n6. Logout\n7. Exit",
                  null
                );
            switch (count) {
              case "1":
                this.profile();
                break;
              case "2":
                this.courseDetails();
                break;
              case "3":
                this.attendance();
                break;
              case "4":
                this.announcement();
                break;
              case "5":
                this.assignment();
                break;
              case "6":
                sn.deleteSession();
                return true;
              case "7":
                this.printExit("Teacher");
                return true;
              default:
                this.printInvalid();
            }
          } while (!count.equals("7"));
          break;
        case 3:
          do {
            count =
              this.inputValidatorString(
                  "1. Profile\n2. Course Enrollment\n3. Assignment\n4. Announcement\n5. View Attendance\n6. Logout\n7. Exit",
                  null
                );
            switch (count) {
              case "1":
                this.profile();
                break;
              case "2":
                for (Student ls : sl) {
                  if (ls.getId() == Main.activeId) {
                    sl.remove(ls);
                    ls.addCourse();
                    sl.add(ls);
                    fh.writeStudent(sl);
                  }
                }
                break;
              case "3":
                this.studentAssignment();
                break;
              case "4":
                this.printAnnouncements();
                break;
              case "5":
                this.printAttendanceStudent();
                break;
              case "6":
                sn.deleteSession();
                return true;
              case "7":
                this.printExit("Student");
                return true;
              default:
            }
          } while (!count.equals("7"));
          break;
        default:
          this.printInvalid();
          break;
      }
    } while (!counts.equals("7"));
    return true;
  }

  void printAttendanceStudent() {
    String section = "";
    for (Student ls : sl) {
      if (ls.getId() == Main.activeId) {
        section = ls.getSection();
        for (Course cls : cl) {
          if (
            cls.getSection().equals(section) && ls.getSCL(cls.getId()) != -1
          ) {
            cls.printDetails();
          }
        }
        id = this.inputValidatorInt("Enter Course id ");
        for (Attendance als : atl) {
          if (
            als.getSection().equals(section) &&
            als.getCourseId() == id &&
            ls.getSCL(id) != -1
          ) {
            als.printAttendanceStudent();
            check = true;
            break;
          }
        }
        break;
      }
    }

    if (!check) {
      System.out.println("No Course Found");
    }
  }

  void submitAssignment(boolean n) {
    String section = "";
    for (Student ls : sl) {
      if (ls.getId() == Main.activeId) {
        section = ls.getSection();
        for (Course cls : cl) {
          if (
            cls.getSection().equals(section) && ls.getSCL(cls.getId()) != -1
          ) {
            cls.printDetails();
          }
        }
        id = this.inputValidatorInt("Enter Course id ");
        for (Assignment als : asl) {
          if (
            als.getSection().equals(section) &&
            als.getCourseId() == id &&
            ls.getSCL(id) != -1
          ) {
            if (n) {
              als.uploadAssignment();
            } else {
              als.printAvailableAssignment();
            }
            check = true;
            break;
          }
        }
        break;
      }
    }

    if (!check) {
      System.out.println("No Course Found");
    }
  }

  void printInvalid() {
    System.out.println("Invalid Input");
  }

  void printExit(String n) {
    System.out.println("Exiting " + n);
  }

  void print(String n) {
    System.out.println(n);
  }

  void profile() {
    if (Main.activeRole == 1) {
      for (Admin i : al) {
        if (i.getId() == Main.activeId) {
          i.printAdmin();
          break;
        }
      }
    } else if (Main.activeRole == 2) {
      for (Teacher i : tl) {
        if (i.getId() == Main.activeId) {
          i.printTeacher();
          break;
        }
      }
    } else {
      for (Student i : sl) {
        if (i.getId() == Main.activeId) {
          i.printStudent();
          break;
        }
      }
    }
  }

  void printStudents(String n) {
    if (n == null) {
      for (Student i : sl) {
        if (i.getId() != -1) {
          i.printStudent();
        }
      }
    } else {
      for (Student i : sl) {
        if (i.getId() != -1 && n != null && n.equals(i.getSection())) {
          i.printStudent();
        }
      }
    }
  }

  void printAnnouncements() {
    for (Announcement i : acl) {
      if (i.getId() != -1) {
        i.printAnnouncement();
      }
    }
  }

  void printCourse(boolean z, String n) {
    if (z) {
      for (Course i : cl) {
        if (i.getId() != -1) {
          i.printDetails();
        }
      }
    } else {
      for (Course i : cl) {
        if (i.getId() != -1 && n != null && n.equals(i.getSection())) {
          i.printDetails();
        }
      }
    }
  }

  void printTimetable() {
    for (TimeTable i : til) {
      if (!i.getSection().equals("")) {
        i.printTimetable();
      }
    }
  }

  void courseDetails() {
    do {
      counts =
        this.inputValidatorString(
            "1. Print Courses\n2. Add Course\n3. Edit Course\n4. Delete Course\n5. Freeze Course\n6. Exit",
            null
          );
      switch (counts) {
        case "1":
          this.printCourse(true, null);
          break;
        case "2":
          cl.add(new Course(true));
          break;
        case "3":
          id = this.inputValidatorInt("Enter Course Id");
          for (Course i : cl) {
            if (i.getId() == id) {
              i.editCourse();
              break;
            }
          }
          break;
        case "4":
          id = this.inputValidatorInt("Enter Course Id");
          for (Course i : cl) {
            if (i.getId() == id) {
              cl.remove(i);
              break;
            }
          }
          break;
        case "5":
          id = this.inputValidatorInt("Enter Course Id");
          for (Course i : cl) {
            if (i.getId() == id) {
              i.setFreeze();
              break;
            }
          }
          break;
        case "6":
          printExit("Course");
          break;
        default:
          printInvalid();
      }
      fh.writeCourse(cl);
    } while (!counts.equals("6"));
  }

  void userManagement() {
    do {
      counts =
        this.inputValidatorString(
            "1. Admin\n2. Teacher\n3. Student\n4. Exit",
            null
          );
      switch (counts) {
        case "1":
          adminMenu();
          break;
        case "2":
          teacherMenu("admin");
          break;
        case "3":
          studentMenu("admin");
          break;
        case "4":
          printExit("User Management");
          break;
        default:
          printInvalid();
      }
    } while (!counts.equals("4"));
  }

  void adminMenu() {
    do {
      System.out.println();
      counts =
        this.inputValidatorString(
            "1.Print Admins\n2. Add Admin\n3. Edit Admin\n4. Delete Admin\n5. Exit",
            null
          );
      check = false;
      switch (counts) {
        case "1":
          for (Admin i : al) {
            if (i.getId() != -1) {
              i.printAdmin();
            }
          }
          break;
        case "2":
          al.add(new Admin(true));
          break;
        case "3":
          id = this.inputValidatorInt("Enter Admin Id");
          for (Admin i : al) {
            if (i.getId() == id) {
              i.editAdmin();
              check = true;
              break;
            }
          }
          break;
        case "4":
          id = this.inputValidatorInt("Enter Admin Id");
          for (Admin i : al) {
            if (i.getId() == id) {
              al.remove(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Admin found with this id");
          }
          break;
        case "5":
          printExit("Admin");
          break;
        default:
          printInvalid();
      }
      fh.writeAdmin(al);
    } while (!counts.equals("5"));
  }

  void teacherMenu(String n) {
    do {
      counts =
        this.inputValidatorString(
            "1. Print Teacher\n2. Add Teacher\n3. Edit Teacher\n4. Delete Teacher\n5. Add TeachCourse\n6. Delete TeachCourse\n7. Exit",
            null
          );
      check = false;
      switch (counts) {
        case "1":
          for (Teacher i : tl) {
            if (i.getId() != -1) {
              i.printTeacher();
            }
          }
          break;
        case "2":
          tl.add(new Teacher(true));
          break;
        case "3":
          id = this.inputValidatorInt("Enter Teacher Id");
          for (Teacher i : tl) {
            if (i.getId() == id) {
              i.editTeacher();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Teacher found with this id");
          }
          break;
        case "4":
          id = this.inputValidatorInt("Enter Teacher Id");
          for (Teacher i : tl) {
            if (i.getId() == id) {
              tl.remove(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Teacher found with this id");
          }
          break;
        case "5":
          id = this.inputValidatorInt("Enter Teacher Id");
          for (Teacher i : tl) {
            if (i.getId() == id) {
              tl.remove(i);
              i.addTeachCourse();
              tl.add(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Teacher found with this id");
          }
          break;
        case "6":
          id = this.inputValidatorInt("Enter Teacher Id");
          for (Teacher i : tl) {
            if (i.getId() == id) {
              tl.remove(i);
              i.deleteTeachCourse();
              tl.add(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Teacher found with this id");
          }
          break;
        case "7":
          printExit("Teacher");
          break;
        default:
          printInvalid();
      }
      fh.writeTeacher(tl);
    } while (!counts.equals("7"));
  }

  void studentMenu(String n) {
    do {
      counts =
        this.inputValidatorString(
            "1. Print Student\n2. Add Student\n3. Edit Student\n4. Delete Student\n5. AddStudentCourse\n6. DeleteStudentCourse\n7. Exit",
            null
          );
      check = false;
      switch (counts) {
        case "1":
          this.printStudents(null);
          break;
        case "2":
          sl.add(new Student(true));
          break;
        case "3":
          id = this.inputValidatorInt("Enter Student Id");
          for (Student i : sl) {
            if (i.getId() == id) {
              i.editStudent();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Student found with this id");
          }
          break;
        case "4":
          id = this.inputValidatorInt("Enter Student Id");
          for (Student i : sl) {
            if (i.getId() == id) {
              sl.remove(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Student found with this id");
          }
          break;
        case "5":
          id = this.inputValidatorInt("Enter Student Id");
          for (Student i : sl) {
            if (i.getId() == id) {
              sl.remove(i);
              i.addCourse();
              sl.add(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Student found with this id");
          }
          break;
        case "6":
          id = this.inputValidatorInt("Enter Student Id");
          for (Student i : sl) {
            if (i.getId() == id) {
              sl.remove(i);
              i.deleteCourse();
              sl.add(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Student found with this id");
          }
          break;
        case "7":
          printExit("Student");
          break;
        default:
          printInvalid();
      }
      fh.writeStudent(sl);
    } while (!counts.equals("7"));
  }

  void announcement() {
    this.printAnnouncements();
    check = false;
    do {
      System.out.println(
        "1. Reload Announcements\n2. Add Announcements\n3. Delete Announcements\n4. Exit"
      );
      counts = sc.nextLine();
      switch (counts) {
        case "1":
          this.printAnnouncements();
          break;
        case "2":
          acl.add(new Announcement(true));
          break;
        case "3":
          id = this.inputValidatorInt("Enter Announcement Id");
          for (Announcement i : acl) {
            if (i.getId() == id) {
              acl.remove(i);
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Announcement found with this id");
          }
          break;
        case "4":
          printExit("Announcements");
          break;
        default:
          printInvalid();
      }
      fh.writeAnnouncement(acl);
    } while (!counts.equals("4"));
  }

  void timetable() {
    do {
      counts =
        this.inputValidatorString(
            "1. Print Timetable\n2. Add Timetable\n3. Edit Timetable\n4. Exit",
            null
          );
      check = false;
      String section;
      switch (counts) {
        case "1":
          this.printTimetable();
          break;
        case "2":
          til.add(new TimeTable(true));
          break;
        case "3":
          section = this.inputValidatorString("Enter Timetable Section", null);
          for (TimeTable i : til) {
            if (i.getSection().equals(section)) {
              i.editTimetable();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Section Found");
          }
          break;
        case "4":
          printExit("TimeTable");
          break;
        default:
          printInvalid();
      }
      fh.writeTimetable(til);
    } while (!counts.equals("4"));
  }

  Teacher getTeacher() {
    for (Teacher ls : tl) {
      if (Main.activeId == ls.getId()) {
        return ls;
      }
    }
    return null;
  }

  boolean checkCourse(int n) {
    ArrayList<Integer> temp;
    Teacher tc = this.getTeacher();
    if (tc == null) {
      this.print("No Teacher Found");
      return false;
    }
    temp = tc.getTeachCourse();
    for (Integer ls : temp) {
      if (ls == n) {
        return true;
      }
    }
    this.print("This Course is Not Registerd");
    return false;
  }

  void attendance() {
    do {
      counts =
        this.inputValidatorString(
            "1. Update Attendance\n2. Add New Attendance \n3. Print Attendance\n4. Exit",
            null
          );
      check = false;
      String section;
      switch (counts) {
        case "1":
          section = this.inputValidatorString("Enter Section", null);
          this.printCourse(false, section);
          id = this.inputValidatorInt("Enter Course id ");
          for (Attendance ls : atl) {
            if (
              ls.getTeacherId() == Main.activeId &&
              ls.getSection().equals(section) &&
              this.checkCourse(id)
            ) {
              ls.updateAttendance();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Section Found");
          }
          break;
        case "2":
          atl.add(new Attendance());
          break;
        case "3":
          section = this.inputValidatorString("Enter Section", null);
          this.printCourse(false, section);
          id = this.inputValidatorInt("Enter Course id ");
          for (Attendance ls : atl) {
            if (
              ls.getTeacherId() == Main.activeId &&
              ls.getSection().equals(section) &&
              this.checkCourse(id)
            ) {
              ls.printAttendance();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Section Found");
          }
          break;
        case "4":
          printExit("Attendance");
          break;
        default:
          printInvalid();
      }
      fh.writeAttendance(atl);
    } while (!counts.equals("4"));
  }

  void assignment() {
    do {
      counts =
        this.inputValidatorString(
            "1. Update Assignment\n2. Create Assignment\n3. View All Assignment\n4. View Uploaded Assignment\n5. Delete Assignment\n6. Exit",
            null
          );
      check = false;
      String section;
      switch (counts) {
        case "1":
          section = this.inputValidatorString("Enter Section", null);
          this.printCourse(false, section);
          id = this.inputValidatorInt("Enter Course id ");
          for (Assignment ls : asl) {
            if (
              ls.getTeacherId() == Main.activeId &&
              ls.getSection().equals(section) &&
              this.checkCourse(id)
            ) {
              ls.updateAssignment();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Section Found");
          }
          break;
        case "2":
          asl.add(new Assignment());
          break;
        case "3":
          section = this.inputValidatorString("Enter Section", null);
          this.printCourse(false, section);
          id = this.inputValidatorInt("Enter Course id ");
          for (Assignment ls : asl) {
            if (
              ls.getTeacherId() == Main.activeId &&
              ls.getSection().equals(section) &&
              this.checkCourse(id)
            ) {
              ls.printAssignment();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Section Found");
          }
          break;
        case "4":
          section = this.inputValidatorString("Enter Section", null);
          this.printCourse(false, section);
          id = this.inputValidatorInt("Enter Course id ");
          for (Assignment ls : asl) {
            if (
              ls.getTeacherId() == Main.activeId &&
              ls.getSection().equals(section) &&
              this.checkCourse(id)
            ) {
              ls.printSubmittedAssignment();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Section Found");
          }
          break;
        case "5":
          section = this.inputValidatorString("Enter Section", null);
          this.printCourse(false, section);
          id = this.inputValidatorInt("Enter Course id ");
          for (Assignment ls : asl) {
            if (
              ls.getTeacherId() == Main.activeId &&
              ls.getSection().equals(section) &&
              this.checkCourse(id)
            ) {
              ls.deleteAssignment();
              check = true;
              break;
            }
          }
          if (!check) {
            System.out.println("No Section Found");
          }
          break;
        case "6":
          printExit("Assignment");
          break;
        default:
          printInvalid();
      }
      fh.writeAssignment(asl);
    } while (!counts.equals("6"));
  }

  void studentAssignment() {
    do {
      counts =
        this.inputValidatorString(
            "1. Available Assignment\n2. Submit Assignment\n3. Exit",
            null
          );
      switch (counts) {
        case "1":
          this.submitAssignment(false);
          break;
        case "2":
          this.submitAssignment(true);
          break;
        case "3":
          printExit("Assignment");
          break;
        default:
          this.printInvalid();
          break;
      }
      fh.writeAssignment(asl);
    } while (!counts.equals("3"));
  }

  int inputValidatorInt(String n) {
    int userInput;
    while (true) {
      try {
        System.out.println(n);
        userInput = sc.nextInt();
        sc.nextLine();
        break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter an integer.");
        sc.next();
      }
    }
    return userInput;
  }

  boolean inputValidatorBool(String n) {
    boolean userInput;
    while (true) {
      try {
        System.out.println(n);
        userInput = sc.nextBoolean();
        break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter true/false.");
        sc.next();
      }
    }
    return userInput;
  }

  String inputValidatorString(String n, String z) {
    if (z != null) {
      System.out.println(
        "Just Enter an Empty String to retain the previous value: " + z
      );
    }

    String userInput;
    while (true) {
      try {
        System.out.println(n);
        userInput = sc.nextLine().trim();

        if (z != null && userInput.isEmpty()) {
          return z;
        }

        if (userInput.isEmpty()) {
          System.out.println("Input cannot be empty. Please enter again.");
          continue;
        }

        break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a String.");
        sc.next();
      }
    }
    return userInput;
  }

  LocalDateTime setTime() {
    LocalDateTime ldc = null;
    System.out.println("Enter Date and Time");
    try {
      int year = inputValidatorInt("Enter Year : ");
      int month = inputValidatorInt("Enter Month :");
      int date = inputValidatorInt("Enter Date :");
      int hours = inputValidatorInt("Enter Hours :");
      int minutes = inputValidatorInt("Enter Minutes :");
      ldc = LocalDateTime.of(year, month, date, hours, minutes);
    } catch (Exception e) {
      print("Invalid Data and Time");
      setTime();
    }
    return ldc;
  }
}
