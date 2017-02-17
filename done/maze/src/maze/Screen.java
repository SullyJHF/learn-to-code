package maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

public class Screen extends JPanel {
  private static final int ROWS = 20;
  private static final int COLS = 20;

  private static final int WIDTH = Cell.SIZE * COLS;
  private static final int HEIGHT = Cell.SIZE * ROWS;

  private Cell[] grid;

  private Cell current;
  private Cell end;

  private Cell fork;

  private int biggest = 0;

  private Stack<Cell> stack;
  private Stack<Cell> solution;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.GRAY);

    grid = new Cell[ROWS * COLS];
    for (int i = 0; i < COLS; i++) {
      for (int j = 0; j < ROWS; j++) {
        grid[i + j * ROWS] = new Cell(i, j);
      }
    }

    current = grid[0];
    end = grid[grid.length - 1];
    fork = null;
    current.visited = true;

    stack = new Stack<Cell>();
    solution = new Stack<Cell>();
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    for (Cell c : grid) {
      if (c.visited) {
        c.fill(g2d, Color.DARK_GRAY);
      }
      if (stack.contains(c)) c.fill(g2d, Color.LIGHT_GRAY);
      if (solution.contains(c)) c.fill(g2d, new Color(200, 67, 170));
      c.draw(g2d, Color.WHITE);
    }
    end.fill(g2d, Color.RED);
    current.fill(g2d, Color.GREEN);
  }

  public void tick() {
    ArrayList<Cell> curNeighbours = getNeighbours(current);
    if (curNeighbours.size() > 0) {
      Cell next = pickCell(curNeighbours);
      stack.push(current);
      if (stack.size() > biggest) {
        biggest = stack.size();
        end = next;
        fork = null;
        solution.clear();
      }
      next.visited = true;
      removeWalls(current, next);
      current = next;
    } else if (!stack.isEmpty()) {
      current = stack.pop();
      fillSolution();
    }
  }

  private void fillSolution() {
    if (fork == null && getNeighbours(current).size() == 0) {
      solution.push(current);
    } else if (fork == null && getNeighbours(current).size() > 0) {
      fork = current;
    } else if (fork != null && current.equals(fork)) {
      solution.push(current);
      fork = null;
    }
  }

  private Cell pickCell(ArrayList<Cell> curNeighbours) {
    Random r = new Random();
    int index = r.nextInt(curNeighbours.size());
    return curNeighbours.get(index);
  }

  public void render() {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  private ArrayList<Cell> getNeighbours(Cell cell) {
    Cell topCell = getUnvisitedCell(cell.i, cell.j - 1);
    Cell rightCell = getUnvisitedCell(cell.i + 1, cell.j);
    Cell bottomCell = getUnvisitedCell(cell.i, cell.j + 1);
    Cell leftCell = getUnvisitedCell(cell.i - 1, cell.j);

    ArrayList<Cell> neighbours = new ArrayList<Cell>();

    if (topCell != null) neighbours.add(topCell);
    if (rightCell != null) neighbours.add(rightCell);
    if (bottomCell != null) neighbours.add(bottomCell);
    if (leftCell != null) neighbours.add(leftCell);

    return neighbours;
  }

  private Cell getCell(int x, int y) {
    int index = x + y * ROWS;
    if (x < 0 || x >= COLS || y < 0 || y >= ROWS) return null;
    return grid[index];
  }

  private Cell getUnvisitedCell(int x, int y) {
    Cell cell = getCell(x, y);
    if (cell == null || cell.visited) return null;
    return cell;
  }

  private void removeWalls(Cell a, Cell b) {
    int xDiff = b.i - a.i;
    int yDiff = b.j - a.j;

    if (xDiff == 1) {
      a.drawRight = false;
      b.drawLeft = false;
    } else if (xDiff == -1) {
      a.drawLeft = false;
      b.drawRight = false;
    }
    if (yDiff == 1) {
      a.drawBottom = false;
      b.drawTop = false;
    } else if (yDiff == -1) {
      a.drawTop = false;
      b.drawBottom = false;
    }
  }
}
