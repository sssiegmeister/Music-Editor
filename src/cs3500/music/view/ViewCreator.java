package cs3500.music.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.Controller;
import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;

/**
 * A factory class that returns different view types for a music piece.
 */
public class ViewCreator {


  /**
   * To create a view based on the given viewtype. Console returns the text view
   * implementation, midiview returns a new midiview implementation, and visual returns a
   * new gui view frame.
   *
   * @param type of game that should be returned
   * @return the CardGameModel
   */
  public static IView create(String type) {
    switch (type) {
      case "console":
        return new TextViewImpl(System.out);
      case "midi":
        return new MidiViewImpl();
      case "visual":
        return new GuiViewFrame();

      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }


}
