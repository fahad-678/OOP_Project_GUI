import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdminMenuGUI {

  private JList<String> adminList;
  private DefaultListModel<String> listModel;
  private JScrollPane scrollPane;
  FileHandling fh = new FileHandling();

  JFrame frame;
  JPanel panel2;
  JPanel panel3;

  JButton add;
  JButton edit;
  JButton delete;

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
    adminList = new JList<>(listModel);
    adminList.setFont(new Font("Arial", Font.PLAIN, 26));
    scrollPane = new JScrollPane(adminList);

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

    adminBtn.addActionListener(e -> adminDis());

    add = new JButton("Add");
    edit = new JButton("Edit");
    delete = new JButton("Delete");

    add.addActionListener(e -> addAdmin(false));
    edit.addActionListener(e -> addAdmin(true));

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
    for (Admin ls : Helper.al) {
      listModel.addElement("ID: " + ls.getId() + ", Name: " + ls.getName());
    }
    adminList.setModel(listModel);
  }

  void adminDis() {
    this.updateList();

    panel3.add(add);
    panel3.add(edit);
    panel3.add(delete);

    panel3.setLayout(new GridLayout(1, 3));
    panel3.setPreferredSize(new Dimension(0, 100));

    panel2.add(scrollPane, BorderLayout.CENTER);
    panel2.add(panel3, BorderLayout.SOUTH);
    // panel2.add(panel4, BorderLayout.WEST);

    frame.revalidate();
  }

  void admin() {
    frame.revalidate();
  }

  void addAdmin(boolean edit) {
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
      int selectedIndex = adminList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedAdmin = listModel.getElementAt(selectedIndex);
            // Split the selected string to get the ID and Name separately
            String[] parts = selectedAdmin.split(", Name: ");
            String idString = parts[0].substring(4); // Extracting the ID
            int adminId = Integer.parseInt(idString.trim()); // Convert ID to integer

        } else {
            JOptionPane.showMessageDialog(frame, "Please select an admin to edit.");
        }
      nameField.setText(null);
      passwordField.setText(null);
    }

    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(passwordLabel);
    panel.add(passwordField);

    JButton addButton = new JButton("Add");
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
        Helper.al.add(new Admin(name, password));
        fh.writeAdmin(Helper.al);
        popupFrame.dispose();
        updateList();
        frame.revalidate();
        frame.setEnabled(true);
        new Toast(frame, "Admin Added").show();
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
}
