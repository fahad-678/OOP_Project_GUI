import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminMenuGUI {

  JFrame frame;
  JButton profileBtn;
  JButton adminBtn;
  JButton teacherBtn;
  JButton studentBtn;
  JButton courseBtn;
  JButton announcementBtn;
  JButton timetableBtn;
  JButton logoutBtn;

  void display() {
    frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    panel1.setPreferredSize(new Dimension(300, 100));

    // panel1.setBackground(Color.black);
    // panel2.setBackground(Color.blue);

    profileBtn = new JButton("Profile");
    adminBtn = new JButton("Admin");
    teacherBtn = new JButton("Teacher");
    studentBtn = new JButton("Student");
    courseBtn = new JButton("Course");
    announcementBtn = new JButton("Announcements");
    timetableBtn = new JButton("Timetable");
    logoutBtn = new JButton("Logout");

    panel1.add(profileBtn);
    panel1.add(adminBtn);
    panel1.add(teacherBtn);
    panel1.add(studentBtn);
    panel1.add(courseBtn);
    panel1.add(announcementBtn);
    panel1.add(timetableBtn);
    panel1.add(logoutBtn);

    panel.setLayout(new BorderLayout(5,0));
    panel1.setLayout(new GridLayout(10, 1, 0, 5));

    panel.add(panel1, BorderLayout.WEST);
    panel.add(panel2, BorderLayout.CENTER);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(1980, 1080);
    frame.setVisible(true);
  }
  void adminDis(){

  }
}
