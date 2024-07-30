package org.tonies.model;

import static java.util.UUID.randomUUID;

import java.io.Serializable;
import java.util.UUID;

public record Tonie(UUID id, String name, String url) implements Serializable {

  public Tonie(String name, String url) {
    this(randomUUID(), name, url);
  }
}
