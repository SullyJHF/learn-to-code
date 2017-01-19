package game;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
  private Thread thread;
  private Screen screen;
  private boolean running = false;

  public Game() {
    super("Game Loop");

    screen = new Screen();
    add(screen);

    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void run() {

  }

  public synchronized void start() {

  }
}
