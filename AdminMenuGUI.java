import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminMenuGUI {

  JFrame frame;

  void display() {
    frame = new JFrame();
    JPanel panel = new JPanel();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(1980, 1080);
    frame.setVisible(true);
  }
}
