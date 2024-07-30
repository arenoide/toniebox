package org.tonies;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import org.tonies.model.Song;
import org.tonies.model.Storage;
import org.tonies.model.Tonie;

public class Toniebox implements Serializable {

  private final MediaPlayer player;
  private Tonie tonieInPlace;
  private Song song;
  private final Storage storage;

  public Toniebox() {
    storage = FileUtils.loadStorageData();
    player = new MediaPlayer();
  }

  public Tonie getTonieInPlace() {
    return tonieInPlace;
  }

  private boolean isTonieInPlace() {
    return tonieInPlace != null;
  }

  public boolean place(Tonie tonie) {
    if (!isTonieInPlace()) {
      tonieInPlace = tonie;
      String songUrl = tonieInPlace.url();
      song = getCachedOrDownload(songUrl);
      if (thereIsASavedPlayTime()) {
        long lastSecondReproduced = getToniesPlayTime().get(tonieInPlace.id());
        player.play(song, lastSecondReproduced);
      } else {
        player.play(song);
      }
      return true;
    }
    saveState();
    return false;
  }

  private Song getCachedOrDownload(String songUrl) {
    if (songIsCached()) {
      return storage.cachedSongs().get(songUrl);
    } else {
      Song downloadedSong = Downloader.downloadSong(songUrl);
      storage.cachedSongs().put(songUrl, downloadedSong);
      return downloadedSong;
    }
  }

  private boolean songIsCached() {
    return storage.cachedSongs().containsKey(tonieInPlace.url());
  }

  private boolean thereIsASavedPlayTime() {
    return getToniesPlayTime().containsKey(tonieInPlace.id());
  }

  private Map<UUID, Long> getToniesPlayTime() {
    return storage.toniesPlayTime();
  }

  public long removeTonie() {
    long lastSecondReproduced = player.stop();
    if (!player.songIsCompletelyReproduced()) {
      getToniesPlayTime().put(tonieInPlace.id(), lastSecondReproduced);
    } else {
      getToniesPlayTime().remove(tonieInPlace.id());
    }
    tonieInPlace = null;
    saveState();
    return lastSecondReproduced;
  }

  public boolean isPlaying() {
    return player.songIsBeingPlayed();
  }

  public int increaseVolume() {
    return player.increaseVolume();
  }

  public int decreaseVolume() {
    return player.decreaseVolume();
  }

  public int getVolume() {
    return player.getVolume();
  }

  private void saveState() {
    FileUtils.saveTonieboxData(storage);
  }

  public String getState() {
    StringBuilder response = new StringBuilder();
    if (isTonieInPlace()) {
      response.append("The Tonie '").append(tonieInPlace.name()).append("' is in place\n");

      if (player.songIsBeingPlayed()) {
        response
            .append("There is a song being played: '")
            .append(player.getSongName())
            .append("' and it is on second ")
            .append(player.getPlaySecond())
            .append(" of ")
            .append(song.songLength())
            .append(" and volume ")
            .append(player.getVolume());
      } else {
        response.append("There is no song being played");
      }

      return response.toString();
    } else {
      return "There is no Tonie at the Toniebox";
    }
  }
}
