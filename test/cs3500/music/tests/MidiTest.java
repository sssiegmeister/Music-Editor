package cs3500.music.tests;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiMock;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MockReceiver;
import cs3500.music.view.IView;
import cs3500.music.view.TextViewImpl;
import cs3500.music.view.ViewCreator;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * To test the midi view using a mock receiver and sequencer, and testing that all notes
 * in a music model are successfully sent to the receiver.
 */
public class MidiTest {

  @Test
  public void testMidiMaryLittleLamb() {

    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);


    try {

      GenericMusicModel<Note> mary = MusicReader.parseFile(new FileReader
              ("mary-little-lamb.txt"), new MusicModel.Builder());

      midi.setTempo(mary.getTempo());

      midi.execute(mary.getNotes());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    //this will be the array of notes that were sent.
    String[] lines = receiver.getNotes().toString().split("\n");


    assertEquals("note 64 volume 72 duration 2", lines[0]); //the first note played
    assertEquals("note 55 volume 70 duration 7", lines[1]); //second note

    //the total lines played was 34 notes
    assertEquals(34, lines.length);
  }

  @Test
  public void testEmptySong() {
    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    StringReader rd = new StringReader("tempo 200000");


    GenericMusicModel<Note> model = MusicReader.parseFile(rd, new MusicModel.Builder());
    midi.setTempo(model.getTempo());

    midi.execute(model.getNotes());

    //indicates that nothing was sent to the receiver because the song had no notes
    assertEquals("", receiver.getNotes().toString());

  }

  @Test
  public void testOneNoteSong() {
    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);


    StringReader rd = new StringReader("tempo 200000 note 0 2 1 64 72");

    GenericMusicModel<Note> model = MusicReader.parseFile(rd, new MusicModel.Builder());

    midi.setTempo(model.getTempo());
    midi.execute(model.getNotes());


    //this will be the array of notes that were sent.
    String[] lines = receiver.getNotes().toString().split("\n");

    //indicates that nothing was sent to the receiver because the song had no notes
    assertEquals("note 64 volume 72 duration 2", lines[0]);

