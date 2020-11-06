package dungeontest;

import static org.junit.Assert.assertEquals;

import dungeon.CreateDungeon;
import dungeon.Dungeon;
import dungeon.DungeonCreationDesign;
import dungeon.DungeonDesign;
import org.junit.Test;

public class TestNonPerfectDungeon {

  @Test
  public void createTheNonPerfectDungeonTest() {
    DungeonCreationDesign nonPerfectDungeon = new CreateDungeon(10, 7);
    nonPerfectDungeon.createTheNonPerfectDungeon(20);

    DungeonDesign dungeon = new Dungeon(new int[]{0, 0}, new int[]{0, 1},
            nonPerfectDungeon.getDungeonLayout());

    assertEquals("    0    1    2    3    4    5    6    \n"
            + "  -----------------------------------  \n"
            + "0 | E    A         20           ||   | 0\n"
            + "  -   --   --   --   --   --   --   -  \n"
            + "  |   ||   ||   ||   ||   ||   ||   |  \n"
            + "1 |   ||                            | 1\n"
            + "  -   --   --   --   --   --   ------  \n"
            + "  |   ||   ||   ||   ||   ||   |-----  \n"
            + "2 | 20         20      || T ||        | 2\n"
            + "  -   --   -----------------   ------  \n"
            + "  |   ||   |---------------|   |-----  \n"
            + "3 |             ||                  | 3\n"
            + "  -   -------   --   --   -----------  \n"
            + "  |   |-----|   ||   ||   |----------  \n"
            + "4 |     20 T   20                || 20 | 4\n"
            + "  -   --   --   --   --   --   --   -  \n"
            + "  |   ||   ||   ||   ||   ||   ||   |  \n"
            + "5 |                     20           | 5\n"
            + "  -   --   --   --   --   -------   -  \n"
            + "  |   ||   ||   ||   ||   |-----|   |  \n"
            + "6 | T                        20    20 | 6\n"
            + "  -   --   --   --   -------   --   -  \n"
            + "  |   ||   ||   ||   |-----|   ||   |  \n"
            + "7 | 20                        20    T | 7\n"
            + "  -   -------   --   --   --   --   -  \n"
            + "  |   |-----|   ||   ||   ||   ||   |  \n"
            + "8 | 20                ||      T || 20 | 8\n"
            + "  -   --   --   --   --   --   --   -  \n"
            + "  |   ||   ||   ||   ||   ||   ||   |  \n"
            + "9 |             ||          20 T     | 9\n"
            + "  -----------------------------------  \n"
            + "    0    1    2    3    4    5    6    \n", dungeon.toString());
  }
}
