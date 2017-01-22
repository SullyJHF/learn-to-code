package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import entities.Entity;
import entities.Food;
import entities.Snake;
import entities.Wall;

public class Screen extends JPanel {
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;

  private Snake snake;

  private Food food;

  private List<Entity> walls;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.GRAY);
    snake = new Snake();
    food = new Food();
    walls = new ArrayList<Entity>();

    for(int i = 0; i < 10; i++) {
      Wall wall = new Wall();
      wall.setPosition(wall.r.nextInt(WIDTH), wall.r.nextInt(HEIGHT));
      walls.add(wall);
    }
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    snake.draw(g2d);
    food.draw(g2d);
    for(Entity wall : walls) {
      wall.draw(g2d);
    }
  }

  public void tick(boolean[] keys) {
    snake.update(keys);
    snake.eat(food);
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
