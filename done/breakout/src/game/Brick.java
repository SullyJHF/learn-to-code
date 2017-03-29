package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Brick {
  private final int BRICK_WIDTH = 70;
  private final int BRICK_HEIGHT = 30;
  double x, y;
  double w, h;

  public boolean alive;

  public Brick(double x, double y) {
    this.x = x;
    this.y = y;

    this.w = BRICK_WIDTH;
    this.h = BRICK_HEIGHT;

    this.alive = true;
  }

  public void draw(Graphics2D g2d) {
    if (!alive) return;
    g2d.fillRect((int) x, (int) y, (int) w, (int) h);
  }

  public Shape getBounds() {
    Shape bounds = new Rectangle.Double(this.x, this.y, this.w, this.h);
    return bounds;
  }

  public void destroy() {
    alive = false;
  }
}
