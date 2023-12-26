import java.io.Serializable;
import java.time.LocalDateTime;

public class Announcement implements Serializable {

  private int id;
  static int idCounter = 0;
  private String text;
  private int createdBy;
  private String createdByRole;
  private String createdAt;
  private LocalDateTime validTill;
  transient Helper hp = new Helper("Validator");

  int getId() {
    return id;
  }

  String getText() {
    return text;
  }

  int getCreatedBy() {
    return createdBy;
  }

  String getCreatedByRole() {
    return createdByRole;
  }

  String getCreatedAt() {
    return createdAt;
  }

  Announcement(String text) {
    this.text = text;
    createdBy = Main.activeId;
    createdByRole = Main.activeRole == 1 ? "Admin" : "Teacher";
    createdAt = LocalDateTime.now().toString();
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
