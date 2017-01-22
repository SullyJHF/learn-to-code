package entities;

import java.awt.Graphics2D;

public class BodyPiece extends Entity {
  public int prevCellX, prevCellY;

  public BodyPiece(int cellX, int cellY) {
    this.cellX = cellX;
    this.cellY = cellY;
  }

  public void move(int nextCellX, int nextCellY) {
    this.prevCellX = this.cellX;
    this.prevCellY = this.cellY;
    this.cellX = nextCellX;
    this.cellY = nextCellY;
  }

  @Override
  public void draw(Graphics2D g2d) {
    g2d.fillRect(
        cellX + Snake.BORDER,
        cellY + Snake.BORDER,
        Snake.SIZE - 2 * Snake.BORDER,
        Snake.SIZE - 2 * Snake.BORDER);
  }
}
