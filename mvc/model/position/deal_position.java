package mvc.model.position;

import mvc.model.Player;
import mvc.model.board.Board;
import mvc.model.card.*;

public class deal_position extends CardPosition {
    private boolean choice;

    /**
     * This is the constructor of this class
     *
     * @param image
     * @param day
     * @param position_on_table
     */
    public deal_position(int position_on_table, Days day, String image,boolean choice) {
        super(position_on_table, day, image);
        this.choice=choice;
    }
    /**
     * This is the setter of choice
     * @param choice
     *
     *
     **/
    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    /**
     * This is the getter for the choice
     * @return choice
     */
    public boolean getChoice(){
        return  choice;
    }

    @Override
    public void getCards(Board b, Player p , Card c) {
        if(this.choice){
            p.buyDealCard((deal_card) c);
        }else{
            b.play_card(c);
        }
    }

}
