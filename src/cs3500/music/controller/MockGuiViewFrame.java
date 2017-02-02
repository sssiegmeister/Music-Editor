package cs3500.music.controller;

import cs3500.music.view.GuiViewFrame;

/**
 * A mock gui view frame that overrides the method allMouseHandler
 */
public class MockGuiViewFrame extends GuiViewFrame {

  @Override
  public void addMouseHandler(MouseHandler e) {
    this.addMouseListener(e);
  }

  @Override
  public int getNumNotes() {
    return 16;
  }

  @Override
  public int getBeats() {
    return 64;
  }
}
