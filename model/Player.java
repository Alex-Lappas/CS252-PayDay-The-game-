package mvc.model;

import mvc.model.board.Jackpot;
import mvc.model.board.MyMonth;
import mvc.model.card.Card;
import mvc.model.card.deal_card;

import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author AlexL
 */
public class Player {
    public ArrayList<Card> player_cards=new ArrayList<Card>();
    private int position=0;
    private boolean hasStarted=false,hasFinished=false;
    private boolean turn;
    private int bills, money, loan=0;
    private int month=1,dice_roll;
    public Player opponent;
    private String player_name;

    /**
     *Setter for variable player name
     * @param player_name
     *
     */
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    /**
     *Getter for variable player name
     * @return player_name
     *
     */
    public String getPlayer_name() {
        return this.player_name;
    }

    /**
     *Setter for variable hasFinished
     * @param hasFinished
     *
     */
    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public boolean getHasFinished() {
       return this.hasFinished ;
    }

    /**
     *Getter for variable position
     * @return turn
     *
     */
     public boolean GetTurn(){
         return turn;
     }

    /**
     *Setter for variable position
     * @param turn
     *
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }


    /**
     *Setter for variable position
     * @param position
     *
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     *This method is meant to be used as the means of rolling the player's dice.It generates a random number between 1 and 6.
     * @post a random number between one and six is generated which is used to move the pawn said tiles.
     * @return roll_dice , the random number generated
     *
     */
    public void Roll_the_dice(Jackpot j){
       setDice_roll ((int)((Math.random() * (7 - 1)) + 1));
       if(getDice_roll()==6){
            money_addition(j.winJackpot());
       }
    }
    /**
     *This method is meant to be used as the means of rolling the player's dice.It generates a random number between 1 and 6.This one is for the automated dice roll.
     * @post a random number between one and six is generated which is used to move the pawn said tiles.
     * @return roll_dice , the random number generated
     *
     */

    public int Roll_the_dice_auto(){
       return ((int)((Math.random() * (7 - 1)) + 1));
    }
    /**
     *Setter for variable dice_roll
     * @param dice_roll
     *
     */
    public void setDice_roll(int dice_roll) {
        this.dice_roll = dice_roll;
    }

    /**
     *Getter for variable dice_roll
     * @return dice_roll, the number outcome of the dice
     *
     */
    public int getDice_roll() {
        return dice_roll;
    }

    /**
     *Getter for the variable month
     * @return the current month of the player, month
     *
     */
    public int getMonth() {
        return month;
    }

    /**
     *Setter for Variable bills
     * @param bills
     *
     */
    public void setBills(int bills) {
        this.bills = bills;
    }

    /**
     *Getter for Variable bills
     * @return bills
     */
    public int getBills() {
        return bills;
    }

    /**
     *Setter for the variable loan
     * @param loan
     *
     */
    public void setLoan(int loan) {
        this.loan = loan;
    }

    /**
     *Getter for the variable loan
     * @return loan
     *
     */
    public int getLoan() {
        return loan;
    }

    /**
     *Setter for the Variable money
     * @param money
     *
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     *Getter for the variable money
     * @return money
     *
     */
    public int getMoney() {
        return money;
    }

    /**
     *Constructor of the class. In its default form
     *
     *
     */
    public Player(String player_name){
        this.setMoney(3500);
        this.setLoan(0);
        this.setBills(0);
        this.month=0;
        this.player_name=player_name;
    }

    /**
     *This method is used to increase the bank account of the player
     * @post increase in bank account of player
     * @param money_increase
     *
     */
    public void money_addition(int money_increase){
        this.money+=money_increase;
    }

    /**
     *This method is used to decrease the bank account of the player
     * @post dencrease in bank account of player
     * @param money_decrease
     *
     */
    public void money_subtraction(int money_decrease) {
        this.money-=money_decrease;
        if(this.money<0) {
            int remainder = (-this.money) % 1000;
            if (remainder == 0) {
                forced_loan(-this.money);
            }

        forced_loan((-this.money) + 1000 - remainder);
        }
    }

