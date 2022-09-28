package mvc.model.card;

import mvc.model.Player;


public class GetMoneyFromNeighbor extends mail_card {
    /**
     * Constructor of the class
     *
     * @param popup_msg
     * @param image
     * @param money
     */
    public GetMoneyFromNeighbor(String popup_msg, String image, int money) {
        super(popup_msg, image, money);
    }

    @Override
    public void player_action(Player p) {
        p.money_addition(money);
        p.opponent.money_subtraction(money);
    }


}
