package cs3500.music.model;

/**
 * Enum to represent an octave
 */
public enum Octave {
  NegOne(-1), Zero(0), One(1), Two(2), Three(3), Four(4),
  Five(5), Six(6), Seven(7), Eight(8), Nine(9);

  private final int octave;

  /**
   * @return a string representation of this Octave
   */
  public String printOctave() {
    return Integer.toString(octave);
  }

  /**
   * Constructor for an Octave enum
   *
   * @param octave the octave number
   */
  Octave(int octave) {
    this.octave = octave;
  }
}
