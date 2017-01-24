package menus;

import java.awt.Graphics2D;

public class GameOverMenu extends Menu {
  String title = "Game Over";
  @Override
  public void render(Graphics2D g2d) {
    g2d.drawString(title, 50, 50);
  }
}
