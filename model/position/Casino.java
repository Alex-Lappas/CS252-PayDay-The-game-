package mvc.model.position;


import mvc.model.board.Jackpot;
import mvc.model.Player;

public class Casino extends OnePlayerDicePosition {

    /**
     * This is the Constructor for this class
     *
     * @param position_on_table
     * @param day
     * @param image
     */
    public Casino(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
    }

    @Override
    void performAction(Player p, int dice_number) {} //dummy


    public void performAction1(Player p, int dice_number, Jackpot j) {
        if(dice_number==1 || dice_number==3 || dice_number==5){
            p.money_subtraction(500);
            j.addToJackpot(500);
        }
    }
}
