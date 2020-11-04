package roomtest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import room.Room;
import room.RoomDesign;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This will test the functionality of the room class.
 */
public class TestRoom {
  private RoomDesign room;
  private String emptyRoom;

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
    assertEquals(emptyRoom, room.visitedRoomDisplay());
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

//  @Test
//  public void getUp() {
//    assertNull(room.findRoomAbove());
//    RoomDesign newRoom = new Room(new int[]{1, 3});
//    room.set(newRoom);
//    assertArrayEquals(new int[]{1, 3}, room.getUp().getLocation());
//  }
//
//  @Test
//  public void getDown() {
//    assertNull(room.getDown());
//    Room newRoom = new Room(new int[]{3, 3});
//    room.setDown(newRoom);
//    assertArrayEquals(new int[]{3, 3}, room.getDown().getLocation());
//  }
//
//  @Test
//  public void getLeft() {
//    assertNull(room.getLeft());
//    Room newRoom = new Room(new int[]{2, 2});
//    room.setLeft(newRoom);
//    assertArrayEquals(new int[]{2, 2}, room.getLeft().getLocation());
//  }
//
//  @Test
//  public void getRight() {
//    assertNull(room.getRight());
//    Room newRoom = new Room(new int[]{2, 4});
//    room.setRight(newRoom);
//    assertArrayEquals(new int[]{2, 4}, room.getRight().getLocation());
//  }
//
  @Test
  public void getThief() {
    assertFalse(room.isThiefInTheRoom());
    room.putThiefInTheRoom();
    assertTrue(room.isThiefInTheRoom());
  }

//  @Test
//  public void getIdentifier() {
//    assertEquals(0, room.getIdentifier());
//    room.setIdentifier(1);
//    assertEquals(1, room.getIdentifier());
//  }
//
//  @Test
//  public void setIdentifier() {
//    assertEquals(0, room.getIdentifier());
//    room.setIdentifier(1);
//    assertEquals(1, room.getIdentifier());
//  }
//
//  @Test
//  public void clearGold() {
//    assertEquals(0, room.getGoldInTheRoomTest());
//    room.setGold(10);
//    assertEquals(10, room.getGoldInTheRoomTest());
//    room.clearGold();
//    assertEquals(0, room.getGoldInTheRoomTest());
//  }
//
//  @Test
//  public void setUp() {
//    assertNull(room.getUp());
//    Room newRoom = new Room(new int[]{1, 3});
//    room.setUp(newRoom);
//    assertArrayEquals(new int[]{1, 3}, room.getUp().getLocation());
//  }
//
//  @Test
//  public void setDown() {
//    assertNull(room.getDown());
//    Room newRoom = new Room(new int[]{3, 3});
//    room.setDown(newRoom);
//    assertArrayEquals(new int[]{3, 3}, room.getDown().getLocation());
//  }
//
//  @Test
//  public void setLeft() {
//    assertNull(room.getLeft());
//    Room newRoom = new Room(new int[]{2, 2});
//    room.setLeft(newRoom);
//    assertArrayEquals(new int[]{2, 2}, room.getLeft().getLocation());
//  }
//
//  @Test
//  public void setRight() {
//    assertNull(room.getRight());
//    Room newRoom = new Room(new int[]{2, 4});
//    room.setRight(newRoom);
//    assertArrayEquals(new int[]{2, 4}, room.getRight().getLocation());
//  }
//
//  @Test
//  public void getOptions() {
//    Room up = new Room(new int[]{1, 3});
//    Room right = new Room(new int[]{2, 4});
//    room.setUp(up);
//    room.setRight(right);
//    ArrayList<String> expect = new ArrayList<>();
//    expect.add("up");
//    expect.add("right");
//    assertEquals(expect, room.getOptions());
//  }
//
//  @Test
//  public void showThief() {
//    assertEquals("UUUUU\n"
//            + "U   U\n"
//            + "UUUUU", room.showThief());
//    room.setThief();
//    assertEquals("UUUUU\n"
//            + "U T U\n"
//            + "UUUUU", room.showThief());
//  }
//
//  @Test
//  public void showGold() {
//    assertEquals("UUUUU\n"
//            + "U   U\n"
//            + "UUUUU", room.showGold());
//    room.setGold(5);
//    assertEquals("UUUUU\n"
//            + "U 5 U\n"
//            + "UUUUU", room.showGold());
//  }
//
//  @Test
//  public void touchString() {
//    assertEquals("     \n"
//            + "     \n"
//            + "     ", room.touchString());
//    room.setTouched();
//    assertEquals("UUUUU\n"
//            + "U   U\n"
//            + "UUUUU", room.touchString());
//  }
//
//  @Test
//  public void testToString() {
//    assertEquals("UUUUU\n"
//            + "U   U\n"
//            + "UUUUU", room.toString());
//    room.setGold(5);
//    assertEquals("UUUUU\n"
//            + "U 5 U\n"
//            + "UUUUU", room.toString());
//    room.setThief();
//    assertEquals("UUUUU\n"
//            + "U5 TU\n"
//            + "UUUUU", room.toString());
//  }
}
