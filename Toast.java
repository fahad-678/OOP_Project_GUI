import java.awt.*;
import javax.swing.*;

public class Toast {

  private final JFrame frame;
  private final JLabel label;
  private final Timer timer;

  public Toast(JFrame frame, String message) {
    this.frame = frame;
    this.label = new JLabel(message);
    this.label.setForeground(Color.WHITE);
    this.label.setBackground(Color.GRAY);
    this.label.setOpaque(true);
    this.label.setHorizontalAlignment(SwingConstants.CENTER);
    this.label.setFont(new Font("Arial", Font.BOLD, 14));

    this.timer = new Timer(3000, e -> hide()); // Set desired duration here
  }

  public void show() {
    frame.add(label);
    label.setBounds(850, 10, 300, 50);
    label.setVisible(true);
    timer.start();
  }

  public void hide() {
    timer.stop();
    label.setVisible(false);
    frame.remove(label);
  }
}
