import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class LoginGUI implements ActionListener {

  private String toast;

  JFrame frame = null;
  JPanel panel1;
  JPanel panel2;

  JTextField text;
  JPasswordField password;

  JButton submit;
  JButton admin;
  JButton teacher;
  JButton student;

  JCheckBox cb;

  LoginGUI() {
    if (Main.activeRole == 1) {
      toast = "Admin Login";
    } else if (Main.activeRole == 2) {
      toast = "Teacher Login";
    } else {
      toast = "Student Login";
    }
  }

  void display() {
    frame = new JFrame("Login");
    new Toast(frame, toast).show();
    JPanel panel = new JPanel();
    panel1 = new JPanel();
    panel2 = new JPanel();

    JLabel labelText = new JLabel("ID");
    text = new JTextField();
    JLabel labelPass = new JLabel("Password");
    password = new JPasswordField();

    submit = new JButton("LOGIN");
    admin = new JButton("Login As Admin");
    teacher = new JButton("Login As Teacher");
    student = new JButton("Login As Student");

    JLabel labelCheck = new JLabel("Remember Me");
    cb = new JCheckBox();

    labelText.setBounds(200, 500, 100, 38);
    text.setBounds(400, 500, 200, 30);
    labelPass.setBounds(200, 570, 100, 30);
    password.setBounds(400, 570, 200, 30);
    labelCheck.setBounds(430, 640, 100, 30);
    cb.setBounds(400, 640, 20, 30);

    submit.setBounds(400, 700, 100, 30);

    admin.setBounds(700, 30, 200, 40);
    student.setBounds(350, 770, 200, 40);
    teacher.setBounds(350, Main.activeRole == 3 ? 770 : 820, 200, 40);

    panel1.add(labelText);
    panel1.add(text);
    panel1.add(labelPass);
    panel1.add(password);
    panel1.add(labelCheck);
    panel1.add(cb);
    panel1.add(submit);

    switch (Main.activeRole) {
      case 3:
        panel2.add(admin);
        panel1.add(teacher);
        break;
      case 2:
        panel2.add(admin);
        panel1.add(student);
        break;
      default:
        panel1.add(teacher);
        panel1.add(student);
        break;
    }

    frame.setLayout(new BorderLayout());

    submit.addActionListener(this);
    admin.addActionListener(this);
    teacher.addActionListener(this);
    student.addActionListener(this);

    panel1.setLayout(null);
    panel2.setLayout(null);

    panel.add(panel1);
    panel.add(panel2);
    panel.setLayout(new GridLayout(1, 2));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(1980, 1080);
    frame.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == admin) {
      Main.activeRole = 1;
      showToast("Admin Login");
    } else if (e.getSource() == teacher) {
      Main.activeRole = 2;
      showToast("Teacher Login");
    } else if (e.getSource() == student) {
      Main.activeRole = 3;
      showToast("Student Login");
    } else if (e.getSource() == submit) {
      handleLogin();
    }
  }

  private void showToast(String message) {
    frame.dispose();
    toast = message;
    this.display();
  }

  private void handleLogin() {
    String text = this.text.getText();
    String password = String.valueOf(this.password.getPassword());

    if (text.trim().isEmpty()) {
      new Toast(frame, "ID is Empty").show();
    } else if (!isNumber(text)) {
      new Toast(frame, "Enter A Valid Id").show();
    } else if (password.trim().isEmpty()) {
      new Toast(frame, "Empty Password").show();
    } else {
      Login lg = new Login();
      boolean check = lg.auth(
        Integer.parseInt(text),
        password,
        cb.isSelected()
      );
      if (!check) {
        new Toast(frame, "Incorrect Id or Password").show();
      } else {
        // new Toast(frame, "Login Successful").show();
        handleMenu();
      }
    }
  }

  void handleMenu() {
    if (frame != null) {
      frame.dispose();
    }
    switch (Main.activeRole) {
      case 1:
        new AdminMenuGUI().display();
        break;
      case 2:
        new TeacherMenuGUI().display();
        break;
      case 3:
        new StudentMenuGUI().display();
        break;
      default:
        break;
    }
  }

  public static boolean isNumber(String text) {
    try {
      Integer.parseInt(text);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
