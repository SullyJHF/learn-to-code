package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import snake.Screen;

public class Food extends Entity {
  private final int SIZE = Snake.SIZE;
  private int x, y;

  public Food() {
    r = new Random();
    update();
  }

  @Override
  public void draw(Graphics2D g2d) {
    g2d.setColor(Color.RED);
    g2d.fillOval(cellX, cellY, SIZE, SIZE);
  }

  @Override
  public void update() {
    x = r.nextInt(Screen.WIDTH);
    y = r.nextInt(Screen.HEIGHT);
    cellX = x / SIZE * SIZE;
    cellY = y / SIZE * SIZE;
    for(Entity wall : Screen.walls) {
      if(cellX == wall.cellX && cellY == wall.cellY) {
        update();
      }
    }
  }
}
