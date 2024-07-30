package org.tonies.model;

import java.io.Serializable;

public record Song(String url, long songLength) implements Serializable {}
