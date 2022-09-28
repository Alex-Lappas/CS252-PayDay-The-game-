package mvc.model.card;

import mvc.model.Player;


public class deal_card extends Card {
    private int buy_cost;
    private int sell_cost;


    @Override
    public String getImage() {
        return super.getImage();
    }

    public int getBuy_cost() {
        return buy_cost;
    }

    @Override
    public String getPopup_msg() {
        return super.getPopup_msg();
    }

    public int getSell_cost() {
        return sell_cost;
    }

    /**
     * sets the cost to buy the card
     *
     * @param buy_cost
     */
    public void setBuy_cost(int buy_cost) {
        this.buy_cost = buy_cost;
    }

    /**
     * sets the gain of selling the card
     *
     * @param sell_cost
     */
    public void setSell_cost(int sell_cost) {
        this.sell_cost = sell_cost;
    }

    /**
     * Constructor of the class
     *
     * @param sell_cost
     * @param buy_cost
     * @param image
     * @param message
     */
    public deal_card(String message, String image, int buy_cost, int sell_cost) {
        super(message, image);
        this.buy_cost = buy_cost;
        this.sell_cost = sell_cost;
    }

    @Override
    public void player_action(Player p) {
        p.buyDealCard(this);
    }
}
