import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tonies.FileUtils;
import org.tonies.Toniebox;
import org.tonies.model.Tonie;

public class TonieboxTests {

  Tonie tonie1 = new Tonie("Tonie1", "url1");
  Tonie tonie2 = new Tonie("Tonie2", "url2");

  @Test
  void canPlaceATonieTest() {
    Toniebox toniebox = new Toniebox();
    toniebox.place(tonie1);
    Assertions.assertEquals(tonie1, toniebox.getTonieInPlace());
  }

  @Test
  void playbackStartsWhenTonieIsPlacedTest() {
    Toniebox toniebox = new Toniebox();
    toniebox.place(tonie1);
    assertTrue(toniebox.isPlaying());
  }

  @Test
  void playbackIsStoppedWhenTonieIsRemovedTest() {
    Toniebox toniebox = new Toniebox();
    toniebox.place(tonie1);
    assertTrue(toniebox.isPlaying());
    toniebox.removeTonie();
    assertFalse(toniebox.isPlaying());

  }

  @Test
  void playbackResumesWhenItWasBeforeRemovingTheTonieTest() {
    Toniebox toniebox = new Toniebox();
    toniebox.place(tonie1);
    assertTrue(toniebox.isPlaying());
    toniebox.removeTonie();
    assertFalse(toniebox.isPlaying());
    toniebox.place(tonie1);

  }

  @Test
  void cantPlaceASecondTonieTest() {
    Toniebox toniebox = new Toniebox();
    toniebox.place(tonie1);
    toniebox.place(tonie2);
    Tonie tonieInPlace = toniebox.getTonieInPlace();
    assertEquals(tonie1, tonieInPlace);
  }

  @AfterAll
  static void cleanup() {
    FileUtils.removeStorage();
  }
}
