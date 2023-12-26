import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

  private JList<String> list1;
  private DefaultListModel<String> listModel1;
  private JScrollPane scrollPane1;

  private JList<String> list2;
  private DefaultListModel<String> listModel2;
  private JScrollPane scrollPane2;

  FileHandling fh = new FileHandling();

  JFrame frame;
  JFrame popupFrame;
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
    addCourse = new JButton("Add Course");

    add.addActionListener(e -> actionListener(e));
    edit.addActionListener(e -> actionListener(e));
    delete.addActionListener(e -> actionListener(e));
    addCourse.addActionListener(e -> actionListener(e));

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

  void getStudent() {
    for (Student ls : Helper.sl) {
      if (ls.getId() == Main.activeId) {
        st = ls;
        break;
      }
    }
  }

  void updateList() {
    listModel.clear();
    switch (role) {
      case 1:
        for (Student ls : Helper.sl) {
          if (Main.activeId == ls.getId()) {
            listModel.addElement(
              "ID: " + ls.getId() + ", Name: " + ls.getName()
            );
            break;
          }
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
        getStudent();
        for (Course ls : Helper.cl) {
          if (st.getSCL(ls.getId()) != -1) {
            listModel.addElement(
              "ID: " + ls.getId() + ", Name: " + ls.getName()
            );
          }
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

    if (role == 1) {} else if (role == 4) {
      panel3.add(addCourse);
    } else if (role == 5) {
      panel3.add(reload);
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
        studentCourse();
        break;
      case 5:
        this.updateList();
        frame.revalidate();
        break;
      default:
        break;
    }
  }

  void validatePopup() {
    validateCourse();
    validateStudent();
    popupFrame.validate();
  }

  void validateCourse() {
    listModel1.clear();
    for (Course ls : Helper.cl) {
      if (
        ls.getSection().equals(st.getSection()) && st.getSCL(ls.getId()) == -1
      ) {
        listModel1.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
      }
    }
    list1.setModel(listModel1);
  }

  void validateStudent() {
    listModel2.clear();
    for (Course ls : Helper.cl) {
      if (st.getSCL(ls.getId()) != -1) {
        listModel2.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
      }
    }
    list2.setModel(listModel2);
  }

  void addCourseListener() {
    int selectedIndex = list1.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedTeacher = listModel1.getElementAt(selectedIndex);

      String[] parts = selectedTeacher.split(", Name: ");
      String idString = parts[0].substring(4);
      int courseId = Integer.parseInt(idString.trim());
      for (Course ls : Helper.cl) {
        if (ls.getId() == courseId) {
          Helper.sl.remove(st);
          st.addCourse(ls.getId());
          Helper.sl.add(st);
          break;
        }
      }
      fh.writeStudent(Helper.sl);
    } else {
      JOptionPane.showMessageDialog(frame, "Please select an Course to Add.");
    }
    validatePopup();
  }

  void studentCourse() {
    popupFrame = new JFrame("Manage Course");
    popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    popupFrame.setSize(1000, 1000);
    popupFrame.setLocationRelativeTo(null); // Center the frame

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    listModel1 = new DefaultListModel<>();
    list1 = new JList<>(listModel1);
    list1.setFont(new Font("Arial", Font.PLAIN, 26));
    scrollPane1 = new JScrollPane(list1);

    listModel2 = new DefaultListModel<>();
    list2 = new JList<>(listModel2);
    list2.setFont(new Font("Arial", Font.PLAIN, 26));
    scrollPane2 = new JScrollPane(list2);

    JButton addBtn = new JButton("Add Course");

    this.validateCourse();
    this.validateStudent();

    addBtn.addActionListener(e -> addCourseListener());

    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 0.4; // 40% height
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(scrollPane1, gbc);

    gbc.weighty = 0.1; // 10% height
    gbc.gridy = 1;
    panel.add(addBtn, gbc);

    gbc.weighty = 0.4; // 40% height
    gbc.gridy = 3;
    panel.add(scrollPane2, gbc);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }
}
