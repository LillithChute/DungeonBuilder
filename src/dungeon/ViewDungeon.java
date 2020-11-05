package dungeon;

import room.Room;
import room.RoomDesign;

public class ViewDungeon {
  /**
   * Visualizes a maze map.
   *
   * @param n   the number of rows
   * @param x   the number of columns
   * @param map the maze map
   * @return the maze visualization as a string
   */
  public static String visualizer(int n, int x, RoomDesign[][] map) {
    String top = "  ";
    for (int i = 0; i < x; i++) {
      top += String.format("  %d  ", i);
    }
    top += "  \n";
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    result.append(top);
    for (int i = 0; i < n; i++) {
      result1.append("  ");
      result2.append(String.format("%d ", i));
      result3.append("  ");
      for (int j = 0; j < x; j++) {
        Room curr = (Room) map[i][j];
        String[] temp = curr.toString().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }
      result1.append("  ");
      result2.append(String.format(" %d", i));
      result3.append("  ");
      result.append(result1);
      result.append("\n");
      result.append(result2);
      result.append("\n");
      result.append(result3);
      result.append("\n");
      result1 = new StringBuilder();
      result2 = new StringBuilder();
      result3 = new StringBuilder();
    }
    result.append(top);
    return result.toString();
  }

  /**
   * Visualizes the "Hard Mode" maze. Only shows the rooms an adventurer has traveled.
   *
   * @param n   the number of rows
   * @param x   the number of columns
   * @param map the maze map
   * @return the maze visualization as a string
   */
  public static String visualizerTouch(int n, int x, Room[][] map) {
    String top = "  ";
    for (int i = 0; i < x; i++) {
      top += String.format("  %d  ", i);
    }
    top += "  \n";
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    result.append(top);
    for (int i = 0; i < n; i++) {
      result1.append("  ");
      result2.append(String.format("%d ", i));
      result3.append("  ");
      for (int j = 0; j < x; j++) {
        Room curr = map[i][j];
        String[] temp = curr.visitedRoomDisplay().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }
      result1.append("  ");
      result2.append(String.format(" %d", i));
      result3.append("  ");
      result.append(result1);
      result.append("\n");
      result.append(result2);
      result.append("\n");
      result.append(result3);
      result.append("\n");
      result1 = new StringBuilder();
      result2 = new StringBuilder();
      result3 = new StringBuilder();
    }
    result.append(top);
    return result.toString();
  }

  /**
   * Visualizes the maze with only gold amount.
   *
   * @param n   the number of rows
   * @param x   the number of columns
   * @param map the maze map
   * @return the maze visualization as a string
   */
  public static String showGold(int n, int x, Room[][] map) {
    String top = "  ";
    for (int i = 0; i < x; i++) {
      top += String.format("  %d  ", i);
    }
    top += "  \n";
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    result.append(top);
    for (int i = 0; i < n; i++) {
      result1.append("  ");
      result2.append(String.format("%d ", i));
      result3.append("  ");
      for (int j = 0; j < x; j++) {
        Room curr = map[i][j];
        String[] temp = curr.displayGold().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }
      result1.append("  ");
      result2.append(String.format(" %d", i));
      result3.append("  ");
      result.append(result1);
      result.append("\n");
      result.append(result2);
      result.append("\n");
      result.append(result3);
      result.append("\n");
      result1 = new StringBuilder();
      result2 = new StringBuilder();
      result3 = new StringBuilder();
    }
    result.append(top);
    return result.toString();
  }

  /**
   * Visualizes the maze with only thief positions.
   *
   * @param n   the number of rows
   * @param x   the number of columns
   * @param map the maze map
   * @return the maze visualization as a string
   */
  public static String showThief(int n, int x, Room[][] map) {
    String top = "  ";
    for (int i = 0; i < x; i++) {
      top += String.format("  %d  ", i);
    }
    top += "  \n";
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    result.append(top);
    for (int i = 0; i < n; i++) {
      result1.append("  ");
      result2.append(String.format("%d ", i));
      result3.append("  ");
      for (int j = 0; j < x; j++) {
        Room curr = map[i][j];
        String[] temp = curr.displayThief().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }
      result1.append("  ");
      result2.append(String.format(" %d", i));
      result3.append("  ");
      result.append(result1);
      result.append("\n");
      result.append(result2);
      result.append("\n");
      result.append(result3);
      result.append("\n");
      result1 = new StringBuilder();
      result2 = new StringBuilder();
      result3 = new StringBuilder();
    }
    result.append(top);
    return result.toString();
  }
}
