package mvc.model.card;

import mvc.model.Player;


public class PayTheNeighbor extends mail_card {
    /**
     * Constructor of the class
     *
     * @param image
     * @param popup_msg
     * @param money
     */
    public PayTheNeighbor(String popup_msg, String image, int money) {
        super(popup_msg, image, money);
    }

    @Override
    public void player_action(Player p) {
        p.opponent.money_addition(money);
        p.money_subtraction(money);
    }
}
