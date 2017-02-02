package cs3500.music.model;

import java.util.List;

/**
 * An interface specifying a music model
 *
 * @param <T> the type of notes being used
 */
public interface GenericMusicModel<T> {
  /**
   * Adds one or more Notes to the list of Notes being played
   *
   * @param note the Note(s) being added
   */
  void addNote(T... note);

  /**
   * Replaces oldNote with newNote in the list of Notes
   *
   * @param oldNote the Note to be replaced
   * @param newNote the replacement Note
   * @throws IllegalArgumentException if oldNote is not present in the piece
   */
  void editNote(T oldNote, T newNote);

  /**
   * Removes a Note from the list of Notes being played
   *
   * @param note the Note being removed
   * @throws IllegalArgumentException if note is not present in the piece
   */
  void removeNote(T note);


  /**
   * Adds another musical piece to play alongside the one already present
   *
   * @param piece the piece to be added
   */
  void addPieceSimultaneous(List<T> piece);

  /**
   * Adds another musical piece to play after the one already present
   *
   * @param piece the piece to be added
   */
  void addPieceConsecutive(List<T> piece);

  /**
   * Removes all Notes from the musical piece
   */
  void removeAllNotes();

  /**
   * Sets the tempo
   */
  void setTempo(int tempo);


  /**
   * To return the tempo of this model
   *
   * @return int tempo
   */
  int getTempo();


  /**
   * @return A list of T objects where T is the type of note
   */
  List<T> getNotes();
}
