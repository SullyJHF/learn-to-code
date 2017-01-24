package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import entities.Entity;
import entities.Food;
import entities.Snake;
import levels.Level;
import menus.GameOverMenu;
import menus.Menu;

public class Screen extends JPanel {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  private Snake snake;

  private Food food;

  public static List<Entity> walls;

  public boolean gameOver = false;

  private Level level;

  private Menu menu;

  public Screen() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.GRAY);
    snake = new Snake();
    level = new Level();
    walls = level.loadLevel("outline");
    food = new Food();
  }

  private void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    if (menu != null) {
      menu.render(g2d);
    }
    snake.draw(g2d);
    food.draw(g2d);
    for (Entity wall : walls) {
      wall.draw(g2d);
    }
  }

  public void tick(boolean[] keys) {
    if (snake.isDead()) {
      // gameover code here
      setMenu(new GameOverMenu());
      gameOver = true;
      render();
    }
    snake.update(keys);
    snake.eat(food);
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
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
