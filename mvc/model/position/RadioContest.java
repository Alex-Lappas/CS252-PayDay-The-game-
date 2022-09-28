package mvc.model.position;

import mvc.model.Player;

public class RadioContest extends BothPlayersDicePosition{

    /**
     * this is the constructor of this class
     * @param position_on_table
     * @param day
     * @param image
     */
    public RadioContest(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
    }

    @Override
    public void performAction(Player p, int dice_number1,int dice_number2) {
        if(dice_number1<dice_number2) {
            p.opponent.money_addition(1000);
        }
        if(dice_number1>dice_number2) {
            p.money_addition(1000);
        }
    }
}

