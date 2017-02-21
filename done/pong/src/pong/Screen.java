package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Screen extends JPanel {
  static final int WIDTH = 800;
  static final int HEIGHT = 600;

  private Paddle player1;
  private Paddle player2;

  private Ball ball;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);

    player1 = new Paddle(true);
    player2 = new Paddle(false);

    ball = new Ball();
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    player1.draw(g2d);
    player2.draw(g2d);
    ball.draw(g2d);
  }

  public void tick(boolean[] keys) {
    player1.tick(keys);
    player2.tick(keys);
    ball.tick();
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
