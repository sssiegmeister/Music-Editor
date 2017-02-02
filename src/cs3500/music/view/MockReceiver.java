package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * A class that mocks a receiver. When a note is sent, it stores the note's pitch, the note's
 * volume, and the beat the note starts or stops on.
 */
public class MockReceiver implements Receiver {

  StringBuilder notes;

  /**
   * Constructor for a MockReciever object
   */
  public MockReceiver() {
    notes = new StringBuilder();
  }


  //this should keep track of all the messages sent to the MIDI. It stores the note pitch,
  // the volume and the duration
  @Override
  public void send(MidiMessage message, long timeStamp) {
    byte[] note = message.getMessage();
    notes.append("note " + note[1] + " volume " + note[2] + " duration " + timeStamp + "\n");

  }

  /**
   * Returns the notes that were sent to this receiver.
   *
   * @return StringBuilder with information about notes.
   */
  public StringBuilder getNotes() {
    return notes;
  }


  @Override
  public void close() {
    throw new UnsupportedOperationException();
  }
}
