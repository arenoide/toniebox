import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.tonies.MediaPlayer;
import org.tonies.model.Song;

public class MediaPlayerTests {
  private final String SONG_NAME = "SongName";
  Song song = new Song(SONG_NAME, 120);

  @Test
  void playsASongTest() {
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.play(song);
    assertTrue(mediaPlayer.songIsBeingPlayed());
    assertEquals(0, mediaPlayer.getPlaySecond());
  }

  @Test
  void itStartsPlayingFromASecond() {
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.play(song, 2);
    assertTrue(mediaPlayer.songIsBeingPlayed());
    assertEquals(2, mediaPlayer.getPlaySecond());
  }

  @Test
  void itFinishesWhenReachingTheEndTest() throws InterruptedException {
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.play(song, 119);
    assertTrue(mediaPlayer.songIsBeingPlayed());
    Thread.sleep(2000);
    assertFalse(mediaPlayer.songIsBeingPlayed());
  }

  @Test
  void itTellsTheNameOfTheSongBeingPlayedTest() {
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.play(song);
    assertEquals(song.url(), mediaPlayer.getSongName());
  }

  @Test
  void itStopsTest() {
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.play(song);
    assertEquals(song.url(), mediaPlayer.getSongName());
    mediaPlayer.stop();
    assertFalse(mediaPlayer.songIsBeingPlayed());
  }

  @Test
  void increasesVolumeTest() {
    MediaPlayer mediaPlayer = new MediaPlayer();
    int volume = mediaPlayer.getVolume();
    mediaPlayer.increaseVolume();
    assertEquals(volume + 1, mediaPlayer.getVolume());
  }

  @Test
  void decreasesVolumeTest() {
    MediaPlayer mediaPlayer = new MediaPlayer();
    int volume = mediaPlayer.getVolume();
    mediaPlayer.decreaseVolume();
    assertEquals(volume - 1, mediaPlayer.getVolume());
  }
}
