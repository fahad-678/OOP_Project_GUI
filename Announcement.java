import java.io.Serializable;
import java.time.LocalDateTime;

public class Announcement implements Serializable {

  private int id;
  static int idCounter = 0;
  private String text;
  private int createdBy;
  private String createdAt;
  private LocalDateTime validTill;
  transient Helper hp = new Helper("Validator");

  int getId() {
    return id;
  }

  Announcement() {
    this.id = -1;
    this.text = "";
  }

  Announcement(boolean n) {
    this.addAnnouncement();
  }

  void addAnnouncement() {
    text = hp.inputValidatorString("Enter text:", null);
    createdBy = Main.activeId;
    createdAt = LocalDateTime.now().toString();
    hp.print("Valid Till");
    validTill = hp.setTime();
    id = idCounter++;
  }

  void printAnnouncement() {
    System.out.println(
      "Id: " +
      id +
      ", Text: " +
      text +
      ", Created By: " +
      createdBy +
      ", Created At: " +
      createdAt +
      ", Valid Till: " +
      validTill
    );
  }
}
