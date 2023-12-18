public class Login {

  Session sn;

  Login() {
    sn = new Session();
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
