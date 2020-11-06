package dungeon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import room.Room;
import room.RoomDesign;

/**
 * This creates a concrete implementation for creating a dungeon.
 */
public class CreateDungeon implements DungeonCreationDesign {

  private final int numberOfRows;
  private final int numberOfColumns;
  private final RoomDesign[][] dungeon;
  private final ArrayList<int[]> dungeonWalls = new ArrayList<>();

  // This is an amount of gold to add to the room
  private static final int GOLD_IN_ROOM = 20;

  /**
   * Instantiate a new dungeon creator.
   *
   * @param numberOfRows Number or rows in the dungeon.
   * @param numberOfColumns Number of columns in the dungeon.
   */
  public CreateDungeon(int numberOfRows, int numberOfColumns) {
    if (numberOfRows <= 0 || numberOfColumns <= 0) {
      throw new IllegalArgumentException("Can't use negative numbers to build a dungeon.");
    }
    
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.dungeon = new Room[numberOfRows][numberOfColumns];
    this.create(numberOfRows, numberOfColumns);
  }

  @Override
  public void createTheDungeon() {
    
  }

  @Override
  public RoomDesign[][] getDungeonLayout() {
    return this.dungeon;
  }

  /**
   * Pick rooms randomly to contain gold.
   */
  protected void putGoldInRoomRandom() {
    Random randomNum = new Random();
    randomNum.setSeed(1);

    // Just set gold to 20.
    int totalRooms = this.numberOfColumns * this.numberOfRows;
    int amountOfGoldInTheRoom = (int) (0.2 * totalRooms);
    HashSet<int[]> goldRooms = addSpecialRooms(randomNum, amountOfGoldInTheRoom);

    //  Based on the rooms that get gold, set the gold there.
    for (int[] item : goldRooms) {
      this.dungeon[item[0]][item[1]].setGoldAmount(GOLD_IN_ROOM);
    }
  }

  /**
   * Select a number of rooms to contain a thief.
   */
  protected void putThiefInRandomRooms() {
    Random randomNum = new Random();
    randomNum.setSeed(2);

    int totalRooms = this.numberOfColumns * this.numberOfRows;
    int numberOfRoomsWithThieves = (int) (0.1 * totalRooms);
    addSpecialRooms(randomNum, numberOfRoomsWithThieves);
    HashSet<int[]> thiefRooms = addSpecialRooms(randomNum, numberOfRoomsWithThieves);

    // Put the thieves in.
    for (int[] item : thiefRooms) {
      this.dungeon[item[0]][item[1]].putThiefInTheRoom();
    }
  }

  /**
   * Gets the number of rows.
   *
   * @return the number of rows
   */
  protected int getNumberOfRows() {
    return this.numberOfRows;
  }

  /**
   * Gets the number of columns.
   *
   * @return the number of columns
   */
  protected int getNumberOfColumns() {
    return this.numberOfColumns;
  }

  @Override
  public ArrayList<int[]> getDungeonWalls() {
    return this.dungeonWalls;
  }

  @Override
  public void createThePerfectDungeon(ArrayList<int[]> dungeonWalls) {
    Random randomNum = new Random();
    randomNum.setSeed(1);

    int numberOfRooms = numberOfRows * numberOfColumns;
    int removeCount = 0;
    ArrayList<HashSet<Integer>> walls = addRoomsToTheDungeon();

    while (removeCount < numberOfRooms - 1) {
      int[] wall = dungeonWalls.get(randomNum.nextInt(dungeonWalls.size()));

      int[] indexOne = new int[]{
              wall[0], wall[1]
      };

      int[] indexTwo = new int[]{
              wall[2], wall[3]
      };

      RoomDesign roomOne = dungeon[indexOne[0]][indexOne[1]];
      RoomDesign roomTwo = dungeon[indexTwo[0]][indexTwo[1]];

      HashSet<Integer> oneSet = walls.get(roomOne.getRoomId());
      HashSet<Integer> twoSet = walls.get(roomTwo.getRoomId());

      // Orient the room structure and set room relationships.
      if (!(oneSet.equals(twoSet))) {
        // Set rooms to the left and right
        if (indexOne[1] < indexTwo[1]) {
          roomOne.tagRoomToTheRight(roomTwo);
          roomTwo.tagRoomToTheLeft(roomOne);
        } else if (indexOne[0] < indexTwo[0]) {
          // Set rooms above and below this one.
          roomOne.tagRoomBelow(roomTwo);
          roomTwo.tagRoomAbove(roomOne);
        } else if (indexOne[1] > indexTwo[1]) {
          // Set the walls to left and right
          roomOne.tagRoomToTheRight(roomTwo);
          roomTwo.tagRoomToTheLeft(roomOne);
        } else if (indexOne[0] > indexTwo[0]) {
          // Rooms above and below.
          roomOne.tagRoomBelow(roomTwo);
          roomTwo.tagRoomAbove(roomOne);
        }

        // update sets
        oneSet.addAll(twoSet);

        // Set the walls.
        for (Integer item : oneSet) {
          walls.set(item, oneSet);
        }

        dungeonWalls.remove(wall);
        removeCount++;
      } else {
        dungeonWalls.remove(wall);
      }
    }

    putGoldInRoomRandom();
    putThiefInRandomRooms();
  }

