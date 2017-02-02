package cs3500.music.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.sound.midi.*;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;

/**
 * A class to display a composition in audio form
 */
public class MidiViewImpl implements IView {

  Sequencer player;
  private int tempo;



  /**
   * To create a MidiViewImpl. The constructor has no parameters.
   */
  public MidiViewImpl() {

    try {
      player = MidiSystem.getSequencer();
      player.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }


  public void execute(List<Note> notes) {
    try {
      this.playNote(notes);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }


  /**
   * A convenience constructor that allows the user to pass in a sequencer.
   *
   * @param s Sequencer
   */
  public MidiViewImpl(Sequencer s) {
    player = s;
  }


  /**
   * Plays the Notes based on the given list of notes.
   *
   * @param notes the given given list of notes to play.
   */
  public void playNote(List<Note> notes) throws InvalidMidiDataException {




    if (tempo == 0) {
      throw new IllegalArgumentException("Tempo wasn't initialed");
    }

    Sequence seq = new Sequence(Sequence.PPQ, 500000);

    Track track = seq.createTrack();

    for (Note n : notes) {


      ShortMessage on = new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument() - 1,
              n.getMidiPitch(), n.getVolume());

      ShortMessage off = new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument() - 1,
              n.getMidiPitch(), n.getVolume());

      track.add(new MidiEvent(on, n.getStart() * tempo));
      track.add(new MidiEvent(off, n.getEnd() * tempo));


      //send the note to the receiver.
      try {
        player.getReceiver().send(on, n.getEnd() - n.getStart());
      } catch (MidiUnavailableException e) {
        e.printStackTrace();
      }
    }

    try {

      player.setSequence(seq);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * To return the microseconds that this player has been playing
   */
  public long getMicrosecondsPos() {
    return player.getMicrosecondPosition();
  }


}
