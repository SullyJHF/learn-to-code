package maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Cell {
  public int i, j;
  static final int SIZE = 40;
  private Line2D top, right, bottom, left;
  public boolean drawTop, drawRight, drawBottom, drawLeft;

  public boolean visited = false;

  public Cell(int i, int j) {
    this.i = i;
    this.j = j;
    this.top = new Line2D.Double(i * SIZE, j * SIZE, (i + 1) * SIZE, j * SIZE);
    this.right = new Line2D.Double((i + 1) * SIZE, j * SIZE, (i + 1) * SIZE, (j + 1) * SIZE);
    this.bottom = new Line2D.Double((i + 1) * SIZE, (j + 1) * SIZE, i * SIZE, (j + 1) * SIZE);
    this.left = new Line2D.Double(i * SIZE, (j + 1) * SIZE, i * SIZE, j * SIZE);
    drawTop = drawRight = drawBottom = drawLeft = true;
  }

  public void draw(Graphics2D g2d, Color color) {
    g2d.setColor(color);
    if (drawTop) g2d.draw(this.top);
    if (drawRight) g2d.draw(this.right);
    if (drawBottom) g2d.draw(this.bottom);
    if (drawLeft) g2d.draw(this.left);
  }

  public void fill(Graphics2D g2d, Color color) {
    g2d.setColor(color);
    g2d.fillRect(i * SIZE, j * SIZE, SIZE, SIZE);
  }
}
