package mvc.model.position;

import mvc.model.Player;

public abstract class OnePlayerDicePosition extends DicePosition {

    /**
     *This is the constructor of this class
     * @param position_on_table
     * @param day
     * @param image
     *
     */
    OnePlayerDicePosition(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
    }

    /**
     * This is the method that allows the action to be executed when the player is on a dice position
     *
     * @param dice_number
     * @param p
     * @pre player is on a dice position
     * @post action is executed
     */
    abstract void performAction(Player p, int dice_number);
}