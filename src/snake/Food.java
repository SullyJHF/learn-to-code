package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Food {
  private Random r;
  private final int SIZE = Snake.SIZE;
  private int x, y;
  public int cellX, cellY;

  public Food() {
    r = new Random();
    update();
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(Color.RED);
    g2d.fillOval(cellX, cellY, SIZE, SIZE);
  }

  public void update() {
    x = r.nextInt(Screen.WIDTH);
    y = r.nextInt(Screen.HEIGHT);
    cellX = x / SIZE * SIZE;
    cellY = y / SIZE * SIZE;
  }
}
