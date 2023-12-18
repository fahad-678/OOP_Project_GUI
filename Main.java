/*
 * Main
 */
public class Main {

  static int activeId;
  static String activeName;
  static int activeRole = 3;

  public static void main(String[] args) {
    Helper hp = new Helper();
    LoginGUI lg = new LoginGUI();
    FileHandling fh = new FileHandling();
    String session[] = fh.retrieveSession();
    Session sn = new Session();
    if (session == null || session.length == 0) {
      lg.display();
    } else {
      sn.retrieveSession();
      lg.handleMenu();
    }
    //   Main main = new Main();
    //   main.start();
    // }

    // void start() {
    //   try {
    //     Login lg = new Login();
    //     Helper hp = new Helper();
    //     lg.loginDisplay();
    //     if (Main.activeName != null) {
    //       hp.mainFn();
    //     }
    //   } catch (Exception e) {
    //     System.out.println("Internal Error Restarting");
    //     e.printStackTrace();
    //     this.start();
    //   }
  }
}
