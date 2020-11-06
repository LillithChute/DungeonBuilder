package dungeontest;

import static org.junit.Assert.assertEquals;

import dungeon.CreateDungeon;
import dungeon.Dungeon;
import dungeon.DungeonCreationDesign;
import dungeon.DungeonDesign;
import org.junit.Test;

public class TestWrappedPerfectDungeon {
  @Test
  public void createTheWrappedPerfectDungeon() {
    DungeonCreationDesign wrappedPerfectDungeon = new CreateDungeon(10, 8);
    wrappedPerfectDungeon.createTheWrappedPerfectDungeon();

    DungeonDesign dungeon = new Dungeon(new int[]{0, 0}, new int[]{0, 4},
            wrappedPerfectDungeon.getDungeonLayout());
    assertEquals("    0    1    2    3    4    5    6    7    \n"
            + "  -----|   ||   |-----|   |-----|   ||   |  \n"
            + "0 | E    20      ||   || A ||        ||   | 0\n"
            + "  -   -------   --   --   -------   ------  \n"
            + "  |   |-----|   ||   ||   |-----|   |-----  \n"
            + "1 |   ||             ||        ||        | 1\n"
            + "  -   ----------------------   --   --   -  \n"
            + "  |   |--------------------|   ||   ||   |  \n"
            + "2 |        || T         20           || 20 | 2\n"
            + "  -----------   ----------------------   -  \n"
            + "  ----------|   |--------------------|   |  \n"
            + "3 |        ||   ||             ||      T | 3\n"
            + "  ------   --   --   --   --   -----------  \n"
            + "  -----|   ||   ||   ||   ||   |----------  \n"
            + "4   20         20 ||   ||   || 20      ||     4\n"
            + "  -----------   -------   ------------   -  \n"
            + "  ----------|   |-----|   |----------|   |  \n"
            + "5   20 ||        ||      20           ||     5\n"
            + "  -   --   ------------   --   -----------  \n"
            + "  |   ||   |----------|   ||   |----------  \n"
            + "6 |   ||          20 T     ||           20 | 6\n"
            + "  ------   -------   ---------------------  \n"
            + "  -----|   |-----|   |--------------------  \n"
            + "7     ||20 T||      20 || 20                  7\n"
            + "  -   -----------------   --   -----------  \n"
            + "  |   |---------------|   ||   |----------  \n"
            + "8 | T ||   ||        ||   || 20    20 ||   | 8\n"
            + "  -   --   --   -------   ------------   -  \n"
            + "  |   ||   ||   |-----|   |----------|   |  \n"
            + "9 |      20 ||   ||      T      || T      | 9\n"
            + "  ------   --   -------   -------   --   -  \n"
            + "    0    1    2    3    4    5    6    7    \n", dungeon.toString());
  }
}
