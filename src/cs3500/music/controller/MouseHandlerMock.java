package cs3500.music.controller;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.GuiView;

/**
 *
 */
public class MouseHandlerMock extends MouseHandler {

  /**
   * To create a MouseHandler. It takes in the midi pitch of the first note, the model and
   * the view.
   *
   * @param firstNotePitch midi integer of the first note represented
   * @param model          model
   * @param view           view
   */
  public MouseHandlerMock(int firstNotePitch, GenericMusicModel<Note> model, GuiView view) {
    super(firstNotePitch, model, view);
  }


}
