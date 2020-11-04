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
  private boolean exitRoom = false;
  private boolean adventurer = false;
  private boolean visited = false;

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
  public void setGoldAmount(int goldAmount) {
    if (goldAmount < 0) {
      throw new IllegalArgumentException("AMount of gold must be greater than zero.");
    }

    this.gold = goldAmount;
  }

  @Override
  public RoomDesign findRoomAbove() {
    return this.roomAbove;
  }

  @Override
  public RoomDesign findRoomBelow() {
    return this.roomBelow;
  }

  @Override
  public RoomDesign findRoomToTheLeft() {
    return this.leftRoom;
  }

  @Override
  public RoomDesign findRoomToTheRight() {
    return this.rightRoom;
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
    return null;
  }

  @Override
  public String displayGold() {
    return null;
  }

  @Override
  public String visitedRoom() {
    return null;
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
