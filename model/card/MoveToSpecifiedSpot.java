package mvc.model.card;

import mvc.model.Player;
import mvc.model.position.buyer;
import mvc.model.position.deal_position;
import mvc.model.position.Position;


public class MoveToSpecifiedSpot extends mail_card {
    /**
     * Constructor of the class
     *
     * @param image
     * @param popup_msg
     */
    public MoveToSpecifiedSpot(String popup_msg, String image) {
        super(popup_msg, image,0);
    }


    public void player_action1(Player p, Position[] arr) {

        for (int i = p.getPosition(); i < arr.length; ++i) {
            if (arr[i] instanceof deal_position || arr[i] instanceof buyer) {
                    p.setPosition((arr[i].getPosition_on_table()));
            }
        }
    }


    @Override
    public void player_action(Player p) {
    }
}
