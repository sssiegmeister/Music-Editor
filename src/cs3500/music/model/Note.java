package cs3500.music.model;

import java.util.ArrayList;


/**
 * A class to represent a musical note
 */
public class Note {
  private Pitch pitch;

  private Octave octave;

  private int beats;

  private int start;

  private int instrument;

  private int volume;

  private int midiPitch;

  /**
   * Constructs a Note object
   *
   * @param pitch  the pitch of the note
   * @param octave the octave of the note
   * @param beats  how many beats the note lasts
   * @param start  when the note starts playing
   * @throws IllegalArgumentException if any inputs are invalid. This includes if any
   *                                  objects passed are null, if beats is less than or
   *                                  equal to 0, and if start is less than 0.
   */
  public Note(Pitch pitch, Octave octave, int beats, int start) {
    if (pitch == null) {
      throw new IllegalArgumentException("Invalid input: null pitch");
    }
    if (octave == null) {
      throw new IllegalArgumentException("Invalid input: null octave");
    }
    if (beats <= 0) {
      throw new IllegalArgumentException("Invalid input: too few beats");
    }
    if (start < 0) {
      throw new IllegalArgumentException("Invalid input: negative start time");
    }
    this.pitch = pitch;
    this.octave = octave;
    this.beats = beats;
    this.start = start;
    this.volume = 70;
    this.instrument = 2;
    this.midiPitch = this.convertPitch(pitch, octave);
  }

  /**
   * Constructs a Note object with MIDI parameters
   *
   * @param start      the beat that this Note starts at
   * @param end        the beat that this Note ends at
   * @param instrument the instrument playing this Note
   * @param pitch      the pitch of this Note, in MIDI format
   * @param volume     the volume of this Note
   * @throws IllegalArgumentException if any input is invalid.This includes if any objects
   *                                  passed are null, if beats is less than or equal to
   *                                  0, if the instrument is less than 1 or greater
   *                                  than 16, and if start is less than 0, if an
   *                                  invalid midi pitch is given, or if an invalid
   *                                  midi volume is given.
   */
  public Note(int start, int end, int instrument, int pitch, int volume) {
    if (start < 0) {
      throw new IllegalArgumentException("Invalid input: negative start time");
    }
    if (end < start) {
      throw new IllegalArgumentException("Invalid input: end came before start");
    }
    if (instrument < 1 || instrument > 16) {
      throw new IllegalArgumentException("Invalid input: instrument out of range");
    }
    if (pitch < 0 || pitch > 127) {
      throw new IllegalArgumentException("Invalid input: pitch out of range");
    }
    if (volume < 0 || volume > 127) {
      throw new IllegalArgumentException("Invalid input: volume out of range");
    }
    this.start = start;
    this.beats = end - start;
    this.instrument = instrument;
    this.pitch = convertPitch(pitch);
    this.octave = convertOctave(pitch);
    this.volume = volume;
    this.midiPitch = pitch;
  }

  /**
   * Converts the given midi pitch to a standard 12 pitch letter.
   *
   * @param pitch the MIDI representation of a pitch
   * @return the Pitch represented by the MIDI pitch
   */
  private Pitch convertPitch(int pitch) {
    int i = pitch % 12;
    switch (i) {
      case (0):
        return Pitch.C;
      case (1):
        return Pitch.CSharp;
      case (2):
        return Pitch.D;
      case (3):
        return Pitch.DSharp;
      case (4):
        return Pitch.E;
      case (5):
        return Pitch.F;
      case (6):
        return Pitch.FSharp;
      case (7):
        return Pitch.G;
      case (8):
        return Pitch.GSharp;
      case (9):
        return Pitch.A;
      case (10):
        return Pitch.ASharp;
      case (11):
        return Pitch.B;
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }


  /**
   * Converts the given midi pitch to the correct octave
   *
   * @param pitch the MIDI representation of a pitch
   * @return the Octave represented by the MIDI pitch
   */
  private Octave convertOctave(int pitch) {
    int i = pitch / 12;
    switch (i) {
      case (0):
        return Octave.NegOne;
      case (1):
        return Octave.Zero;
      case (2):
        return Octave.One;
      case (3):
        return Octave.Two;
      case (4):
        return Octave.Three;
      case (5):
        return Octave.Four;
      case (6):
        return Octave.Five;
      case (7):
        return Octave.Six;
      case (8):
        return Octave.Seven;
      case (9):
        return Octave.Eight;
      case (10):
        return Octave.Nine;
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }


  public  String convertMidiToString(int midiPitch) {
    Pitch p = convertPitch(midiPitch);
    Octave o = convertOctave(midiPitch);

    String s =  p.printPitch() + o.printOctave();
    return s;
  }

  /**
   * Converts the given pitch and octave to the corresponding midi pitch.
   *
   * @param pitch  Pitch of note
   * @param octave Pitch of octave
   * @return the midi pitch representation of the given pitch and octave;
   */
  private int convertPitch(Pitch pitch, Octave octave) {

    int i = ((Integer.parseInt(octave.printOctave())) + 1) * 12;

    switch (pitch) {
      case C:
        return i;
      case CSharp:
        return i + 1;
      case D:
        return i + 2;
      case DSharp:
        return i + 3;
      case E:
        return i + 4;
      case F:
        return i + 5;
      case FSharp:
        return i + 6;
      case G:
        return i + 7;
      case GSharp:
        return i + 8;
      case A:
        return i + 9;
      case ASharp:
        return i + 10;
      case B:
        return i + 11;
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 17 * hash + this.start;
    hash = 17 * hash + this.beats;
    hash = 17 * hash + this.octave.hashCode();
    hash = 17 * hash + this.pitch.hashCode();
    return hash;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Note)) {
      return false;
    }
    Note that = (Note) other;
    return this.octave.equals(that.octave) &&
            this.pitch.equals(that.pitch) &&
            this.start == that.start &&
            this.beats == that.beats;
  }

  /**
   * @return a string representation of this Note's tone
   */
  public String printTone() {
    return pitch.printPitch() + octave.printOctave();
  }

  /**
   * @return pitch
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * @return octave
   */
  public Octave getOctave() {
    return octave;
  }

  /**
   * @return start
   */
  public int getStart() {
    return start;
  }

  /**
   * @return the end time of this Note
   */
  public int getEnd() {
    return start + beats;
  }

  /**
   * @return instrument
   */
  public int getInstrument() {
    return instrument;
  }

  /**
   * @return volume
   */
  public int getVolume() {
    return volume;
  }

  /**
   * @return midiPitch
   */
  public int getMidiPitch() {
    return midiPitch;
  }

  /**
   * @param nudge the amount that the start time should be raised by
   * @return a new Note with an increased start time
   */

  //(int start, int end, int instrument, int pitch, int volume)
  public Note raiseStart(int nudge) {
    return new Note(this.start + nudge, this.getEnd() + nudge, this.getInstrument(),
            this.midiPitch, this.volume);
  }

  /**
   * Adds string representations of each beat of this Note in the given List
   *
   * @param grid a 2D arraylist containing tones on the top row, beat numbers on the
   *             lefthand column, and strings representing Notes corresponding to those
   *             axes
   */
  public void addNote(ArrayList<ArrayList<String>> grid) {
    for (int i = 1; i < grid.get(0).size(); i++) {
      if (grid.get(0).get(i).equals(printTone())) {
        grid.get(start + 1).set(i, "  X  ");
        for (int j = start + 2; j <= getEnd(); j++) {
          grid.get(j).set(i, "  |  ");
        }
      }
    }
  }
}
