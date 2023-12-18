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

  void login(int id, String password, boolean check) {
    System.out.println("Enter Login Credential");
    this.id = id;
    this.password = password;
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
        // this.login();
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
          // this.auth();
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

  boolean auth(int id, String password, boolean remember) {
    Helper.retrieveData();
    boolean check = false;
    if (Main.activeRole == 1) {
      for (Admin ls : Helper.al) {
        if (ls.getId() == id && ls.getPassword().equals(password)) {
          Main.activeId = ls.getId();
          Main.activeName = ls.getName();
          check = true;
          break;
        }
      }
    } else if (Main.activeRole == 2) {
      for (Teacher ls : Helper.tl) {
        if (ls.getId() == id && ls.getPassword().equals(password)) {
          Main.activeId = ls.getId();
          Main.activeName = ls.getName();
          check = true;
          break;
        }
      }
    } else {
      for (Student ls : Helper.sl) {
        if (ls.getId() == id && ls.getPassword().equals(password)) {
          Main.activeId = ls.getId();
          Main.activeName = ls.getName();
          check = true;
          break;
        }
      }
    }
    if (remember) {
      sn.saveSession();
    }
    return check;
  }
}
