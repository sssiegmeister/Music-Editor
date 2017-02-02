package cs3500.music.controller;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.Note;

/**
 * A runnable function object. When it is successfully run, the boolean flag switches
 * from false to true to indicate that it ran.
 */
public class MockRunnable implements Runnable {

  public boolean ran;

  public MockRunnable(boolean ran) {
    ran = false;
  }

  public void run() {
    ran = true;
  }
}


