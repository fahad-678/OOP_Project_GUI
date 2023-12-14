/*
 * Main
 */
public class Main {

  static int activeId;
  static String activeName;
  static int activeRole;

  public static void main(String[] args) {
    Main main = new Main();
    main.start();
  }

  void start() {
    try {
      Login lg = new Login();
      Helper hp = new Helper();
      lg.loginDisplay();
      if (Main.activeName != null) {
        hp.mainFn();
      }
    } catch (Exception e) {
      System.out.println("Internal Error Restarting");
      e.printStackTrace();
      this.start();
    }
  }
}
