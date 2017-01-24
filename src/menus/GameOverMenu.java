package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import snake.Screen;

public class GameOverMenu extends Menu {
  private String[] options = { "RESTART", "BACK TO TITLE", "END GAME" };
  private int selected = 0;
  private String title;
  private Color titleColor;
  private Font titleFont;
  private Font scoreFont;
  private Font optionsFont;
  private String score;

  public GameOverMenu(int score) {
    this.title = "GAME OVER";
    this.titleColor = new Color(255, 255, 255);
    this.titleFont = new Font("Agency FB", Font.BOLD, 96);
    this.scoreFont = new Font("Agency FB", Font.PLAIN, 72);
    this.optionsFont = new Font("Agency FB", Font.PLAIN, 48);
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

    FontMetrics optionsMetrics = g2d.getFontMetrics(optionsFont);
    g2d.setFont(optionsFont);
    int optionsY = scoreY + 100;
    int arrowWidth = optionsMetrics.stringWidth("-> ");
    for (int i = 0; i < options.length; i++) {
      String option = options[i];
      String output = "";
      int optionWidth = optionsMetrics.stringWidth(option);
      int x = Screen.WIDTH / 2 - optionWidth / 2;
      if (i == selected) {
        output += "-> ";
        x -= arrowWidth;
      }
      output += option;
      g2d.drawString(output, x, optionsY + i * 50);
    }
  }

  @Override
  public void tick(boolean[] keys) {

  }
}
