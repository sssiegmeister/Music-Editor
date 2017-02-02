package cs3500.music.model;

import java.util.Comparator;

/**
 * Compares Notes by the tone they represent
 */
public class NoteToneComparator implements Comparator<Note> {

  @Override
  public int compare(Note n1, Note n2) {
    return n1.getPitch().compareTo(n2.getPitch()) +
            (100 * n1.getOctave().compareTo(n2.getOctave()));
  }

}
