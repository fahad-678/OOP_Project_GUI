/*
 * Main
 */
public class Main {

  static int activeId;
  static String activeName;
  static int activeRole = 3;

  static String[] sections = { "Bcs-3B", "Bcs-4A" };

  public static void main(String[] args) {
    Helper.retrieveData();
    FileHandling fh = new FileHandling();
    String session[] = fh.retrieveSession();
    if (session == null || session.length == 0) {
      new LoginGUI().display();
    } else {
      new Session().retrieveSession();
      new LoginGUI().handleMenu();
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
