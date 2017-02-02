package cs3500.music.tests;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.TextViewImpl;

import static org.junit.Assert.*;

public class TextViewImplTest {
  Appendable out = new StringBuffer();
  IView view = new TextViewImpl(out);

  @Test
  public void testDisplayConsoleView() {
    BufferedReader mary = null;
    try {
      mary = new BufferedReader(new FileReader("mary-little-lamb.txt"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    GenericMusicModel<Note> modelMary = MusicReader.parseFile(mary, new MusicModel.Builder());

    view.execute(modelMary.getNotes());
    assertEquals(new String(out.toString()),
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   " +
                    "F4  F#4   G4 \n" +
                    " 0                 X                                           " +
                    " X                 \n" +
                    " 1                 |                                           " +
                    " |                 \n" +
                    " 2                 |                                  X         " +
                    "                  \n" +
                    " 3                 |                                  |          " +
                    "                 \n" +
                    " 4                 |                        X                     " +
                    "                \n" +
                    " 5                 |                        |                     " +
                    "                \n" +
                    " 6                 |                                  X            " +
                    "               \n" +

                    " 7                                                    |            " +
                    "               \n" +
                    " 8                 X                                            X" +
                    "                 \n" +
                    " 9                 |                                            | " +
                    "                \n" +

                    "10                 |                                            X  " +
                    "               \n" +

                    "11                 |                                            |   " +
                    "              \n" +
                    "12                 |                                            X" +
                    "                 \n" +
                    "13                 |                                            |" +
                    "                 \n" +
                    "14                 |                                            | " +
                    "                \n" +
                    "15                                                                " +
                    "                \n" +
                    "16                 X                                  X           " +
                    "                \n" +
                    "17                 |                                  |           " +
                    "                \n" +


                    "18                 |                                  X             " +
                    "              \n" +
                    "19                 |                                  |              " +
                    "             \n" +
                    "20                 |                                  X             " +
                    "              \n" +
                    "21                 |                                  |              " +
                    "             \n" +

                    "22                 |                                  |              " +
                    "             \n" +
                    "23                 |                                  |              " +
                    "             \n" +
                    "24                 X                                            X    " +
                    "             \n" +
                    "25                 |                                            |    " +
                    "             \n" +
                    "26                                                                   " +
                    "          X  \n" +
                    "27                                                                   " +
                    "          |  \n" +
                    "28                                                                    " +
                    "         X  \n" +
                    "29                                                                    " +
                    "         |  \n" +
                    "30                                                                    " +
                    "         |  \n" +

                    "31                                                                    " +
                    "         |  \n" +
                    "32                 X                                            X    " +
                    "             \n" +
                    "33                 |                                            |    " +
                    "             \n" +
                    "34                 |                                  X              " +
                    "             \n" +
                    "35                 |                                  |               " +
                    "            \n" +


                    "36                 |                        X                       " +
                    "              \n" +
                    "37                 |                        |                       " +
                    "              \n" +

                    "38                 |                                  X              " +
                    "             \n" +
                    "39                 |                                  |              " +
                    "             \n" +
                    "40                 X                                            X    " +
                    "             \n" +
                    "41                 |                                            |      " +
                    "           \n" +
                    "42                 |                                            X    " +
                    "             \n" +
                    "43                 |                                            |    " +
                    "             \n" +
                    "44                 |                                            X   " +
                    "              \n" +

                    "45                 |                                            |    " +
                    "             \n" +
                    "46                 |                                            X    " +
                    "             \n" +

                    "47                 |                                            |   " +
                    "              \n" +
                    "48                 X                                  X            " +
                    "               \n" +
                    "49                 |                                  |            " +
                    "               \n" +
                    "50                 |                                  X            " +
                    "               \n" +
                    "51                 |                                  |           " +
                    "                \n" +
                    "52                 |                                            X  " +
                    "               \n" +
                    "53                 |                                            |   " +
                    "              \n" +
                    "54                 |                                  X            " +
                    "               \n" +
                    "55                 |                                  |            " +
                    "               \n" +
                    "56  X                                       X                     " +
                    "                \n" +

                    "57  |                                       |                     " +
                    "                \n" +
                    "58  |                                       |                       " +
                    "              \n" +
                    "59  |                                       |                       " +
                    "              \n" +
                    "60  |                                       |                       " +
                    "              \n" +
                    "61  |                                       |                        " +
                    "             \n" +
                    "62  |                                       |                      " +
                    "               \n" +
                    "63  |                                       |                       " +
                    "              \n");
  }

  @Test
  public void testDisplayEmptyView() {

    StringReader r = new StringReader("tempo 0");
    GenericMusicModel<Note> model = MusicReader.parseFile(r, new MusicModel
            .Builder());

    view.execute(model.getNotes());
    assertEquals(new String(out.toString()), "No music");
  }

  @Test
  public void testDisplayConsoleException() {
    try {

      StringReader r = new StringReader("Bad input");
      GenericMusicModel<Note> model = MusicReader.parseFile(r, new MusicModel
              .Builder());

      view.execute(model.getNotes());


    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Bad line type: Bad");
    }
    try {
      StringReader r = new StringReader("tempo Bad input");
      GenericMusicModel<Note> model = MusicReader.parseFile(r, new MusicModel
              .Builder());

      view.execute(model.getNotes());

    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Malformed tempo line: Bad input");
    }
    try {
      StringReader r = new StringReader("tempo 0 note Bad input");
      GenericMusicModel<Note> model = MusicReader.parseFile(r, new MusicModel
              .Builder());

      view.execute(model.getNotes());
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Malformed note line: Bad input");
    }
  }
}