import java.io.*;
import java.util.*;

public class FileHandling {

  private int temp = 0;
  private String adminFile = "File//Admin//Admin.txt";
  private String studentFile = "File//Student//Student.txt";
  private String courseFile = "File//Course//Course.txt";
  private String teacherFile = "File//Teacher//Teacher.txt";
  private String announcementFile = "File//Announcement//Announcement.txt";
  private String sessionFile = "File//Session//Session.txt";
  private String timetableFile = "File//Timetable//Timetable.txt";
  private String attendanceFile = "File//Attendance//Attendance.txt";
  private String assignmentFile = "File//Assignment//Assignment.txt";

  private <T> void writeFile(List<T> ls, String filePath) {
    try (
      ObjectOutputStream fileOSB = new ObjectOutputStream(
        new FileOutputStream(filePath)
      )
    ) {
      for (T st : ls) {
        fileOSB.writeObject(st);
      }
    } catch (FileNotFoundException fn) {
      fn.printStackTrace();
    } catch (IOException io) {
      io.printStackTrace();
    }
  }

  private <T> ArrayList<T> readFile(String filePath) {
    ArrayList<T> st = new ArrayList<>();
    try (
      ObjectInputStream fileSB = new ObjectInputStream(
        new FileInputStream(filePath)
      )
    ) {
      while (true) {
        try {
          st.add((T) fileSB.readObject());
        } catch (EOFException e) {
          break;
        } catch (ClassNotFoundException | IOException io) {
          io.printStackTrace();
          break;
        }
      }
    } catch (FileNotFoundException fe) {
      System.out.println("Error: File Not Found - " + filePath);
    } catch (IOException io) {
      System.out.println("Reading File Error - " + filePath);
    }
    return st;
  }

  ArrayList<Admin> retrieveAdmin() {
    ArrayList<Admin> al = readFile(adminFile);
    if (!al.isEmpty()) {
      for (Admin admin : al) {
        int adminId = admin.getId();
        if (adminId > temp) {
          temp = adminId;
        }
      }
      Admin.idCounter = temp + 1;
    }
    return al;
  }

  void writeAdmin(List<Admin> ls) {
    writeFile(ls, adminFile);
  }

  ArrayList<Student> retrieveStudent() {
    ArrayList<Student> al = readFile(studentFile);
    if (!al.isEmpty()) {
      for (Student student : al) {
        int studentId = student.getId();
        if (studentId > temp) {
          temp = studentId;
        }
      }
      Student.idCounter = temp + 1;
    }
    return al;
  }

  void writeStudent(List<Student> ls) {
    writeFile(ls, studentFile);
  }

  ArrayList<Teacher> retrieveTeacher() {
    ArrayList<Teacher> al = readFile(teacherFile);
    if (!al.isEmpty()) {
      for (Teacher teacher : al) {
        int teacherId = teacher.getId();
        if (teacherId > temp) {
          temp = teacherId;
        }
      }
      Teacher.idCounter = temp + 1;
    }
    return al;
  }

  void writeTeacher(List<Teacher> ls) {
    writeFile(ls, teacherFile);
  }

  ArrayList<Course> retrieveCourse() {
    ArrayList<Course> al = readFile(courseFile);
    if (!al.isEmpty()) {
      for (Course course : al) {
        int courseId = course.getId();
        if (courseId > temp) {
          temp = courseId;
        }
      }
      Course.idCounter = temp + 1;
    }
    return al;
  }

  void writeCourse(List<Course> ls) {
    writeFile(ls, courseFile);
  }

  ArrayList<Announcement> retrieveAnnouncement() {
    ArrayList<Announcement> al = readFile(announcementFile);
    return al;
  }

  void writeAnnouncement(List<Announcement> ls) {
    writeFile(ls, announcementFile);
  }

  public void writeSession(String[] sessionData) {
    List<String[]> sessions = new ArrayList<>();
    sessions.add(sessionData);
    writeFile(sessions, sessionFile);
  }

  public String[] retrieveSession() {
    List<String[]> sessions = readFile(sessionFile);
    if (!sessions.isEmpty()) {
      return sessions.get(0);
    }
    return null;
  }

  ArrayList<TimeTable> retrieveTimetable() {
    ArrayList<TimeTable> al = readFile(timetableFile);
    return al;
  }

  void writeTimetable(List<TimeTable> ls) {
    writeFile(ls, timetableFile);
  }

  ArrayList<Attendance> retrieveAttendance() {
    ArrayList<Attendance> al = readFile(attendanceFile);
    return al;
  }

  void writeAttendance(List<Attendance> ls) {
    writeFile(ls, attendanceFile);
  }

  ArrayList<Assignment> retrieveAssignment() {
    ArrayList<Assignment> al = readFile(assignmentFile);
    return al;
  }

  void writeAssignment(List<Assignment> ls) {
    writeFile(ls, assignmentFile);
  }
}
