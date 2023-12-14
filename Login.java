public class Login {

  private int id;
  private String password;
  private int role;
  private String[] session;
  Helper hp = new Helper("Validate");
  FileHandling fh = new FileHandling();
  Session sn = new Session();

  Login() {
    this.id = -1;
    this.password = "";
    this.role = -1;
  }

  void login() {
    System.out.println("Enter Login Credential");
    id = hp.inputValidatorInt("Enter id");
    password = hp.inputValidatorString("Enter Password", null);
    this.getRole();
    System.out.println("Login Successful");
    this.printUser();
  }

  void loginDisplay() {
    System.out.println("Welcome");
    session = fh.retrieveSession();
    if (session == null || session.length == 0) {
      System.out.println("No Session Found!");
      if (!fh.retrieveAdmin().isEmpty()) {
        this.login();
      }
    } else {
      System.out.println("Session Found");
      sn.retrieveSession();
      this.printUser();
    }
  }

  void printUser() {
    System.out.println(
      "Id : " +
      Main.activeId +
      ", Name : " +
      Main.activeName +
      ", Role : " +
      Main.activeRole
    );
  }

  void getRole() {
    int n;
    boolean shouldExit = false;
    do {
      n = hp.inputValidatorInt("1. Admin \n2. Teacher \n3. Student\n4. Exit");
      switch (n) {
        case 1:
        case 2:
        case 3:
          this.role = n;
          this.auth();
          shouldExit = true;
          break;
        case 4:
          hp.printExit("Role");
          shouldExit = true;
          break;
        default:
          hp.printInvalid();
          break;
      }
    } while (!shouldExit);
  }

  void auth() {
    boolean check = false;
    if (role == 1) {
      for (Admin ls : Helper.al) {
        if (ls.getId() == this.id && ls.getPassword().equals(this.password)) {
          Main.activeId = ls.getId();
          Main.activeName = ls.getName();
          Main.activeRole = this.role;
          check = true;
          break;
        }
      }
    } else if (role == 2) {
      for (Teacher ls : Helper.tl) {
        if (ls.getId() == this.id && ls.getPassword().equals(this.password)) {
          Main.activeId = ls.getId();
          Main.activeName = ls.getName();
          Main.activeRole = this.role;
          check = true;
          break;
        }
      }
    } else {
      for (Student ls : Helper.sl) {
        if (ls.getId() == this.id && ls.getPassword().equals(this.password)) {
          Main.activeId = ls.getId();
          Main.activeName = ls.getName();
          Main.activeRole = this.role;
          check = true;
          break;
        }
      }
    }
    if (!check) {
      System.out.println("Incorrect Id or Password");
      this.login();
    }
    sn.saveSession();
  }
}