    /**
     *This method is meant to end a players turn and allow the next player to play
     * @pre A player has started playing
     * @post end turn of said player and pass turn to the next one
     *
     */
    public void end_turn(){
        this.setTurn(false);
        this.opponent.setTurn(true);
    }

    /**
     *This method is meant to be used to allow the player to get a loan.It should be mentioned that this is a loan by choice and not a forced loan(the case where the player doesn't have enough money to pay a bill).
     * @pre player chooses to get a loan
     * @post player gets a loan increased by variable increase. Loan stacks up and must pe paid.
     * @param increase
     */
    public void get_loan(int increase){
        this.money+=increase;
        this.loan+=increase;
    };

    /**
     *This method allows the player to get a forced loan when he lacks the money to pay a bill.
     * @pre player doesn't have enough money to pay a certain bill
     * @post player gets a loan fitted to be the specific number wanted by the rules and the least money possible to not destroy his bank account. Loan stacks up and must be paid.
     * @param increase
     */
    public void forced_loan(int increase){
        this.loan+= increase;
        this.money=increase+this.money;
        JOptionPane.showMessageDialog(null, "You had to get a loan", "Forced loan!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *Getter for variable position
     * @return position
     *
     */
    public int getPosition() {
        return position;
    }

    /**
     *This method allows the player to move his pawn through the board. This method combined with the dice roll are the building blocks of movement in this board game
     * @pre player has thrown a die to be able to move
     * @post player moves said tiles.
     * @param steps the moves he has to make
     * @return position
     */
    public int movePosition(int steps) {
        if(!this.getHasFinished()){
            if (this.getPosition() + steps >= 31) {
                this.position = 31;
                //update_month();
            } else this.position += steps;
        }
            return position;
    }


    /**
     *Getter for array list get playerPlayer_cards
     * @return player_cards
     */
    public ArrayList<Card> getPlayer_cards() {
        return player_cards;
    }

    /**
     *This method allows the player to add a deal card to his personal deck.
     * @pre Player falls on a deal card tile
     * @post player adds card to personal deck.
     */
    public void addCards(Card card) {
        this.player_cards.add(card);
    }

    /**
     *Setter for the variable oppontent
     * @param opponent
     *
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }


    /**
     *Getter for variable get opponent
     * @return opponent
     *
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     *This method allows the player to update his month(not on command) at the end of his round.
     * @pre player has ended his turn and has probably moved tiles
     * @post player has new month
     *
     */
    public void update_month(){this.month++;}

    public void sell_deal_card(int choice){
        deal_card c =((deal_card)(player_cards).get(choice));
        player_cards.remove(choice);
        money_addition(c.getSell_cost());
    }

    /**
     * This method is meant to be used to determine how many months each player has ahead of him to finish the game.Months in this case represent the times a player has to run through the board.
     *
     * @param
     * @return remaining_months
     * @post the number of months left for each player is calculated.
     */
   /* public int calculate_remaining_months(){
        return  m.getGeneral_month()-getMonth();
    }*/

    /**
     * This method is meant to be used to enable the player to buy a deal card.
     *
     * @param c
     * @pre player falls on a deal position
     * @post player buys card if he chooses to.
     */
    public void buyDealCard(deal_card c){
        this.money_subtraction(c.getBuy_cost());
        this.player_cards.add(c);
    }

    /**
     * This method is meant to allow player to pay rent.
     *
     * @param m
     * @pre player has to pay rent
     * @post rent is paid.
     */
    public void pay_loan(int m){
        this.money_subtraction(m);
        this.loan=this.loan-m;
    }

    /**
     * This method is meant to check if game is finished.
     *
     * @param m
     * @param p
     * @pre player has to pay rent
     * @post rent is paid.
     * @return
     */
    public boolean checkIfFinished(MyMonth m , Player p){
        setHasFinished(false);
        if( p.getMonth()==p.getOpponent().getMonth() && p.getMonth()==0){
            setHasFinished(true);
        }
       return  this.hasFinished ;
    }


}
