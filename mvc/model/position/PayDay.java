package mvc.model.position;

import mvc.model.Player;
import mvc.model.board.Board;
import mvc.model.board.MyMonth;
import mvc.model.card.Bill;


public class PayDay extends Position {

    /**
     * This is the constructor of this class
     *
     * @param image
     */
    public PayDay(String image) {
        super(31, Days.Wednesday, image);
    }


    /**
     * This is the method that executed the action on payday
     *
     * @param p,m,b
     * @pre its payday
     * @post actions are executed
     */
   public void performAction(Player p, MyMonth m, Board b) {
        if(p.getMonth()==m.getGeneral_month()){
            p.setHasFinished(true);
            for(int i=0;i<p.player_cards.size();++i) {
                b.play_card(p.player_cards.get(i));
                p.player_cards.remove(i);
            }
        }else  {
            p.money_addition(3500);
            p.money_subtraction(p.getBills());

            for(int i=0; i<p.player_cards.size(); ++i) {
                if(p.player_cards.get(i) instanceof Bill) {
                    p.player_cards.remove(i);
                }
            }

            p.money_subtraction((10*p.getLoan())/100);
            p.setPosition(0);
        }
        p.update_month();
        if ((p.getMonth())==m.getGeneral_month()){
            p.setHasFinished(true);
        }
    }
}
