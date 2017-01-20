package snake;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener, FocusListener {
  public boolean[] keys = new boolean[65536];

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    if (code > 0 && code < keys.length) {
      keys[code] = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    if (code > 0 && code < keys.length) {
      keys[code] = false;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void focusGained(FocusEvent e) {}

  @Override
  public void focusLost(FocusEvent e) {
    for (int i = 0; i < keys.length; i++) {
      keys[i] = false;
    }
  }
}
