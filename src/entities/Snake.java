package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import snake.Game;
import snake.Screen;

public class Snake extends Entity {
  public static final int SIZE = Screen.CELL_SIZE;
  protected static final int BORDER = 1;

  private Color color;

  private double x;
  private double y;

  public int prevCellX;
  public int prevCellY;

  private double speed = 6.0;
  private int cellTime;

  private enum D {
    UP, RIGHT, DOWN, LEFT
  };

  private D dir = D.RIGHT;
  private D prevDir;

  public static List<BodyPiece> body;
  public static Entity self;

  public int score;

  private boolean dead = false;

  public Snake() {
    this.cellX = 3 * SIZE;
    this.cellY = 4 * SIZE;
    this.prevCellX = this.cellX;
    this.prevCellY = this.cellY;
    Snake.body = new ArrayList<BodyPiece>(0);
    Snake.self = this;
    this.color = new Color(51, 51, 51);
    this.score = 0;
    this.cellTime = (int) (Game.UPS / this.speed);
  }

  @Override
  public void draw(Graphics2D g2d) {
    for (BodyPiece bp : body) {
      bp.draw(g2d);
    }
    g2d.setColor(color);
    g2d.fillRect(
        cellX + BORDER,
        cellY + BORDER,
        SIZE - 2 * BORDER,
        SIZE - 2 * BORDER);
  }

  @Override
  public void update(boolean[] keys) {
    --cellTime;
    prevCellX = cellX;
    prevCellY = cellY;

    if (keys[KeyEvent.VK_RIGHT]) {
      dir = D.RIGHT;
    }
    if (keys[KeyEvent.VK_LEFT]) {
      dir = D.LEFT;
    }
    if (keys[KeyEvent.VK_UP]) {
      dir = D.UP;
    }
    if (keys[KeyEvent.VK_DOWN]) {
      dir = D.DOWN;
    }

    if (cellTime <= 0) {
      move();
      cellTime = (int) (Game.UPS / speed);
    }

    if (cellX != prevCellX || cellY != prevCellY) {
      // This has to be done because for most ticks the previous cellX is the same as the current cellX
      // because we're moving at 'subpixels'
      if (body.size() > 0) {
        body.get(0).move(prevCellX, prevCellY);
        for (int i = 0; i < body.size() - 1; i++) {
          // 0 is the first body part
          // however it has a pos of 1
          body.get(i + 1).move(body.get(i).prevCellX, body.get(i).prevCellY);
        }
      }
      if (collide(Screen.walls) || selfCollide(body)) {
        dead = true;
      }
    }
  }

  public void eat(Food food) {
    if (collide(food)) {
      ++this.score;
      speed += speed * 0.05;
      if (body.size() < 1)
        body.add(new BodyPiece(prevCellX, prevCellY, food.color));
      else
        body.add(new BodyPiece(body.get(body.size() - 1).prevCellX, body.get(body.size() - 1).prevCellY, food.color));
      food.update();
    }
  }

  public void move() {
    if ((dir == D.RIGHT && prevDir == D.LEFT) ||
        (dir == D.LEFT && prevDir == D.RIGHT) ||
        (dir == D.UP && prevDir == D.DOWN) ||
        (dir == D.DOWN && prevDir == D.UP)) {
      dir = prevDir;
    }
    switch (dir) {
    case UP:
      cellY -= Screen.CELL_SIZE;
      break;
    case RIGHT:
      cellX += Screen.CELL_SIZE;
      break;
    case DOWN:
      cellY += Screen.CELL_SIZE;
      break;
    case LEFT:
      cellX -= Screen.CELL_SIZE;
      break;
    }
    prevDir = dir;
  }

  // Custom collide that calls collide on each Entity
  public boolean selfCollide(List<BodyPiece> body) {
    boolean hit = false;
    for (BodyPiece e : body) {
      hit |= super.collide(e);
    }
    return hit;
  }

  public boolean isDead() {
    return dead;
  }
}
