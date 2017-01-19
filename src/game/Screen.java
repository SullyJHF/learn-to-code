package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Screen extends JPanel {
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.GRAY);
  }
}
