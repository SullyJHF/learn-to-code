package snake;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Snake {
  private final int SIZE = 20;

  private int x;
  private int y;

  public int cellX;
  public int cellY;

  private double xVel = 1.0;
  private double yVel = 0.0;

  public Snake() {
    this.x = 100;
    this.y = 100;
    this.cellX = this.x;
    this.cellY = this.y;
  }

  public void draw(Graphics2D g2d) {
    g2d.fillRect(cellX, cellY, SIZE, SIZE);
  }

  public void update(boolean[] keys) {
    if (keys[KeyEvent.VK_RIGHT] && xVel != -1.0) {
      xVel = 1.0;
      yVel = 0.0;
    }
    if (keys[KeyEvent.VK_LEFT] && xVel != 1.0) {
      xVel = -1.0;
      yVel = 0.0;
    }
    if (keys[KeyEvent.VK_UP] && yVel != 1.0) {
      xVel = 0.0;
      yVel = -1.0;
    }
    if (keys[KeyEvent.VK_DOWN] && yVel != -1.0) {
      xVel = 0.0;
      yVel = 1.0;
    }

    x += xVel;
    y += yVel;
    cellX = (x / SIZE) * SIZE;
    cellY = (y / SIZE) * SIZE;
  }

  public void eat(Food food) {
    if (food.cellX == cellX && food.cellY == cellY) {
      food.update();
    }
  }
}
