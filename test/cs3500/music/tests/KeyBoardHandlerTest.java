package cs3500.music.tests;

import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MockRunnable;
import cs3500.music.controller.MockRunnable2;
import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;

import static junit.framework.TestCase.assertEquals;

/**
 * A testing class to test the keyboardhandler, using a mock runnable
 */
public class KeyBoardHandlerTest {


  //to test that keyboard presses respond as they should.
  @Test
  public void testKeyBoardPresses() {
    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();


    KeyboardHandler keyboardHandler = new KeyboardHandler();

    MockRunnable r = new MockRunnable(false);
    MockRunnable r2 = new MockRunnable(false);
    MockRunnable r3 = new MockRunnable(false);
    MockRunnable r4 = new MockRunnable(false);

    keyPresses.put(84, r);
    keyPresses.put(85, r2);
    keyPresses.put(86, r3);
    keyPresses.put(87, r4);

    //set up the keyboard types.
    keyboardHandler.setKeyTypedMap(keyTypes);
    keyboardHandler.setKeyPressedMap(keyPresses);
    keyboardHandler.setKeyReleasedMap(keyReleases);


    KeyEvent k = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 84,
            'w');

    //checks to see if the key is typed--in this case it is
    keyboardHandler.keyPressed(k);

    assertEquals(true, r.ran);  //this is the only one that should have ran,
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k2 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 65,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyPressed(k2);
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);

    KeyEvent k3 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 86,
            'v');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyPressed(k3);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k4 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 87,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyPressed(k4);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(true, r4.ran);


    KeyEvent k5 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 85,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyPressed(k5);
    assertEquals(true, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(true, r4.ran);

  }


  @Test
  public void testKeyBoardTyped() {
    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();


    KeyboardHandler keyboardHandler = new KeyboardHandler();

    MockRunnable r = new MockRunnable(false);
    MockRunnable r2 = new MockRunnable(false);
    MockRunnable r3 = new MockRunnable(false);
    MockRunnable r4 = new MockRunnable(false);

    keyTypes.put(84, r);
    keyTypes.put(85, r2);
    keyTypes.put(86, r3);
    keyTypes.put(87, r4);

    //set up the keyboard types.
    keyboardHandler.setKeyTypedMap(keyTypes);
    keyboardHandler.setKeyPressedMap(keyPresses);
    keyboardHandler.setKeyReleasedMap(keyReleases);


    KeyEvent k = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 84,
            'w');

    //checks to see if the key is typed--in this case it is
    keyboardHandler.keyTyped(k);

    assertEquals(true, r.ran);  //this is the only one that should have ran,
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k2 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 65,
            'a');

    //checks to see if the key is typed--in this case none of them are typed as the
    // control key isn't down
    keyboardHandler.keyTyped(k2);
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);

    KeyEvent k3 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent
            .CTRL_DOWN_MASK, 86,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyTyped(k3);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k4 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 87,
            'a');


    keyboardHandler.keyTyped(k4);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(true, r4.ran);


    KeyEvent k5 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 85,
            'a');


    keyboardHandler.keyTyped(k5);
    assertEquals(true, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(true, r4.ran);

  }

  @Test
  public void testKeyBoardReleases() {
    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();


    KeyboardHandler keyboardHandler = new KeyboardHandler();

    MockRunnable r = new MockRunnable(false);
    MockRunnable r2 = new MockRunnable(false);
    MockRunnable r3 = new MockRunnable(false);
    MockRunnable r4 = new MockRunnable(false);

    keyReleases.put(84, r);
    keyReleases.put(85, r2);
    keyReleases.put(86, r3);
    keyReleases.put(87, r4);

    //set up the keyboard types.
    keyboardHandler.setKeyTypedMap(keyTypes);
    keyboardHandler.setKeyPressedMap(keyPresses);
    keyboardHandler.setKeyReleasedMap(keyReleases);


    KeyEvent k = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 84,
            'w');

    //checks to see if the key is typed--in this case it is
    keyboardHandler.keyReleased(k);

    assertEquals(false, r.ran);
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k2 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 65,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyReleased(k2);
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k3 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 86,
            'v');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyReleased(k3);
    assertEquals(false, r.ran);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k4 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 87,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyReleased(k4);
    assertEquals(false, r.ran);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(true, r4.ran);


    KeyEvent k5 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 85,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyReleased(k5);
    assertEquals(true, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(true, r4.ran);

  }


  @Test
  public void testKeyBoardMix() {
    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();


    KeyboardHandler keyboardHandler = new KeyboardHandler();

    MockRunnable r = new MockRunnable(false);
    MockRunnable r2 = new MockRunnable(false);
    MockRunnable r3 = new MockRunnable(false);
    MockRunnable r4 = new MockRunnable(false);

    keyReleases.put(84, r);
    keyPresses.put(85, r2);
    keyTypes.put(86, r3);
    keyTypes.put(87, r4);

    //set up the keyboard types.
    keyboardHandler.setKeyTypedMap(keyTypes);
    keyboardHandler.setKeyPressedMap(keyPresses);
    keyboardHandler.setKeyReleasedMap(keyReleases);


    KeyEvent k = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 84,
            'w');

    //checks to see if the key is typed--in this case it is
    keyboardHandler.keyReleased(k);

    assertEquals(false, r.ran);
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k2 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 65,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyReleased(k2);
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k3 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 84,
            'v');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyReleased(k3);
    assertEquals(true, r.ran);
    assertEquals(false, r2.ran);
    assertEquals(false, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k4 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 86,
            'a');

    //checks to see if the key is typed--in this case none of them are typed
    keyboardHandler.keyTyped(k4);
    assertEquals(true, r.ran);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(false, r4.ran);


    KeyEvent k5 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 87,
            'a');

    //checks to see if keyTyped key is typed--in this case none of them are typed
    keyboardHandler.keyTyped(k5);
    assertEquals(false, r2.ran);
    assertEquals(true, r3.ran);
    assertEquals(true, r4.ran);

  }

  //tests to see that appropriate actions are taken when keys are pressed. The mock
  // runnable adds a new note to the model, so everytime a correct key is pressed, it
  // should add a note. This ensures that the appropriate action is taken on the
  // keypresses, and that the runnable objects perform as they should.
  @Test
  public void test2() {

    GenericMusicModel<Note> mary = null;

    try {
      mary = MusicReader.parseFile(new FileReader
              ("mary-little-lamb.txt"), new MusicModel.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();


    KeyboardHandler keyboardHandler = new KeyboardHandler();

    MockRunnable2 r = new MockRunnable2(mary);
    MockRunnable2 r2 = new MockRunnable2(mary);
    MockRunnable2 r3 = new MockRunnable2(mary);
    MockRunnable2 r4 = new MockRunnable2(mary);

    keyReleases.put(84, r);
    keyPresses.put(85, r2);
    keyTypes.put(86, r3);
    keyTypes.put(87, r4);

    //set up the keyboard types.
    keyboardHandler.setKeyTypedMap(keyTypes);
    keyboardHandler.setKeyPressedMap(keyPresses);
    keyboardHandler.setKeyReleasedMap(keyReleases);


    KeyEvent k = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.SHIFT_DOWN_MASK, 85,
            'w');

    //this successfully added a note to the model.
    keyboardHandler.keyPressed(k);

    assertEquals(35, mary.getNotes().size());


    KeyEvent k2 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 84,
            'w');

    //this successfully again added a note to the model.
    keyboardHandler.keyReleased(k2);
    assertEquals(36, mary.getNotes().size());


    KeyEvent k3 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 87,
            'w');

    //this successfully again added a note to the model.
    keyboardHandler.keyTyped(k3);

    assertEquals(37, mary.getNotes().size());


    KeyEvent k4 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 86,
            'w');

    //this successfully again added a note to the model.
    keyboardHandler.keyTyped(k4);

    assertEquals(38, mary.getNotes().size());


    KeyEvent k5 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 84,
            'w');

    //this successfully again added a note to the model.
    keyboardHandler.keyReleased(k5);
    assertEquals(39, mary.getNotes().size());

    //a wrong key is pressed: nothing should occur
    KeyEvent k6 = new KeyEvent(new Scrollbar(), 12, 12, InputEvent.CTRL_DOWN_MASK, 24,
            'w');

    //no note is added to the model
    keyboardHandler.keyReleased(k6);
    assertEquals(39, mary.getNotes().size());

  }


}
