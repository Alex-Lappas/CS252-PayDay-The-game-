package mvc.model.card;

import mvc.model.board.Jackpot;
import mvc.model.Player;

 public class Charity extends mail_card {
    /**
     * Constructor of the class
     *
     * @param money
     * @param image
     * @param popup_msg
     */
    public Charity(String popup_msg, String image, int money) {
        super(popup_msg, image, money);
    }

    public void action1(Player p, Jackpot j) {
        p.money_subtraction(money);
        j.addToJackpot(money);
    }

     @Override
     public void player_action(Player p) {}
 }