  @Override
  public void createTheWrappedPerfectDungeon() {
    ArrayList<int[]> allWalls = this.getDungeonWalls();
    int numberOfRows = this.numberOfRows;
    int numberOfColumns = this.numberOfColumns;

    // to make a wrapped perfect maze, the wall edges should be considered.
    // add left and right walls
    for (int i = 0; i < numberOfRows; i++) {
      allWalls.add(new int[]{i, numberOfColumns - 1, i, 0});
    }
    // Add the walls above and below.
    for (int j = 0; j < numberOfColumns; j++) {
      allWalls.add(new int[]{numberOfRows - 1, j, 0, j});
    }

    createThePerfectDungeon(allWalls);
  }

  @Override
  public void createTheNonPerfectDungeon(int dungeonWallCount) {
    if (dungeonWallCount < 0
            || dungeonWallCount > numberOfRows * (numberOfColumns - 1)
            + (numberOfRows - 1) * numberOfColumns - numberOfRows * numberOfColumns + 1) {
      throw new IllegalArgumentException("Wrong number of remaining walls.");
    }

    ArrayList<int[]> remainingWalls = new ArrayList<>();
    Random randomNum = new Random();
    randomNum.setSeed(1);

    int numberOfCells = numberOfRows * numberOfColumns;
    int removeCount = 0;

    final int numberOfWalls = this.dungeonWalls.size();
    ArrayList<HashSet<Integer>> walls = addRoomsToTheDungeon();

    while (removeCount < numberOfCells - 1) {
      int[] edge = dungeonWalls.get(randomNum.nextInt(dungeonWalls.size()));

      int[] indexOne = new int[]{
              edge[0], edge[1]
      };

      int[] indexTwo = new int[]{
              edge[2], edge[3]
      };

      RoomDesign roomOne = dungeon[indexOne[0]][indexOne[1]];
      RoomDesign roomTwo = dungeon[indexTwo[0]][indexTwo[1]];

      HashSet<Integer> setRoomRelationshipOne = walls.get(roomOne.getRoomId());
      HashSet<Integer> setRoomRelationshipTwo = walls.get(roomTwo.getRoomId());

      if (!(setRoomRelationshipOne.equals(setRoomRelationshipTwo))) {
        // left and right
        if (indexOne[1] < indexTwo[1]) {
          roomOne.tagRoomToTheRight(roomTwo);
          roomTwo.tagRoomToTheLeft(roomOne);
        } else if (indexOne[0] < indexTwo[0]) {
          // up and down
          roomOne.tagRoomBelow(roomTwo);
          roomTwo.tagRoomAbove(roomOne);
        }

        // update sets
        setRoomRelationshipOne.addAll(setRoomRelationshipTwo);

        for (Integer item : setRoomRelationshipOne) {
          walls.set(item, setRoomRelationshipOne);
        }

        dungeonWalls.remove(edge);
        removeCount++;
      } else {
        remainingWalls.add(edge);
        dungeonWalls.remove(edge);
      }
    }

    remainingWalls.addAll(dungeonWalls);

    int remainWallCount = numberOfWalls - numberOfCells + 1;

    while (remainWallCount > dungeonWallCount) {
      int[] edge = remainingWalls.get(randomNum.nextInt(remainingWalls.size()));

      int[] indexOne = new int[]{
              edge[0], edge[1]
      };

      int[] indexTwo = new int[]{
              edge[2], edge[3]
      };

      RoomDesign roomOne = dungeon[indexOne[0]][indexOne[1]];
      RoomDesign roomTwo = dungeon[indexTwo[0]][indexTwo[1]];

      if (indexOne[1] < indexTwo[1]) {
        roomOne.tagRoomToTheRight(roomTwo);
        roomTwo.tagRoomToTheLeft(roomOne);
      } else if (indexOne[0] < indexTwo[0]) {
        // up and down
        roomOne.tagRoomBelow(roomTwo);
        roomTwo.tagRoomAbove(roomOne);
      }

      remainingWalls.remove(edge);
      remainWallCount--;
    }

    putGoldInRoomRandom();
    putThiefInRandomRooms();
  }

  // region Private Methods
  private ArrayList<HashSet<Integer>> addRoomsToTheDungeon() {
    ArrayList<HashSet<Integer>> walls = new ArrayList<>();

    // Grab the rooms based on the dungeon dimensions.
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns; j++) {
        HashSet<Integer> room = new HashSet<>();
        room.add(this.getDungeonLayout()[i][j].getRoomId());

        walls.add(room);
      }
    }

    return walls;
  }

  private void create(int numberOfRows, int numberOfColumns) {
    int roomId = 0;

    //  This creates the basic rooms of the dungeon based on size of
    //  requested dungeon.
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns; j++) {
        Room copyRoom = new Room(new int[]{i, j});
        copyRoom.setRoomId(roomId);
        roomId++;
        this.dungeon[i][j] = copyRoom;
      }
    }

    // Add the "walls" of the dungeon.
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns - 1; j++) {
        this.dungeonWalls.add(new int[]{i, j, i, j + 1});
      }
    }

    for (int j = 0; j < numberOfColumns; j++) {
      for (int i = 0; i < numberOfRows - 1; i++) {
        this.dungeonWalls.add(new int[]{i, j, i + 1, j});
      }
    }
  }

  /**
   * This adds rooms like gold, thief, etc.
   *
   * @param randomNum Random number object.
   * @param numberOfRooms Number of rooms.
   * @return The rooms.
   */
  private HashSet<int[]> addSpecialRooms(Random randomNum, int numberOfRooms) {
    HashSet<int[]> specialRooms = new HashSet<>();

    while (specialRooms.size() < numberOfRooms) {
      int row = randomNum.nextInt(this.numberOfRows);
      int column = randomNum.nextInt(this.numberOfColumns);

      specialRooms.add(new int[]{row, column});
    }

    return specialRooms;
  }
  // endregion
}
