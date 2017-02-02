package cs3500.music.view;

import java.util.List;

import cs3500.music.model.Note;


//Documented Change: Interface was changed so that it takes in a list of notes, not a
// model, in order to further the lose coupling between the view and the model.

/**
 * Specifies methods for a MusicView implementation
 */
public interface IView {


  /**
   * Creates a view based on the given list of notes.
   *
   * @param notes list of notes
   */
  void execute(List<Note> notes);


  /**
   * To set the tempo of the music piece this view show show as.
   *
   * @param tempo of music to display
   */
  void setTempo(int tempo);


}
