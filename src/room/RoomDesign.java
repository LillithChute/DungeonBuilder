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
   * Set's the room above the current room.
   *
   * @param roomAbove The room being assigned
   */
  void tagRoomAbove(RoomDesign roomAbove);


  /**
   * Get's the room above the current room.
   *
   * @return The room above this one.
   */
  RoomDesign findRoomAbove();

  /**
   * Set's the room below the current room.
   *
   * @param roomBelow The room being assigned.
   */
  void tagRoomBelow(RoomDesign roomBelow);

  /**
   * Get's the room below the current room.
   *
   * @return The room below this one.
   */
  RoomDesign findRoomBelow();

  /**
   * Set's the room to the left of the current room.
   *
   * @param roomLeft The room being assigned.
   */
  void tagRoomToTheLeft(RoomDesign roomLeft);

  /**
   * Get's the room to the left of the current room.
   *
   * @return The room to the left of this one.
   */
  RoomDesign findRoomToTheLeft();

  /**
   * Set's the room to the left of the current room.
   *
   * @param roomRight The room being assigned.
   */
  void tagRoomToTheRight(RoomDesign roomRight);

  /**
   * Get's the room to the right of the current room.
   *
   * @return The room to the right of this one.
   */
  RoomDesign findRoomToTheRight();

  /**
   * Gets the room ID. This is used to track the individual rooms in the maze.  This required for
   * generation of the dungeon.
   *
   * @return the identifier of this maze {@link Room}
   */
  int getRoomId();

  /**
   * Sets the room ID.
   *
   * @param roomId The room ID.
   */
  void setRoomId(int roomId);

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
  String toggleRoomVisibility();

  /**
   * Prints this {@link Room} in a "Hard Mode": visible only after the adventurer travels this
   * {@link Room}.
   *
   * @return the print of this {@link Room}
   */
  String visitedRoomDisplay();
}
