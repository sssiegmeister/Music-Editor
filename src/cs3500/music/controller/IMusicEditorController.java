package cs3500.music.controller;


/**
 * To specify methods needed for a controller that facilitates interactions between a
 * model and a view.
 */
public interface IMusicEditorController {


  /**
   * Configure's the keyboard in such a way that a user can press certain keys in order to
   * control or change something. Certain keys map to functions, so when those keys are
   * pressed, a certain function occurs.
   *
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of
   * code as Runnable object, one for each time a key is typed, pressed and released, only
   * for those that the program needs.
   */
  public void configureKeyBoardListener();


  /**
   * Configure's the mouse in such a way that a user can click in certain areas in order
   * to control or change something. Clicked places map to functions, so when those areas
   * are pressed, a certain function occurs.
   *
   * Creates and sets a mouse listener for the view.
   */
  public void configureMouseListener();


  /**
   * Starts the functionality of the controller.
   */
  public void start();

}
