package mvc.model.card;

import mvc.model.Player;


public class Bill extends mail_card {
    /**
     * Constructor of the class
     *
     * @param popup_msg
     * @param image
     * @param money
     */
    public Bill(String popup_msg, String image, int money) {
        super(popup_msg, image, money);
    }

    @Override
    public void player_action(Player p) {
        p.setBills(p.getBills()+money);
        p.addCards(this);
    }
}
