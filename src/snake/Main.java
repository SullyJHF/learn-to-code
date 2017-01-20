package snake;

import java.awt.EventQueue;

public class Main {
  private void createAndShowGUI() {
    Game g = new Game();
    g.start();
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      Main m = new Main();
      m.createAndShowGUI();
    });
  }
}
