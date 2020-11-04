package adventurertest;

import static org.junit.Assert.assertEquals;

import adventurer.Adventurer;
import adventurer.RolePlayer;
import org.junit.Before;
import org.junit.Test;

/**
 * This will test the adventurer.
 */
public class TestAdventurer {

  private RolePlayer adventurer;

  @Before
  public void setup() {
    adventurer = new Adventurer();
  }

  @Test
  public void constructor() {
    RolePlayer test = new Adventurer();
    assertEquals(0, test.getGoldAmount());
  }
  
  @Test
  public void addGoldToBag() {
    adventurer.addGoldToBag(10);
    assertEquals(10, adventurer.getGoldAmount());
  }

  @Test (expected = IllegalArgumentException.class)
  public void addNegativeGold() {
    adventurer.addGoldToBag(-10);
  }

  @Test
  public void getGoldAmount() {
    adventurer.addGoldToBag(10);
    assertEquals(10, adventurer.getGoldAmount());
  }

  @Test
  public void goldIsStolen() {
    adventurer.addGoldToBag(10);
    adventurer.goldIsStolen();
    assertEquals(9, adventurer.getGoldAmount());
  }
}
