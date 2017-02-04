package maze;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Cell {
  private int i, j;
  static final int SIZE = 40;
  private Line2D top, right, bottom, left;

  public Cell(int i, int j) {
    this.i = i;
    this.j = j;
    this.top = new Line2D.Double(i * SIZE, j * SIZE, (i + 1) * SIZE, j * SIZE);
    this.right = new Line2D.Double((i + 1) * SIZE, j * SIZE, (i + 1) * SIZE, (j + 1) * SIZE);
    this.bottom = new Line2D.Double((i + 1) * SIZE, (j + 1) * SIZE, i * SIZE, (j+1) * SIZE);
    this.left = new Line2D.Double(i * SIZE, (j + 1) * SIZE, i * SIZE, j * SIZE);
  }

  public void draw(Graphics2D g2d) {
    g2d.draw(this.top);
    g2d.draw(this.right);
    g2d.draw(this.bottom);
    g2d.draw(this.left);
  }
}