    //indicates the song only has one note, and only one note was sent to the receiver
    assertEquals(1, lines.length);

  }

  @Test
  public void testRepeatingNoteSong() {
    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    StringReader rd = new StringReader("tempo 200000 note 0 2 1 64 72" +
            " note 0 2 1 64 72 note 0 2 1 64 72 note 0 2 1 64 72 note 0 2 1 64 72");

    GenericMusicModel<Note> model = MusicReader.parseFile(rd, new MusicModel.Builder());

    midi.setTempo(model.getTempo());
    midi.execute(model.getNotes());

    //this will be the array of notes that were sent.
    String[] lines = receiver.getNotes().toString().split("\n");

    //all 5 notes were sent to the receiver
    assertEquals("note 64 volume 72 duration 2", lines[0]);
    assertEquals("note 64 volume 72 duration 2", lines[1]);
    assertEquals("note 64 volume 72 duration 2", lines[2]);
    assertEquals("note 64 volume 72 duration 2", lines[3]);
    assertEquals("note 64 volume 72 duration 2", lines[4]);

    //indicates the song has 5 notes
    assertEquals(5, lines.length);

  }

  @Test
  public void testShortDuration() {

    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    StringReader rd = new StringReader("tempo 200000 note 0 0 1 64 72");


    //a song with one note that has "0" duration
    try {

      GenericMusicModel<Note> model = MusicReader.parseFile(rd, new MusicModel.Builder());
      midi.setTempo(model.getTempo());
      midi.execute(model.getNotes());

    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid input: end came before start");
    }

    //this will be the array of notes that were sent.
    String[] lines = receiver.getNotes().toString().split("\n");

    //all 5 notes were sent to the receiver
    assertEquals("", lines[0]);

    assertEquals(1, lines.length);

  }

  @Test
  public void testLongDuration() {

    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    StringReader rd = new StringReader("tempo 200000 note 0 100000 1 64 72");

    GenericMusicModel<Note> model = MusicReader.parseFile(rd, new MusicModel.Builder());

    midi.setTempo(model.getTempo());
    //a song with one note that has "0" duration
    midi.execute(model.getNotes());

    //this will be the array of notes that were sent.
    String[] lines = receiver.getNotes().toString().split("\n");

    //all 5 notes were sent to the receiver
    assertEquals("note 64 volume 72 duration 100000", lines[0]);

    //only 1 note was added
    assertEquals(1, lines.length);

  }

  @Test
  public void testMystery1() {

    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    BufferedReader br = null;

    try {
      br = new BufferedReader(new FileReader("mystery-1.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    GenericMusicModel<Note> model = MusicReader.parseFile(br, new MusicModel.Builder());
    midi.setTempo(model.getTempo());

    midi.execute(model.getNotes());


    //this will be the array of notes that were sent.
    String[] lines = receiver.getNotes().toString().split("\n");

    //testing various notes that were sent to the receiver

    //first note sent to the receiver
    assertEquals("note 76 volume 64 duration 2", lines[0]);

    //104th note sent to the receiver
    assertEquals("note 74 volume 64 duration 2", lines[104]);

    //194th note
    assertEquals("note 77 volume 64 duration 2", lines[194]);

    //790th note
    assertEquals("note 53 volume 64 duration 2", lines[790]);

    //1068th note
    assertEquals("note 42 volume 64 duration 2", lines[1068]);

    //1278 (last note) sent to receiver
    assertEquals("note 42 volume 64 duration 2", lines[1278]);

    //the song has a total of 1279 notes, and they were all sent
    assertEquals(1279, lines.length);

  }


  @Test
  public void test() {
    StringBuffer r = new StringBuffer();
    IView i = new TextViewImpl(r);


    StringReader rd = new StringReader("tempo 20 " +
            "note 0 1 1 64 72");

    GenericMusicModel<Note> model = MusicReader.parseFile(rd, new MusicModel.Builder());


    i.execute(model.getNotes());


  }


  //tests a song combined consecutively, and makes sure that all notes are sent
  @Test
  public void testZeldaConsecutively() {

    BufferedReader brZL = null;
    BufferedReader brLW = null;
    try {
      brZL = new BufferedReader(new FileReader("zoot-zl.txt"));
      brLW = new BufferedReader(new FileReader("zoot-lw.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    GenericMusicModel<Note> modelZL = MusicReader.parseFile(brZL, new MusicModel.Builder());
    GenericMusicModel<Note> modelLW = MusicReader.parseFile(brLW, new MusicModel.Builder());

    List<Note> l = modelZL.getNotes();

    //adding the piece consecutively
    modelLW.addPieceConsecutive(l);


    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    midi.setTempo(modelLW.getTempo());
    midi.execute(modelLW.getNotes());

    String[] lines = receiver.getNotes().toString().split("\n");

    //indicates that notes from both songs are now in the song (they are consecutive)
    assertEquals(853, lines.length);

  }

  //tests a song combined simultaneously
  @Test
  public void testZeldaSimultaneously() {

    BufferedReader brZL = null;
    BufferedReader brLW = null;
    try {
      brZL = new BufferedReader(new FileReader("zoot-zl.txt"));
      brLW = new BufferedReader(new FileReader("zoot-lw.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    GenericMusicModel<Note> modelZL = MusicReader.parseFile(brZL, new MusicModel.Builder());
    GenericMusicModel<Note> modelLW = MusicReader.parseFile(brLW, new MusicModel.Builder());

    List<Note> l = modelZL.getNotes();

    //adding the piece simultaneously
    modelLW.addPieceSimultaneous(l);


    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);
    midi.setTempo(modelLW.getTempo());
    midi.execute(modelLW.getNotes());

    String[] lines = receiver.getNotes().toString().split("\n");

    //indicates that notes from both songs are now in the song (they are consecutive)
    assertEquals(853, lines.length);


  }

  @Test
  public void testAddNoteMidi() {
    GenericMusicModel<Note> model = new MusicModel();

    model.addNote(new Note(0, 2, 5, 69, 70));
    model.addNote(new Note(0, 2, 5, 34, 70));
    model.addNote(new Note(0, 2, 5, 62, 70));
    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    midi.setTempo(1000);

    midi.execute(model.getNotes());

    String[] lines = receiver.getNotes().toString().split("\n");

    //indicates that all the notes were added
    assertEquals("note 69 volume 70 duration 2", lines[0]);
    assertEquals("note 34 volume 70 duration 2", lines[1]);
    assertEquals("note 62 volume 70 duration 2", lines[2]);

    //indicates that all notes from the model were added.
    assertEquals(3, lines.length);

  }

  @Test
  public void testAddRemoveNoteMidi() {
    GenericMusicModel<Note> model = new MusicModel();

    Note a = new Note(0, 2, 5, 69, 70);
    model.addNote(a);
    Note v = new Note(0, 2, 5, 34, 70);
    model.addNote(v);
    model.addNote(new Note(0, 2, 5, 62, 70));

    //edited a note, and removed another
    model.editNote(a, new Note(0, 2, 5, 40, 40));
    model.removeNote(v);

    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);

    midi.setTempo(1000);
    midi.execute(model.getNotes());

    String[] lines = receiver.getNotes().toString().split("\n");


    //indicates that all the notes were added
    assertEquals("note 62 volume 70 duration 2", lines[0]);
    assertEquals("note 40 volume 40 duration 2", lines[1]);

    //indicates that all notes from the model were added.
    assertEquals(2, lines.length);

  }

  @Test
  public void testCombine3Songs() {
    BufferedReader brZL = null;
    BufferedReader brLW = null;
    BufferedReader mary = null;
    try {
      brZL = new BufferedReader(new FileReader("zoot-zl.txt"));
      brLW = new BufferedReader(new FileReader("zoot-lw.txt"));
      mary = new BufferedReader(new FileReader("mary-little-lamb.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    GenericMusicModel<Note> modelZL = MusicReader.parseFile(brZL, new MusicModel.Builder());
    GenericMusicModel<Note> modelLW = MusicReader.parseFile(brLW, new MusicModel.Builder());
    GenericMusicModel<Note> maryModel = MusicReader.parseFile(mary, new MusicModel.Builder());

    List<Note> l = modelZL.getNotes();

    //adding the piece consecutively
    modelLW.addPieceSimultaneous(l);

    //adding the third song consecutively
    modelLW.addPieceSimultaneous(maryModel.getNotes());


    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);
    midi.setTempo(modelLW.getTempo());
    midi.execute(modelLW.getNotes());

    String[] lines = receiver.getNotes().toString().split("\n");

    //indicates that notes from all three songs are now in the song (they are consecutive)
    assertEquals(887, lines.length);
  }

  @Test
  public void testCombine3Songs2() {

    BufferedReader brZL = null;
    BufferedReader brLW = null;
    BufferedReader mary = null;
    try {
      brZL = new BufferedReader(new FileReader("zoot-zl.txt"));
      brLW = new BufferedReader(new FileReader("zoot-lw.txt"));
      mary = new BufferedReader(new FileReader("mary-little-lamb.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    GenericMusicModel<Note> modelZL = MusicReader.parseFile(brZL, new MusicModel.Builder());
    GenericMusicModel<Note> modelLW = MusicReader.parseFile(brLW, new MusicModel.Builder());
    GenericMusicModel<Note> maryModel = MusicReader.parseFile(mary, new MusicModel.Builder());

    //adding the second song simultaneously
    modelLW.addPieceSimultaneous(modelZL.getNotes());

    //adding the third song consecutively
    modelLW.addPieceConsecutive(maryModel.getNotes());


    MockReceiver receiver = new MockReceiver();
    MidiMock mock = new MidiMock(receiver);

    MidiViewImpl midi = new MidiViewImpl(mock);
    midi.setTempo(modelLW.getTempo());
    midi.execute(modelLW.getNotes());

    String[] lines = receiver.getNotes().toString().split("\n");

    //indicates that notes from all three songs are now in the song, and it doesn't matter
    // if one was added consecutively or simultaneously, all notes are sent and received.
    assertEquals(887, lines.length);

  }

  @Test
  public void testFactoryView() {
    IView console = ViewCreator.create("console");
    IView visual = ViewCreator.create("visual");
    IView midi = ViewCreator.create("midi");

    assertTrue(console instanceof TextViewImpl);
    assertTrue(visual instanceof GuiViewFrame);
    assertTrue(midi instanceof MidiViewImpl);
  }

  @Test
  public void test2() {

    BufferedReader brZL = null;
    BufferedReader brLW = null;
    try {
      brZL = new BufferedReader(new FileReader("zoot-zl.txt"));
      brLW = new BufferedReader(new FileReader("zoot-lw.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    GenericMusicModel<Note> modelZL = MusicReader.parseFile(brZL, new MusicModel.Builder());
    GenericMusicModel<Note> modelLW = MusicReader.parseFile(brLW, new MusicModel.Builder());


    MockReceiver receiver1 = new MockReceiver();
    MidiMock mock1 = new MidiMock(receiver1);

    MidiViewImpl midi = new MidiViewImpl(mock1);

    MockReceiver receiver2 = new MockReceiver();
    MidiMock mock2 = new MidiMock(receiver1);

    MidiViewImpl midi2 = new MidiViewImpl(mock2);

    midi.setTempo(modelLW.getTempo());
    midi2.setTempo(modelZL.getTempo());

    midi.execute(modelLW.getNotes());
    midi2.execute(modelZL.getNotes());

  }


  @Test
  public void test4() {
    BufferedReader mary = null;

    try {
      mary = new BufferedReader(new FileReader("mary-little-lamb.txt"));
      //brLW = new BufferedReader(new FileReader("zoot-lw.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    GenericMusicModel<Note> modelMary = MusicReader.parseFile(mary, new MusicModel.Builder());

    MockReceiver receiver1 = new MockReceiver();
    MidiMock mock1 = new MidiMock(receiver1);

    MidiViewImpl midi = new MidiViewImpl(mock1);

    midi.setTempo(modelMary.getTempo());
    midi.execute(modelMary.getNotes());

    String[] lines = receiver1.getNotes().toString().split("\n");

    assertEquals(34, lines.length);

  }

  @Test
  public void test5() {
    BufferedReader mary = null;

    try {
      mary = new BufferedReader(new FileReader("mary-little-lamb.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    GenericMusicModel<Note> modelMary = MusicReader.parseFile(mary, new MusicModel.Builder());

    MockReceiver receiver1 = new MockReceiver();
    MidiMock mock1 = new MidiMock(receiver1);
    MidiViewImpl midi = new MidiViewImpl(mock1);

    midi.setTempo(modelMary.getTempo());
    midi.execute(modelMary.getNotes());

    String mockOutput = "note 64 volume 72 duration 2\n" +
            "note 55 volume 70 duration 7\n" +
            "note 62 volume 72 duration 2\n" +
            "note 60 volume 71 duration 2\n" +
            "note 62 volume 79 duration 2\n" +
            "note 55 volume 79 duration 7\n" +
            "note 64 volume 85 duration 2\n" +
            "note 64 volume 78 duration 2\n" +
            "note 64 volume 74 duration 3\n" +
            "note 55 volume 77 duration 8\n" +
            "note 62 volume 75 duration 2\n" +
            "note 62 volume 77 duration 2\n" +
            "note 62 volume 75 duration 4\n" +
            "note 55 volume 79 duration 2\n" +
            "note 64 volume 82 duration 2\n" +
            "note 67 volume 84 duration 2\n" +
            "note 67 volume 75 duration 4\n" +
            "note 55 volume 78 duration 8\n" +
            "note 64 volume 73 duration 2\n" +
            "note 62 volume 69 duration 2\n" +
            "note 60 volume 71 duration 2\n" +
            "note 62 volume 80 duration 2\n" +
            "note 55 volume 79 duration 8\n" +
            "note 64 volume 84 duration 2\n" +
            "note 64 volume 76 duration 2\n" +
            "note 64 volume 74 duration 2\n" +
            "note 64 volume 77 duration 2\n" +
            "note 55 volume 78 duration 8\n" +
            "note 62 volume 75 duration 2\n" +
            "note 62 volume 74 duration 2\n" +
            "note 64 volume 81 duration 2\n" +
            "note 62 volume 70 duration 2\n" +
            "note 52 volume 72 duration 8\n" +
            "note 60 volume 73 duration 8\n";

    assertEquals(mockOutput, receiver1.getNotes().toString());

  }


}
