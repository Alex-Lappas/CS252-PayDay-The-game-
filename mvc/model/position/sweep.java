package mvc.model.position;

import mvc.model.Player;

public class sweep extends OnePlayerDicePosition {

    /**
     * This is the constructor of this class
     * @param position_on_table
     * @param day
     * @param image
     */
    public sweep(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
    }

    @Override
    public void performAction(Player p, int dice_number) {
        p.money_addition(1000*dice_number);
    }
}