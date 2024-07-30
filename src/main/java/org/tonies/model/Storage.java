package org.tonies.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record Storage(Map<String, Song> cachedSongs, Map<UUID, Long> toniesPlayTime)
    implements Serializable {
  public Storage() {
    this(new HashMap<>(), new HashMap<>());
  }
}
