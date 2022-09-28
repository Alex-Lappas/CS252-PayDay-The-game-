package mvc.model.position;

import mvc.model.Player;

public class lottery_ticket extends BothPlayersDicePosition {
     int rand;

    /**
     * This is the getter for the variable rand
     * @return rand
     *
     */
    public int getRand() {
        return rand;
    }

    /**
     * This is the constructor of this class
     *
     * @param position_on_table
     * @param day
     * @param image
     */
    public lottery_ticket(int position_on_table, Days day, String image) {
        super(position_on_table, day, image );
    }

    @Override
    public void performAction(Player p, int choice1, int choice2) {
        boolean f1=false ,f2=false;
         this.rand=0;
        do{
            rand= ((int)((Math.random() * (7 - 1)) + 1));
            if(rand!=choice1) f1=true;
            if(rand!=choice2) f2=true;
        } while((choice1 != rand) && (choice2 != rand));
        if(choice1==rand){
            p.money_addition(1000);
        }else p.getOpponent().money_addition(1000);
    }
}
