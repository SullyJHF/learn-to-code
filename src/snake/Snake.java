package snake;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
  protected static final int SIZE = 20;

  private int x;
  private int y;

  public int prevCellX;
  public int prevCellY;

  public int cellX;
  public int cellY;

  private double xVel = 1.0;
  private double yVel = 0.0;

  private ArrayList<BodyPiece> body;

  private int score;

  public Snake() {
    this.x = 100;
    this.y = 100;
    this.cellX = this.x;
    this.cellY = this.y;
    this.prevCellX = this.cellX;
    this.prevCellY = this.cellY;
    body = new ArrayList<BodyPiece>(0);
    this.score = 0;
  }

  public void draw(Graphics2D g2d) {
    g2d.fillRect(cellX, cellY, SIZE, SIZE);
    for (BodyPiece bp : body) {
      bp.draw(g2d);
    }
  }

  public void update(boolean[] keys) {
    prevCellX = cellX;
    prevCellY = cellY;
    if (keys[KeyEvent.VK_RIGHT] && xVel != -1.0) {
      xVel = 1.0;
      yVel = 0.0;
    }
    if (keys[KeyEvent.VK_LEFT] && xVel != 1.0) {
      xVel = -1.0;
      yVel = 0.0;
    }
    if (keys[KeyEvent.VK_UP] && yVel != 1.0) {
      xVel = 0.0;
      yVel = -1.0;
    }
    if (keys[KeyEvent.VK_DOWN] && yVel != -1.0) {
      xVel = 0.0;
      yVel = 1.0;
    }

    x += xVel;
    y += yVel;
    cellX = (x / SIZE) * SIZE;
    cellY = (y / SIZE) * SIZE;
    if (cellX != prevCellX || cellY != prevCellY) {
      // This has to be done because for most ticks the previous cellX is the same as the current cellX
      // because we're moving at 'subpixels'
      if(body.size() > 0) {
        body.get(0).update(prevCellX, prevCellY);
      }
      for (int i = 0; i < body.size() - 1; i++) {
        // 0 is the first body part
        // however it has a pos of 1
        body.get(i + 1).update(body.get(i).prevCellX, body.get(i).prevCellY);
      }
    }
  }

  public void eat(Food food) {
    if (food.cellX == cellX && food.cellY == cellY) {
      ++this.score;
      food.update();
      body.add(new BodyPiece(cellX, cellY));
    }
  }
}
