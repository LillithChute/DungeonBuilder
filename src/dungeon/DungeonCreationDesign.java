package dungeon;

import java.util.ArrayList;
import room.RoomDesign;

/**
 * These are the operations needed for creating a dungeon.
 */
public interface DungeonCreationDesign {

  /**
   * Creates the dungeon.
   */
  void createTheDungeon();

  /**
   * Returns the layout of the dungeon.
   *
   * @return The layout of the dungeon.
   */
  RoomDesign[][] getDungeonLayout();

  /**
   * Gets the list of all the walls in the dungeon.
   *
   * @return List of all the walls in the dungeon.
   */
  ArrayList<int[]> getDungeonWalls();

  /**
   * This creates a "perfect" dungeon.
   *
   * @param dungeonWalls The list of walls.
   */
  void createThePerfectDungeon(ArrayList<int[]> dungeonWalls);

  /**
   * This creates a wrapped "perfect" dungeon.
   */
  void createTheWrappedPerfectDungeon();

  /**
   * This creates a non-perfect dungeon.
   *
   * @param dungeonWallCount The list of walls in the dungeon
   */
  void createTheNonPerfectDungeon(int dungeonWallCount);
}
