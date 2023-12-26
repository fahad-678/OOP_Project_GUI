import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminMenuGUI {

  private Admin ad;
  private Teacher tc;
  private Student st;
  private Course co;
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

    adminBtn.addActionListener(e -> adminDis(e));
    teacherBtn.addActionListener(e -> adminDis(e));
    studentBtn.addActionListener(e -> adminDis(e));
    courseBtn.addActionListener(e -> adminDis(e));
    announcementBtn.addActionListener(e -> adminDis(e));
    timetableBtn.addActionListener(e -> adminDis(e));
    logoutBtn.addActionListener(e -> logout());

    add = new JButton("Add");
    edit = new JButton("Edit");
    delete = new JButton("Delete");
    addCourse = new JButton("Add Course");
    deleteCourse = new JButton("Delete Course");
    reload = new JButton("Reload");

    add.addActionListener(e -> actionListener(e));
    edit.addActionListener(e -> actionListener(e));
    delete.addActionListener(e -> actionListener(e));
    addCourse.addActionListener(e -> actionListener(e));
    deleteCourse.addActionListener(e -> actionListener(e));

    panel1.add(profileBtn);
    panel1.add(adminBtn);
    panel1.add(teacherBtn);
    panel1.add(studentBtn);
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

  void adminDis(ActionEvent e) {
    if (e.getSource() == adminBtn) {
      role = 1;
    } else if (e.getSource() == teacherBtn) {
      role = 2;
    } else if (e.getSource() == studentBtn) {
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

    if (role == 3 || role == 2) {
      panel3.add(add);
      panel3.add(edit);
      panel3.add(delete);
      panel3.add(addCourse);
    } else if (role == 5) {
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
          delete();
        } else if (e.getSource() == add) {
          admin(false);
        } else {
          admin(true);
        }
        break;
      case 2:
        if (e.getSource() == delete) {
          deleteTeacher();
        } else if (e.getSource() == add) {
          teacher(false);
        } else if (e.getSource() == edit) {
          teacher(true);
        } else if (e.getSource() == addCourse) {
          if (list.getSelectedIndex() != -1) {
            String selectedTeacher = listModel.getElementAt(
              list.getSelectedIndex()
            );
            String[] parts = selectedTeacher.split(", Name: ");
            String idString = parts[0].substring(4);
            int teacherId = Integer.parseInt(idString.trim());
            for (Teacher ls : Helper.tl) {
              if (ls.getId() == teacherId) {
                tc = ls;
                break;
              }
            }
            teacherCourse();
          } else {
            JOptionPane.showMessageDialog(
              frame,
              "No Selected Teacher.",
              "Error",
              JOptionPane.ERROR_MESSAGE
            );
          }
        }
        break;
      case 3:
        if (e.getSource() == delete) {
          deleteStudent();
        } else if (e.getSource() == add) {
          student(false);
        } else if (e.getSource() == edit) {
          student(true);
        } else if (e.getSource() == addCourse) {
          if (list.getSelectedIndex() != -1) {
            String selectedStudent = listModel.getElementAt(
              list.getSelectedIndex()
            );
            String[] parts = selectedStudent.split(", Name: ");
            String idString = parts[0].substring(4);
            int studentId = Integer.parseInt(idString.trim());
            for (Student ls : Helper.sl) {
              if (ls.getId() == studentId) {
                st = ls;
                break;
              }
            }
            studentCourse();
          } else {
            JOptionPane.showMessageDialog(
              frame,
              "No Selected Student.",
              "Error",
              JOptionPane.ERROR_MESSAGE
            );
          }
        }
        break;
      case 4:
        if (e.getSource() == delete) {
          deleteCourse();
        } else if (e.getSource() == add) {
          course(false);
        } else {
          course(true);
        }
        break;
      case 5:
        if (e.getSource() == delete) {
          deleteAnnouncement();
        } else if (e.getSource() == add) {
          announcement();
        } else {
          this.updateList();
          frame.revalidate();
        }
        break;
      default:
        break;
    }
  }

  void admin(boolean edit) {
    frame.setEnabled(false); // Disable the main frame

    JFrame popupFrame = new JFrame("Add Admin");
    popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    popupFrame.setSize(500, 500);
    popupFrame.setLocationRelativeTo(null); // Center the frame

    JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password:");
    JTextField passwordField = new JTextField();

    if (edit) {
      int selectedIndex = list.getSelectedIndex();
      if (selectedIndex != -1) {
        String selectedAdmin = listModel.getElementAt(selectedIndex);
        // Split the selected string to get the ID and Name separately
        String[] parts = selectedAdmin.split(", Name: ");
        String idString = parts[0].substring(4); // Extracting the ID
        int adminId = Integer.parseInt(idString.trim()); // Convert ID to integer
        for (Admin ls : Helper.al) {
          if (ls.getId() == adminId) {
            ad = ls;
            nameField.setText(ls.getName());
            passwordField.setText(ls.getPassword());
            break;
          }
        }
      } else {
        JOptionPane.showMessageDialog(frame, "Please select an admin to edit.");
        frame.setEnabled(true);
        return;
      }
    }

    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(passwordLabel);
    panel.add(passwordField);

    JButton addButton = new JButton(edit ? "Edit" : "Add");
    addButton.addActionListener(e -> {
      String name = nameField.getText();
      String password = passwordField.getText();
      if (name.trim().isEmpty()) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter a valid name.",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else if (password.trim().isEmpty()) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter Password.",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else {
        if (edit) {
          for (Admin ls : Helper.al) {
            if (ls.getId() == ad.getId()) {
              ls.editAdmin(name, password);
              break;
            }
          }
        } else {
          Helper.al.add(new Admin(name, password));
        }
        fh.writeAdmin(Helper.al);
        popupFrame.dispose();
        updateList();
        frame.revalidate();
        frame.setEnabled(true);
        new Toast(frame, edit ? "Admin Edited" : "Admin Added").show();
      }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> {
      popupFrame.dispose();
      frame.setEnabled(true); // Enable the main frame after closing the popup
    });

    panel.add(addButton);
    panel.add(cancelButton);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }

  void delete() {
    int selectedIndex = list.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedAdmin = listModel.getElementAt(selectedIndex);
      // Split the selected string to get the ID and Name separately
      String[] parts = selectedAdmin.split(", Name: ");
      String idString = parts[0].substring(4); // Extracting the ID
      int adminId = Integer.parseInt(idString.trim()); // Convert ID to integer

      int dialogResult = JOptionPane.showConfirmDialog(
        frame,
        "Are you Sure",
        "Delete",
        JOptionPane.YES_NO_OPTION
      );
      if (dialogResult == JOptionPane.YES_OPTION) {
        for (Admin ls : Helper.al) {
          if (ls.getId() == adminId) {
            Helper.al.remove(ls);
            break;
          }
        }
        fh.writeAdmin(Helper.al);
      }
    } else {
      JOptionPane.showMessageDialog(frame, "Please select an admin to Delete.");
      return;
    }
    updateList();
    frame.revalidate();
  }

  void teacher(boolean edit) {
    frame.setEnabled(false); // Disable the main frame

    JFrame popupFrame = new JFrame("Add Teacher");
    popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    popupFrame.setSize(500, 500);
    popupFrame.setLocationRelativeTo(null); // Center the frame

    JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password:");
    JTextField passwordField = new JTextField();

    if (edit) {
      int selectedIndex = list.getSelectedIndex();
      if (selectedIndex != -1) {
        String selectedTeacher = listModel.getElementAt(selectedIndex);
        // Split the selected string to get the ID and Name separately
        String[] parts = selectedTeacher.split(", Name: ");
        String idString = parts[0].substring(4); // Extracting the ID
        int teacherId = Integer.parseInt(idString.trim()); // Convert ID to integer
        for (Teacher ls : Helper.tl) {
          if (ls.getId() == teacherId) {
            tc = ls;
            nameField.setText(ls.getName());
            passwordField.setText(ls.getPassword());
            break;
          }
        }
      } else {
        JOptionPane.showMessageDialog(
          frame,
          "Please select an Teacher to edit."
        );
        frame.setEnabled(true);
        return;
      }
    }

    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(passwordLabel);
    panel.add(passwordField);

    JButton addButton = new JButton(edit ? "Edit" : "Add");
    addButton.addActionListener(e -> {
      String name = nameField.getText();
      String password = passwordField.getText();
      if (name.trim().isEmpty()) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter a valid name.",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else if (password.trim().isEmpty()) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter Password.",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else {
        if (edit) {
          for (Teacher ls : Helper.tl) {
            if (ls.getId() == tc.getId()) {
              ls.editTeacher(name, password);
              break;
            }
          }
        } else {
          Helper.tl.add(new Teacher(name, password));
        }
        fh.writeTeacher(Helper.tl);
        popupFrame.dispose();
        updateList();
        frame.revalidate();
        frame.setEnabled(true);
        new Toast(frame, edit ? "Teacher Edited" : "Teacher Added").show();
      }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> {
      popupFrame.dispose();
      frame.setEnabled(true); // Enable the main frame after closing the popup
    });

    panel.add(addButton);
    panel.add(cancelButton);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }

  void deleteTeacher() {
    int selectedIndex = list.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedTeacher = listModel.getElementAt(selectedIndex);
      // Split the selected string to get the ID and Name separately
      String[] parts = selectedTeacher.split(", Name: ");
      String idString = parts[0].substring(4); // Extracting the ID
      int teacherId = Integer.parseInt(idString.trim()); // Convert ID to integer

      int dialogResult = JOptionPane.showConfirmDialog(
        frame,
        "Are you Sure",
        "Delete",
        JOptionPane.YES_NO_OPTION
      );
      if (dialogResult == JOptionPane.YES_OPTION) {
        for (Teacher ls : Helper.tl) {
          if (ls.getId() == teacherId) {
            Helper.tl.remove(ls);
            break;
          }
        }
        fh.writeTeacher(Helper.tl);
      }
    } else {
      JOptionPane.showMessageDialog(
        frame,
        "Please select an Teacher to Delete."
      );
      return;
    }
    updateList();
    frame.revalidate();
  }

  void validatePopupTeacher() {
    validateCourseTeacher();
    validateStudentTeacher();
    popupFrame.validate();
  }

  void validateCourseTeacher() {
    listModel1.clear();
    for (Course ls : Helper.cl) {
      if (tc.getTeachCourse(ls.getId()) == -1) {
        listModel1.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
      }
    }
    list1.setModel(listModel1);
  }

  void validateStudentTeacher() {
    listModel2.clear();
    for (Course ls : Helper.cl) {
      if (tc.getTeachCourse(ls.getId()) != -1) {
        ls.printDetails();
        listModel2.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
      }
    }
    list2.setModel(listModel2);
  }

  void addCourseListenerTeacher() {
    int selectedIndex = list1.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedTeacher = listModel1.getElementAt(selectedIndex);

      String[] parts = selectedTeacher.split(", Name: ");
      String idString = parts[0].substring(4);
      int courseId = Integer.parseInt(idString.trim());
      for (Course ls : Helper.cl) {
        if (ls.getId() == courseId) {
          Helper.tl.remove(tc);
          tc.addCourse(ls.getId());
          Helper.tl.add(tc);
          break;
        }
      }
      fh.writeTeacher(Helper.tl);
    } else {
      JOptionPane.showMessageDialog(frame, "Please select an Course to Add.");
    }
    validatePopupTeacher();
  }

  void deleteCourseListenerTeacher() {
    int selectedIndex = list2.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedTeacher = listModel2.getElementAt(selectedIndex);

      String[] parts = selectedTeacher.split(", Name: ");
      String idString = parts[0].substring(4);
      int courseId = Integer.parseInt(idString.trim());
      for (Course ls : Helper.cl) {
        if (ls.getId() == courseId) {
          Helper.tl.remove(tc);
          tc.deleteCourse(ls.getId());
          Helper.tl.add(tc);
          break;
        }
      }
      fh.writeTeacher(Helper.tl);
    } else {
      JOptionPane.showMessageDialog(
        frame,
        "Please select an Course to Delete."
      );
    }
    validatePopupTeacher();
  }

  void teacherCourse() {
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
    JButton deleteBtn = new JButton("Delete Course");

    validateCourseTeacher();
    validateStudentTeacher();

    addBtn.addActionListener(e -> addCourseListenerTeacher());
    deleteBtn.addActionListener(e -> deleteCourseListenerTeacher());

    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 0.4; // 40% height
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(scrollPane1, gbc);

    gbc.weighty = 0.1; // 10% height
    gbc.gridy = 1;
    panel.add(addBtn, gbc);

    gbc.gridy = 2;
    panel.add(deleteBtn, gbc);

    gbc.weighty = 0.4; // 40% height
    gbc.gridy = 3;
    panel.add(scrollPane2, gbc);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }

  void student(boolean edit) {
    frame.setEnabled(false); // Disable the main frame

    JFrame popupFrame = new JFrame("Add Student");
    popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    popupFrame.setSize(500, 500);
    popupFrame.setLocationRelativeTo(null); // Center the frame

    JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField();
    JLabel fNameLabel = new JLabel("Father name:");
    JTextField fNameField = new JTextField();
    JLabel ageLabel = new JLabel("Age:");
    JTextField ageField = new JTextField();
    JLabel addressLabel = new JLabel("Address:");
    JTextField addressField = new JTextField();
    JLabel sectionLabel = new JLabel("Section:");
    JComboBox<String> sectionField = new JComboBox<>(Main.sections);
    JLabel passwordLabel = new JLabel("Password:");
    JTextField passwordField = new JTextField();

    if (edit) {
      int selectedIndex = list.getSelectedIndex();
      if (selectedIndex != -1) {
        String selectedStudent = listModel.getElementAt(selectedIndex);
        // Split the selected string to get the ID and Name separately
        String[] parts = selectedStudent.split(", Name: ");
        String idString = parts[0].substring(4); // Extracting the ID
        int studentId = Integer.parseInt(idString.trim()); // Convert ID to integer
        for (Student ls : Helper.sl) {
          if (ls.getId() == studentId) {
            st = ls;
            nameField.setText(ls.getName());
            fNameField.setText(ls.getFName());
            ageField.setText(String.valueOf(ls.getAge()));
            addressField.setText(ls.getAddress());
            sectionField.setSelectedItem(ls.getSection());
            passwordField.setText(ls.getPassword());
            break;
          }
        }
      } else {
        JOptionPane.showMessageDialog(
          frame,
          "Please select a student to edit."
        );
        frame.setEnabled(true);
        return;
      }
    }

    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(fNameLabel);
    panel.add(fNameField);
    panel.add(ageLabel);
    panel.add(ageField);
    panel.add(addressLabel);
    panel.add(addressField);
    panel.add(sectionLabel);
    panel.add(sectionField);
    panel.add(passwordLabel);
    panel.add(passwordField);

    JButton addButton = new JButton(edit ? "Edit" : "Add");
    addButton.addActionListener(e -> {
      String name = nameField.getText();
      String fName = fNameField.getText();
      String age = ageField.getText();
      String address = addressField.getText();
      String section = String.valueOf(sectionField.getSelectedItem());
      String password = passwordField.getText();
      // You can retrieve other fields' values similarly...

      if (
        name.trim().isEmpty() ||
        password.trim().isEmpty() ||
        address.trim().isEmpty() ||
        section.trim().isEmpty() ||
        password.trim().isEmpty() ||
        age.trim().isEmpty()
      ) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter valid data.NO Filed Should Be Empty",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else if (!LoginGUI.isNumber(age)) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter Valid Age",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else {
        if (edit) {
          for (Student ls : Helper.sl) {
            if (ls.getId() == st.getId()) {
              // Update other fields' values in a similar manner...
              ls.editStudent(
                name,
                fName,
                Integer.parseInt(age),
                address,
                section,
                password
              );
              break;
            }
          }
        } else {
          // Fetch other fields' values and create a new Student object...
          Helper.sl.add(
            new Student(
              name,
              fName,
              Integer.parseInt(age),
              address,
              section,
              password
            )
          );
        }
        fh.writeStudent(Helper.sl);
        popupFrame.dispose();
        updateList();
        frame.revalidate();
        frame.setEnabled(true);
        new Toast(frame, edit ? "Student Edited" : "Student Added").show();
      }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> {
      popupFrame.dispose();
      frame.setEnabled(true); // Enable the main frame after closing the popup
    });

    panel.add(addButton);
    panel.add(cancelButton);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }

  void deleteStudent() {
    int selectedIndex = list.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedStudent = listModel.getElementAt(selectedIndex);
      // Split the selected string to get the ID and Name separately
      String[] parts = selectedStudent.split(", Name: ");
      String idString = parts[0].substring(4); // Extracting the ID
      int studentId = Integer.parseInt(idString.trim()); // Convert ID to integer

      int dialogResult = JOptionPane.showConfirmDialog(
        frame,
        "Are you Sure",
        "Delete",
        JOptionPane.YES_NO_OPTION
      );
      if (dialogResult == JOptionPane.YES_OPTION) {
        for (Student ls : Helper.sl) {
          if (ls.getId() == studentId) {
            Helper.sl.remove(ls);
            break;
          }
        }
        fh.writeStudent(Helper.sl);
      }
    } else {
      JOptionPane.showMessageDialog(
        frame,
        "Please select a Student to Delete."
      );
      return;
    }
    updateList();
    frame.revalidate();
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
        ls.printDetails();
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

  void deleteCourseListener() {
    int selectedIndex = list2.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedTeacher = listModel2.getElementAt(selectedIndex);

      String[] parts = selectedTeacher.split(", Name: ");
      String idString = parts[0].substring(4);
      int courseId = Integer.parseInt(idString.trim());
      for (Course ls : Helper.cl) {
        if (ls.getId() == courseId) {
          Helper.sl.remove(st);
          st.deleteCourse(ls.getId());
          Helper.sl.add(st);
          break;
        }
      }
      fh.writeStudent(Helper.sl);
    } else {
      JOptionPane.showMessageDialog(
        frame,
        "Please select an Course to Delete."
      );
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
    JButton deleteBtn = new JButton("Delete Course");

    this.validateCourse();
    this.validateStudent();

    addBtn.addActionListener(e -> addCourseListener());
    deleteBtn.addActionListener(e -> deleteCourseListener());

    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 0.4; // 40% height
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(scrollPane1, gbc);

    gbc.weighty = 0.1; // 10% height
    gbc.gridy = 1;
    panel.add(addBtn, gbc);

    gbc.gridy = 2;
    panel.add(deleteBtn, gbc);

    gbc.weighty = 0.4; // 40% height
    gbc.gridy = 3;
    panel.add(scrollPane2, gbc);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }

  void announcement() {
    frame.setEnabled(false); // Disable the main frame

    JFrame popupFrame = new JFrame("Add Announcement");
    popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    popupFrame.setSize(500, 300); // Set the initial size
    popupFrame.setLocationRelativeTo(null); // Center the frame

    JPanel panel = new JPanel(new BorderLayout());

    JTextArea nameField = new JTextArea();
    panel.add(new JScrollPane(nameField), BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Button panel with right alignment
    JButton addButton = new JButton("Add");
    addButton.setPreferredSize(new Dimension(100, 40)); // Set preferred size
    addButton.addActionListener(e -> {
      String text = nameField.getText();

      if (text.trim().isEmpty()) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter valid Announcement",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else {
        Helper.acl.add(new Announcement(text));
      }
      fh.writeAnnouncement(Helper.acl);
      popupFrame.dispose();
      updateList();
      frame.revalidate();
      frame.setEnabled(true);
      new Toast(frame, "Announcement Added").show();
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setPreferredSize(new Dimension(100, 40)); // Set preferred size
    cancelButton.addActionListener(e -> {
      popupFrame.dispose();
      frame.setEnabled(true); // Enable the main frame after closing the popup
    });

    buttonPanel.add(addButton);
    buttonPanel.add(cancelButton);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }

  void deleteAnnouncement() {
    int selectedIndex = list.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedAnnouncement = listModel.getElementAt(selectedIndex);
      // Split the selected string to get the ID and Name separately
      String[] parts = selectedAnnouncement.split(", Announcement: ");
      String idString = parts[0].substring(4); // Extracting the ID
      int announcementId = Integer.parseInt(idString.trim()); // Convert ID to integer

      int dialogResult = JOptionPane.showConfirmDialog(
        frame,
        "Are you Sure",
        "Delete",
        JOptionPane.YES_NO_OPTION
      );
      if (dialogResult == JOptionPane.YES_OPTION) {
        for (Announcement ls : Helper.acl) {
          if (ls.getId() == announcementId) {
            Helper.acl.remove(ls);
            break;
          }
        }
        fh.writeAnnouncement(Helper.acl);
      }
    } else {
      JOptionPane.showMessageDialog(
        frame,
        "Please select a Announcement to Delete."
      );
      return;
    }
    updateList();
    frame.revalidate();
  }

  void course(boolean edit) {
    frame.setEnabled(false); // Disable the main frame

    JFrame popupFrame = new JFrame("Add Course");
    popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    popupFrame.setSize(500, 500);
    popupFrame.setLocationRelativeTo(null); // Center the frame

    JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

    JLabel nameLabel = new JLabel("Course Name:");
    JTextField nameField = new JTextField();
    JLabel timeLabel = new JLabel("Time:");
    JTextField timeField = new JTextField();
    JLabel sectionLabel = new JLabel("Section:");
    JComboBox<String> sectionField = new JComboBox<>(Main.sections);

    if (edit) {
      int selectedIndex = list.getSelectedIndex();
      if (selectedIndex != -1) {
        String selectedCourse = listModel.getElementAt(selectedIndex);
        // Split the selected string to get the ID and Name separately
        String[] parts = selectedCourse.split(", Name: ");
        String idString = parts[0].substring(4); // Extracting the ID
        int courseId = Integer.parseInt(idString.trim()); // Convert ID to integer
        for (Course course : Helper.cl) {
          if (course.getId() == courseId) {
            co = course;
            nameField.setText(course.getName());
            timeField.setText(course.getTime());
            sectionField.setSelectedItem(course.getSection());
            break;
          }
        }
      } else {
        JOptionPane.showMessageDialog(frame, "Please select a course to edit.");
        frame.setEnabled(true);
        return;
      }
    }

    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(timeLabel);
    panel.add(timeField);
    panel.add(sectionLabel);
    panel.add(sectionField);

    JButton addButton = new JButton(edit ? "Edit" : "Add");
    addButton.addActionListener(e -> {
      String name = nameField.getText();
      String time = timeField.getText();
      String section = String.valueOf(sectionField.getSelectedItem());

      if (
        name.trim().isEmpty() ||
        time.trim().isEmpty() ||
        section.trim().isEmpty()
      ) {
        JOptionPane.showMessageDialog(
          popupFrame,
          "Please enter valid data. No field should be empty.",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else {
        if (edit) {
          for (Course course : Helper.cl) {
            if (course.getId() == co.getId()) {
              course.editCourse(name, time, section);
              break;
            }
          }
        } else {
          Helper.cl.add(new Course(name, time, section));
        }
        fh.writeCourse(Helper.cl);
        popupFrame.dispose();
        updateList();
        frame.revalidate();
        frame.setEnabled(true);
        new Toast(frame, edit ? "Course Edited" : "Course Added").show();
      }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> {
      popupFrame.dispose();
      frame.setEnabled(true); // Enable the main frame after closing the popup
    });

    panel.add(addButton);
    panel.add(cancelButton);

    popupFrame.add(panel);
    popupFrame.setVisible(true);
  }

  void deleteCourse() {
    int selectedIndex = list.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedCourse = listModel.getElementAt(selectedIndex);
      // Split the selected string to get the ID and Name separately
      String[] parts = selectedCourse.split(", Name: ");
      String idString = parts[0].substring(4); // Extracting the ID
      int courseId = Integer.parseInt(idString.trim()); // Convert ID to integer

      int dialogResult = JOptionPane.showConfirmDialog(
        frame,
        "Are you sure you want to delete this course?",
        "Delete Course",
        JOptionPane.YES_NO_OPTION
      );

      if (dialogResult == JOptionPane.YES_OPTION) {
        for (Course course : Helper.cl) {
          if (course.getId() == courseId) {
            Helper.cl.remove(course);
            break;
          }
        }
        fh.writeCourse(Helper.cl);
      }
    } else {
      JOptionPane.showMessageDialog(frame, "Please select a course to delete.");
      return;
    }
    updateList();
    frame.revalidate();
  }
  
}
