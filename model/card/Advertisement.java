package mvc.model.card;

import mvc.model.Player;


public class Advertisement extends mail_card {
    /**
     * Constructor of the class
     *
     * @param popup_msg
     * @param image
     */
    public Advertisement(String popup_msg, String image,int money) {
        super(popup_msg, image , money);
    }

    @Override

    public void player_action(Player p) {
        p.setMoney(p.getMoney() + money);
    }

}
