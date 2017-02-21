package pong;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

public class Paddle {
  private float x, y;
  private float w;
  private float h;
  private Color color;

  private boolean left;

  public Paddle(boolean left) {
    this.left = left;
    this.h = 125;
    this.w = 13;
    this.x = this.left ? this.w : Screen.WIDTH - 2 * this.w;
    this.y = Screen.HEIGHT / 2 - this.h / 2;
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

  public void tick(boolean[] keys) {
    if (this.left) {
      // player 1
      if (keys[KeyEvent.VK_W]) moveUp();
      if (keys[KeyEvent.VK_S]) moveDown();
    } else {
      // player 2
      if (keys[KeyEvent.VK_UP]) moveUp();
      if (keys[KeyEvent.VK_DOWN]) moveDown();
    }
  }

  private void moveUp() {
    if (y > 0)
      this.y--;
  }

  private void moveDown() {
    if (y + h < Screen.HEIGHT)
      this.y++;
  }
}
