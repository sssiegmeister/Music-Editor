package cs3500.music.view;

import java.awt.*;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

import cs3500.music.controller.MouseHandler;
import cs3500.music.model.Note;
import cs3500.music.model.NotePlaceComparator;
import cs3500.music.model.NoteToneComparator;


/**
 * A Frame (i.e., a window) in Swing. This builds the GUI view of the Music Editor. GuiViewFrame
 * implements GuiView interface.
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {

  private ConcreteGuiViewPanel displayPanel;  //the main GUI panel
  private int tempo;                          //tempo of song
  private List<Note> notes;                   //notes that make up the song
  private JScrollPane pane;                   //the scrollable pane in the GUI
  private int firstNotePitch;                 //the initial maximum midi pitch
  private JPanel top;                         //the top pane of the GUI
  private JPanel west;                        //the west pane of GUI
  private int maxMidi;                        //the max note represented in the song
  private int minMidi;                        //the min note represented in the song
  private JPanel middlePanel;                 //the middle planel of this GUI
  private int numNotes;                       //number of notes represented in this view
  private int octaveBound;                    //indication where octave boundary occurs
  private Note min;                           //int indication where an octave change
  // occurs
  private int measureEnd;                     //int indicating where the measures ends
  private JScrollPane scrolls;                //the scrollable main panel
  private int beats;                          //beats represented in the

  private long seconds;                       //the current microsecond position of
  // this view


  /**
   * Constructor for a GuiViewFrame object.
   */
  public GuiViewFrame() {
    super();
  }


  /**
   * Creates a visual GUI based on the given list of notes.
   *
   * @param notes list of notes
   */
  public void execute(List<Note> notes) {

    this.create(notes);
    this.notes = notes;
    this.initialize();

  }


  /**
   * To initialize the frame. It sets up the dimensions of this frame, and sets it visible so that a
   * user can interact with the GUI.
   */
  private void initialize() {

    this.pack();
    this.setVisible(true);
    this.setTitle("Music Editor");
    this.setSize(1100, 700);
    this.setResizable(false);
  }


  /**
   * Creates the main GUI using the given list of notes.
   */
  private void create(List<Note> notes) {

    this.notes = notes;

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.top = new JPanel();

    Collections.sort(notes, new NotePlaceComparator());

    if (notes.size() < 1) {
      return;
    }
    int end = notes.get(notes.size() - 1).getEnd();


    BoxLayout b1 = new BoxLayout(top, BoxLayout.LINE_AXIS);

    top.setLayout(b1);

    top.add(Box.createRigidArea(new Dimension(48, 3)));

    this.measureEnd = 0;

    this.drawMeasures(end);
    this.beats = end;

    this.west = new JPanel();


    Collections.sort(notes, new NoteToneComparator());
    this.min = notes.get(notes.size() - 1);
    //give it the initial min and max
    this.drawOctaves(notes.get(notes.size() - 1).getMidiPitch(), notes.get(0).getMidiPitch());


    //the displayPanel is the main GUI portion of the Music Editor GUI view.
    this.displayPanel = new ConcreteGuiViewPanel(this.numNotes, this.beats, this
            .octaveBound,
            notes,
            min.getMidiPitch(), this.tempo);


    this.firstNotePitch = min.getMidiPitch();


    this.middlePanel = new JPanel();
    middlePanel.setLayout(new BorderLayout());

    middlePanel.add(west, BorderLayout.WEST);

    middlePanel.add(displayPanel, BorderLayout.CENTER);
    middlePanel.add(top, BorderLayout.NORTH);

    //the panel is now scrollable
    this.scrolls = new JScrollPane(middlePanel);

    this.pane = scrolls;

    JScrollBar verticalScrollBar = this.pane.getVerticalScrollBar();
    JScrollBar horizontalScrollBar = this.pane.getHorizontalScrollBar();

    verticalScrollBar.setUnitIncrement(20);
    horizontalScrollBar.setUnitIncrement(50);

    this.add(scrolls, BorderLayout.CENTER);

    this.resetFocus();

  }


  /**
   * To draw the measure numbers that are represented in this song. It starts at the current measure
   * end, and draws measure numbers up to the given int increase value.
   *
   * @param increase in measure size that must be drawn.
   */
  private void drawMeasures(int increase) {
    //creates the measure numbers
    int temp = 0;
    for (int i = measureEnd; i <= measureEnd + increase; i += 16) {

      //String toAdd = this.addWhiteSpace(Integer.toString(i));

      JLabel a = new JLabel(Integer.toString(i));

      //a.setBorder(new LineBorder(Color.BLACK));
      a.setMinimumSize(new Dimension(336, 20));
      a.setPreferredSize(new Dimension(336, 20));
      a.setMaximumSize(new Dimension(336, 20));

      a.setFont(new Font("Sans-serif", Font.PLAIN, 13));

      top.add(a);
      temp += 16;
    }
    measureEnd += temp;
  }


  /**
   * To draw the octave and pitches that are represented in this song. It starts at the given
   * minMidi value and ends at the maxMidi value.
   *
   * @param minMidi the minimum midi pitch that must be represented.
   * @param maxMidi the maximum midi pitch that must be represented.
   */
  private void drawOctaves(int minMidi, int maxMidi) {

    west.removeAll();
    BoxLayout b2 = new BoxLayout(west, BoxLayout.PAGE_AXIS);
    west.setLayout(b2);
    west.add(Box.createRigidArea(new Dimension(50, 0)));


    this.minMidi = minMidi;
    this.maxMidi = maxMidi;

    int octaveBound = 0;
    int numN = 0;

    boolean found = false;

    west.add(Box.createRigidArea(new Dimension(0, 5)));


    for (int i = minMidi; i >= maxMidi; i--) {
      String a = min.convertMidiToString(i);

      //to indicate where the octave changes on a C
      if ((i % 12 == 0) && !found) {
        octaveBound = numN + 1;
        found = true;
      }

      JLabel pitchString = new JLabel(a);

      west.add(pitchString);
      west.add(Box.createRigidArea(new Dimension(0, 5)));

      numN++;
    }

    this.getContentPane().add(top, BorderLayout.NORTH);

    this.numNotes = minMidi - maxMidi + 1;


    this.octaveBound = octaveBound;


    if (this.displayPanel != null) {
      this.displayPanel.setOctaveBound(this.octaveBound);
      displayPanel.setNumNotes(numNotes);
    }
  }


  /**
   * Add's an appropriate amount of whitespace to the given String based on the length of the given
   * string.
   *
   * @param s String
   * @return The same string but with an appropriate amount of whitespace behind it.
   */
  private String addWhiteSpace(String s) {

    int toAdd = 84 - s.length() * 2;

    for (int i = 0; i < toAdd; i++) {
      s = s + " ";
    }
    return s;

  }


  @Override
  public void scrollLeft() {

    JViewport viewport = this.pane.getViewport();

    Point origin = viewport.getViewPosition();

    if (origin.getX() == 0) {
      return;
    } else {
      origin.x -= 20;
    }
    viewport.setViewPosition(origin);
  }


  @Override
  public void scrollRight() {

    JViewport viewport = this.pane.getViewport();

    Point origin = viewport.getViewPosition();

    origin.x += 20;

    viewport.setViewPosition(origin);
  }


  @Override
  public void scrollUp() {

    JViewport viewport = this.pane.getViewport();

    Point origin = viewport.getViewPosition();

    if (origin.getY() == 0) {
      return;
    } else {
      origin.y -= 20;
    }

    viewport.setViewPosition(origin);
  }


  @Override
  public void scrollDown() {

    JViewport viewport = this.pane.getViewport();

    Point origin = viewport.getViewPosition();

    if (this.pane.getViewport().getY() == pane.getHeight() - origin.getY())
      return;
    else origin.y += 20;

    viewport.setViewPosition(origin);
  }


  @Override
  public void scrollToBeginning() {
    JViewport viewport = this.pane.getViewport();

    Point origin = viewport.getViewPosition();

    origin.x = 0;
    origin.y = 0;

    viewport.setViewPosition(origin);
  }


  @Override
  public void scrollToEnd() {
    JViewport viewport = this.pane.getViewport();

    Point origin = viewport.getViewPosition();

    origin.y = 0;

    origin.x = displayPanel.getWidth();

    viewport.setViewPosition(origin);
  }


  @Override
  public void extendBeats() {

    displayPanel.updateBeats(this.beats + 16);
    displayPanel.repaint();

    this.scrolls.setViewportView(middlePanel);

    this.drawMeasures(8);
    this.beats += 16;
  }


  @Override
  public void extendOctaveRangeUpward() {

    int temp = Math.min(minMidi + 12, 127);

    while (minMidi < temp) {
      minMidi += 1;
    }

    displayPanel.setFirstNoteMIDI(minMidi);

    this.drawOctaves(minMidi, maxMidi);
    displayPanel.repaint();

    middlePanel.add(displayPanel, BorderLayout.CENTER);
    middlePanel.add(top, BorderLayout.NORTH);

    this.repaint();

    this.scrolls.setViewportView(middlePanel);

  }


  @Override
  public void extendOctaveRangeDownward() {

    int temp = Math.max(maxMidi - 12, 0);

    while (maxMidi > temp) {
      maxMidi -= 1;
    }

    this.drawOctaves(minMidi, maxMidi);
    displayPanel.repaint();
    middlePanel.add(displayPanel, BorderLayout.CENTER);
    middlePanel.add(top, BorderLayout.NORTH);

    this.repaint();

    this.scrolls.setViewportView(middlePanel);

  }


  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


  @Override
  public void toggleSongPlaying() {
    return;
  }


  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }


  @Override
  public void addMouseHandler(MouseHandler e) {
    this.addMouseListener(e);
    this.displayPanel.addMouseListener(e);
  }


  @Override
  public int getFirstMidi() {
    return minMidi;
  }

  @Override
  public void rescale() {
    Collections.sort(notes, new NotePlaceComparator());
    if (notes.size() < 1) {
      return;
    }
    int end = notes.get(notes.size() - 1).getEnd();

    displayPanel.updateBeats(end);


    this.beats = end;
    Collections.sort(notes, new NoteToneComparator());
    this.min = notes.get(notes.size() - 1);

    //give it the initial min and max
    this.drawOctaves(notes.get(notes.size() - 1).getMidiPitch(), notes.get(0).getMidiPitch());

    displayPanel.setFirstNoteMIDI(minMidi);

    displayPanel.repaint();
    middlePanel.add(displayPanel, BorderLayout.CENTER);
    middlePanel.add(top, BorderLayout.NORTH);

    this.repaint();

    this.scrolls.setViewportView(middlePanel);
  }

  @Override
  public void setSeconds(long seconds) {

    this.seconds = seconds;

    if (displayPanel == null) {
      return;
    }
    displayPanel.setTime(seconds);
  }


  @Override
  public long getMicroseconds() {
    return seconds;
  }


  @Override
  public int getFirstNotePitch() {
    return firstNotePitch;
  }

  @Override
  public int getBeats() {
    return beats;
  }

  @Override
  public void update(List<Note> notes) {
    this.notes = notes;
  }

  @Override
  public void checkBounds(long microseconds) {
    int beats = (int) (microseconds / tempo);

    if ((beats % 48 == 0) && (beats != 0) && (beats < getBeats())) {

      JViewport viewport = this.pane.getViewport();

      Point origin = viewport.getViewPosition();

      origin.x = beats * 21;

      viewport.setViewPosition(origin);
    }
  }

  @Override
  public int getNumNotes() {
    return numNotes;
  }

}

