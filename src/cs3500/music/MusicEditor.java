package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IView;
import cs3500.music.view.IView;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewCreator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Class to display a composition in any format, including a console rendering, a midi view, a
 * visual view, and then a midi and visual view combinied together into an interactive music editor
 */
public class MusicEditor {
  /**
   * Displays the given composition file in the given format. The given file format is one of
   * "midi", "visual", "text" or "composite"
   *
   * @param args The file containing the composition, followed by a string, console, visual, midi,
   *             or composite representing which view format to display the composition in
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(args[0]));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    GenericMusicModel<Note> model = MusicReader.parseFile(br, new MusicModel.Builder());

    IView view;

    switch (args[1]) {
      case "midi":
      case "console":
      case "visual":
        view = ViewCreator.create(args[1]);

        view.setTempo(model.getTempo());
        view.execute(model.getNotes());
        break;

      case "composite":

        GuiViewFrame frame = new GuiViewFrame();
        MidiViewImpl midi = new MidiViewImpl();

        GuiView composite = new CompositeView(frame, midi);
        composite.setTempo(model.getTempo());

        Controller c = new Controller(model, composite);
        c.start();
    }


  }
}
