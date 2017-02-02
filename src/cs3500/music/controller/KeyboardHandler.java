package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * This class represents a keyboard listener. It is configurable by the controller that
 * instantiates it.
 *
 * This listener keeps three maps, one each for key typed, key pressed and key released
 * Each map stores a key mapping. A key mapping is a pair (keystroke,code to be executed
 * with that keystroke) The latter part of that pair is actually a function object, i.e.
 * an object of a class that implements the Runnable interface
 *
 * This class implements the KeyListener interface, so that its object can be used as a
 * valid keylistener for Java Swing.
 */
public class KeyboardHandler implements KeyListener {

  private Map<Integer, Runnable> keyTypedMap, keyPressedMap, keyReleasedMap;


  /**
   * Set the map for key typed events to the given map. This allows a user to install
   * runnables for various key events that are of interest.
   */
  public void setKeyTypedMap(Map<Integer, Runnable> map) {
    keyTypedMap = map;
  }


  /**
   * Set the map for key pressed events to the given map. This allows a user to install
   * runnables for various key events that are of interest.
   */
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    keyPressedMap = map;
  }


  /**
   * Set the map for key released events to the given map. This allows a user to install
   * runnables for various key events that are of interest.
   */
  public void setKeyReleasedMap(Map<Integer, Runnable> map) {
    keyReleasedMap = map;
  }


  @Override
  public void keyTyped(KeyEvent e) {
    if (keyTypedMap.containsKey(e.getKeyCode()))
      keyTypedMap.get(e.getKeyCode()).run();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode()))
      keyPressedMap.get(e.getKeyCode()).run();

  }


  @Override
  public void keyReleased(KeyEvent e) {
    if ((keyReleasedMap.containsKey(e.getKeyCode())) && e.isControlDown()) {
      keyReleasedMap.get(e.getKeyCode()).run();

    }
  }
}
