package cs3500.music.model;

/**
 * Enum to represent a pitch
 */
public enum Pitch {
  C("C"), CSharp("C#"), D("D"), DSharp("D#"), E("E"), F("F"),
  FSharp("F#"), G("G"), GSharp("G#"), A("A"), ASharp("A#"), B("B");

  private final String pitch;

  /**
   * @return a string representation of this pitch
   */
  public String printPitch() {
    return pitch;
  }

  /**
   * Constructor for a Pitch enum
   *
   * @param pitch the pitch name
   */
  Pitch(String pitch) {
    this.pitch = pitch;
  }
}
