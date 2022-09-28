package mvc.model.position;

import mvc.model.Player;

public class buyer extends Position {

    /**
     * This is the constructor of this class
     *
     * @param position_on_table
     * @param day
     * @param image
     */
    public buyer(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
    }

    public void performAction(Player p, int choice) {
       p.sell_deal_card(choice);
    }

}
