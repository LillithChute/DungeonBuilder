package adventurer;

/**
 * These are the operations that an adventurer in the dungeon can perform.
 */
public interface RolePlayer {

  /**
   * This adds gold to the adventurer's bag.
   *
   * @param goldToAdd The gold to put in the bag.
   */
  void addGoldToBag(int goldToAdd);

  /**
   * The amount of gold the adventurer currently has.
   *
   * @return The amount of gold the adventurer currently has.
   */
  int getGoldAmount();

  /**
   * If the adventurer encounters a thief, this will remove 10% of their gold.
   */
  void goldIsStolen();
}
