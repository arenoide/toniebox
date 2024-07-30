package org.tonies;

import java.time.Duration;
import java.time.LocalDateTime;
import org.tonies.model.Song;

public class MediaPlayer {
  private final int MAX_VOLUME = 10;
  private long startingSecond;
  private Song songPlayed;
  private LocalDateTime startDateTime;
  private boolean isPlaying;
  private int volume = 5;

  public void play(Song song) {
    play(song, 0);
  }

  public void play(Song song, long startingSecond) {
    this.startingSecond = startingSecond;
    startDateTime = LocalDateTime.now();
    songPlayed = song;
    isPlaying = true;
  }

  public boolean songIsBeingPlayed() {
    if (songPlayed == null) {
      return false;
    }
    updateIsPlaying();
    return isPlaying;
  }

  public long getPlaySecond() {
    if (isPlaying) {
      LocalDateTime time = LocalDateTime.now();
      long reproducedSeconds = Duration.between(startDateTime, time).getSeconds();
      return reproducedSeconds + startingSecond;
    } else {
      return startingSecond;
    }
  }

  public boolean songIsCompletelyReproduced() {
    return startingSecond >= songPlayed.songLength();
  }

  public String getSongName() {
    if (songIsBeingPlayed()) {
      return songPlayed.url();
    }
    return "No song is being played";
  }

  public int increaseVolume() {
    if (volume <= MAX_VOLUME) {
      volume++;
    }
    return volume;
  }

  public int decreaseVolume() {
    if (volume >= 0) {
      volume--;
    }
    return volume;
  }

  public int getVolume() {
    return volume;
  }

  public long stop() {
    startingSecond = getPlaySecond();
    isPlaying = false;
    if (songIsCompletelyReproduced()) {
      return 0;
    }
    return startingSecond;
  }

  private void updateIsPlaying() {
    if (isPlaying) {
      long timeReproduced = getPlaySecond();
      isPlaying = songPlayed.songLength() >= timeReproduced;
    }
  }
}
