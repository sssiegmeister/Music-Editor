package cs3500.music.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cs3500.music.util.CompositionBuilder;

/**
 * Class to represent a music model. This class implements GenericMusicModel and is
 * parametrized over type Note.
 */
public class MusicModel implements GenericMusicModel<Note> {
  private ArrayList<Note> allNotes;
  private int tempo;

  /**
   * Constructor for a MusicModel object. The tempo gets initialized to 0, and the notes
   * gets initializezd to an empty arraylist.
   */
  public MusicModel() {
    this.allNotes = new ArrayList<>();
    this.tempo = 0;
  }

  @Override
  public void addNote(Note... note) {
    allNotes.addAll(Arrays.asList(note));
  }

  @Override
  public void editNote(Note oldNote, Note newNote) {
    removeNote(oldNote);
    allNotes.add(newNote);
  }

  @Override
  public void removeNote(Note note) {
    int n = allNotes.size();
    allNotes.remove(note);
    if (allNotes.size() == n) {
      throw new IllegalArgumentException("This note does not exist");
    }
  }

  @Override
  public void addPieceSimultaneous(List<Note> piece) {
    Note[] array = piece.toArray(new Note[piece.size()]);
    addNote(array);
  }

  @Override
  public void addPieceConsecutive(List<Note> piece) {
    int end = getLastBeat();
    for (Note n : piece) {
      addNote(n.raiseStart(end));
    }
  }

  @Override
  public void removeAllNotes() {
    allNotes.clear();
  }


  /**
   * @return an integer to represent the last beat number in the piece
   */
  private int getLastBeat() {
    Collections.sort(allNotes, new NotePlaceComparator());
    return allNotes.get(allNotes.size() - 1).getEnd();
  }


  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public ArrayList<Note> getNotes() {
    return allNotes;
  }


  /**
   * Builder class for a MusicModel object.
   */
  public static final class Builder implements
          CompositionBuilder<GenericMusicModel<Note>> {

    GenericMusicModel<Note> model;

    /**
     * Constructor for this builder
     */
    public Builder() {
      model = new MusicModel();
    }

    @Override
    public GenericMusicModel<Note> build() {
      return model;
    }

    @Override
    public CompositionBuilder<GenericMusicModel<Note>> setTempo(int tempo) {
      model.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<GenericMusicModel<Note>> addNote(int start, int end, int instrument,
                                                               int pitch, int volume) {
      model.addNote(new Note(start, end, instrument, pitch, volume));
      return this;
    }
  }
}
