package org.tonies;

import java.util.List;
import org.tonies.model.Tonie;

public class Manager {

  private User user;
  private Toniebox toniebox;

  public Manager() {
    loadState();
  }

  public void printToniesList() {
    List<Tonie> tonies = user.getTonies();
    if (tonies.isEmpty()) {
      System.out.println("You don't have any Tonies");
    } else {
      tonies.forEach(tonie -> System.out.println(tonie.name()));
    }
  }

  public void buyTonie(String name, String url) {
    user.addTonie(new Tonie(name, url));
    System.out.println("Tonie added");
    FileUtils.saveUserData(user);
  }

  public void factoryReset() {
    FileUtils.removeStorage();
    loadState();
  }

  public void increaseVolume() {
    System.out.println(toniebox.increaseVolume());
  }

  public void decreaseVolume() {
    System.out.println(toniebox.decreaseVolume());
  }

  public void printVolume() {
    System.out.println(toniebox.getVolume());
  }

  public void placeTonie(int index) {
    Tonie tonieToPlace = user.getTonies().get(index);
    if (toniebox.place(tonieToPlace)) {
      System.out.println("The tonie " + tonieToPlace.name() + " has been placed at the Toniebox");
    } else {
      System.out.println("It was not possible to place the Tonie at the Toniebox");
    }
  }

  public void removeTonieFromUser(int index) {
    if (index < user.getTonies().size()) {
      user.removeTonie(index);
      FileUtils.saveUserData(user);
    }
  }

  public void removeTonieFromToniebox() {
    long lastSecondPlayed = toniebox.removeTonie();
    System.out.println(
        "Tonie has been removed from the Toniebox. The last second played is " + lastSecondPlayed);
  }

  public void getTonieboxState() {
    System.out.println(toniebox.getState());
  }

  private void loadState() {
    user = FileUtils.loadUserData();
    toniebox = new Toniebox();
  }
}
