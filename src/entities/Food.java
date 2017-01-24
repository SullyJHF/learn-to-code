package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import snake.Screen;

public class Food extends Entity {
  private final int SIZE = Snake.SIZE;
  private int x, y;
  private Color color;

  public Food() {
    r = new Random();
    color = chooseColour();
    update();
  }

  @Override
  public void draw(Graphics2D g2d) {
    g2d.setColor(color);
    g2d.fillOval(cellX, cellY, SIZE, SIZE);
  }

  @Override
  public void update() {
    color = chooseColour();
    x = r.nextInt(Screen.WIDTH);
    y = r.nextInt(Screen.HEIGHT);
    cellX = x / SIZE * SIZE;
    cellY = y / SIZE * SIZE;
    for (Entity wall : Screen.walls) {
      if (collide(wall)) {
        update();
      }
    }
    for (Entity bodyPiece : Snake.body) {
      if (collide(bodyPiece)) {
        update();
      }
    }
    if (collide(Snake.self)) {
      update();
    }
  }

  private Color chooseColour() {
    float a = r.nextFloat() / 4 + 0.7f;
    float b = r.nextFloat() / 4 + 0.1f;
    float c = r.nextFloat() / 4 + 0.1f;
    List<Float> colours = new ArrayList<Float>();
    colours.add(a);
    colours.add(b);
    colours.add(c);
    Collections.shuffle(colours);
    return new Color(
        colours.get(0),
        colours.get(1),
        colours.get(2));
  }
}
