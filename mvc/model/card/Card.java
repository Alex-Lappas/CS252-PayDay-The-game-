package mvc.model.card;


import mvc.model.Player;

/**
 *
 * @author AlexL
 */
 public abstract  class Card {

    String image;
    String popup_msg;

    /**
     * This method is meant to be used as the means of executing the effect of each card by the player.
     * @param p player
     * inherited by mail_card and deal_card
     * @pre if the card is a deal card player decides to buy it, he should have enough money to buy it or else he is going to be forced to get a loan. Both ways this is not 100% a precondition because this method will always be executed
     * @post  executes the action preformed by the player
     */
    public abstract void player_action(Player p);

    /**
     *getter for pop up message
     * @return popup_msg
     *
     */
    public String getPopup_msg() {
        return popup_msg;
    }

    /**
     *getter for image
     * @return image
     *
     */
    public String getImage() {
        return image;
    }

    /**
     *setter for pop up message
     * @param popup_msg
     */
    public void setPopup_msg(String popup_msg) {
        this.popup_msg = popup_msg;
    }

    /**
     * Constructor for the class card
     * @param message
     * @param image
     */
    public Card(String message , String image){
        this.popup_msg=message;
        this.image=image;
    }
}


