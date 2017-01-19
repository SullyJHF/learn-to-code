package game;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
  private Thread thread;
  private Screen screen;
  private boolean running = false;
  private final int UPS = 120;
  private InputHandler inputHandler;

  public Game() {
    super("Game Loop");

    screen = new Screen();
    add(screen);

    inputHandler = new InputHandler();
    addKeyListener(inputHandler);
    addFocusListener(inputHandler);

    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public synchronized void start() {
    if (running) return;
    running = true;
    thread = new Thread(this);
    thread.start();
  }

  public synchronized void stop() {
    if (!running) return;
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    double secondsPerTick = 1.0 / UPS;

    double nextTime = System.nanoTime() / 1000000000.0;
    double maxTimeDiff = 0.5;
    int skippedFrames = 1;
    int maxSkippedFrames = 5;

    while (running) {
      double curTime = System.nanoTime() / 1000000000.0;
      if ((curTime - nextTime) > maxTimeDiff) nextTime = curTime;
      if (curTime >= nextTime) {
        // do update code, this will get run UPS times a second
        nextTime += secondsPerTick;
        if ((curTime < nextTime) || (skippedFrames > maxSkippedFrames)) {
          // do rendering code
          skippedFrames = 1;
        } else {
          ++skippedFrames;
        }
      } else {
        int sleepTime = (int) (1000.0 * (nextTime - curTime));
        if (sleepTime > 0) {
          try {
            Thread.sleep(sleepTime);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
