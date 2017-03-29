package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Ball {
  private double x, y, s;

  private double angle, speed;

  private boolean released = false;

  private Paddle paddle;

  public Ball(Paddle paddle) {
    this.paddle = paddle;
    this.s = 20;
    this.x = paddle.x + paddle.w / 2 - this.s / 2;
    this.y = paddle.y - this.s;

    this.angle = Math.toRadians(225);
    this.speed = 3;
  }

  public void tick() {
    if (!released) {
      this.x = this.paddle.x + this.paddle.w / 2 - this.s / 2;
    } else {
      double dx = Math.cos(angle) * speed;
      double dy = Math.sin(angle) * speed;
      this.x += dx;
      this.y += dy;

      if (this.x < 0) {
        this.x = 0;
        this.angle = Math.PI - this.angle;
      } else if (this.x > Screen.WIDTH - this.s) {
        this.x = Screen.WIDTH - this.s;
        this.angle = Math.PI - this.angle;
      }

      if (this.y < 0) {
        this.y = 0;
        this.angle = 2 * Math.PI - this.angle;
      } else if (this.y > Screen.HEIGHT - this.s) {
        // add one to score
      }

      if (paddle.getBounds().intersects((Rectangle2D) getBounds())) {
        this.y = paddle.y - this.s;
        this.angle = 2 * Math.PI - this.angle;
      }
    }
  }

  public void draw(Graphics2D g2d) {
    g2d.fillOval((int) this.x, (int) this.y, (int) this.s, (int) this.s);
  }

  public void release() {
    if (!released) {
      released = true;
    }
  }

  public Shape getBounds() {
    Shape bounds = new Rectangle.Double(this.x, this.y, this.s, this.s);
    return bounds;
  }

  public void checkHit(Brick brick) {
    if (!getBounds().intersects((Rectangle2D) brick.getBounds())) return;
    Point up = new Point((int) (x + s / 2), (int) y);
    Point right = new Point((int) (x + s), (int) (y + s / 2));
    Point left = new Point((int) x, (int) (y + s / 2));
    Point down = new Point((int) (x + s / 2), (int) (y + s));

    if(brick.getBounds().contains(up) || brick.getBounds().contains(down)) {
      this.angle = 2 * Math.PI - this.angle;
    }

    if(brick.getBounds().contains(left) || brick.getBounds().contains(right)) {
      this.angle = Math.PI - this.angle;
    }
  }
}
