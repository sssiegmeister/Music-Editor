package cs3500.music.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

import cs3500.music.controller.MouseHandler;
import cs3500.music.model.Note;

/**
 * To create a CompositeView that allows two preexisting views to execute at the same
 * time. This class implements GuiView.
 */
public class CompositeView implements GuiView {

  private GuiView visual;
  private MidiViewImpl audio;
  private int tempo;
  private boolean playing;
  private List<Note> notes;

  private Timer timer = new Timer(1, new updateSeconds());

  /**
   * To create CompositeView. A CompositeView takes in a GuiView and a MidiViewImpl and
   * essentially combines them, so that they execute at the same time.
   *
   * @param visual GuiView
   * @param audio  MidiViewImpl
   */
  public CompositeView(GuiView visual, MidiViewImpl audio) {
    this.visual = visual;
    this.audio = audio;
    this.playing = false;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
    this.visual.setTempo(tempo);
    this.audio.setTempo(tempo);
  }

  @Override
  public void execute(List<Note> notes) {
    this.notes = notes;
    visual.execute(notes);
    audio.execute(notes);
    visual.resetFocus();
  }


  @Override
  public void resetFocus() {
    visual.resetFocus();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    visual.addKeyListener(listener);
  }


  @Override
  public void addMouseListener(MouseListener listener) {
    visual.addMouseListener(listener);
  }

  @Override
  public void addMouseHandler(MouseHandler e) {
    visual.addMouseHandler(e);
  }


  @Override
  public void toggleSongPlaying() {

    if (playing) {
      audio.player.stop();
      timer.stop();
    } else {
      audio.player.start();
      timer.start();
    }
    playing = !playing;
  }


  @Override
  public void scrollLeft() {
    visual.scrollLeft();
  }

  @Override
  public void scrollRight() {

    visual.scrollRight();
  }

  @Override
  public void scrollUp() {
    visual.scrollUp();
  }

  @Override
  public void scrollDown() {
    visual.scrollDown();
  }

  @Override
  public void scrollToBeginning() {
    visual.scrollToBeginning();
  }

  @Override
  public void scrollToEnd() {
    visual.scrollToEnd();
  }

  @Override
  public void extendBeats() {
    visual.extendBeats();
  }

  @Override
  public void extendOctaveRangeUpward() {
    visual.extendOctaveRangeUpward();
  }

  @Override
  public void extendOctaveRangeDownward() {
    visual.extendOctaveRangeDownward();
  }

  @Override
  public void repaint() {
    visual.repaint();
  }

  @Override
  public int getFirstMidi() {
    return visual.getFirstMidi();
  }

  @Override
  public void rescale() {
    visual.rescale();
  }

  @Override
  public long getMicroseconds() {
    return audio.getMicrosecondsPos();
  }

  @Override
  public void setSeconds(long sec) {
    visual.setSeconds(sec);
  }

  @Override
  public int getFirstNotePitch() {
    return visual.getFirstNotePitch();
  }

  @Override
  public int getBeats() {
    return visual.getBeats();
  }


  @Override
  public void update(List<Note> notes) {
    try {
      this.audio.playNote(notes);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void checkBounds(long microseconds) {
    visual.checkBounds(microseconds);
  }

  @Override
  public int getNumNotes() {
    return visual.getNumNotes();
  }


  /**
   * A private class that implements Actionlistener. This class's action is to set the
   * audio's microseconds position, and then repaint the visual GUI.
   */
  private class updateSeconds implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      setSeconds(audio.getMicrosecondsPos());
      visual.repaint();

      visual.checkBounds(audio.getMicrosecondsPos());
    }
  }


}



