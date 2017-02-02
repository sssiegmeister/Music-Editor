package cs3500.music.controller;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.Note;

/**
 * A mock runnable object that add's a note to the given model when it is run.
 */
public class MockRunnable2 implements Runnable {

  public GenericMusicModel<Note> model;

  public MockRunnable2(GenericMusicModel<Note> m) {
    model = m;
  }

  public void run() {
    model.addNote(new Note(12, 15, 12, 12, 12));
  }
}