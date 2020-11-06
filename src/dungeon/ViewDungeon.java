package dungeon;

import room.Room;
import room.RoomDesign;

public class ViewDungeon {
  /**
   * Displays the dungeon.
   *
   * @param numberOfRows The number of the rows.
   * @param numberOfColumns The number of columns
   * @param dungeon The dungeon.
   * @return Hopefully, what the dungeon looks like.
   */
  public static String buildAndDisplayDungeon(int numberOfRows, int numberOfColumns,
                                              RoomDesign[][] dungeon) {
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    String top = createMazeTop(numberOfColumns, result);

    for (int i = 0; i < numberOfRows; i++) {
      appendRowCoordinates(result1, result2, result3, i);

      for (int j = 0; j < numberOfColumns; j++) {
        Room curr = (Room) dungeon[i][j];
        String[] temp = curr.toString().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }

      appendSpacesAndNewLines(result1, result2, result3, result, i);

      result1 = new StringBuilder();
      result2 = new StringBuilder();
      result3 = new StringBuilder();
    }

    result.append(top);

    return result.toString();
  }

  /**
   * Display only the rooms an adventurer has visited.
   *
   * @param numberOfRows The number of the rows.
   * @param numberOfColumns The number of columns
   * @param dungeon The dungeon.
   * @return Hopefully, what the dungeon looks like.
   */
  public static String displayVisitedRooms(int numberOfRows, int numberOfColumns,
                                           Room[][] dungeon) {
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    String top = createMazeTop(numberOfColumns, result);

    for (int i = 0; i < numberOfRows; i++) {
      appendRowCoordinates(result1, result2, result3, i);

      for (int j = 0; j < numberOfColumns; j++) {
        Room curr = dungeon[i][j];
        String[] temp = curr.visitedRoomDisplay().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }

      appendSpacesAndNewLines(result1, result2, result3, result, i);

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
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    String top = createMazeTop(x, result);

    for (int i = 0; i < n; i++) {
      appendRowCoordinates(result1, result2, result3, i);

      for (int j = 0; j < x; j++) {
        Room curr = map[i][j];
        String[] temp = curr.displayGold().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }

      appendSpacesAndNewLines(result1, result2, result3, result, i);

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
    StringBuilder result1 = new StringBuilder();
    StringBuilder result2 = new StringBuilder();
    StringBuilder result3 = new StringBuilder();
    StringBuilder result = new StringBuilder();

    String top = createMazeTop(x, result);

    for (int i = 0; i < n; i++) {
      appendRowCoordinates(result1, result2, result3, i);

      for (int j = 0; j < x; j++) {
        Room curr = map[i][j];
        String[] temp = curr.displayThief().split("\n");
        result1.append(temp[0]);
        result2.append(temp[1]);
        result3.append(temp[2]);
      }

      appendSpacesAndNewLines(result1, result2, result3, result, i);

      result1 = new StringBuilder();
      result2 = new StringBuilder();
      result3 = new StringBuilder();
    }

    result.append(top);
    return result.toString();
  }

  private static void appendRowCoordinates(StringBuilder result1, StringBuilder result2,
                                           StringBuilder result3, int i) {
    result1.append("  ");
    result2.append(String.format("%d ", i));
    result3.append("  ");
  }

  private static String createMazeTop(int x, StringBuilder result) {
    String top = "  ";

    for (int i = 0; i < x; i++) {
      top += String.format("  %d  ", i);
    }

    top += "  \n";

    result.append(top);
    return top;
  }

  private static void appendSpacesAndNewLines(StringBuilder result1, StringBuilder result2,
                                              StringBuilder result3, StringBuilder result, int i) {
    result1.append("  ");
    result2.append(String.format(" %d", i));
    result3.append("  ");
    result.append(result1);
    result.append("\n");
    result.append(result2);
    result.append("\n");
    result.append(result3);
    result.append("\n");
  }
}
