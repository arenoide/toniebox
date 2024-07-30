package org.tonies;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Manager manager = new Manager();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    printHelp();
    System.out.println("Write 'help' to get the available commands.");
    while (!exit) {
      System.out.print("> ");
      String input = scanner.nextLine();
      String[] commandParts = input.split(" ");
      String command = commandParts[0];

      switch (command) {
        case "help":
          printHelp();
          break;
        case "listTonies", "1", "ls":
          manager.printToniesList();
          break;
        case "buy", "2", "add":
          manager.buyTonie(commandParts[1], commandParts[2]);
          break;
        case "removeTonieForUser", "3", "rm":
          manager.removeTonieFromUser(Integer.parseInt(commandParts[1]));
          break;
        case "placeTonie", "4", "place":
          manager.placeTonie(Integer.parseInt(commandParts[1]));
          break;
        case "removeTonie", "5":
          manager.removeTonieFromToniebox();
          break;
        case "state", "6":
          manager.getTonieboxState();
          break;
        case "volumeUp", "7":
          manager.increaseVolume();
          break;
        case "volumeDown", "8":
          manager.decreaseVolume();
          break;
        case "getVolume", "9":
          manager.printVolume();
          break;
        case "factoryReset", "10":
          manager.factoryReset();
          break;
        case "exit", "11":
          exit = true;
          break;
        default:
          System.out.println("I don't get that.");
          break;
      }
    }

    scanner.close();
  }

  private static void printHelp() {
    System.out.println(
  """
  Available commands:
          help                    - It shows this help.
      1   ls                      - Gives a list of available Tonies for the user.
      2   buy <name> <url>        - Adds a Tonie to the list of Tonies available for the user.
      3   return <index>          - Remove the Tonie with index <index> from the list of Tonies available for the user.
      4   placeTonie <index>      - Places the Tonie with index <index> at the Toniebox.
      5   removeTonie             - Removes the current Tonie from the Toniebox.
      6   state                   - Gets the current status of the Toniebox.
      7   volumeUp                - Increase the volume of the player.
      8   volumeDown              - Decrease the volume of the player.
      9   getVolume               - Get the current volume of the player.
      10  factoryReset            - Remove storage (it won't remove the Tonies belonging to the user).
      11  exit                    - Exits the CLI.
      """);
  }
}
