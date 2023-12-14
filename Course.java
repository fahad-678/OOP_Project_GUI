import java.io.Serializable;

public class Course implements Serializable {

  private int id;
  static int idCounter = 0;
  private String name;
  private String time;
  private boolean freeze;
  private String section;
  private int createdBy;
  transient Helper hp = new Helper("Validator");

  //   private String courseMaterial;

  Course() {
    id = -1;
    name = "";
    time = "";
    freeze = true;
    section = "";
  }

  Course(boolean n) {
    this.addCourse();
  }

  int getId() {
    return id;
  }

  void setFreeze() {
    this.freeze = !freeze;
  }

  int getCreatedBy() {
    return createdBy;
  }

  String getSection() {
    return section;
  }

  void addCourse() {
    name = hp.inputValidatorString("Enter Course Name:", null);
    time = hp.inputValidatorString("Enter Time:", null);
    section = hp.inputValidatorString("Enter Section:", null);
    createdBy = Main.activeId;
    id = idCounter++;
  }

  void editCourse() {
    hp = new Helper("Print");
    name = hp.inputValidatorString("Enter Course Name:", name);
    time = hp.inputValidatorString("Enter Time:", time);
    section = hp.inputValidatorString("Enter Section:", section);
    freeze = hp.inputValidatorBool("Freeze (true/false):");
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
      (freeze ? "true" : "false") +
      ", Section " +
      section
    );
  }
}
