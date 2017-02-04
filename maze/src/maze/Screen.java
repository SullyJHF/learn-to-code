package maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Screen extends JPanel {
  private static final int ROWS = 20;
  private static final int COLS = 20;

  private static final int WIDTH = Cell.SIZE * COLS;
  private static final int HEIGHT = Cell.SIZE * ROWS;

  private Cell[] grid;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.GRAY);

    grid = new Cell[ROWS * COLS];
    int index = 0;
    for(int i = 0; i < COLS; i++) {
      for(int j = 0; j < ROWS; j++) {
        grid[index] = new Cell(i, j);
        ++index;
      }
    }
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    for(Cell c : grid) {
      c.draw(g2d);
    }
  }

  public void tick() {

  }

  public void render() {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }
}
