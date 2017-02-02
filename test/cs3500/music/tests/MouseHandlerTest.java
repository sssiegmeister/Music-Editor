package cs3500.music.tests;

import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.Controller;

import cs3500.music.controller.MockController;
import cs3500.music.controller.MockGuiViewFrame;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;


import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import static java.awt.event.MouseEvent.MOUSE_ENTERED;
import static java.awt.event.MouseEvent.MOUSE_EXITED;
import static java.awt.event.MouseEvent.MOUSE_PRESSED;
import static java.awt.event.MouseEvent.MOUSE_RELEASED;
import static junit.framework.TestCase.assertEquals;

/**
 *
 */
public class MouseHandlerTest {

  //this tests mouse handling functions. Functions tested are mouse clicks, if a mouse
  // enters and exits the view, if a note is removed, if a note is added, if a note is
  // added and then removed, if a note is dragged out (longer duration), if a note is
  // removed from it's middle, and if a note is tried to be added outside of the frame,
  // or further than the beats.
  @Test
  public void testMouseHandler1() {

    GenericMusicModel<Note> mary = null;

    try {
      mary = MusicReader.parseFile(new FileReader
              ("mary-little-lamb.txt"), new MusicModel.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    MockGuiViewFrame g = new MockGuiViewFrame();
    g.update(mary.getNotes());

    MouseHandler m = new MouseHandler(67, mary, g);

    Controller controller = new Controller(mary, g);

    MockController mc = new MockController(controller, mary, g);

    mc.configureMouseListener(m);

    MouseEvent entered = new MouseEvent(new Scrollbar(), 12, 12, MOUSE_ENTERED, 2, 3,
            3,
            false);

    MouseEvent clicked = new MouseEvent(new Scrollbar(), 12, 12, MOUSE_CLICKED, 2, 3,
            3,
            false);

    MouseEvent exit = new MouseEvent(new Scrollbar(), 12, 12, MOUSE_EXITED, 2, 3,
            3,
            false);

    //the mouse was clicked
    m.mouseClicked(clicked);
    assertEquals(34, mary.getNotes().size());


    //the mouse was clicked
    m.mouseExited(exit);
    assertEquals(34, mary.getNotes().size());

    //the mouse was clicked
    m.mouseEntered(entered);
    assertEquals(34, mary.getNotes().size());


    MouseEvent pressed1 = new MouseEvent(g, 15, 80, MOUSE_PRESSED, 8, 10,
            3,
            false);
    MouseEvent released1 = new MouseEvent(g, 15, 80, MOUSE_RELEASED, 8, 10,
            3,
            false);

    //the mouse was clicked: and it added a new note. Now there are 35 notes.
    m.mousePressed(pressed1);
    m.mouseReleased(released1);
    assertEquals(35, mary.getNotes().size());


    //the mouse was clicked in the same region where a new note was just added: this
    // removes the note again.
    m.mousePressed(pressed1);
    m.mouseReleased(released1);
    assertEquals(34, mary.getNotes().size());


    //this is a mouse event where the mouse is dragged
    MouseEvent pressed2 = new MouseEvent(g, 15, 80, MOUSE_PRESSED, 8, 10,
            3,
            false);
    MouseEvent released2 = new MouseEvent(g, 15, 80, MOUSE_RELEASED, 8, 24,
            3,
            false);

    //the mouse was dragged, and this causes a new note to be added.
    m.mousePressed(pressed1);
    m.mouseReleased(released1);
    assertEquals(35, mary.getNotes().size());


    //this is a mouse event where the mouse is clicked off screen: It should not do
    // anything to the notes.
    MouseEvent pressed3 = new MouseEvent(g, 15, 80, MOUSE_PRESSED, 1000, 1000,
            3,
            false);
    MouseEvent released3 = new MouseEvent(g, 15, 80, MOUSE_RELEASED, 1000, 1000,
            3,
            false);

    //the mouse was dragged, and this causes a new note to be added.
    m.mousePressed(pressed3);
    m.mouseReleased(released3);
    assertEquals(35, mary.getNotes().size());


    //this tests a mouse clicking the middle of a note, and it removes that entire note.
    MouseEvent pressed4 = new MouseEvent(g, 15, 80, MOUSE_PRESSED, 640, 13,
            3,
            false);
    MouseEvent released4 = new MouseEvent(g, 15, 80, MOUSE_RELEASED, 640, 13,
            3,
            false);

    //the mouse was dragged, and this causes a new note to be added.
    m.mousePressed(pressed4);
    m.mouseReleased(released4);


    //this tests a mouse clicking past the end of the beats of the song. This does not
    // change the notes in the song in any way.
    MouseEvent pressed5 = new MouseEvent(g, 15, 80, MOUSE_PRESSED, 1000, 13,
            3,
            false);
    MouseEvent released5 = new MouseEvent(g, 15, 80, MOUSE_RELEASED, 1000, 13,
            3,
            false);

    //the mouse was dragged, and this causes a new note to be added.
    m.mousePressed(pressed4);
    m.mouseReleased(released4);
    assertEquals(35, mary.getNotes().size());

  }

}
