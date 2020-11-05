package dungeontest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dungeon.Dungeon;
import dungeon.DungeonDesign;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import room.Room;
import room.RoomDesign;


/**
 * This tests the creation of the dungeon.
 */
public class TestDungeon {

  private DungeonDesign testDungeon;
  private RoomDesign[][] dungeon;

  /**
   * Construct the dungeon.
   */
  @Before
  public void setup() {
    // Create the rooms
    final Room roomOne = new Room(new int[]{0, 0});
    final Room roomTwo = new Room(new int[]{0, 1});
    final Room roomThree = new Room(new int[]{1, 0});
    final Room roomFour = new Room(new int[]{1, 1});

    // Set room relationships.
    roomOne.tagRoomToTheRight(roomTwo);
    roomTwo.tagRoomToTheLeft(roomOne);
    roomThree.tagRoomAbove(roomOne);
    roomOne.tagRoomBelow(roomThree);
    roomThree.tagRoomToTheRight(roomFour);
    roomFour.tagRoomToTheLeft(roomThree);

    // Make the dungeon
    dungeon = new Room[][]{{roomOne, roomTwo}, {roomThree, roomFour}};
    testDungeon = new Dungeon(new int[]{1, 1}, new int[]{0, 1}, dungeon);
  }

  @Test
  public void getPlayerLocation() {
    assertArrayEquals(new int[]{0, 1}, testDungeon.retrieveAdventurerCoordinates());
  }

  @Test
  public void retrieveAdventurerGoldTest() {
    assertEquals(0, testDungeon.retrieveAdventurerGold());
    dungeon[0][1].setGoldAmount(10);
    testDungeon = new Dungeon(new int[]{1, 1}, new int[]{0, 1}, dungeon);
    assertEquals(10, testDungeon.retrieveAdventurerGold());
  }

  @Test
  public void retrieveAdventurerMovementOptionsTest() {
    ArrayList<String> expected = new ArrayList<>();
    expected.add("left");
    assertEquals(expected, testDungeon.retrieveAdventurerMovementOptions());
  }

  @Test
  public void makeAdventurerMoveTest() {
    assertArrayEquals(new int[]{0, 1}, testDungeon.retrieveAdventurerCoordinates());
    testDungeon.makeAdventurerMove("left");
    assertArrayEquals(new int[]{0, 0}, testDungeon.retrieveAdventurerCoordinates());
  }

  @Test (expected = IllegalArgumentException.class)
  public void makeAdventurerMoveBadTest() {
    System.out.println(testDungeon.toString());
    testDungeon.makeAdventurerMove("up");
  }

  @Test
  public void retrieveExitCoordinatesTest() {
    assertArrayEquals(new int[]{1, 1}, testDungeon.retrieveExitCoordinates());
  }

  @Test
  public void updateGameStatus() {
    assertFalse(testDungeon.dungeonCompleted());
    testDungeon = new Dungeon(new int[]{1, 1}, new int[]{1, 1}, dungeon);
    assertTrue(testDungeon.dungeonCompleted());
  }

  @Test
  public void testToString() {
    assertEquals("    0    1    \n"
            + "  ----------  \n"
            + "0 |      A | 0\n"
            + "  -   ------  \n"
            + "  |   |-----  \n"
            + "1 |      E | 1\n"
            + "  ----------  \n"
            + "    0    1    \n", testDungeon.toString());

    testDungeon.makeAdventurerMove("left");

    assertEquals("    0    1    \n"
            + "  ----------  \n"
            + "0 | A      | 0\n"
            + "  -   ------  \n"
            + "  |   |-----  \n"
            + "1 |      E | 1\n"
            + "  ----------  \n"
            + "    0    1    \n", testDungeon.toString());
  }

//  @Test
//  public void touchString() {
//    assertEquals("    0    1    \n"
//            + "       UUUUU  \n"
//            + "0       ***U 0\n"
//            + "       UUUUU  \n"
//            + "              \n"
//            + "1            1\n"
//            + "              \n"
//            + "    0    1    \n", testDungeon.touchString());
//    testDungeon.playerMove("left");
//    assertEquals("    0    1    \n"
//            + "  UUUUUUUUUU  \n"
//            + "0 U***     U 0\n"
//            + "  U   UUUUUU  \n"
//            + "              \n"
//            + "1            1\n"
//            + "              \n"
//            + "    0    1    \n", testDungeon.touchString());
//  }
//
//  @Test
//  public void isFinished() {
//    assertFalse(testDungeon.isFinished());
//    testDungeon.playerMove("left");
//    testDungeon.playerMove("down");
//    testDungeon.playerMove("right");
//    assertTrue(testDungeon.isFinished());
//  }
}
