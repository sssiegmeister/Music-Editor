package cs3500.music.controller;

import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.GuiView;

/**
 * A mock controller to test that keyboard handlers work as expected.
 */
public class MockController implements IMusicEditorController {

  public Controller c;
  public KeyboardHandler keyboardHandler;
  public MouseHandler mouseHandler;

  public GenericMusicModel<Note> model;
  public GuiView view;

  public MockController(Controller c, GenericMusicModel<Note> model, GuiView view) {
    this.c = c;
    this.model = model;
    this.view = view;
  }


  @Override
  public void configureKeyBoardListener() {

    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();


    this.keyboardHandler = new KeyboardHandler();

    keyPresses.put(39, new MockRunnable(false));

    //set up the keyboard types.
    keyboardHandler.setKeyTypedMap(keyTypes);
    keyboardHandler.setKeyPressedMap(keyPresses);
    keyboardHandler.setKeyReleasedMap(keyReleases);

    view.addKeyListener(keyboardHandler);

  }


  @Override
  public void configureMouseListener() {

    this.mouseHandler = new MouseHandler(view.getFirstNotePitch(), model, view);

    view.addMouseHandler(mouseHandler);
  }

  public void configureMouseListener(MouseHandler m) {
    view.addMouseHandler(m);
  }


  @Override
  public void start() {
  }





}
