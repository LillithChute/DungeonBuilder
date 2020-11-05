package roomtest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import room.Room;
import room.RoomDesign;

/**
 * This will test the functionality of the room class.
 */
public class TestRoom {
  private RoomDesign room;
  private String emptyRoom;

  /**
   * Create an empty room display.
   */
  @Before
  public void setup() {
    room = new Room(new int[]{2, 3});
    emptyRoom =     "-----\n"
            + "|   |\n"
            + "-----";

  }

  @Test
  public void adventurerIsInThisRoomTest() {
    room.adventurerIsInThisRoom();
    assertEquals("-----\n"
            + "| A |\n"
            + "-----", room.toString());
  }

  @Test
  public void adventurerLeavesRoomTest() {
    room.adventurerIsInThisRoom();
    room.adventurerLeavesRoom();
    assertEquals(emptyRoom, room.toString());
  }

  @Test
  public void visitedRoomDisplayTest() {
    assertEquals("     \n"
            + "     \n"
            + "     ", room.visitedRoomDisplay());
    room.roomHasBeenVisited();
    assertEquals(emptyRoom, room.visitedRoomDisplay());
  }

  @Test
  public void setExit() {
    assertEquals(emptyRoom, room.toString());
    room.exitRoom();
    assertEquals("-----\n"
            + "| E |\n"
            + "-----", room.toString());
  }

  @Test
  public void setGoldAmountTest() {
    assertEquals(0, room.getGoldInTheRoom());
    room.setGoldAmount(10);
    assertEquals(10, room.getGoldInTheRoom());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setGoldAmountBadTest() {
    room.setGoldAmount(-9);
  }

  @Test
  public void putThiefInTheRoomTest() {
    assertEquals(emptyRoom, room.toString());
    room.putThiefInTheRoom();
    assertEquals("-----\n"
            + "| T |\n"
            + "-----", room.toString());
  }

  @Test
  public void getRoomLocationTest() {
    assertArrayEquals(new int[]{2, 3}, room.getRoomLocation());
  }

  @Test
  public void getGoldInTheRoomTest() {
    room.setGoldAmount(20);
    assertEquals(20, room.getGoldInTheRoom());
  }

  @Test
  public void findAndTagRoomAboveTest() {
    assertNull(room.findRoomAbove());
    RoomDesign newRoom = new Room(new int[]{1, 3});
    room.tagRoomAbove(newRoom);
    assertArrayEquals(new int[]{1, 3}, room.findRoomAbove().getRoomLocation());
  }

  @Test
  public void findAndTagRoomBelowTest() {
    assertNull(room.findRoomBelow());
    RoomDesign newRoom = new Room(new int[]{3, 3});
    room.tagRoomBelow(newRoom);
    assertArrayEquals(new int[]{3, 3}, room.findRoomBelow().getRoomLocation());
  }

  @Test
  public void findAndTagRoomToTheLeftTest() {
    assertNull(room.findRoomToTheLeft());
    RoomDesign newRoom = new Room(new int[]{2, 2});
    room.tagRoomToTheLeft(newRoom);
    assertArrayEquals(new int[]{2, 2}, room.findRoomToTheLeft().getRoomLocation());
  }

  @Test
  public void findAndTagRoomToTheRightTest() {
    assertNull(room.findRoomToTheRight());
    RoomDesign newRoom = new Room(new int[]{2, 4});
    room.tagRoomToTheRight(newRoom);
    assertArrayEquals(new int[]{2, 4}, room.findRoomToTheRight().getRoomLocation());
  }

  @Test
  public void isThiefInTheRoomTest() {
    assertFalse(room.isThiefInTheRoom());
    room.putThiefInTheRoom();
    assertTrue(room.isThiefInTheRoom());
  }

  @Test
  public void getAndSetRoomIdTest() {
    assertEquals(0, room.getRoomId());
    room.setRoomId(1);
    assertEquals(1, room.getRoomId());
  }

  @Test
  public void removeGoldInRoomTest() {
    assertEquals(0, room.getGoldInTheRoom());
    room.setGoldAmount(20);
    assertEquals(20, room.getGoldInTheRoom());
    room.removeGoldInRoom();
    assertEquals(0, room.getGoldInTheRoom());
  }

  @Test
  public void movementOptionsTest() {
    RoomDesign above = new Room(new int[]{1, 3});
    RoomDesign right = new Room(new int[]{2, 4});

    room.tagRoomAbove(above);
    room.tagRoomToTheRight(right);

    ArrayList<String> expectedMovementOptions = new ArrayList<>();

    expectedMovementOptions.add("up");
    expectedMovementOptions.add("right");

    assertEquals(expectedMovementOptions, room.movementOptions());
  }

  @Test
  public void displayThiefTest() {
    assertEquals(emptyRoom, room.displayThief());
    room.putThiefInTheRoom();
    assertEquals("-----\n"
            + "| T |\n"
            + "-----", room.displayThief());
  }

  @Test
  public void displayGoldTest() {
    assertEquals(emptyRoom, room.displayGold());
    room.setGoldAmount(10);
    assertEquals("-----\n"
            + "| 10 |\n"
            + "-----", room.displayGold());
  }

  @Test
  public void touchString() {
    assertEquals("     \n"
            + "     \n"
            + "     ", room.toggleRoomVisibility());
    room.roomHasBeenVisited();
    assertEquals(emptyRoom, room.toggleRoomVisibility());
  }

  @Test
  public void testToString() {
    assertEquals(emptyRoom, room.toString());
    room.setGoldAmount(10);
    assertEquals("-----\n"
            + "| 10 |\n"
            + "-----", room.toString());
    room.putThiefInTheRoom();
    assertEquals("-----\n"
            + "|10 T|\n"
            + "-----", room.toString());
  }
}
