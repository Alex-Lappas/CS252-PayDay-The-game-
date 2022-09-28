package mvc.model.position;

import mvc.model.Player;
import mvc.model.board.Board;
import mvc.model.card.Card;


abstract public class CardPosition extends Position {



    /**
     * This is the constructor for this class
     *
     * @param position_on_table
     * @param image
     * @param day
     */
    public CardPosition(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
    }

    /**
     * This is the method that allows the player to get the cards if he is on a card position
     *
     * @param b
     * @param p
     * @pre player ius on a card position
     * @post player gets card
     */
    public abstract void getCards(Board b, Player p, Card c);
}
