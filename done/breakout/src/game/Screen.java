package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

public class Screen extends JPanel {
  static final int WIDTH = 800;
  static final int HEIGHT = 600;

  private Paddle paddle;

  private static Ball ball;

  private ArrayList<Brick> bricks;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);
    paddle = new Paddle();
    ball = new Ball(paddle);
    bricks = new ArrayList<Brick>();
    for(int i = 0; i < 9; i++) {
      bricks.add(new Brick(40 + i * 80, 40));
    }
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    paddle.draw(g2d);
    ball.draw(g2d);
    for(Brick b : bricks) {
      b.draw(g2d);
    }
  }

  public void tick(boolean[] keys) {
    paddle.tick(keys[KeyEvent.VK_LEFT], keys[KeyEvent.VK_RIGHT], keys[KeyEvent.VK_SPACE]);
    ball.tick();
    for(Brick b : bricks) {
      ball.checkHit(b);
    }
  }

  public void render() {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public static void releaseBall() {
    ball.release();
  }
}
