package adventurer;

/**
 * This creates an instance of a role player for the dungeon.
 */
public class Adventurer implements RolePlayer {

  private int goldAmount;

  /**
   * Instantiates the adventurer with zero gold.
   */
  public Adventurer() {
    this.goldAmount = 0;
  }

  @Override
  public void addGoldToBag(int goldToAdd) {
    if (goldToAdd < 0) {
      throw new IllegalArgumentException("No such thing as negative gold!");
    }

    if (goldToAdd > 0) {
      System.out.println(String.format("\t- I have discovered %d gold.  "
              + "I shall add this to my bag!", goldToAdd));
    }

    this.goldAmount += goldToAdd;
  }

  @Override
  public int getGoldAmount() {
    return this.goldAmount;
  }

  @Override
  public void goldIsStolen() {
    System.out.println("\t- Wait!  What! thief! Come back! Lose 10% of the gold.");
    this.goldAmount = (int) (0.9 * this.goldAmount);
  }
}
