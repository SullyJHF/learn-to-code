package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Screen extends JPanel {
  static final int WIDTH = 800;
  static final int HEIGHT = 600;

  private Paddle player1;
  private Paddle player2;

  private static int player1Score;
  private static int player2Score;

  private static Ball ball;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);

    player1 = new Paddle(true);
    player2 = new Paddle(false);

    player1Score = 0;
    player2Score = 0;

    ball = new Ball();
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    player1.draw(g2d);
    player2.draw(g2d);
    ball.draw(g2d);
    printScores(g2d);
  }

  private void printScores(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 36));
    g2d.drawString(String.valueOf(player1Score), 30, 50);
    g2d.drawString(String.valueOf(player2Score), 750, 50);
  }

  public void tick(boolean[] keys) {
    player1.tick(keys);
    player2.tick(keys);
    ball.tick();
    ball.collide(player1);
    ball.collide(player2);
  }

  public void render() {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public static void addPoint(boolean b) {
    if (b) {
      player1Score++;
    } else {
      player2Score++;
    }
    reset();
  }

  private static void reset() {
    ball = new Ball();
  }
}
