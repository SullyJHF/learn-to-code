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
  private String score;

  public GameOverMenu(int score) {
    this.title = "Game Over";
    this.titleColor = new Color(21, 21, 21);
    this.font = new Font("Courier", Font.BOLD, 72);
    this.score = String.valueOf(score);
  }

  @Override
  public void render(Graphics2D g2d) {
    FontMetrics metrics = g2d.getFontMetrics(font);
    int titleWidth = metrics.stringWidth(title);
    int titleX = Screen.WIDTH / 2 - titleWidth / 2;
    int titleY = titleX;

    int scoreWidth = metrics.stringWidth(score);
    int scoreX = Screen.WIDTH / 2 - scoreWidth / 2;
    int scoreY = titleY + 100;

    g2d.setFont(font);
    g2d.setColor(titleColor);
    g2d.drawString(title, titleX, titleY);
    g2d.drawString(score, scoreX, scoreY);
  }
}
