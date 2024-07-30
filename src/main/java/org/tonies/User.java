package org.tonies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.tonies.model.Tonie;

public class User implements Serializable {
  private final List<Tonie> tonies = new ArrayList<>();

  public List<Tonie> getTonies() {
    return tonies;
  }

  public void addTonie(Tonie tonie) {
    tonies.add(tonie);
  }

  public void removeTonie(int index) {
    tonies.remove(index);
  }
}
