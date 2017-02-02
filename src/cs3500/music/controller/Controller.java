package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.GuiView;


/**
 * To represent a controller that controls a MusicEditor. This is an asynchronous controller that
 * responds to user input. This controller implements IMusicEditorController and ActionListener. The
 * controller handle's all mouse and keyevents from the GUI as well. This controller facilitates
 * interaction between a model and a a view
 */
public class Controller implements IMusicEditorController, ActionListener {

  private GenericMusicModel<Note> model;
  private GuiView view;


  /**
   * To create a Controller. It takes in a model and a GUI view and facilitaes interactions between
   * the two.
   *
   * @param model to store and keep track of a music composition
   * @param view  to create the view of the musical composition/display the GUI
   */
  public Controller(GenericMusicModel<Note> model, GuiView view) {

    this.model = model;
    this.view = view;

  }

  @Override
  public void start() {
    view.execute(model.getNotes());
    view.resetFocus();

    //configures the keyboardListener and mouseListener
    configureKeyBoardListener();
    configureMouseListener();
  }

  /**
   * To configure the keyboard. When the user presses 'p', playing of the music is toggled on and
   * off. A user can press the left and right arrow keys to scroll to the right and left. A user can
   * press the home and end keys in order to scroll to the beginning or the end of the music piece
   * (in the visual gui). A user can press ctrl 'a', 'c', 'b', and 'd' to extend the duration of the
   * song, increase the range in octaves (for notes) and then downscale the music piece again.
   */
  @Override
  public void configureKeyBoardListener() {

    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();


    KeyboardHandler keyboardHandler = new KeyboardHandler();

    keyPresses.put(39, new MoveRight());             //right arrow scrolls right
    keyPresses.put(37, new MoveLeft());              //left arrow scrolls left
    keyPresses.put(38, new MoveUp());                //up arrow key scrolls up
    keyPresses.put(40, new MoveDown());              //down arrow key scrolls down
    keyPresses.put(36, new ScrollToBeginning());     //home key scrolls to beginning
    keyPresses.put(35, new ScrollToEnd());           //end key scrolls to end
    keyPresses.put(80, new TogglePlaying());         //p key plays, pauses and resumes
    // playing


    keyReleases.put(66, new ExtendBeats());  //control b extends beats
    keyReleases.put(85, new Upwards());      //control u extends the range of octaves up
    keyReleases.put(68, new Downwards());    //control d extends range of octaves down
    keyReleases.put(82, new Rescale());      //control r rescales it


    //set up the keyboard types.
    keyboardHandler.setKeyTypedMap(keyTypes);
    keyboardHandler.setKeyPressedMap(keyPresses);
    keyboardHandler.setKeyReleasedMap(keyReleases);

    view.addKeyListener(keyboardHandler);

  }


  @Override
  public void configureMouseListener() {

    MouseHandler handler = new MouseHandler(view.getFirstNotePitch(), model, view);

    view.addMouseHandler(handler);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
  }


  /**
   * A Runnable class that toggles the song playing. If the song is playing, it will pause, and if
   * the song is paused, it will resume playing.
   */
  private class TogglePlaying implements Runnable {
    public void run() {

      view.toggleSongPlaying();
    }
  }


  /**
   * A runnable function object that scrolls the view to the right
   */
  private class MoveRight implements Runnable {
    public void run() {
      view.scrollRight();
    }
  }

  /**
   * A runnable function object that scrolls the view to the left
   */
  private class MoveLeft implements Runnable {
    public void run() {
      view.scrollLeft();
    }
  }

  /**
   * A runnable function object that scrolls the view upwards
   */
  private class MoveUp implements Runnable {
    public void run() {
      view.scrollUp();
    }
  }

  /**
   * A runnable function object that scrolls the view downward
   */
  private class MoveDown implements Runnable {
    public void run() {
      view.scrollDown();
    }
  }

  /**
   * A runnable function object, whose only method scrolls the view to the beginning
   */
  private class ScrollToBeginning implements Runnable {
    public void run() {
      view.scrollToBeginning();
    }
  }

  /**
   * A runnable function object, whose only method scrolls the view to the end.
   */
  private class ScrollToEnd implements Runnable {
    public void run() {
      view.scrollToEnd();
    }
  }


  /**
   * A runnable function object, whose only method extend's the duration of the song, by adding new
   * beats to the end of the song.
   */
  private class ExtendBeats implements Runnable {
    public void run() {
      view.extendBeats();
    }
  }

  /**
   * A runnable function object, whose only method add's extra pitches and octaves to the view, if
   * they aren't currently in the view. Add's the new octaves on an upward scale.
   */
  private class Upwards implements Runnable {
    public void run() {
      view.extendOctaveRangeUpward();
    }
  }

  /**
   * A runnable function object, whose only method add's extra pitches and octaves to the view, if
   * they aren't currently in the view. Add's the new octaves on a downward scale.
   */
  private class Downwards implements Runnable {
    public void run() {
      view.extendOctaveRangeDownward();
    }
  }


  /**
   * A runnable function object whose only method rescales the view, such that any notes that are
   * outside the boundary of the highest and lowest note representations are removed.
   */
  private class Rescale implements Runnable {
    public void run() {
      view.rescale();
    }
  }


}




