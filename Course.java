import java.io.Serializable;

public class Course implements Serializable {

  private int id;
  static int idCounter = 0;
  private String name;
  private String time;
  private String section;
  private int createdBy;
  transient Helper hp = new Helper("Validator");

  int getId() {
    return id;
  }

  int getCreatedBy() {
    return createdBy;
  }

  String getSection() {
    return section;
  }

  String getName() {
    return name;
  }

  String getTime() {
    return time;
  }

  Course(String name, String time, String section) {
    this.name = name;
    this.time = time;
    this.section = section;
    createdBy = Main.activeId;
    id = idCounter++;
  }

  void editCourse(String name, String time, String section) {
    this.name = name;
    this.time = time;
    this.section = section;
    createdBy = Main.activeId;
  }

  void printDetails() {
    System.out.println(
      "Id: " +
      id +
      ", Name: " +
      name +
      ", Total Time: " +
      time +
      ", Freeze: " +
      ", Section " +
      section
    );
  }
}
