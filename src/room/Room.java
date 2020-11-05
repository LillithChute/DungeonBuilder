package room;

import java.util.ArrayList;

public class Room implements RoomDesign {
  private RoomDesign roomAbove;
  private RoomDesign roomBelow;
  private RoomDesign leftRoom;
  private RoomDesign rightRoom;
  private final int[] location;
  private int gold;
  private boolean thief;
  private int roomId = 0;
  private boolean exitRoom = false;
  private boolean adventurer = false;
  private boolean visited = false;

  /**
   * This is an implementation of a single room, to include gold, thief, walls, and goals.
   *
   * @param location Set the coordinates of the room.
   */
  public Room(int[] location) {
    // Make sure no clever person tries to create an antimatter room.
    if (location[0] < 0 || location[1] < 0) {
      throw new IllegalArgumentException("No alternate universe room.");
    }

    // Start up values.
    this.roomAbove = null;
    this.roomBelow = null;
    this.leftRoom = null;
    this.rightRoom = null;
    this.location = location;
    this.gold = 0;
    this.thief = false;
  }

  @Override
  public void adventurerIsInThisRoom() {
    this.adventurer = true;
  }

  @Override
  public void adventurerLeavesRoom() {
    this.adventurer = false;
  }

  @Override
  public void roomHasBeenVisited() {
    this.visited = true;
  }

  @Override
  public void exitRoom() {
    this.exitRoom = true;
  }

  @Override
  public int[] getRoomLocation() {
    return this.location;
  }

  @Override
  public int getGoldInTheRoom() {
    return this.gold;
  }

  @Override
  public void tagRoomAbove(RoomDesign roomAbove) {
    this.roomAbove = roomAbove;
  }

  @Override
  public void setGoldAmount(int goldAmount) {
    if (goldAmount < 0) {
      throw new IllegalArgumentException("Amount of gold must be greater than zero.");
    }

    this.gold = goldAmount;
  }

  @Override
  public RoomDesign findRoomAbove() {
    return this.roomAbove;
  }

  @Override
  public void tagRoomBelow(RoomDesign roomBelow) {
    this.roomBelow = roomBelow;
  }

  @Override
  public RoomDesign findRoomBelow() {
    return this.roomBelow;
  }

  @Override
  public void tagRoomToTheLeft(RoomDesign roomLeft) {
    this.leftRoom = roomLeft;
  }

  @Override
  public RoomDesign findRoomToTheLeft() {
    return this.leftRoom;
  }

  @Override
  public void tagRoomToTheRight(RoomDesign roomRight) {
    this.rightRoom = roomRight;

  }

  @Override
  public RoomDesign findRoomToTheRight() {
    return this.rightRoom;
  }

  @Override
  public int getRoomId() {
    return this.roomId;
  }

  @Override
  public void setRoomId(int roomId) {
    if (roomId < 0) {
      throw new IllegalArgumentException("Room ID can't be less than zero.");
    }

    this.roomId = roomId;
  }

  @Override
  public boolean isThiefInTheRoom() {
    return this.thief;
  }

  @Override
  public void putThiefInTheRoom() {
    this.thief = true;
  }

  @Override
  public void removeGoldInRoom() {
    this.gold = 0;
  }

  @Override
  public ArrayList<String> movementOptions() {
    ArrayList<String> result = new ArrayList<>();

    if (this.roomAbove != null) {
      result.add("up");
    }
    if (this.roomBelow != null) {
      result.add("down");
    }
    if (this.leftRoom != null) {
      result.add("left");
    }
    if (this.rightRoom != null) {
      result.add("right");
    }

    return result;
  }

  @Override
  public String displayThief() {
    String result = "";

    // This is a wall
    result = checkForWallAbove(result);

    // Check the left side.
    if (this.leftRoom == null) {

      // Is there a thief
      if (this.thief) {
        result += String.format("| T ", this.getGoldInTheRoom());
      } else {
        result += "|   ";
      }
    } else {
      if (this.thief) {
        result += String.format("  T ", this.getGoldInTheRoom());
      } else {
        result += "    ";
      }
    }

    // Check the right side
    if (this.rightRoom == null) {
      result += "|\n";
    } else {
      result += " \n";
    }

    // Check room below
    if (this.roomBelow == null) {
      result += "-----";
    } else {
      result += "|   |";
    }

    return result;
  }

  private String checkForWallAbove(String result) {
    if (this.roomAbove == null) {
      result += "-----\n";
    } else {
      result += "|   |\n";
    }
    return result;
  }

  @Override
  public String displayGold() {
    String result = "";

    // Check for the wall above.
    if (this.roomAbove == null) {
      result += "-----\n";
    } else {
      result += "|   |\n";
    }

    // Check left
    if (this.leftRoom == null) {

      // Is there gold here
      if (this.gold != 0) {
        result += String.format("| %d ", this.getGoldInTheRoom());
      } else {
        result += "|   ";
      }
    } else {
      if (this.gold != 0) {
        result += String.format("  %d ", this.getGoldInTheRoom());
      } else {
        result += "    ";
      }
    }

    // Check the right
    if (this.rightRoom == null) {
      result += "|\n";
    } else {
      result += " \n";
    }

    if (this.roomBelow == null) {
      result += "-----";
    } else {
      result += "|   |";
    }

    return result;
  }

  @Override
  public String toggleRoomVisibility() {
    if (!this.visited) {
      return "     \n     \n     ";
    } else {
      return this.toString();
    }
  }

  @Override
  public String visitedRoomDisplay() {
    if (!this.visited) {
      return "     \n     \n     ";
    } else {
      return this.toString();
    }
  }

  @Override
  public String toString() {
    String result = "";

    // The best way to test settings is to build a rudimentary map and use the functionality
    // to alter it.

    // If up is null then there is a wall.  Otherwise an opening.
    if (this.roomAbove == null) {
      result += "-----\n";
    } else {
      result += "|   |\n";
    }

    // If there is a wall to the left.
    if (this.leftRoom == null) {
      // Is this an exit room and is the adventurer here.
      if (this.exitRoom && this.adventurer) {
        result += "| A E";
      } else if (this.exitRoom) {
        result += "| E ";
      } else if (this.adventurer) {
        result += "| A ";
      } else if (this.gold > 0 && this.thief) {
        result += String.format("|%d T", this.getGoldInTheRoom());
      } else if (this.gold > 0) {
        result += String.format("| %d ", this.getGoldInTheRoom());
      } else if (this.thief) {
        result += "| T ";
      } else {
        result += "|   ";
      }
    } else {
      if (this.exitRoom && this.adventurer) {
        result += " A E";
      } else if (this.exitRoom) {
        result += "  E ";
      } else if (this.adventurer) {
        result += "  A ";
      } else if (this.gold > 0 && this.thief) {
        result += String.format(" %d T", this.getGoldInTheRoom());
      } else if (this.gold > 0) {
        result += String.format("  %d ", this.getGoldInTheRoom());
      } else if (this.thief) {
        result += "  T ";
      } else {
        result += "    ";
      }
    }

    // We have a wall.
    if (this.rightRoom == null) {
      result += "|\n";
    } else {
      result += " \n";
    }

    if (this.roomBelow == null) {
      result += "-----";
    } else {
      result += "-   -";
    }

    return result;
  }
}
