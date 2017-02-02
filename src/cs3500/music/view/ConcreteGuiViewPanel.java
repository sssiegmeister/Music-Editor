package cs3500.music.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.model.Note;

/**
 * To create the main GUI section of the music editor. This class extends JPanel
 */
public class ConcreteGuiViewPanel extends JPanel {

  private int firstNoteMIDI;              //the midi int of the first note represented
  private int numNotes;                   //number of notes represented in view
  private int beatsLength;                //length of song in beats
  private int octaveBound;                //integer indicating an octave boundary
  private List<Note> notes;               //notes to display
  private int lineWidth = 21;
  private long time;
  private int tempo;


  /**
   * Creates a concrete GUIViewPanel that shows the main portion of the music editor. It
   * takes in the number of notes that need to be represented (the amount of notes from
   * the highest note to the lowest note), the amount of beats this song plays for, the
   * note number where an octave bound occurs at, a list of notes and the midi value of
   * the first note.
   *
   * @param numNotes      number of notes that this song ranges in (from highest note to
   *                      lowest note)
   * @param beatsLength   the amount of beats this song plays for
   * @param octaveBound   the number where an octave boundary occurs at
   * @param list          list of notes that are in the song
   * @param firstNoteMIDI the midi value of the first note.
   */
  public ConcreteGuiViewPanel(int numNotes, int beatsLength, int octaveBound, List<Note>
          list, int firstNoteMIDI, int tempo) {
    super();

    this.numNotes = numNotes;
    this.beatsLength = beatsLength;
    this.octaveBound = octaveBound;
    this.notes = list;
    this.firstNoteMIDI = firstNoteMIDI;
    this.tempo = tempo;
  }


  /**
   * To set the time (in milliseconds) that the song is at currently
   *
   * @param time in milliseconds
   */
  public void setTime(long time) {

    this.time = time;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D drawing = (Graphics2D) g;

    //draws all the notes
    this.drawNotes(drawing);

    //draws the lines to indicate where the boundaries of measures and notes are.
    this.drawLines(drawing);

    //draws a red line to indicate the current beat.
    this.drawRedLine(drawing);

  }


  /**
   * To draw the lines of musicEditor. The lines indicate the boundaries where measures
   * and notes are.
   *
   * @param g the image that the lines are being added to
   */
  private void drawLines(Graphics2D g) {

    int horizontalDistance = (beatsLength) * lineWidth; //distance to draw horizontal lines

    int numHorizontalLines = this.numNotes + 1;         //number of horizontal lines to draw

    int verticalDistance = this.numNotes * lineWidth;   //distance to draw vertical lines.

    g.setStroke(new BasicStroke(2));

    //this draws all of the horizontal lines
    for (int i = 1; i < numHorizontalLines * lineWidth; i += lineWidth) {
      g.drawLine(0, i, horizontalDistance, i);
    }

    //draws all the vertical lines
    for (int i = 1; i <= horizontalDistance + lineWidth; i += lineWidth * 4) {
      g.drawLine(i, 0, i, verticalDistance);
    }

    //draws the thick middle lines
    g.setStroke(new BasicStroke(3));
    for (int i = this.octaveBound; i < numNotes; i += 12) {
      g.drawLine(0, i * lineWidth, horizontalDistance, i * lineWidth);

    }
  }


  /**
   * To draw the notes in the given list of notes.
   *
   * @param g the image that the notes are being added to
   */
  private void drawNotes(Graphics2D g) {

    for (Note n : this.notes) {

      int startBeat = n.getStart();

      //trailing notes are rendered in green.
      g.setColor(Color.GREEN);
      for (int j = startBeat + 1; j < n.getEnd(); j += 1) {
        g.fillRect(j * lineWidth, lineWidth * (firstNoteMIDI - n.getMidiPitch()),
                lineWidth, lineWidth);
      }

      //the first note is rendered in black.
      g.setColor(Color.BLACK);
      g.fillRect(startBeat * lineWidth, lineWidth * (firstNoteMIDI - n.getMidiPitch()),
              lineWidth, lineWidth);
    }
  }


  @Override
  public Dimension getPreferredSize() {

    int horizontalDistance = (beatsLength) * lineWidth;
    int verticalDistance = this.numNotes * lineWidth;

    //the preferred size is the size of this GUI panel's horizontal and vertical distances.
    return new Dimension(horizontalDistance, verticalDistance);
  }


  /**
   * Draws a red line on the image
   *
   * @param drawing the image that the line is being added to
   */
  private void drawRedLine(Graphics2D drawing) {
    int verticalDistance = this.numNotes * lineWidth;

    drawing.setStroke(new BasicStroke(3));
    drawing.setColor(Color.RED);

    int x1 = (int) ((this.time / tempo) * lineWidth);

    drawing.drawLine(x1, 1, x1, verticalDistance);
  }


  /**
   * To update the beat's length of this song to the given int.
   *
   * @param beat integer of new beats length of the song.
   */
  void updateBeats(int beat) {
    this.beatsLength = beat;
  }


  /**
   * To rest the note of the first midi to the given note.
   *
   * @param i int of the new first Midi note. Since the new midi note is one higher than
   *          the last one, the number of notes represented in this song increases by 1.
   */
  void setFirstNoteMIDI(int i) {
    this.firstNoteMIDI = i;
    numNotes++;
  }


  /**
   * To set the new octave boundary to the given integer.
   *
   * @param i int of new octave boundary
   */
  void setOctaveBound(int i) {
    this.octaveBound = i;
  }


  /**
   * To set the number of notes in this view to the given number of notes.
   *
   * @param i number of notes
   */
  void setNumNotes(int i) {
    this.numNotes = i;
  }


}
