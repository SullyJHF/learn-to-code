package entities;

import java.awt.Graphics2D;
import java.util.Random;

public class Entity {
  public static Random r;
  protected int cellX, cellY;

  public Entity() {
    this.r = new Random();
  }

  public Entity(int cellX, int cellY) {
    this.r = new Random();
    this.cellX = cellX;
    this.cellY = cellY;
  }

  public void setPosition(int cellX, int cellY) {
    this.cellX = cellX;
    this.cellY = cellY;
  }

  public void update() {}

  public void draw(Graphics2D g2d) {}

  public boolean collide(Entity entity) {
    if (entity.cellX == this.cellX && entity.cellY == this.cellY)
      return true;
    return false;
  }
}
