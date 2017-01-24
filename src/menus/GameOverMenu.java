package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import snake.Screen;

public class GameOverMenu extends Menu {
  private String title;
  private Color titleColor;
  private Font font;

  public GameOverMenu() {
    this.title = "Game Over";
    this.titleColor = new Color(21, 21, 21);
    this.font = new Font("Courier", Font.BOLD, 72);
  }

  @Override
  public void render(Graphics2D g2d) {
    FontMetrics metrics = g2d.getFontMetrics(font);
    int width = metrics.stringWidth(title);
    int x = Screen.WIDTH / 2 - width / 2;
    int y = x;
    g2d.setFont(font);
    g2d.setColor(titleColor);
    g2d.drawString(title, x, y);
  }
}
