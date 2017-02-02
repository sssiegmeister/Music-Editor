package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.Note;
import cs3500.music.view.GuiView;

/**
 * A class that handle's mouse events
 */
public class MouseHandler implements MouseListener {

  private int firstNotePitch;                  //the midi pitch of the first note
  private GenericMusicModel<Note> model;       //the model
  private GuiView view;                        //the view
  private MouseEvent clicked;                  //mouse event where the mouse was
  //clicked at


  /**
   * To create a MouseHandler. It takes in the midi pitch of the first note, the model and the
   * view.
   *
   * @param firstNotePitch midi integer of the first note represented
   * @param model          model
   * @param view           view
   */
  public MouseHandler(int firstNotePitch, GenericMusicModel<Note> model, GuiView view) {

    this.firstNotePitch = firstNotePitch;
    this.model = model;
    this.view = view;
  }


  @Override
  public void mousePressed(MouseEvent e) {
    this.firstNotePitch = view.getFirstMidi();

    //the mouse event where the mouse was pressed at.
    this.clicked = e;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    {
      //sends the location of where the mouse was clicked and released to method for
      // processing.
      this.mouseClick(this.clicked, e);

    }
  }

  /**
   * To handle mouse clicking. The MouseEvent where the mouse was clicked at and the MouseEvent
   * where the mouse is released at is sent to this in order to add and remove notes if appropriate.
   * If the pressed and released mousevents are the same, this indicates that the mouse was clicked.
   * If they aren't the same, this indicates that the mouse was dragged.
   *
   * @param pressed  MouseEvent
   * @param released MouseEvent.
   */
  private void mouseClick(MouseEvent pressed, MouseEvent released) {

    int pressedXCord = pressed.getX();
    int pressedYCord = pressed.getY();

    int releasedXCord = released.getX();
    int releasedYCord = released.getY();


    if (pressedYCord > view.getNumNotes() * 21) {
      return;
    }
    if ((pressedXCord > view.getBeats() * 21) || (releasedXCord > view.getBeats() * 21)) {
      return;
    }


    //if it was pressed and released in the same block --either remove or add a note there
    //this means that the mouse was only clicked. Not dragged!
    if (pressedXCord == releasedXCord && pressedYCord == releasedYCord) {

      int midiPitch = firstNotePitch - (releasedYCord / 21);


      int beat = releasedXCord / 21;

      List<Note> notes = model.getNotes();

      //to remove a note
      for (Note n : notes) {
        if ((n.getMidiPitch() == midiPitch) && (beat <= n.getEnd() - 1 && beat >= n
                .getStart
                        ())) {
          model.removeNote(n);

          this.view.repaint();
          this.view.update(model.getNotes());

          return;
        }
      }


      //if there is no note where the Mouse is clicked, then add a note of
      // duration 1 at the beat, at the given pitch

      Note n = new Note(beat, beat + 1, 1, midiPitch, 65);

      model.addNote(n);

      this.view.repaint();
      this.view.update(model.getNotes());


      //this means that the mouse was pressed and dragged. This means that a note of
      // that duration should be added.
      //if it was pressed and dragged: then add a note where it was
    } else {

      //in case the user dragged the note backwards.
      if (pressedXCord > releasedXCord) {
        int p = pressedXCord;
        pressedXCord = releasedXCord;
        releasedXCord = p;
      }
      if (pressedYCord != releasedYCord) {
        pressedYCord = releasedYCord;

        if (pressedYCord > view.getNumNotes() * 21) {
          return;
        }
      }

      int beat = pressedXCord / 21;

      int midiPitch = firstNotePitch - pressedYCord / 21;


      Note n = new Note(beat, (beat + 1 + Math.abs(pressedXCord - releasedXCord) / 21)
              , 1, midiPitch, 65);

      model.addNote(n);

      this.view.repaint();
      this.view.update(model.getNotes());
    }
  }


  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }
}
