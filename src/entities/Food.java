package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import snake.Screen;

public class Food extends Entity {
  private final int SIZE = Screen.CELL_SIZE;
  public Color color;
  private float h, s, b;

  public Food() {
    r = new Random();
    s = 1.0f;
    b = 1.0f;
    h = r.nextFloat();
    color = new Color(Color.HSBtoRGB(h, s, b));
    update();
  }

  @Override
  public void draw(Graphics2D g2d) {
    g2d.setColor(color);
    g2d.fillOval(cellX, cellY, SIZE, SIZE);
  }

  @Override
  public void update() {
    h += 0.03f;
    color = new Color(Color.HSBtoRGB(h, s, b));
    cellX = r.nextInt(Screen.WIDTH / Screen.CELL_SIZE) * Screen.CELL_SIZE;
    cellY = r.nextInt(Screen.HEIGHT / Screen.CELL_SIZE) * Screen.CELL_SIZE;
    for (Entity wall : Screen.walls) {
      if (collide(wall)) {
        update();
      }
    }
    for (Entity bodyPiece : Snake.body) {
      if (collide(bodyPiece)) {
        update();
      }
    }
    if (collide(Snake.self)) {
      update();
    }
  }
}
