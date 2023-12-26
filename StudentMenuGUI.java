import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StudentMenuGUI {

  private Admin ad;
  private Teacher tc;
  private Student st;
  private Course co;
  private Announcement an;
  private int role;

  private JList<String> list;
  private DefaultListModel<String> listModel;
  private JScrollPane scrollPane;
  FileHandling fh = new FileHandling();

  JFrame frame;
  JPanel panel2;
  JPanel panel3;

  JButton add;
  JButton edit;
  JButton delete;
  JButton addCourse;
  JButton editCourse;
  JButton deleteCourse;
  JButton reload;

  JButton profileBtn;
  JButton attendanceBtn;
  JButton assignmentBtn;
  JButton courseBtn;
  JButton announcementBtn;
  JButton timetableBtn;
  JButton logoutBtn;

  void display() {
    frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    panel2 = new JPanel();
    panel3 = new JPanel();
    JPanel nav = new JPanel();
    JPanel footer = new JPanel();

    listModel = new DefaultListModel<>();
    list = new JList<>(listModel);
    list.setFont(new Font("Arial", Font.PLAIN, 26));
    scrollPane = new JScrollPane(list);

    panel1.setPreferredSize(new Dimension(300, 100));
    nav.setPreferredSize(new Dimension(0, 50));
    footer.setPreferredSize(new Dimension(0, 30));

    profileBtn = new JButton("Profile");
    attendanceBtn = new JButton("Attendance");
    assignmentBtn = new JButton("Assignment");
    courseBtn = new JButton("Course Enrolment");
    announcementBtn = new JButton("Announcements");
    timetableBtn = new JButton("Timetable");
    logoutBtn = new JButton("Logout");

    profileBtn.addActionListener(e -> studentDis(e));
    attendanceBtn.addActionListener(e -> studentDis(e));
    assignmentBtn.addActionListener(e -> studentDis(e));
    courseBtn.addActionListener(e -> studentDis(e));
    announcementBtn.addActionListener(e -> studentDis(e));
    logoutBtn.addActionListener(e -> logout());

    add = new JButton("Add");
    edit = new JButton("Edit");
    delete = new JButton("Delete");
    reload = new JButton("Reload");

    add.addActionListener(e -> actionListener(e));
    edit.addActionListener(e -> actionListener(e));
    delete.addActionListener(e -> actionListener(e));

    panel1.add(profileBtn);
    panel1.add(profileBtn);
    panel1.add(attendanceBtn);
    panel1.add(assignmentBtn);
    panel1.add(courseBtn);
    panel1.add(announcementBtn);
    panel1.add(timetableBtn);
    panel1.add(logoutBtn);

    panel.setLayout(new BorderLayout(5, 0));
    panel1.setLayout(new GridLayout(10, 1, 0, 5));
    panel2.setLayout(new BorderLayout());

    panel.add(panel1, BorderLayout.WEST);
    panel.add(panel2, BorderLayout.CENTER);
    panel.add(nav, BorderLayout.NORTH);
    panel.add(footer, BorderLayout.SOUTH);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(1980, 1080);
    frame.setVisible(true);
  }

  void updateList() {
    listModel.clear();
    switch (role) {
      case 1:
        for (Admin ls : Helper.al) {
          listModel.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
        }
        break;
      case 2:
        for (Teacher ls : Helper.tl) {
          listModel.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
        }
        break;
      case 3:
        for (Student ls : Helper.sl) {
          listModel.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
        }
        break;
      case 4:
        for (Course ls : Helper.cl) {
          listModel.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
        }
        break;
      case 5:
        for (Announcement ls : Helper.acl) {
          listModel.addElement(
            "ID: " + ls.getId() + ", Announcement: " + ls.getText()
          );
        }
        break;
      default:
        break;
    }

    list.setModel(listModel);
  }

  void logout() {
    int dialogResult = JOptionPane.showConfirmDialog(
      frame,
      "Are you Sure",
      "Logout",
      JOptionPane.YES_NO_OPTION
    );
    if (dialogResult == JOptionPane.YES_OPTION) {
      frame.dispose();
      new Session().deleteSession();
      new LoginGUI().display();
    }
  }

  void studentDis(ActionEvent e) {
    if (e.getSource() == profileBtn) {
      role = 1;
    } else if (e.getSource() == attendanceBtn) {
      role = 2;
    } else if (e.getSource() == assignmentBtn) {
      role = 3;
    } else if (e.getSource() == courseBtn) {
      role = 4;
    } else if (e.getSource() == announcementBtn) {
      role = 5;
    } else if (e.getSource() == timetableBtn) {
      role = 6;
    }
    this.updateList();

    panel3.removeAll();

    if (role == 5) {
      panel3.add(add);
      panel3.add(reload);
      panel3.add(delete);
    } else {
      panel3.add(add);
      panel3.add(edit);
      panel3.add(delete);
    }

    panel3.setLayout(new GridLayout(1, 3));
    panel3.setPreferredSize(new Dimension(0, 100));

    panel2.add(scrollPane, BorderLayout.CENTER);

    panel2.add(panel3, BorderLayout.SOUTH);

    frame.revalidate();
  }

  void actionListener(ActionEvent e) {
    switch (role) {
      case 1:
        if (e.getSource() == delete) {
          //   delete();
        } else if (e.getSource() == add) {
          //   admin(false);
        } else {
          //   admin(true);
        }
        break;
      case 2:
        if (e.getSource() == delete) {
          //   deleteTeacher();
        } else if (e.getSource() == add) {
          //   teacher(false);
        } else {
          //   teacher(true);
        }
        break;
      case 3:
        if (e.getSource() == delete) {
          //   deleteStudent();
        } else if (e.getSource() == add) {
          //   student(false);
        } else {
          //   student(true);
        }
        break;
      case 4:
        if (e.getSource() == delete) {
          //   deleteCourse();
        } else if (e.getSource() == add) {
          //   course(false);
        } else {
          //   course(true);
        }
        break;
      case 5:
        if (e.getSource() == delete) {
          //   deleteAnnouncement();
        } else if (e.getSource() == add) {
          //   announcement();
        } else {
          this.updateList();
          frame.revalidate();
        }
        break;
      default:
        break;
    }
  }
}
