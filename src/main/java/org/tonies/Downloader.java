package org.tonies;

import java.util.Random;
import org.tonies.model.Song;

// This class is a mock since I don't have much time left to implement it

public class Downloader {

  private static final Random random = new Random();

  public static Song downloadSong(String url) {
    long randomLength = random.nextLong(50);
    return new Song(url, randomLength);
  }
}
