package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Ball {
  private float x, y;
  private float angle;
  private float size;

  private float speed;

  private Color color;

  public Ball() {
    Random r = new Random();
    this.angle = (float) (r.nextFloat() * 2 * Math.PI);
    this.size = 13;
    this.speed = 2;
    this.x = Screen.WIDTH / 2 - this.size / 2;
    this.y = Screen.HEIGHT / 2 - this.size / 2;
    this.color = Color.WHITE;
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(color);
    g2d.fillRect(
        (int) this.x,
        (int) this.y,
        (int) this.size,
        (int) this.size);
  }

  public void tick() {
    if (this.x + this.size > Screen.WIDTH) {
      // game over, one point to left
    } else if (this.x < 0) {
      // game over, one point to right
    }

    if (this.y + this.size > Screen.HEIGHT || this.y < 0) {
      this.angle = (float) (Math.PI - this.angle);
    }

    float d = this.speed;
    float dx = (float) (d * Math.sin(this.angle));
    float dy = (float) (d * Math.cos(this.angle));

    this.x += dx;
    this.y += dy;
  }
}
