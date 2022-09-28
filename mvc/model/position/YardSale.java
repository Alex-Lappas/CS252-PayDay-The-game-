package mvc.model.position;

import mvc.model.Player;
import mvc.model.board.Board;

public class YardSale extends OnePlayerDicePosition {

    /**
     * @param position_on_table
     * @param day
     * @param image
     */
    public YardSale(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
    }

    @Override
    void performAction(Player p, int dice_number)
    {}

    public void performAction1(Player p, Board b, int diceNumber ) {
        p.money_subtraction(diceNumber*100);
        p.addCards(b.TakeDealCard());

    }


}
