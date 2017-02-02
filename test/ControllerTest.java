import org.junit.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import cs3500.music.controller.Controller;
import cs3500.music.controller.IMusicEditorController;
import cs3500.music.controller.MockController;
import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;

import static junit.framework.TestCase.assertEquals;

/**
 * to test various methods in the controller class
 */
public class ControllerTest {


  //To test the configure mouse listener.
  @Test
  public void testController() {
    GenericMusicModel<Note> mary = null;
    try {
      mary = MusicReader.parseFile(new FileReader
              ("mary-little-lamb.txt"), new MusicModel.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Controller controller = new Controller(mary, new GuiViewFrame());

    GuiView g = new GuiViewFrame();

    MockController control = new MockController(controller, mary, g);

    assertEquals(control.keyboardHandler, null);
    assertEquals(control.mouseHandler, null);

    control.configureKeyBoardListener();

    //the configureKeyBoardListern updated the key board listener
    assertEquals(control.keyboardHandler != null, true);

    //the mouse handler hasn't been updated yet
    assertEquals(control.mouseHandler, null);

    control.configureMouseListener();

    assertEquals(control.mouseHandler != null, true);



  }

  public void test() {
    try {
      GenericMusicModel<Note> mary = MusicReader.parseFile(new FileReader
              ("mary-little-lamb.txt"), new MusicModel.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
