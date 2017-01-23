package entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import snake.Screen;

public class Snake extends Entity {
  protected static final int SIZE = 20;
  protected static final int BORDER = 1;

  private double x;
  private double y;

  public int prevCellX;
  public int prevCellY;

  private double speed = 1.0;

  private double xVel = speed;
  private double yVel = 0.0;

  public List<BodyPiece> body;

  private int score;

  private boolean dead = false;

  public Snake() {
    this.x = 100;
    this.y = 100;
    this.cellX = (int) this.x;
    this.cellY = (int) this.y;
    this.prevCellX = this.cellX;
    this.prevCellY = this.cellY;
    body = new ArrayList<BodyPiece>(0);
    this.score = 0;
  }

  @Override
  public void draw(Graphics2D g2d) {
    g2d.fillRect(
        cellX + BORDER,
        cellY + BORDER,
        SIZE - 2 * BORDER,
        SIZE - 2 * BORDER);
    for (BodyPiece bp : body) {
      bp.draw(g2d);
    }
  }

  @Override
  public void update(boolean[] keys) {
    prevCellX = cellX;
    prevCellY = cellY;
    if (keys[KeyEvent.VK_RIGHT] && xVel != -speed) {
      xVel = speed;
      yVel = 0;
    }
    if (keys[KeyEvent.VK_LEFT] && xVel != speed) {
      xVel = -speed;
      yVel = 0;
    }
    if (keys[KeyEvent.VK_UP] && yVel != speed) {
      xVel = 0;
      yVel = -speed;
    }
    if (keys[KeyEvent.VK_DOWN] && yVel != -speed) {
      xVel = 0;
      yVel = speed;
    }

    x += xVel;
    y += yVel;
    cellX = (int) (x / SIZE) * SIZE;
    cellY = (int) (y / SIZE) * SIZE;
    if (cellX != prevCellX || cellY != prevCellY) {
      // This has to be done because for most ticks the previous cellX is the same as the current cellX
      // because we're moving at 'subpixels'
      if (body.size() > 0) {
        body.get(0).move(prevCellX, prevCellY);
      }
      for (int i = 0; i < body.size() - 1; i++) {
        // 0 is the first body part
        // however it has a pos of 1
        body.get(i + 1).move(body.get(i).prevCellX, body.get(i).prevCellY);
      }
      if (collide(Screen.walls) || selfCollide(body)) {
        dead = true;
      }
    }
  }

  public void eat(Food food) {
    if (collide(food)) {
      ++this.score;
      food.update();
      if (body.size() < 1)
        body.add(new BodyPiece(prevCellX, prevCellY));
      else
        body.add(new BodyPiece(body.get(body.size() - 1).prevCellX, body.get(body.size() - 1).prevCellY));
    }
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
