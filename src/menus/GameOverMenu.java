package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import snake.Screen;

public class GameOverMenu extends Menu {
  private String title;
  private Color titleColor;
  private Font titleFont;
  private Font scoreFont;
  private String score;

  public GameOverMenu(int score) {
    this.title = "GAME OVER";
    this.titleColor = new Color(255, 255, 255);
    this.titleFont = new Font("Agency FB", Font.BOLD, 96);
    this.scoreFont = new Font("Agency FB", Font.PLAIN, 72);
    this.score = String.valueOf(score);
  }

  @Override
  public void render(Graphics2D g2d) {
    FontMetrics titleMetrics = g2d.getFontMetrics(titleFont);
    int titleWidth = titleMetrics.stringWidth(title);
    int titleX = Screen.WIDTH / 2 - titleWidth / 2;
    int titleY = titleMetrics.getHeight();

    FontMetrics scoreMetrics = g2d.getFontMetrics(scoreFont);
    int scoreWidth = scoreMetrics.stringWidth(score);
    int scoreX = Screen.WIDTH / 2 - scoreWidth / 2;
    int scoreY = titleY + 100;

    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    g2d.setFont(titleFont);
    g2d.setColor(titleColor);
    g2d.drawString(title, titleX, titleY);
    g2d.setFont(scoreFont);
    g2d.drawString(score, scoreX, scoreY);
  }
}
