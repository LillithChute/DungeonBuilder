package dungeontest;

import static org.junit.Assert.assertEquals;

import dungeon.CreateDungeon;
import dungeon.Dungeon;
import dungeon.DungeonCreationDesign;
import dungeon.DungeonDesign;
import org.junit.Test;

/**
 * Test for creating a perfect dungeon.
 */
public class TestPerfectDungeon {

  @Test
  public void createThePerfectDungeonTest() {
    DungeonCreationDesign perfectDungeon = new CreateDungeon(5, 10);
    perfectDungeon.createThePerfectDungeon(perfectDungeon.getDungeonWalls());

    DungeonDesign dungeon = new Dungeon(new int[]{0, 0}, new int[]{4, 5},
            perfectDungeon.getDungeonLayout());
    assertEquals("    0    1    2    3    4    5    6    7    8    9    \n"
            + "  --------------------------------------------------  \n"
            + "0 | E || T      ||                  ||   || 20      | 0\n"
            + "  -   -------   --   --   -------   --   --   ------  \n"
            + "  |   |-----|   ||   ||   |-----|   ||   ||   |-----  \n"
            + "1 |        ||        ||   ||             ||      20 | 1\n"
            + "  ------   ------------   ------------   --   ------  \n"
            + "  -----|   |----------|   |----------|   ||   |-----  \n"
            + "2 |           20 || 20 || 20      ||             || T | 2\n"
            + "  ------   --   --   --   ------------   --   --   -  \n"
            + "  -----|   ||   ||   ||   |----------|   ||   ||   |  \n"
            + "3 |        ||   ||        ||             || 20 ||   | 3\n"
            + "  ------   --   --   --   --   --   --   -------   -  \n"
            + "  -----|   ||   ||   ||   ||   ||   ||   |-----|   |  \n"
            + "4 |        ||      20 ||20 T|| A ||20 T||             | 4\n"
            + "  --------------------------------------------------  \n"
            + "    0    1    2    3    4    5    6    7    8    9    \n", dungeon.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createThePerfectDungeonZeroRowsTest() {
    DungeonCreationDesign perfectDungeon = new CreateDungeon(0, 1);
    perfectDungeon.createThePerfectDungeon(perfectDungeon.getDungeonWalls());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createThePerfectDungeonZeroColumnsTest() {
    DungeonCreationDesign perfectDungeon = new CreateDungeon(1, 0);
    perfectDungeon.createThePerfectDungeon(perfectDungeon.getDungeonWalls());
  }

  @Test (expected = IllegalArgumentException.class)
  public void createThePerfectDungeonNegativeRowsTest() {
    DungeonCreationDesign perfectDungeon = new CreateDungeon(-1, 0);
    perfectDungeon.createThePerfectDungeon(perfectDungeon.getDungeonWalls());
  }

  @Test (expected = IllegalArgumentException.class)
  public void createThePerfectDungeonNegativeColumnsTest() {
    DungeonCreationDesign perfectDungeon = new CreateDungeon(1, -1);
    perfectDungeon.createThePerfectDungeon(perfectDungeon.getDungeonWalls());
  }
}
