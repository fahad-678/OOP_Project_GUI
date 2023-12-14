public class Session {

  private String[] session;
  FileHandling fh = new FileHandling();

  Session() {
    session = new String[3];
  }

  void retrieveSession() {
    session = fh.retrieveSession();
    Main.activeId = Integer.parseInt(session[0]);
    Main.activeName = session[1];
    Main.activeRole = Integer.parseInt(session[2]);
    System.out.println("Session Retrieved");
  }

  void saveSession() {
    session[0] = String.valueOf(Main.activeId);
    session[1] = Main.activeName;
    session[2] = String.valueOf(Main.activeRole);
    fh.writeSession(session);
    System.out.println("Session Saved");
  }

  void deleteSession() {
    session = null;
    Main.activeId = -1;
    Main.activeName = null;
    Main.activeRole = -1;
    fh.writeSession(session);
    System.out.println("Session Deleted");
  }
}
