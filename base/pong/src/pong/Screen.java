package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Screen extends JPanel {
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
  }

  public void tick(boolean[] keys) {

  }

  public void render() {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }
}
