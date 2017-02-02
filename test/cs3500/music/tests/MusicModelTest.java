package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.GenericMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.NotePlaceComparator;
import cs3500.music.model.NoteToneComparator;
import cs3500.music.model.Octave;
import cs3500.music.model.Pitch;
import cs3500.music.view.TextViewImpl;

import static org.junit.Assert.*;

public class MusicModelTest {

  GenericMusicModel<Note> model = new MusicModel();

  @Test
  public void testEditMusic() {
    //Checks that the model properly displays no music
    StringBuffer a = new StringBuffer();
    TextViewImpl i = new TextViewImpl(a);
    i.execute(model.getNotes());
    assertEquals(a.toString(), "No music");

    model.addNote(new Note(Pitch.E, Octave.Four, 2, 0));
    //First test for a note added


    //Checks that the model properly displays no music
    StringBuffer b = new StringBuffer();
    TextViewImpl ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "   E4 \n" +
                    "0  X  \n" +
                    "1  |  \n");

    model.addNote(new Note(Pitch.D, Octave.Four, 2, 2));
    model.addNote(new Note(Pitch.C, Octave.Four, 2, 4));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 6));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 8));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 10));
    model.addNote(new Note(Pitch.E, Octave.Four, 3, 12));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 16));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 18));
    model.addNote(new Note(Pitch.D, Octave.Four, 4, 20));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 24));
    model.addNote(new Note(Pitch.G, Octave.Four, 2, 26));
    model.addNote(new Note(Pitch.G, Octave.Four, 4, 28));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 32));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 34));
    model.addNote(new Note(Pitch.C, Octave.Four, 2, 36));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 38));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 40));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 42));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 44));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 46));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 48));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 50));
    model.addNote(new Note(Pitch.E, Octave.Four, 2, 52));
    model.addNote(new Note(Pitch.D, Octave.Four, 2, 54));
    model.addNote(new Note(Pitch.C, Octave.Four, 8, 56));


    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    //Test for various notes, and that the line numbers and tone labels are adjusted properly
    assertEquals(b.toString(),
            "    C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                      X                 \n" +
                    " 1                      |                 \n" +
                    " 2            X                           \n" +
                    " 3            |                           \n" +
                    " 4  X                                     \n" +
                    " 5  |                                     \n" +
                    " 6            X                           \n" +
                    " 7            |                           \n" +
                    " 8                      X                 \n" +
                    " 9                      |                 \n" +
                    "10                      X                 \n" +
                    "11                      |                 \n" +
                    "12                      X                 \n" +
                    "13                      |                 \n" +
                    "14                      |                 \n" +
                    "15                                        \n" +
                    "16            X                           \n" +
                    "17            |                           \n" +
                    "18            X                           \n" +
                    "19            |                           \n" +
                    "20            X                           \n" +
                    "21            |                           \n" +
                    "22            |                           \n" +
                    "23            |                           \n" +
                    "24                      X                 \n" +
                    "25                      |                 \n" +
                    "26                                     X  \n" +
                    "27                                     |  \n" +
                    "28                                     X  \n" +
                    "29                                     |  \n" +
                    "30                                     |  \n" +
                    "31                                     |  \n" +
                    "32                      X                 \n" +
                    "33                      |                 \n" +
                    "34            X                           \n" +
                    "35            |                           \n" +
                    "36  X                                     \n" +
                    "37  |                                     \n" +
                    "38            X                           \n" +
                    "39            |                           \n" +
                    "40                      X                 \n" +
                    "41                      |                 \n" +
                    "42                      X                 \n" +
                    "43                      |                 \n" +
                    "44                      X                 \n" +
                    "45                      |                 \n" +
                    "46                      X                 \n" +
                    "47                      |                 \n" +
                    "48            X                           \n" +
                    "49            |                           \n" +
                    "50            X                           \n" +
                    "51            |                           \n" +
                    "52                      X                 \n" +
                    "53                      |                 \n" +
                    "54            X                           \n" +
                    "55            |                           \n" +
                    "56  X                                     \n" +
                    "57  |                                     \n" +
                    "58  |                                     \n" +
                    "59  |                                     \n" +
                    "60  |                                     \n" +
                    "61  |                                     \n" +
                    "62  |                                     \n" +
                    "63  |                                     \n");


    model.addNote(new Note(Pitch.G, Octave.Three, 7, 0));
    model.addNote(new Note(Pitch.G, Octave.Three, 7, 8));
    model.addNote(new Note(Pitch.G, Octave.Three, 8, 16));
    model.addNote(new Note(Pitch.G, Octave.Three, 2, 24));
    model.addNote(new Note(Pitch.G, Octave.Three, 8, 32));
    model.addNote(new Note(Pitch.G, Octave.Three, 8, 40));
    model.addNote(new Note(Pitch.G, Octave.Three, 8, 48));
    model.addNote(new Note(Pitch.E, Octave.Three, 8, 56));
    //Test for even more notes, this time with overlapping pitches w/ different octaves
    //Lines split to meet 100 character limit

    b = new StringBuffer();
    ii = new TextViewImpl(b);

    ii.execute(model.getNotes());
    assertEquals(b.toString(),
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3  " +
                    " C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                 X                     " +
                    "                       X                 \n" +
                    " 1                 |                    " +
                    "                        |                 \n" +
                    " 2                 |                   " +
                    "               X                           \n" +
                    " 3                 |               " +
                    "                   |                           \n" +
                    " 4                 |                  " +
                    "      X                                     \n" +
                    " 5                 |                      " +
                    "  |                                     \n" +
                    " 6                 |                      " +
                    "            X                           \n" +
                    " 7                                       " +
                    "             |                           \n" +
                    " 8                 X                     " +
                    "                       X                 \n" +
                    " 9                 |                     " +
                    "                       |                 \n" +
                    "10                 |              " +
                    "                              X                 \n" +
                    "11                 |                 " +
                    "                           |                 \n" +
                    "12                 |             " +
                    "                               X                 \n" +
                    "13                 |             " +
                    "                               |                 \n" +
                    "14                 |           " +
                    "                                 |                 \n" +
                    "15                                   " +
                    "                                             \n" +
                    "16                 X                " +
                    "                  X                           \n" +
                    "17                 |                " +
                    "                  |                           \n" +
                    "18                 |                 " +
                    "                 X                           \n" +
                    "19                 |                  " +
                    "                |                           \n" +
                    "20                 |                 " +
                    "                 X                           \n" +
                    "21                 |                 " +
                    "                 |                           \n" +
                    "22                 |                  " +
                    "                |                           \n" +
                    "23                 |                  " +
                    "                |                           \n" +
                    "24                 X                  " +
                    "                          X                 \n" +
                    "25                 |                 " +
                    "                           |                 \n" +
                    "26                                     " +
                    "                                        X  \n" +
                    "27                                    " +
                    "                                         |  \n" +
                    "28                                   " +
                    "                                          X  \n" +
                    "29                                  " +
                    "                                           |  \n" +
                    "30                                 " +
                    "                                            |  \n" +
                    "31                                 " +
                    "                                            |  \n" +
                    "32                 X               " +
                    "                             X                 \n" +
                    "33                 |              " +
                    "                              |                 \n" +
                    "34                 |               " +
                    "                   X                           \n" +
                    "35                 |                " +
                    "                  |                           \n" +
                    "36                 |                " +
                    "        X                                     \n" +
                    "37                 |               " +
                    "         |                                     \n" +
                    "38                 |                 " +
                    "                 X                           \n" +
                    "39                 |                " +
                    "                  |                           \n" +
                    "40                 X                  " +
                    "                          X                 \n" +
                    "41                 |                     " +
                    "                       |                 \n" +
                    "42                 |                    " +
                    "                        X                 \n" +
                    "43                 |                    " +
                    "                        |                 \n" +
                    "44                 |                    " +
                    "                        X                 \n" +
                    "45                 |                    " +
                    "                        |                 \n" +
                    "46                 |                     " +
                    "                       X                 \n" +
                    "47                 |                     " +
                    "                       |                 \n" +
                    "48                 X                     " +
                    "             X                           \n" +
                    "49                 |                      " +
                    "            |                           \n" +
                    "50                 |                      " +
                    "            X                           \n" +
                    "51                 |                      " +
                    "            |                           \n" +
                    "52                 |                      " +
                    "                      X                 \n" +
                    "53                 |                     " +
                    "                       |                 \n" +
                    "54                 |                      " +
                    "            X                           \n" +
                    "55                 |                     " +
                    "             |                           \n" +
                    "56  X                                    " +
                    "   X                                     \n" +
                    "57  |                                  " +
                    "     |                                     \n" +
                    "58  |                                    " +
                    "   |                                     \n" +
                    "59  |                                    " +
                    "   |                                     \n" +
                    "60  |                                    " +
                    "   |                                     \n" +
                    "61  |                                   " +
                    "    |                                     \n" +
                    "62  |                                    " +
                    "   |                                     \n" +
                    "63  |                                     " +
                    "  |                                     \n");
    model.removeNote(new Note(Pitch.G, Octave.Three, 7, 0));
    model.removeNote(new Note(Pitch.G, Octave.Three, 7, 8));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 16));
    model.removeNote(new Note(Pitch.G, Octave.Three, 2, 24));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 32));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 40));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 48));
    model.removeNote(new Note(Pitch.E, Octave.Three, 8, 56));
    //Tests that removing all of the previously added Notes successfully reverts the
    //display to its previous state

    b = new StringBuffer();
    ii = new TextViewImpl(b);

    ii.execute(model.getNotes());
    assertEquals(b.toString(),
            "    C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                      X                 \n" +
                    " 1                      |                 \n" +
                    " 2            X                           \n" +
                    " 3            |                           \n" +
                    " 4  X                                     \n" +
                    " 5  |                                     \n" +
                    " 6            X                           \n" +
                    " 7            |                           \n" +
                    " 8                      X                 \n" +
                    " 9                      |                 \n" +
                    "10                      X                 \n" +
                    "11                      |                 \n" +
                    "12                      X                 \n" +
                    "13                      |                 \n" +
                    "14                      |                 \n" +
                    "15                                        \n" +
                    "16            X                           \n" +
                    "17            |                           \n" +
                    "18            X                           \n" +
                    "19            |                           \n" +
                    "20            X                           \n" +
                    "21            |                           \n" +
                    "22            |                           \n" +
                    "23            |                           \n" +
                    "24                      X                 \n" +
                    "25                      |                 \n" +
                    "26                                     X  \n" +
                    "27                                     |  \n" +
                    "28                                     X  \n" +
                    "29                                     |  \n" +
                    "30                                     |  \n" +
                    "31                                     |  \n" +
                    "32                      X                 \n" +
                    "33                      |                 \n" +
                    "34            X                           \n" +
                    "35            |                           \n" +
                    "36  X                                     \n" +
                    "37  |                                     \n" +
                    "38            X                           \n" +
                    "39            |                           \n" +
                    "40                      X                 \n" +
                    "41                      |                 \n" +
                    "42                      X                 \n" +
                    "43                      |                 \n" +
                    "44                      X                 \n" +
                    "45                      |                 \n" +
                    "46                      X                 \n" +
                    "47                      |                 \n" +
                    "48            X                           \n" +
                    "49            |                           \n" +
                    "50            X                           \n" +
                    "51            |                           \n" +
                    "52                      X                 \n" +
                    "53                      |                 \n" +
                    "54            X                           \n" +
                    "55            |                           \n" +
                    "56  X                                     \n" +
                    "57  |                                     \n" +
                    "58  |                                     \n" +
                    "59  |                                     \n" +
                    "60  |                                     \n" +
                    "61  |                                     \n" +
                    "62  |                                     \n" +
                    "63  |                                     \n");
    try {
      model.removeNote(new Note(Pitch.DSharp, Octave.Five, 9, 9));
    } catch (IllegalArgumentException e) {
      //Tests that removing a non existant Note doesn't work
      assertEquals(e.getMessage(), "This note does not exist");
    }
    model.editNote(new Note(Pitch.C, Octave.Four, 2, 36),
            new Note(Pitch.GSharp, Octave.Four, 2, 36));
    //Tests that editing a Note works

    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "    C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4 \n" +
                    " 0                      X                      \n" +
                    " 1                      |                      \n" +
                    " 2            X                                \n" +
                    " 3            |                                \n" +
                    " 4  X                                          \n" +
                    " 5  |                                          \n" +
                    " 6            X                                \n" +
                    " 7            |                                \n" +
                    " 8                      X                      \n" +
                    " 9                      |                      \n" +
                    "10                      X                      \n" +
                    "11                      |                      \n" +
                    "12                      X                      \n" +
                    "13                      |                      \n" +
                    "14                      |                      \n" +
                    "15                                             \n" +
                    "16            X                                \n" +
                    "17            |                                \n" +
                    "18            X                                \n" +
                    "19            |                                \n" +
                    "20            X                                \n" +
                    "21            |                                \n" +
                    "22            |                                \n" +
                    "23            |                                \n" +
                    "24                      X                      \n" +
                    "25                      |                      \n" +
                    "26                                     X       \n" +
                    "27                                     |       \n" +
                    "28                                     X       \n" +
                    "29                                     |       \n" +
                    "30                                     |       \n" +
                    "31                                     |       \n" +
                    "32                      X                      \n" +
                    "33                      |                      \n" +
                    "34            X                                \n" +
                    "35            |                                \n" +
                    "36                                          X  \n" +
                    "37                                          |  \n" +
                    "38            X                                \n" +
                    "39            |                                \n" +
                    "40                      X                      \n" +
                    "41                      |                      \n" +
                    "42                      X                      \n" +
                    "43                      |                      \n" +
                    "44                      X                      \n" +
                    "45                      |                      \n" +
                    "46                      X                      \n" +
                    "47                      |                      \n" +
                    "48            X                                \n" +
                    "49            |                                \n" +
                    "50            X                                \n" +
                    "51            |                                \n" +
                    "52                      X                      \n" +
                    "53                      |                      \n" +
                    "54            X                                \n" +
                    "55            |                                \n" +
                    "56  X                                          \n" +
                    "57  |                                          \n" +
                    "58  |                                          \n" +
                    "59  |                                          \n" +
                    "60  |                                          \n" +
                    "61  |                                          \n" +
                    "62  |                                          \n" +
                    "63  |                                          \n");
    model.editNote(new Note(Pitch.C, Octave.Four, 2, 4),
            new Note(Pitch.GSharp, Octave.Four, 2, 4));
    model.editNote(new Note(Pitch.C, Octave.Four, 8, 56),
            new Note(Pitch.GSharp, Octave.Four, 2, 56));
    //Tests that editing more Notes works

    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "    D4  D#4   E4   F4  F#4   G4  G#4 \n" +
                    " 0            X                      \n" +
                    " 1            |                      \n" +
                    " 2  X                                \n" +
                    " 3  |                                \n" +
                    " 4                                X  \n" +
                    " 5                                |  \n" +
                    " 6  X                                \n" +
                    " 7  |                                \n" +
                    " 8            X                      \n" +
                    " 9            |                      \n" +
                    "10            X                      \n" +
                    "11            |                      \n" +
                    "12            X                      \n" +
                    "13            |                      \n" +
                    "14            |                      \n" +
                    "15                                   \n" +
                    "16  X                                \n" +
                    "17  |                                \n" +
                    "18  X                                \n" +
                    "19  |                                \n" +
                    "20  X                                \n" +
                    "21  |                                \n" +
                    "22  |                                \n" +
                    "23  |                                \n" +
                    "24            X                      \n" +
                    "25            |                      \n" +
                    "26                           X       \n" +
                    "27                           |       \n" +
                    "28                           X       \n" +
                    "29                           |       \n" +
                    "30                           |       \n" +
                    "31                           |       \n" +
                    "32            X                      \n" +
                    "33            |                      \n" +
                    "34  X                                \n" +
                    "35  |                                \n" +
                    "36                                X  \n" +
                    "37                                |  \n" +
                    "38  X                                \n" +
                    "39  |                                \n" +
                    "40            X                      \n" +
                    "41            |                      \n" +
                    "42            X                      \n" +
                    "43            |                      \n" +
                    "44            X                      \n" +
                    "45            |                      \n" +
                    "46            X                      \n" +
                    "47            |                      \n" +
                    "48  X                                \n" +
                    "49  |                                \n" +
                    "50  X                                \n" +
                    "51  |                                \n" +
                    "52            X                      \n" +
                    "53            |                      \n" +
                    "54  X                                \n" +
                    "55  |                                \n" +
                    "56                                X  \n" +
                    "57                                |  \n");
    try {
      model.editNote(new Note(Pitch.DSharp, Octave.Five, 9, 9),
              new Note(Pitch.DSharp, Octave.Five, 9, 200));
    } catch (IllegalArgumentException e) {
      //Tests that editing a non existant note doesn't work
      assertEquals(e.getMessage(), "This note does not exist");
    }
    //Tests that trying to edit a non existant Note doesn't change the model
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());
    assertEquals(b.toString(),
            "    D4  D#4   E4   F4  F#4   G4  G#4 \n" +
                    " 0            X                      \n" +
                    " 1            |                      \n" +
                    " 2  X                                \n" +
                    " 3  |                                \n" +
                    " 4                                X  \n" +
                    " 5                                |  \n" +
                    " 6  X                                \n" +
                    " 7  |                                \n" +
                    " 8            X                      \n" +
                    " 9            |                      \n" +
                    "10            X                      \n" +
                    "11            |                      \n" +
                    "12            X                      \n" +
                    "13            |                      \n" +
                    "14            |                      \n" +
                    "15                                   \n" +
                    "16  X                                \n" +
                    "17  |                                \n" +
                    "18  X                                \n" +
                    "19  |                                \n" +
                    "20  X                                \n" +
                    "21  |                                \n" +
                    "22  |                                \n" +
                    "23  |                                \n" +
                    "24            X                      \n" +
                    "25            |                      \n" +
                    "26                           X       \n" +
                    "27                           |       \n" +
                    "28                           X       \n" +
                    "29                           |       \n" +
                    "30                           |       \n" +
                    "31                           |       \n" +
                    "32            X                      \n" +
                    "33            |                      \n" +
                    "34  X                                \n" +
                    "35  |                                \n" +
                    "36                                X  \n" +
                    "37                                |  \n" +
                    "38  X                                \n" +
                    "39  |                                \n" +
                    "40            X                      \n" +
                    "41            |                      \n" +
                    "42            X                      \n" +
                    "43            |                      \n" +
                    "44            X                      \n" +
                    "45            |                      \n" +
                    "46            X                      \n" +
                    "47            |                      \n" +
                    "48  X                                \n" +
                    "49  |                                \n" +
                    "50  X                                \n" +
                    "51  |                                \n" +
                    "52            X                      \n" +
                    "53            |                      \n" +
                    "54  X                                \n" +
                    "55  |                                \n" +
                    "56                                X  \n" +
                    "57                                |  \n");
    model.editNote(new Note(Pitch.GSharp, Octave.Four, 2, 36),
            new Note(Pitch.C, Octave.Four, 2, 36));
    model.editNote(new Note(Pitch.GSharp, Octave.Four, 2, 4),
            new Note(Pitch.C, Octave.Four, 2, 4));
    model.editNote(new Note(Pitch.GSharp, Octave.Four, 2, 56),
            new Note(Pitch.C, Octave.Four, 8, 56));
    //Tests that undoing the previous edits reverts the model successfully
    //Lines split to meet 100 character limit

    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "    C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                      X                 \n" +
                    " 1                      |                 \n" +
                    " 2            X                           \n" +
                    " 3            |                           \n" +
                    " 4  X                                     \n" +
                    " 5  |                                     \n" +
                    " 6            X                           \n" +
                    " 7            |                           \n" +
                    " 8                      X                 \n" +
                    " 9                      |                 \n" +
                    "10                      X                 \n" +
                    "11                      |                 \n" +
                    "12                      X                 \n" +
                    "13                      |                 \n" +
                    "14                      |                 \n" +
                    "15                                        \n" +
                    "16            X                           \n" +
                    "17            |                           \n" +
                    "18            X                           \n" +
                    "19            |                           \n" +
                    "20            X                           \n" +
                    "21            |                           \n" +
                    "22            |                           \n" +
                    "23            |                           \n" +
                    "24                      X                 \n" +
                    "25                      |                 \n" +
                    "26                                     X  \n" +
                    "27                                     |  \n" +
                    "28                                     X  \n" +
                    "29                                     |  \n" +
                    "30                                     |  \n" +
                    "31                                     |  \n" +
                    "32                      X                 \n" +
                    "33                      |                 \n" +
                    "34            X                           \n" +
                    "35            |                           \n" +
                    "36  X                                     \n" +
                    "37  |                                     \n" +
                    "38            X                           \n" +
                    "39            |                           \n" +
                    "40                      X                 \n" +
                    "41                      |                 \n" +
                    "42                      X                 \n" +
                    "43                      |                 \n" +
                    "44                      X                 \n" +
                    "45                      |                 \n" +
                    "46                      X                 \n" +
                    "47                      |                 \n" +
                    "48            X                           \n" +
                    "49            |                           \n" +
                    "50            X                           \n" +
                    "51            |                           \n" +
                    "52                      X                 \n" +
                    "53                      |                 \n" +
                    "54            X                           \n" +
                    "55            |                           \n" +
                    "56  X                                     \n" +
                    "57  |                                     \n" +
                    "58  |                                     \n" +
                    "59  |                                     \n" +
                    "60  |                                     \n" +
                    "61  |                                     \n" +
                    "62  |                                     \n" +
                    "63  |                                     \n");
    ArrayList<Note> piece1 = new ArrayList<>();
    piece1.add(new Note(Pitch.G, Octave.Three, 7, 0));
    piece1.add(new Note(Pitch.G, Octave.Three, 7, 8));
    piece1.add(new Note(Pitch.G, Octave.Three, 8, 16));
    piece1.add(new Note(Pitch.G, Octave.Three, 2, 24));
    piece1.add(new Note(Pitch.G, Octave.Three, 8, 32));
    piece1.add(new Note(Pitch.G, Octave.Three, 8, 40));
    piece1.add(new Note(Pitch.G, Octave.Three, 8, 48));
    piece1.add(new Note(Pitch.E, Octave.Three, 8, 56));
    model.addPieceSimultaneous(piece1);
    //Tests that adding a simultaneous piece works
    //Lines split to meet 100 character limit
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3  " +
                    " C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                 X                     " +
                    "                       X                 \n" +
                    " 1                 |                    " +
                    "                        |                 \n" +
                    " 2                 |                   " +
                    "               X                           \n" +
                    " 3                 |               " +
                    "                   |                           \n" +
                    " 4                 |                  " +
                    "      X                                     \n" +
                    " 5                 |                      " +
                    "  |                                     \n" +
                    " 6                 |                      " +
                    "            X                           \n" +
                    " 7                                       " +
                    "             |                           \n" +
                    " 8                 X                     " +
                    "                       X                 \n" +
                    " 9                 |                     " +
                    "                       |                 \n" +
                    "10                 |              " +
                    "                              X                 \n" +
                    "11                 |                 " +
                    "                           |                 \n" +
                    "12                 |             " +
                    "                               X                 \n" +
                    "13                 |             " +
                    "                               |                 \n" +
                    "14                 |           " +
                    "                                 |                 \n" +
                    "15                                   " +
                    "                                             \n" +
                    "16                 X                " +
                    "                  X                           \n" +
                    "17                 |                " +
                    "                  |                           \n" +
                    "18                 |                 " +
                    "                 X                           \n" +
                    "19                 |                  " +
                    "                |                           \n" +
                    "20                 |                 " +
                    "                 X                           \n" +
                    "21                 |                 " +
                    "                 |                           \n" +
                    "22                 |                  " +
                    "                |                           \n" +
                    "23                 |                  " +
                    "                |                           \n" +
                    "24                 X                  " +
                    "                          X                 \n" +
                    "25                 |                 " +
                    "                           |                 \n" +
                    "26                                     " +
                    "                                        X  \n" +
                    "27                                    " +
                    "                                         |  \n" +
                    "28                                   " +
                    "                                          X  \n" +
                    "29                                  " +
                    "                                           |  \n" +
                    "30                                 " +
                    "                                            |  \n" +
                    "31                                 " +
                    "                                            |  \n" +
                    "32                 X               " +
                    "                             X                 \n" +
                    "33                 |              " +
                    "                              |                 \n" +
                    "34                 |               " +
                    "                   X                           \n" +
                    "35                 |                " +
                    "                  |                           \n" +
                    "36                 |                " +
                    "        X                                     \n" +
                    "37                 |               " +
                    "         |                                     \n" +
                    "38                 |                 " +
                    "                 X                           \n" +
                    "39                 |                " +
                    "                  |                           \n" +
                    "40                 X                  " +
                    "                          X                 \n" +
                    "41                 |                     " +
                    "                       |                 \n" +
                    "42                 |                    " +
                    "                        X                 \n" +
                    "43                 |                    " +
                    "                        |                 \n" +
                    "44                 |                    " +
                    "                        X                 \n" +
                    "45                 |                    " +
                    "                        |                 \n" +
                    "46                 |                     " +
                    "                       X                 \n" +
                    "47                 |                     " +
                    "                       |                 \n" +
                    "48                 X                     " +
                    "             X                           \n" +
                    "49                 |                      " +
                    "            |                           \n" +
                    "50                 |                      " +
                    "            X                           \n" +
                    "51                 |                      " +
                    "            |                           \n" +
                    "52                 |                      " +
                    "                      X                 \n" +
                    "53                 |                     " +
                    "                       |                 \n" +
                    "54                 |                      " +
                    "            X                           \n" +
                    "55                 |                     " +
                    "             |                           \n" +
                    "56  X                                    " +
                    "   X                                     \n" +
                    "57  |                                  " +
                    "     |                                     \n" +
                    "58  |                                    " +
                    "   |                                     \n" +
                    "59  |                                    " +
                    "   |                                     \n" +
                    "60  |                                    " +
                    "   |                                     \n" +
                    "61  |                                   " +
                    "    |                                     \n" +
                    "62  |                                    " +
                    "   |                                     \n" +
                    "63  |                                     " +
                    "  |                                     \n");
    model.addPieceSimultaneous(piece1);
    //Tests that adding a second simultaneous piece identical to the first one does not
    //visually change the result of getMusic
    //Lines split to meet 100 character limit
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3  " +
                    " C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                 X                     " +
                    "                       X                 \n" +
                    " 1                 |                    " +
                    "                        |                 \n" +
                    " 2                 |                   " +
                    "               X                           \n" +
                    " 3                 |               " +
                    "                   |                           \n" +
                    " 4                 |                  " +
                    "      X                                     \n" +
                    " 5                 |                      " +
                    "  |                                     \n" +
                    " 6                 |                      " +
                    "            X                           \n" +
                    " 7                                       " +
                    "             |                           \n" +
                    " 8                 X                     " +
                    "                       X                 \n" +
                    " 9                 |                     " +
                    "                       |                 \n" +
                    "10                 |              " +
                    "                              X                 \n" +
                    "11                 |                 " +
                    "                           |                 \n" +
                    "12                 |             " +
                    "                               X                 \n" +
                    "13                 |             " +
                    "                               |                 \n" +
                    "14                 |           " +
                    "                                 |                 \n" +
                    "15                                   " +
                    "                                             \n" +
                    "16                 X                " +
                    "                  X                           \n" +
                    "17                 |                " +
                    "                  |                           \n" +
                    "18                 |                 " +
                    "                 X                           \n" +
                    "19                 |                  " +
                    "                |                           \n" +
                    "20                 |                 " +
                    "                 X                           \n" +
                    "21                 |                 " +
                    "                 |                           \n" +
                    "22                 |                  " +
                    "                |                           \n" +
                    "23                 |                  " +
                    "                |                           \n" +
                    "24                 X                  " +
                    "                          X                 \n" +
                    "25                 |                 " +
                    "                           |                 \n" +
                    "26                                     " +
                    "                                        X  \n" +
                    "27                                    " +
                    "                                         |  \n" +
                    "28                                   " +
                    "                                          X  \n" +
                    "29                                  " +
                    "                                           |  \n" +
                    "30                                 " +
                    "                                            |  \n" +
                    "31                                 " +
                    "                                            |  \n" +
                    "32                 X               " +
                    "                             X                 \n" +
                    "33                 |              " +
                    "                              |                 \n" +
                    "34                 |               " +
                    "                   X                           \n" +
                    "35                 |                " +
                    "                  |                           \n" +
                    "36                 |                " +
                    "        X                                     \n" +
                    "37                 |               " +
                    "         |                                     \n" +
                    "38                 |                 " +
                    "                 X                           \n" +
                    "39                 |                " +
                    "                  |                           \n" +
                    "40                 X                  " +
                    "                          X                 \n" +
                    "41                 |                     " +
                    "                       |                 \n" +
                    "42                 |                    " +
                    "                        X                 \n" +
                    "43                 |                    " +
                    "                        |                 \n" +
                    "44                 |                    " +
                    "                        X                 \n" +
                    "45                 |                    " +
                    "                        |                 \n" +
                    "46                 |                     " +
                    "                       X                 \n" +
                    "47                 |                     " +
                    "                       |                 \n" +
                    "48                 X                     " +
                    "             X                           \n" +
                    "49                 |                      " +
                    "            |                           \n" +
                    "50                 |                      " +
                    "            X                           \n" +
                    "51                 |                      " +
                    "            |                           \n" +
                    "52                 |                      " +
                    "                      X                 \n" +
                    "53                 |                     " +
                    "                       |                 \n" +
                    "54                 |                      " +
                    "            X                           \n" +
                    "55                 |                     " +
                    "             |                           \n" +
                    "56  X                                    " +
                    "   X                                     \n" +
                    "57  |                                  " +
                    "     |                                     \n" +
                    "58  |                                    " +
                    "   |                                     \n" +
                    "59  |                                    " +
                    "   |                                     \n" +
                    "60  |                                    " +
                    "   |                                     \n" +
                    "61  |                                   " +
                    "    |                                     \n" +
                    "62  |                                    " +
                    "   |                                     \n" +
                    "63  |                                     " +
                    "  |                                     \n");
    model.removeNote(new Note(Pitch.G, Octave.Three, 7, 0));
    model.removeNote(new Note(Pitch.G, Octave.Three, 7, 8));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 16));
    model.removeNote(new Note(Pitch.G, Octave.Three, 2, 24));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 32));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 40));
    model.removeNote(new Note(Pitch.G, Octave.Three, 8, 48));
    model.removeNote(new Note(Pitch.E, Octave.Three, 8, 56));
    //Tests that adding a second simultaneous piece identical to the first one does not override
    //the first one, and that removing the second one leaves the first one intact
    //Lines split to meet 100 character limit
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3  " +
                    " C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                 X                     " +
                    "                       X                 \n" +
                    " 1                 |                    " +
                    "                        |                 \n" +
                    " 2                 |                   " +
                    "               X                           \n" +
                    " 3                 |               " +
                    "                   |                           \n" +
                    " 4                 |                  " +
                    "      X                                     \n" +
                    " 5                 |                      " +
                    "  |                                     \n" +
                    " 6                 |                      " +
                    "            X                           \n" +
                    " 7                                       " +
                    "             |                           \n" +
                    " 8                 X                     " +
                    "                       X                 \n" +
                    " 9                 |                     " +
                    "                       |                 \n" +
                    "10                 |              " +
                    "                              X                 \n" +
                    "11                 |                 " +
                    "                           |                 \n" +
                    "12                 |             " +
                    "                               X                 \n" +
                    "13                 |             " +
                    "                               |                 \n" +
                    "14                 |           " +
                    "                                 |                 \n" +
                    "15                                   " +
                    "                                             \n" +
                    "16                 X                " +
                    "                  X                           \n" +
                    "17                 |                " +
                    "                  |                           \n" +
                    "18                 |                 " +
                    "                 X                           \n" +
                    "19                 |                  " +
                    "                |                           \n" +
                    "20                 |                 " +
                    "                 X                           \n" +
                    "21                 |                 " +
                    "                 |                           \n" +
                    "22                 |                  " +
                    "                |                           \n" +
                    "23                 |                  " +
                    "                |                           \n" +
                    "24                 X                  " +
                    "                          X                 \n" +
                    "25                 |                 " +
                    "                           |                 \n" +
                    "26                                     " +
                    "                                        X  \n" +
                    "27                                    " +
                    "                                         |  \n" +
                    "28                                   " +
                    "                                          X  \n" +
                    "29                                  " +
                    "                                           |  \n" +
                    "30                                 " +
                    "                                            |  \n" +
                    "31                                 " +
                    "                                            |  \n" +
                    "32                 X               " +
                    "                             X                 \n" +
                    "33                 |              " +
                    "                              |                 \n" +
                    "34                 |               " +
                    "                   X                           \n" +
                    "35                 |                " +
                    "                  |                           \n" +
                    "36                 |                " +
                    "        X                                     \n" +
                    "37                 |               " +
                    "         |                                     \n" +
                    "38                 |                 " +
                    "                 X                           \n" +
                    "39                 |                " +
                    "                  |                           \n" +
                    "40                 X                  " +
                    "                          X                 \n" +
                    "41                 |                     " +
                    "                       |                 \n" +
                    "42                 |                    " +
                    "                        X                 \n" +
                    "43                 |                    " +
                    "                        |                 \n" +
                    "44                 |                    " +
                    "                        X                 \n" +
                    "45                 |                    " +
                    "                        |                 \n" +
                    "46                 |                     " +
                    "                       X                 \n" +
                    "47                 |                     " +
                    "                       |                 \n" +
                    "48                 X                     " +
                    "             X                           \n" +
                    "49                 |                      " +
                    "            |                           \n" +
                    "50                 |                      " +
                    "            X                           \n" +
                    "51                 |                      " +
                    "            |                           \n" +
                    "52                 |                      " +
                    "                      X                 \n" +
                    "53                 |                     " +
                    "                       |                 \n" +
                    "54                 |                      " +
                    "            X                           \n" +
                    "55                 |                     " +
                    "             |                           \n" +
                    "56  X                                    " +
                    "   X                                     \n" +
                    "57  |                                  " +
                    "     |                                     \n" +
                    "58  |                                    " +
                    "   |                                     \n" +
                    "59  |                                    " +
                    "   |                                     \n" +
                    "60  |                                    " +
                    "   |                                     \n" +
                    "61  |                                   " +
                    "    |                                     \n" +
                    "62  |                                    " +
                    "   |                                     \n" +
                    "63  |                                     " +
                    "  |                                     \n");
    model.addPieceConsecutive(piece1);
    //Tests that adding a consecutive piece works
    //Lines split to meet 100 character limit
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "     E3   F3  F#3   G3  G#3   A3  A#3   B3 " +
                    "  C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    "  0                 X                    " +
                    "                        X                 \n" +
                    "  1                 |                    " +
                    "                        |                 \n" +
                    "  2                 |                  " +
                    "                X                           \n" +
                    "  3                 |                " +
                    "                  |                           \n" +
                    "  4                 |                " +
                    "        X                                     \n" +
                    "  5                 |             " +
                    "           |                                     \n" +
                    "  6                 |             " +
                    "                     X                           \n" +
                    "  7                              " +
                    "                      |                           \n" +
                    "  8                 X            " +
                    "                                X                 \n" +
                    "  9                 |            " +
                    "                                |                 \n" +
                    " 10                 |                " +
                    "                            X                 \n" +
                    " 11                 |             " +
                    "                               |                 \n" +
                    " 12                 |              " +
                    "                              X                 \n" +
                    " 13                 |                 " +
                    "                           |                 \n" +
                    " 14                 |                 " +
                    "                           |                 \n" +
                    " 15                                   " +
                    "                                             \n" +
                    " 16                 X                 " +
                    "                 X                           \n" +
                    " 17                 |                  " +
                    "                |                           \n" +
                    " 18                 |                " +
                    "                  X                           \n" +
                    " 19                 |               " +
                    "                   |                           \n" +
                    " 20                 |              " +
                    "                    X                           \n" +
                    " 21                 |               " +
                    "                   |                           \n" +
                    " 22                 |                " +
                    "                  |                           \n" +
                    " 23                 |               " +
                    "                   |                           \n" +
                    " 24                 X               " +
                    "                             X                 \n" +
                    " 25                 |                " +
                    "                            |                 \n" +
                    " 26                                 " +
                    "                                            X  \n" +
                    " 27                               " +
                    "                                              |  \n" +
                    " 28                              " +
                    "                                               X  \n" +
                    " 29                               " +
                    "                                              |  \n" +
                    " 30                              " +
                    "                                               |  \n" +
                    " 31                              " +
                    "                                               |  \n" +
                    " 32                 X            " +
                    "                                X                 \n" +
                    " 33                 |        " +
                    "                                    |                 \n" +
                    " 34                 |            " +
                    "                      X                           \n" +
                    " 35                 |               " +
                    "                   |                           \n" +
                    " 36                 |              " +
                    "          X                                     \n" +
                    " 37                 |               " +
                    "         |                                     \n" +
                    " 38                 |               " +
                    "                   X                           \n" +
                    " 39                 |               " +
                    "                   |                           \n" +
                    " 40                 X              " +
                    "                              X                 \n" +
                    " 41                 |               " +
                    "                             |                 \n" +
                    " 42                 |             " +
                    "                               X                 \n" +
                    " 43                 |              " +
                    "                              |                 \n" +
                    " 44                 |            " +
                    "                                X                 \n" +
                    " 45                 |            " +
                    "                                |                 \n" +
                    " 46                 |           " +
                    "                                 X                 \n" +
                    " 47                 |            " +
                    "                                |                 \n" +
                    " 48                 X            " +
                    "                      X                           \n" +
                    " 49                 |             " +
                    "                     |                           \n" +
                    " 50                 |             " +
                    "                     X                           \n" +
                    " 51                 |             " +
                    "                     |                           \n" +
                    " 52                 |             " +
                    "                               X                 \n" +
                    " 53                 |             " +
                    "                               |                 \n" +
                    " 54                 |             " +
                    "                     X                           \n" +
                    " 55                 |            " +
                    "                      |                           \n" +
                    " 56  X                            " +
                    "           X                                     \n" +
                    " 57  |                           " +
                    "            |                                     \n" +
                    " 58  |                           " +
                    "            |                                     \n" +
                    " 59  |                           " +
                    "            |                                     \n" +
                    " 60  |                             " +
                    "          |                                     \n" +
                    " 61  |                             " +
                    "          |                                     \n" +
                    " 62  |                            " +
                    "           |                                     \n" +
                    " 63  |                             " +
                    "          |                                     \n" +
                    " 64                 X              " +
                    "                                                \n" +
                    " 65                 |              " +
                    "                                                \n" +
                    " 66                 |              " +
                    "                                                \n" +
                    " 67                 |              " +
                    "                                                \n" +
                    " 68                 |                 " +
                    "                                             \n" +
                    " 69                 |               " +
                    "                                               \n" +
                    " 70                 |                " +
                    "                                              \n" +
                    " 71                                   " +
                    "                                             \n" +
                    " 72                 X                 " +
                    "                                             \n" +
                    " 73                 |                  " +
                    "                                            \n" +
                    " 74                 |                   " +
                    "                                           \n" +
                    " 75                 |                   " +
                    "                                           \n" +
                    " 76                 |                    " +
                    "                                          \n" +
                    " 77                 |                     " +
                    "                                         \n" +
                    " 78                 |                     " +
                    "                                         \n" +
                    " 79                                       " +
                    "                                         \n" +
                    " 80                 X                     " +
                    "                                         \n" +
                    " 81                 |                     " +
                    "                                         \n" +
                    " 82                 |                     " +
                    "                                         \n" +
                    " 83                 |                     " +
                    "                                         \n" +
                    " 84                 |                      " +
                    "                                        \n" +
                    " 85                 |                      " +
                    "                                        \n" +
                    " 86                 |                     " +
                    "                                         \n" +
                    " 87                 |                     " +
                    "                                         \n" +
                    " 88                 X                   " +
                    "                                           \n" +
                    " 89                 |                    " +
                    "                                          \n" +
                    " 90                                      " +
                    "                                          \n" +
                    " 91                                      " +
                    "                                          \n" +
                    " 92                                      " +
                    "                                          \n" +
                    " 93                                       " +
                    "                                         \n" +
                    " 94                                       " +
                    "                                         \n" +
                    " 95                                       " +
                    "                                         \n" +
                    " 96                 X                    " +
                    "                                          \n" +
                    " 97                 |                    " +
                    "                                          \n" +
                    " 98                 |                    " +
                    "                                          \n" +
                    " 99                 |                     " +
                    "                                         \n" +
                    "100                 |                     " +
                    "                                         \n" +
                    "101                 |                   " +
                    "                                           \n" +
                    "102                 |                     " +
                    "                                         \n" +
                    "103                 |                     " +
                    "                                         \n" +
                    "104                 X                     " +
                    "                                         \n" +
                    "105                 |                     " +
                    "                                         \n" +
                    "106                 |                     " +
                    "                                         \n" +
                    "107                 |                     " +
                    "                                         \n" +
                    "108                 |                     " +
                    "                                         \n" +
                    "109                 |                      " +
                    "                                        \n" +
                    "110                 |                      " +
                    "                                        \n" +
                    "111                 |                      " +
                    "                                        \n" +
                    "112                 X                      " +
                    "                                        \n" +
                    "113                 |                      " +
                    "                                        \n" +
                    "114                 |                      " +
                    "                                        \n" +
                    "115                 |                      " +
                    "                                        \n" +
                    "116                 |                      " +
                    "                                        \n" +
                    "117                 |                      " +
                    "                                        \n" +
                    "118                 |                      " +
                    "                                        \n" +
                    "119                 |                       " +
                    "                                       \n" +
                    "120  X                                      " +
                    "                                       \n" +
                    "121  |                                      " +
                    "                                       \n" +
                    "122  |                                      " +
                    "                                       \n" +
                    "123  |                                      " +
                    "                                       \n" +
                    "124  |                                      " +
                    "                                       \n" +
                    "125  |                                     " +
                    "                                        \n" +
                    "126  |                                      " +
                    "                                       \n" +
                    "127  |                                      " +
                    "                                       \n");
    model.addPieceConsecutive(piece1);
    //Tests that adding another consecutive piece is still added after the first one added
    //and the original piece
    //Lines split to meet 100 character limit
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());
    assertEquals(b.toString(),
            "     E3   F3  F#3   G3  G#3   A3  A#3   B3 " +
                    "  C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    "  0                 X                     " +
                    "                       X                 \n" +
                    "  1                 |                     " +
                    "                       |                 \n" +
                    "  2                 |                    " +
                    "              X                           \n" +
                    "  3                 |                   " +
                    "               |                           \n" +
                    "  4                 |               " +
                    "         X                                     \n" +
                    "  5                 |               " +
                    "         |                                     \n" +
                    "  6                 |               " +
                    "                   X                           \n" +
                    "  7                                " +
                    "                    |                           \n" +
                    "  8                 X              " +
                    "                              X                 \n" +
                    "  9                 |             " +
                    "                               |                 \n" +
                    " 10                 |             " +
                    "                               X                 \n" +
                    " 11                 |            " +
                    "                                |                 \n" +
                    " 12                 |            " +
                    "                                X                 \n" +
                    " 13                 |            " +
                    "                                |                 \n" +
                    " 14                 |           " +
                    "                                 |                 \n" +
                    " 15                                " +
                    "                                                \n" +
                    " 16                 X              " +
                    "                    X                           \n" +
                    " 17                 |               " +
                    "                   |                           \n" +
                    " 18                 |               " +
                    "                   X                           \n" +
                    " 19                 |                    " +
                    "              |                           \n" +
                    " 20                 |                  " +
                    "                X                           \n" +
                    " 21                 |                   " +
                    "               |                           \n" +
                    " 22                 |                  " +
                    "                |                           \n" +
                    " 23                 |                 " +
                    "                 |                           \n" +
                    " 24                 X                 " +
                    "                           X                 \n" +
                    " 25                 |                 " +
                    "                           |                 \n" +
                    " 26                                  " +
                    "                                           X  \n" +
                    " 27                                   " +
                    "                                          |  \n" +
                    " 28                                    " +
                    "                                         X  \n" +
                    " 29                                    " +
                    "                                         |  \n" +
                    " 30                                    " +
                    "                                         |  \n" +
                    " 31                                    " +
                    "                                         |  \n" +
                    " 32                 X                   " +
                    "                         X                 \n" +
                    " 33                 |                   " +
                    "                         |                 \n" +
                    " 34                 |                   " +
                    "               X                           \n" +
                    " 35                 |                   " +
                    "               |                           \n" +
                    " 36                 |                   " +
                    "     X                                     \n" +
                    " 37                 |                   " +
                    "     |                                     \n" +
                    " 38                 |                    " +
                    "              X                           \n" +
                    " 39                 |                    " +
                    "              |                           \n" +
                    " 40                 X                    " +
                    "                        X                 \n" +
                    " 41                 |                    " +
                    "                        |                 \n" +
                    " 42                 |                    " +
                    "                        X                 \n" +
                    " 43                 |                    " +
                    "                        |                 \n" +
                    " 44                 |                    " +
                    "                        X                 \n" +
                    " 45                 |                    " +
                    "                        |                 \n" +
                    " 46                 |                    " +
                    "                        X                 \n" +
                    " 47                 |                    " +
                    "                        |                 \n" +
                    " 48                 X                    " +
                    "              X                           \n" +
                    " 49                 |                    " +
                    "              |                           \n" +
                    " 50                 |                    " +
                    "              X                           \n" +
                    " 51                 |                    " +
                    "              |                           \n" +
                    " 52                 |                    " +
                    "                        X                 \n" +
                    " 53                 |                    " +
                    "                        |                 \n" +
                    " 54                 |                    " +
                    "              X                           \n" +
                    " 55                 |                    " +
                    "              |                           \n" +
                    " 56  X                                   " +
                    "    X                                     \n" +
                    " 57  |                                   " +
                    "    |                                     \n" +
                    " 58  |                                   " +
                    "    |                                     \n" +
                    " 59  |                                   " +
                    "    |                                     \n" +
                    " 60  |                                   " +
                    "    |                                     \n" +
                    " 61  |                                   " +
                    "    |                                     \n" +
                    " 62  |                                   " +
                    "    |                                     \n" +
                    " 63  |                                   " +
                    "    |                                     \n" +
                    " 64                 X                    " +
                    "                                          \n" +
                    " 65                 |                    " +
                    "                                          \n" +
                    " 66                 |                    " +
                    "                                          \n" +
                    " 67                 |                    " +
                    "                                          \n" +
                    " 68                 |                    " +
                    "                                          \n" +
                    " 69                 |                    " +
                    "                                          \n" +
                    " 70                 |                    " +
                    "                                          \n" +
                    " 71                                      " +
                    "                                          \n" +
                    " 72                 X                    " +
                    "                                          \n" +
                    " 73                 |                    " +
                    "                                          \n" +
                    " 74                 |                    " +
                    "                                          \n" +
                    " 75                 |                    " +
                    "                                          \n" +
                    " 76                 |                    " +
                    "                                          \n" +
                    " 77                 |                    " +
                    "                                          \n" +
                    " 78                 |                    " +
                    "                                          \n" +
                    " 79                                      " +
                    "                                          \n" +
                    " 80                 X                    " +
                    "                                          \n" +
                    " 81                 |                    " +
                    "                                          \n" +
                    " 82                 |                    " +
                    "                                          \n" +
                    " 83                 |                    " +
                    "                                          \n" +
                    " 84                 |                    " +
                    "                                          \n" +
                    " 85                 |                    " +
                    "                                          \n" +
                    " 86                 |                    " +
                    "                                          \n" +
                    " 87                 |                    " +
                    "                                          \n" +
                    " 88                 X                    " +
                    "                                          \n" +
                    " 89                 |                    " +
                    "                                          \n" +
                    " 90                                      " +
                    "                                          \n" +
                    " 91                                      " +
                    "                                          \n" +
                    " 92                                      " +
                    "                                          \n" +
                    " 93                                      " +
                    "                                          \n" +
                    " 94                                      " +
                    "                                          \n" +
                    " 95                                      " +
                    "                                          \n" +
                    " 96                 X                    " +
                    "                                          \n" +
                    " 97                 |                    " +
                    "                                          \n" +
                    " 98                 |                    " +
                    "                                          \n" +
                    " 99                 |                    " +
                    "                                          \n" +
                    "100                 |                    " +
                    "                                          \n" +
                    "101                 |                    " +
                    "                                          \n" +
                    "102                 |                    " +
                    "                                          \n" +
                    "103                 |                    " +
                    "                                          \n" +
                    "104                 X                    " +
                    "                                          \n" +
                    "105                 |                    " +
                    "                                          \n" +
                    "106                 |                    " +
                    "                                          \n" +
                    "107                 |                    " +
                    "                                          \n" +
                    "108                 |                    " +
                    "                                          \n" +
                    "109                 |                    " +
                    "                                          \n" +
                    "110                 |                    " +
                    "                                          \n" +
                    "111                 |                    " +
                    "                                          \n" +
                    "112                 X                    " +
                    "                                          \n" +
                    "113                 |                    " +
                    "                                          \n" +
                    "114                 |                    " +
                    "                                          \n" +
                    "115                 |                    " +
                    "                                          \n" +
                    "116                 |                    " +
                    "                                          \n" +
                    "117                 |                    " +
                    "                                          \n" +
                    "118                 |                    " +
                    "                                          \n" +
                    "119                 |                    " +
                    "                                          \n" +
                    "120  X                                   " +
                    "                                          \n" +
                    "121  |                                   " +
                    "                                          \n" +
                    "122  |                                   " +
                    "                                          \n" +
                    "123  |                                   " +
                    "                                          \n" +
                    "124  |                                   " +
                    "                                          \n" +
                    "125  |                                   " +
                    "                                          \n" +
                    "126  |                                   " +
                    "                                          \n" +
                    "127  |                                   " +
                    "                                          \n" +
                    "128                 X                    " +
                    "                                          \n" +
                    "129                 |                    " +
                    "                                          \n" +
                    "130                 |                    " +
                    "                                          \n" +
                    "131                 |                    " +
                    "                                          \n" +
                    "132                 |                    " +
                    "                                          \n" +
                    "133                 |                    " +
                    "                                          \n" +
                    "134                 |                    " +
                    "                                          \n" +
                    "135                                      " +
                    "                                          \n" +
                    "136                 X                    " +
                    "                                          \n" +
                    "137                 |                    " +
                    "                                          \n" +
                    "138                 |                    " +
                    "                                          \n" +
                    "139                 |                    " +
                    "                                          \n" +
                    "140                 |                    " +
                    "                                          \n" +
                    "141                 |                    " +
                    "                                          \n" +
                    "142                 |                    " +
                    "                                          \n" +
                    "143                                      " +
                    "                                          \n" +
                    "144                 X                    " +
                    "                                          \n" +
                    "145                 |                    " +
                    "                                          \n" +
                    "146                 |                    " +
                    "                                          \n" +
                    "147                 |                    " +
                    "                                          \n" +
                    "148                 |                    " +
                    "                                          \n" +
                    "149                 |                    " +
                    "                                          \n" +
                    "150                 |                    " +
                    "                                          \n" +
                    "151                 |                    " +
                    "                                          \n" +
                    "152                 X                    " +
                    "                                          \n" +
                    "153                 |                    " +
                    "                                          \n" +
                    "154                                      " +
                    "                                          \n" +
                    "155                                      " +
                    "                                          \n" +
                    "156                                      " +
                    "                                          \n" +
                    "157                                      " +
                    "                                          \n" +
                    "158                                      " +
                    "                                          \n" +
                    "159                                      " +
                    "                                          \n" +
                    "160                 X                    " +
                    "                                          \n" +
                    "161                 |                    " +
                    "                                          \n" +
                    "162                 |                    " +
                    "                                          \n" +
                    "163                 |                    " +
                    "                                          \n" +
                    "164                 |                    " +
                    "                                          \n" +
                    "165                 |                    " +
                    "                                          \n" +
                    "166                 |                    " +
                    "                                          \n" +
                    "167                 |                    " +
                    "                                          \n" +
                    "168                 X                    " +
                    "                                          \n" +
                    "169                 |                    " +
                    "                                          \n" +
                    "170                 |                    " +
                    "                                          \n" +
                    "171                 |                    " +
                    "                                          \n" +
                    "172                 |                    " +
                    "                                          \n" +
                    "173                 |                    " +
                    "                                          \n" +
                    "174                 |                    " +
                    "                                          \n" +
                    "175                 |                    " +
                    "                                          \n" +
                    "176                 X                    " +
                    "                                          \n" +
                    "177                 |                    " +
                    "                                          \n" +
                    "178                 |                    " +
                    "                                          \n" +
                    "179                 |                    " +
                    "                                          \n" +
                    "180                 |                    " +
                    "                                          \n" +
                    "181                 |                    " +
                    "                                          \n" +
                    "182                 |                    " +
                    "                                          \n" +
                    "183                 |                    " +
                    "                                          \n" +
                    "184  X                                   " +
                    "                                          \n" +
                    "185  |                                   " +
                    "                                          \n" +
                    "186  |                                   " +
                    "                                          \n" +
                    "187  |                                   " +
                    "                                          \n" +
                    "188  |                                   " +
                    "                                          \n" +
                    "189  |                                   " +
                    "                                          \n" +
                    "190  |                                   " +
                    "                                          \n" +
                    "191  |                                   " +
                    "                                          \n");
    model.removeAllNotes();
    //Tests that removing all notes works
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(), "No music");
    model.addNote(new Note(Pitch.C, Octave.NegOne, 2, 0),
            new Note(Pitch.B, Octave.Nine, 2, 0));
    //Tests that all pitches and Octaves can be displayed properly
    //Lines split to meet 100 character limit

    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());

    assertEquals(b.toString(),
            "  C-1  C#-1 D-1  D#-1 E-1  F-1  F#-1 G-1  G#-1 A-1  A#-1 B-1 " +
                    "  C0  C#0   D0  D#0   E0   F0  F#0   G0  G#0   A0  A#0   B0 " +
                    "  C1  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1 " +
                    "  C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2  A#2   B2 " +
                    "  C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3 " +
                    "  C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4 " +
                    "  C5  C#5   D5  D#5   E5   F5  F#5   G5  G#5   A5  A#5   B5 " +
                    "  C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   A6  A#6   B6 " +
                    "  C7  C#7   D7  D#7   E7   F7  F#7   G7  G#7   A7  A#7   B7 " +
                    "  C8  C#8   D8  D#8   E8   F8  F#8   G8  G#8   A8  A#8   B8 " +
                    "  C9  C#9   D9  D#9   E9   F9  F#9   G9  G#9   A9  A#9   B9 \n" +
                    "0  X                                                        " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                          X  \n" +
                    "1  |                                                        " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                            " +
                    "                                                          |  \n");
    model.removeAllNotes();
    model.addNote(new Note(Pitch.ASharp, Octave.Five, 8, 0),
            new Note(Pitch.ASharp, Octave.Five, 8, 2));
    //Tests that overlapping Notes format correctly, and that only the parts that don't
    //overlap are shown
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());
    assertEquals(b.toString(),
            "  A#5 \n" +
                    "0  X  \n" +
                    "1  |  \n" +
                    "2  X  \n" +
                    "3  |  \n" +
                    "4  |  \n" +
                    "5  |  \n" +
                    "6  |  \n" +
                    "7  |  \n" +
                    "8  |  \n" +
                    "9  |  \n");
    model.editNote(new Note(Pitch.ASharp, Octave.Five, 8, 2),
            new Note(Pitch.A, Octave.Five, 8, 3));
    //Tests both Notes are fully present, even when they overlap.
    //Also tests that the spacing on the line numbers is formatted upon a new digit being added
    b = new StringBuffer();
    ii = new TextViewImpl(b);
    ii.execute(model.getNotes());
    assertEquals(b.toString(),
            "    A5  A#5 \n" +
                    " 0       X  \n" +
                    " 1       |  \n" +
                    " 2       |  \n" +
                    " 3  X    |  \n" +
                    " 4  |    |  \n" +
                    " 5  |    |  \n" +
                    " 6  |    |  \n" +
                    " 7  |    |  \n" +
                    " 8  |       \n" +
                    " 9  |       \n" +
                    "10  |       \n");
  }

  @Test
  public void testNote() {
    try {
      Note n = new Note(null, Octave.Five, 1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid input: null pitch");
    }
    try {
      Note n = new Note(Pitch.B, null, 1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid input: null octave");
    }
    try {
      Note n = new Note(Pitch.B, Octave.Five, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid input: too few beats");
    }
    try {
      Note n = new Note(Pitch.B, Octave.Five, 1, -1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid input: negative start time");
    }
    Note n1 = new Note(Pitch.B, Octave.Five, 4, 0);
    Note n2 = new Note(Pitch.B, Octave.Five, 4, 0);
    Note n3 = new Note(Pitch.C, Octave.Five, 4, 0);
    Note n4 = new Note(Pitch.B, Octave.Four, 4, 0);
    Note n5 = new Note(Pitch.B, Octave.Five, 5, 0);
    Note n6 = new Note(Pitch.B, Octave.Five, 4, 1);
    assertTrue(n1.equals(n2));
    assertFalse(n1.equals(n3));
    assertFalse(n1.equals(n4));
    assertFalse(n1.equals(n5));
    assertFalse(n1.equals(n6));
    assertFalse(n1.equals("Not a note"));
    assertTrue(n1.hashCode() == n2.hashCode());
    assertTrue(n1.hashCode() != n3.hashCode());
    assertTrue(n1.hashCode() != n4.hashCode());
    assertTrue(n1.hashCode() != n5.hashCode());
    assertTrue(n1.hashCode() != n6.hashCode());
  }

  @Test
  public void testComparators() {
    NoteToneComparator comp1 = new NoteToneComparator();
    Note n1 = new Note(Pitch.FSharp, Octave.Five, 1, 0);
    Note n2 = new Note(Pitch.FSharp, Octave.Four, 1, 0);
    Note n3 = new Note(Pitch.FSharp, Octave.Six, 1, 0);
    Note n4 = new Note(Pitch.F, Octave.Five, 1, 0);
    Note n5 = new Note(Pitch.G, Octave.Five, 1, 100);
    Note n6 = new Note(Pitch.FSharp, Octave.Five, 1, 0);
    assertTrue(comp1.compare(n1, n2) > 0);
    assertTrue(comp1.compare(n1, n3) < 0);
    assertTrue(comp1.compare(n1, n4) > 0);
    assertTrue(comp1.compare(n1, n5) < 0);
    assertTrue(comp1.compare(n1, n6) == 0);
    NotePlaceComparator comp2 = new NotePlaceComparator();
    Note n7 = new Note(Pitch.FSharp, Octave.Five, 4, 0);
    Note n8 = new Note(Pitch.FSharp, Octave.Five, 5, 0);
    Note n9 = new Note(Pitch.FSharp, Octave.Five, 3, 0);
    Note n10 = new Note(Pitch.A, Octave.Seven, 4, 0);
    assertTrue(comp2.compare(n7, n8) < 0);
    assertTrue(comp2.compare(n7, n9) > 0);
    assertTrue(comp2.compare(n7, n10) == 0);
  }
}