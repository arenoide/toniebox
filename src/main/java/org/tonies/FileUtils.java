package org.tonies;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.tonies.model.Storage;

public class FileUtils {
  public static final String USER_FILENAME = "./user.data";
  public static final String STORAGE_FILENAME = "./storage.data";

  public static void saveUserData(User user) {
    saveData(user, USER_FILENAME);
  }

  public static void saveTonieboxData(Storage storage) {
    saveData(storage, STORAGE_FILENAME);
  }

  public static User loadUserData() {
    User user = loadData(User.class, USER_FILENAME);
    return (user != null) ? user : new User();
  }

  public static Storage loadStorageData() {
    Storage storage = loadData(Storage.class, STORAGE_FILENAME);
    return (storage != null) ? storage : new Storage();
  }

  public static void saveData(Serializable object, String filename) {
    try (FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(object);
    } catch (IOException exception) {
      System.out.println(exception.getMessage());
    }
  }

  public static <T> T loadData(Class<T> cls, String filename) {

    try (FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn)) {

      return cls.cast(in.readObject());

    } catch (IOException | ClassNotFoundException exception) {
      System.out.println(exception.getMessage());
    }
    return null;
  }

  public static void removeStorage() {
    Path path = Paths.get(STORAGE_FILENAME);
    try {
      Files.delete(path);
    } catch (IOException e) {
      System.out.println("There is no storage to be deleted");
    }
  }
}
