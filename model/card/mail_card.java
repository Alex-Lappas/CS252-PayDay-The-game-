package mvc.model.card;



public abstract class mail_card extends Card {
    int money;

    /**
     * Getter for the variable money
     * @return  money*/
    public int getMoney(){
        return money;
    }
    /**
     * Constructor for the class
     *  @param popup_msg
     * @param image
     * @param money*/
    mail_card(String popup_msg, String image, int money) {
        super(popup_msg, image);
        this.money = money;
    }


}
