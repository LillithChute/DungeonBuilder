package dungeon;

import adventurer.Adventurer;
import adventurer.RolePlayer;
import java.util.ArrayList;
import java.util.Arrays;
import room.RoomDesign;

/**
 * Creates a concrete implementation of a dungeon.
 */
public class Dungeon implements DungeonDesign {

  private final RolePlayer adventurer;
  private final int[] exitLocation;
  private int[] adventurerLocation;
  private final RoomDesign[][] dungeon;
  private boolean dungeonComplete = false;

  /**
   * Constructs an abstract maze class.
   *
   * @param exitLocation   the exit location of the maze
   * @param adventurerLocation the player start location of the maze
   * @param dungeon            the generated maze map
   */
  public Dungeon(int[] exitLocation, int[] adventurerLocation,
                          RoomDesign[][] dungeon) {

    // Make sure that the incoming dungeon isn't blank or null.
    if (dungeon == null || dungeon.length == 0) {
      throw new IllegalArgumentException("The dungeon can't be null.");
    }

    // The exit of the dungeon has to be a room within the dungeon.
    if (exitLocation[0] < 0 || exitLocation[0] >= dungeon.length
            || exitLocation[1] < 0 || exitLocation[1] >= dungeon[0].length
    ) {
      throw new IllegalArgumentException("There is an issue with the exit location.");
    }

    // The adventurer has to be within the dungeon.
    if (adventurerLocation[0] < 0 || adventurerLocation[0] >= dungeon.length
            || adventurerLocation[1] < 0 || adventurerLocation[1] >= dungeon[0].length
    ) {
      throw new IllegalArgumentException("There is an issue with the adventurer location.");
    }

    this.adventurer = new Adventurer();
    this.adventurerLocation = adventurerLocation;
    this.exitLocation = exitLocation;

    this.dungeon = dungeon;

    // set initialization of the dungeon.
    this.dungeonInitialization();

    // We have the dungeon, set has the player landed in the exit location?
    this.updateDungeon();

    // Update the adventurer's status
    this.updateAdventurer();
  }

  /**
   * This helper initializes the dungeon by placing the adventurer, exit, and flagging the
   * room as visited.
   */
  private void dungeonInitialization() {
    // Put the adventurer in the first room
    dungeon[adventurerLocation[0]][adventurerLocation[1]].adventurerIsInThisRoom();

    // Set the exit room location
    dungeon[exitLocation[0]][exitLocation[1]].exitRoom();

    // Set the room the adventurer is in to visited.
    dungeon[adventurerLocation[0]][adventurerLocation[1]].roomHasBeenVisited();
  }

  private void updateAdventurer() {
    // Set the current location to the adventurer.
    int[] currLocation = this.adventurerLocation;

    // initialize to first room.
    RoomDesign thisRoom = this.dungeon[currLocation[0]][currLocation[1]];

    // The adventurer gets the gold
    int goldInTheRoom = thisRoom.getGoldInTheRoom();
    this.adventurer.addGoldToBag(goldInTheRoom);
    thisRoom.removeGoldInRoom();

    // Thief steals players gold if he's in the room.
    if (thisRoom.isThiefInTheRoom()) {
      this.adventurer.goldIsStolen();
    }
  }

  @Override
  public int[] retrieveExitCoordinates() {
    return this.exitLocation;
  }

  @Override
  public int[] retrieveAdventurerCoordinates() {
    return this.adventurerLocation;
  }

  @Override
  public void makeAdventurerMove(String moveDirection) {
    ArrayList<String> allowableMoves = this.retrieveAdventurerMovementOptions();
    boolean isValidMove = false;

    // Get the list of allowed moves to make sure that a legit one is in the list.
    for (String o : allowableMoves) {
      if (o.equals(moveDirection)) {
        isValidMove = true;
        break;
      }
    }

    //  Throw error if this is not allowed.
    if (!isValidMove) {
      throw new IllegalArgumentException("Invalid movement.");
    }

    // option is valid
    int[] location = this.retrieveAdventurerCoordinates();
    RoomDesign thisRoom = this.dungeon[location[0]][location[1]];
    RoomDesign newRoom;

    switch (moveDirection) {
      case "up":
        newRoom = thisRoom.findRoomAbove();
        break;
      case "down":
        newRoom = thisRoom.findRoomBelow();
        break;
      case "left":
        newRoom = thisRoom.findRoomToTheLeft();
        break;
      default:
        newRoom = thisRoom.findRoomToTheRight();
    }

    // The adventurer is in this new room.  Set the basic flags for the movement.
    thisRoom.adventurerLeavesRoom();
    newRoom.adventurerIsInThisRoom();
    newRoom.roomHasBeenVisited();

    // Update the adventurer status.
    this.adventurerLocation = newRoom.getRoomLocation();
    this.updateAdventurer();
    this.updateDungeon();
  }

  @Override
  public ArrayList<String> retrieveAdventurerMovementOptions() {
    int[] location = this.retrieveAdventurerCoordinates();
    RoomDesign presentRoom = this.dungeon[location[0]][location[1]];

    return presentRoom.movementOptions();
  }

  @Override
  public void updateDungeon() {
    // Is the adventurer is at the exit then the crawl is over.
    if (Arrays.equals(this.adventurerLocation, this.exitLocation)) {
      this.dungeonComplete = true;
    }
  }

  @Override
  public int retrieveAdventurerGold() {
    return this.adventurer.getGoldAmount();
  }

  @Override
  public boolean dungeonCompleted() {
    return this.dungeonComplete;
  }

  @Override
  public String toString() {
    int numberOfRows = this.dungeon.length;
    int numberOfColumns = this.dungeon[0].length;

    return ViewDungeon.visualizer(numberOfRows, numberOfColumns, dungeon);
  }

}
