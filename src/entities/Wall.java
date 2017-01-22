package entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall extends Entity {
  private final int SIZE = 20;

  public Wall() {
    super();
  }

  public Wall(int cellX, int cellY) {
    super(cellX, cellY);
  }

  @Override
  public void setPosition(int x, int y) {
    this.cellX = x / SIZE * SIZE;
    this.cellY = y / SIZE * SIZE;
  }

  @Override
  public void draw(Graphics2D g2d) {
    g2d.setColor(new Color(200, 200, 200));
    g2d.fillRect(cellX, cellY, SIZE, SIZE);
  }
}
