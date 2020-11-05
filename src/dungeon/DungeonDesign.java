package dungeon;

import java.util.ArrayList;

/**
 * These are the operations allowed in crawling this fixed dungeon.
 */
public interface DungeonDesign {

  /**
   * This returns the coordinates of the exit room.
   *
   * @return The coordinates of the exit room.
   */
  int[] retrieveExitCoordinates();

  /**
   * Returns the coordinates of the adventurer.
   *
   * @return The coordinates of the adventurer.
   */
  int[] retrieveAdventurerCoordinates();

  /**
   * The adventurer moves.
   *
   * @param moveDirection the move direction.
   */
  void makeAdventurerMove(String moveDirection);

  /**
   * This will get the options available to the adventurer for movement.
   *
   * @return options available to the adventurer for movement.
   */
  ArrayList<String> retrieveAdventurerMovementOptions();

  /**
   * This takes in the data and updates the dungeon accordingly.
   */
  void updateDungeon();

  /**
   * This returns the amount of gold the adventurer has.
   */
  int retrieveAdventurerGold();

  /**
   * Is the dungeon completed.
   *
   * @return If the dungeon is complete..
   */
  boolean dungeonCompleted();
}
