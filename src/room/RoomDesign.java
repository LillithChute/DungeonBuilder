package room;

import java.util.ArrayList;

/**
 * This provides the operations and constraints for a room in the dungeon.
 */
public interface RoomDesign {

  /**
   * Put adventurer in the room.
   */
  void adventurerIsInThisRoom();

  /**
   * This will let the program know if the adventurer has left the room.
   */
  void adventurerLeavesRoom();

  /**
   * This lets the program know if the adventurer has been to this room.
   */
  void roomHasBeenVisited();

  /**
   * This identifies this room as the way out of the dungeon.
   */
  void exitRoom();

  /**
   * Gets the coordinates of this room.
   *
   * @return The x,y of where this room is in the dungeon.
   */
  int[] getRoomLocation();

  /**
   * The amount of gold in the room.
   *
   * @return The amount of gold in the room.
   */
  int getGoldInTheRoom();

  /**
   * Get's the room above the current room.
   *
   * @return The room above this one.
   */
  RoomDesign findRoomAbove();

  /**
   * Get's the room below the current room.
   *
   * @return The room below this one.
   */
  RoomDesign findRoomBelow();

  /**
   * Get's the room to the left of the current room.
   *
   * @return The room to the left of this one.
   */
  RoomDesign findRoomToTheLeft();

  /**
   * Get's the room to the right of the current room.
   *
   * @return The room to the right of this one.
   */
  RoomDesign findRoomToTheRight();

  /**
   * Checks if there is a thief in the room.
   *
   * @return Checks if there is a thief in the room.
   */
  boolean isThiefInTheRoom();

  /**
   * If the adventurer picks up the gold, clear the gold in the room.
   */
  void removeGoldInRoom();

  /**
   * Sets the gold amount in the room.
   *
   * @param goldAmount the gold amount.
   */
  void setGoldAmount(int goldAmount);

  /**
   * A collection of allowable movements.
   *
   * @return Possible moves.
   */
  ArrayList<String> movementOptions();

  /**
   * Displays the thief in the room.
   *
   * @return A very rough print out of the room.
   */
  String displayThief();

  /**
   * Put a thief in the room.
   */
  void putThiefInTheRoom();

  /**
   * A rough display of the gold in the room.
   *
   * @return A print out of the gold amount.
   */
  String displayGold();

  /**
   * Displays rooms that have been visited.
   *
   * @return A display of where the adventurer has been.
   */
  String visitedRoom();

  /**
   * Prints this {@link Room} in a "Hard Mode": visible only after the adventurer travels this
   * {@link Room}.
   *
   * @return the print of this {@link Room}
   */
  String visitedRoomDisplay();
}
