package cs3500.music.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.music.model.Note;
import cs3500.music.model.NotePlaceComparator;
import cs3500.music.model.NoteToneComparator;
import cs3500.music.model.Octave;
import cs3500.music.model.Pitch;

/**
 * A class to display a composition in text format. This class implements the
 * MusicViewInterface.
 */
public class TextViewImpl implements IView {

  private Appendable ap;
  private List<Note> allNotes;


  /**
   * Constructor for a TextViewImp object. It takes in an appendable object in order to
   * specify what output this view should be in.
   *
   * @param ap the output to be displayed in the form of an appendable.
   */
  public TextViewImpl(Appendable ap) {
    this.ap = ap;
  }


  //Documented Change: The view is now responsible for the console ouput, not the model
  @Override
  public void execute(List<Note> allNotes) {
    this.allNotes = allNotes;

    if (allNotes.size() == 0) {
      try {
        ap.append("No music");
        return;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    StringBuilder builder = new StringBuilder();
    ArrayList<ArrayList<String>> music = new ArrayList<>();
    NoteToneComparator comp = new NoteToneComparator();
    Collections.sort(allNotes, comp);
    music.add(new ArrayList<>());
    music.get(0).add("");
    Note min = allNotes.get(0);
    Note max = allNotes.get(allNotes.size() - 1);
    for (Octave o : Octave.values()) {
      for (Pitch p : Pitch.values()) {
        Note n = new Note(p, o, 1, 0);
        if (comp.compare(n, min) >= 0 &&
                comp.compare(n, max) <= 0) {
          music.get(0).add(p.printPitch() + o.printOctave());
        }
      }
    }
    int length = music.get(0).size();
    int end = getLastBeat();
    music.get(0).set(0, resizeNums("", Integer.toString(end - 1).length()));
    for (int i = 1; i <= end; i++) {
      music.add(new ArrayList<>());
      while (music.get(i).size() < length) {
        music.get(i).add(null);
      }
      music.get(i).set(0, resizeNums(Integer.toString(i - 1),
              Integer.toString(end - 1).length()));
    }
    for (Note n : allNotes) {
      n.addNote(music);
    }
    for (int i = 1; i < length; i++) {
      music.get(0).set(i, resizeTone(music.get(0).get(i)));
    }
    for (int i = 0; i < music.size(); i++) {
      for (int j = 0; j < length; j++) {
        if (music.get(i).get(j) == null) {
          builder.append("     ");
        } else {
          builder.append(music.get(i).get(j));
        }
      }
      builder.append("\n");
    }

    try {
      ap.append(builder.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * @return an integer to represent the last beat number in the piece
   */
  private int getLastBeat() {
    Collections.sort(allNotes, new NotePlaceComparator());
    return allNotes.get(allNotes.size() - 1).getEnd();
  }

  /**
   * Formats the string representing the tone to be five characters long, and mostly
   * centered
   *
   * @param tone the string representation of the tone
   * @return the formatted tone
   */
  private String resizeTone(String tone) {
    StringBuilder builder = new StringBuilder();
    builder.append(tone);
    for (int i = tone.length(); i < 5; i++) {
      if (i % 2 == 0) {
        builder.insert(0, " ");
      } else {
        builder.append(" ");
      }
    }
    return builder.toString();
  }


  /**
   * Formats the string representing the beat number to be as many characters long as the
   * beat number with the most characters
   *
   * @param num the string representation of the number
   * @param max the length of the longest number string
   * @return the formatted number
   */
  private String resizeNums(String num, int max) {
    StringBuilder builder = new StringBuilder();
    builder.append(num);
    while (builder.length() < max) {
      builder.insert(0, " ");
    }
    return builder.toString();
  }


  @Override
  public void setTempo(int tempo) {
  }


}
