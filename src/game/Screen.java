package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Screen extends JPanel {
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  private double size = 50.0;
  private double inc = 1.0;

  private double x = WIDTH / 2;
  private double y = HEIGHT / 2;

  private double xVel = 2.0;
  private double yVel = 2.0;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.GRAY);
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.fillOval((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);
  }

  public void tick(boolean[] keys) {
    if (size >= HEIGHT || size <= 10) inc = -inc;
    if (x + size / 2 >= WIDTH) {
      x = WIDTH - size / 2;
      xVel = -xVel;
    }
    if (x - size / 2 <= 0) {
      x = size / 2;
      xVel = -xVel;
    }

    if (y + size / 2 >= HEIGHT) {
      y = HEIGHT - size / 2;
      yVel = -yVel;
    }
    if (y - size / 2 <= 0) {
      y = size / 2;
      yVel = -yVel;
    }
    x += xVel;
    y += yVel;
    size += inc;
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
