package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import cs3500.music.controller.MouseHandler;
import cs3500.music.model.Note;


/**
 * To specify methods that a GUI view displaying a music composition should have. This
 * interface extends MusicViewInterface.
 */
public interface GuiView extends IView {


  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener
   * attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();


  /**
   * To deliberately force the view to have a method that sets up the keyboard listener to
   * the given keylistener.
   */
  void addKeyListener(KeyListener listener);


  /**
   * To deliberately force the view to have a method that sets up the mouse listener.
   *
   * @param listener the mouselistener to add to the view
   */
  void addMouseListener(MouseListener listener);


  /**
   * To add a the given mousehandler as a mouselistener to this view.
   */
  void addMouseHandler(MouseHandler e);


  /**
   * To start the song if the song hasn't started yet. It also pauses and resumes the song
   * if calld again. It plays the song if the song is paused, and pauses the song if the
   * song is playing.
   */
  void toggleSongPlaying();


  /**
   * To scroll this view to the left if possible. The scrolling stays within the main
   * panel, meaning you cannot scroll out of the frame.
   */
  void scrollLeft();


  /**
   * To scroll this view to the right if possible. The scrolling stays within the main
   * panel, meaning you cannot scroll out of the frame.
   */
  void scrollRight();


  /**
   * To scroll this view upward if possible. The scrolling stays within the main panel,
   * meaning you cannot scroll out of the frame.
   */
  void scrollUp();


  /**
   * To scroll this view downward, if possible. The scrolling stays within the main panel,
   * meaning you cannot scroll out of the frame.
   */
  void scrollDown();


  /**
   * Scrolls the view to the beginning.
   */
  void scrollToBeginning();


  /**
   * Scrolls the view to the end.
   */
  void scrollToEnd();


  /**
   * To add measures to the end of the view. This would increase the duration of the song
   * by adding extra beats to the end of the song.
   */
  void extendBeats();


  /**
   * Extends the octave range representing the music. This would then display notes that
   * weren't currently represented in the piece. Essentially expands the octave range of
   * the view. If the current view has all notes and pitches up to G9, (the highest note
   * allowed), then the method doesn't change the view.
   */
  void extendOctaveRangeUpward();


  /**
   * Extends the octave range representing the music. This would cause the view to display
   * notes that weren't currently represented in the piece. Essentially expands the octave
   * range of the view. If the current view has all notes and pitches down to a C-1, (the
   * lowest note allowed), this method doesn't change the view.
   */
  void extendOctaveRangeDownward();


  /**
   * To repaint this view.
   */
  void repaint();


  /**
   * To return the midi pitch of the highest note represented in the song
   *
   * @return integer of the midi pitch of the highest note represented in this song.
   */
  int getFirstMidi();


  /**
   * To rescale this view, such that notes that are beyond the range of the highest and
   * lowest note are hidden from view.
   */
  void rescale();


  /**
   * To get the microseconds that this view is currently at. (playing at).
   *
   * @return microseconds
   */
  long getMicroseconds();


  /**
   * To set the microseconds time of this view.
   *
   * @param sec microseconds
   */
  void setSeconds(long sec);


  /**
   * To determine the midi pitch of the first note
   */
  int getFirstNotePitch();


  /**
   * To return the total duration of this view, in beats
   *
   * @return int beats
   */
  int getBeats();


  /**
   * To update this view with the given list of notes.
   *
   * @param notes representing a music piece.
   */
  void update(List<Note> notes);


  /**
   * To check if this view needs to be scrolled over to the right, as determined by the
   * given microseconds placement in the song.
   *
   * @param microseconds position
   */
  void checkBounds(long microseconds);

  /**
   * To get the number of notes represented in this view
   *
   * @return int notes
   */
  public int getNumNotes();

}
