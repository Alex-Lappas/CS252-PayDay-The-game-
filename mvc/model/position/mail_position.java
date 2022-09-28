package mvc.model.position;

import mvc.model.Player;
import mvc.model.board.Board;
import mvc.model.card.Card;
import mvc.model.card.MoveToSpecifiedSpot;
import mvc.model.card.mail_card;


public class mail_position extends CardPosition {
    private int pick_number;
    private int money;


    /**
     * This is the constructor for this class
     *
     * @param pick_number
     * @param day
     * @param position_on_table
     * @param image
     */
    public mail_position(int position_on_table,Days day, String image, int pick_number) {
        super(position_on_table, day, image);
        this.pick_number = pick_number;
    }

    /**
     * This is getter for the variable pick_number
     * @return pick number
     *
     */
    public int getPick_number() {
        return pick_number;
    }

    /**
     * This method is meant to be used to enable the player to get the mail cards. He picks up the amount given by pick number
     *
     * @param p
     * @param b
     * @pre player gets on a tile with mail cards
     * @post player gets mail cards
     */
    public void getCards(Board b, Player p, Card c) {
        mail_card m1,m2;
        for(int i= 0; i<pick_number;++i){
            m1=b.TakeMailCard(p);
            if(m1 instanceof MoveToSpecifiedSpot && pick_number==2) {
                m2=b.TakeMailCard(p);
                ++i;
                m2.player_action(p);
                m1.player_action(p);
            }
        }
    }

}
