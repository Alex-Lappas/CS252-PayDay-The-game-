package mvc.model.position;


public abstract class DicePosition extends Position {
    int money, dice_number;

    /**
     * This is the constructor of this class
     *  @param position_on_table
     * @param day
     * @param image
     */
    DicePosition(int position_on_table, Days day, String image) {
        super(position_on_table, day, image);
        this.dice_number = dice_number;
    }


}
