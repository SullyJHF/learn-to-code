package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import snake.Screen;

public class Wall extends Entity {
  private final int SIZE = Screen.CELL_SIZE;

  public Wall() {
    super();
  }

  public Wall(int cellX, int cellY) {
    super(cellX, cellY);
  }

  @Override
  public void setPosition(int cellX, int cellY) {
    this.cellX = cellX;
    this.cellY = cellY;
  }

  @Override
  public void draw(Graphics2D g2d) {
    g2d.setColor(new Color(75, 75, 75));
    g2d.fillRect(cellX, cellY, SIZE, SIZE);
  }
}
