package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Paddle {
  private final double INIT_W = 125;
  private final double INIT_H = 20;

  private final double MAX_SPEED = 4;

  public double x, y;
  public double w, h;

  private Color color;

  private double accel;
  private double vel;

  public Paddle() {
    this.w = INIT_W;
    this.h = INIT_H;
    this.x = Screen.WIDTH / 2 - this.w / 2;
    this.y = Screen.HEIGHT - this.h * 2;

    this.accel = 0.3;
    this.vel = 0;

    this.color = Color.WHITE;
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(this.color);
    g2d.fillRect(
        (int) this.x,
        (int) this.y,
        (int) this.w,
        (int) this.h);
  }

  public void tick(boolean left, boolean right, boolean use) {
    if(left) {
      this.vel -= this.accel;
    } else if(right) {
      this.vel += this.accel;
    } else {
      this.vel *= 0.9;
    }

    if(this.vel > MAX_SPEED) {
      this.vel = MAX_SPEED;
    } else if(this.vel < -MAX_SPEED) {
      this.vel = -MAX_SPEED;
    }

    this.x += this.vel;

    if(this.x < 0) {
      this.x = 0;
      this.vel = -this.vel * 0.75;
    } else if(this.x >= Screen.WIDTH - this.w) {
      this.x = Screen.WIDTH - this.w;
      this.vel = -this.vel * 0.75;
    }

    if(use) {
      Screen.releaseBall();
    }
  }

  public Shape getBounds() {
    Shape bounds = new Rectangle.Double(this.x, this.y, this.w, this.h);
    return bounds;
  }
}
