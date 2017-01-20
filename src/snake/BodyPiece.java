package snake;

import java.awt.Graphics2D;

public class BodyPiece {
  private int cellX, cellY;
  public int prevCellX, prevCellY;

  public BodyPiece(int cellX, int cellY) {
    this.cellX = cellX;
    this.cellY = cellY;
  }

  public void update(int nextCellX, int nextCellY) {
    this.prevCellX = this.cellX;
    this.prevCellY = this.cellY;
    this.cellX = nextCellX;
    this.cellY = nextCellY;
  }

  public void draw(Graphics2D g2d) {
    g2d.fillRect(cellX + Snake.BORDER, cellY + Snake.BORDER, Snake.SIZE - 2 * Snake.BORDER, Snake.SIZE - 2 * Snake.BORDER);
  }
}
