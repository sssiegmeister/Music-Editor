package cs3500.music.model;

import java.util.Comparator;

/**
 * Compares Notes by the time that they end
 */
public class NotePlaceComparator implements Comparator<Note> {

  @Override
  public int compare(Note n1, Note n2) {
    return n1.getEnd() - n2.getEnd();
  }

}
