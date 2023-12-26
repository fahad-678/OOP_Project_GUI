import java.io.Serializable;

public class Admin implements Serializable {

  private int id;
  static int idCounter = 0;
  private String name;
  private String password;
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

  Admin() {
    this.id = -1;
    this.name = "";
    this.password = "";
  }

  Admin(String name, String password) {
    this.id = idCounter++;
    this.name = name;
    this.password = password;
  }

  Admin(boolean n) {
    this.addAdmin();
  }

  void addAdmin() {
    name = hp.inputValidatorString("Enter Name:", null);
    password = hp.inputValidatorString("Enter Password:", null);
    id = idCounter++;
  }

  void editAdmin() {
    hp = new Helper("Print");
    name = hp.inputValidatorString("Enter Name:", name);
    password = hp.inputValidatorString("Enter Password:", password);
  }

  void editAdmin(String name, String password) {
    this.name = name;
    this.password = password;
  }

  void printAdmin() {
    System.out.println("Id: " + id + ", Name: " + name);
  }
}
