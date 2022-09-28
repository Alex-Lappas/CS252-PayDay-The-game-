package mvc.model.board;

import mvc.model.Player;
import mvc.model.card.Bill;
import mvc.model.card.Card;
import mvc.model.card.deal_card;
import mvc.model.card.mail_card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *It is important to understand that this project is about a board-game with said and applied to the coding rules.Handling exceptions is thus unnecessary as input and behavior is 100% determined during coding.
 *If I need to handle a certain case where an output similar to an error message or exception is needed to be used, I am going to use a custom error message.This is tags considering exceptions or not included in these javadocs.
 * @author AlexL
 */

public class Board {
    String current_day;
    int current_dice_roll;
    public ArrayList<deal_card> dealCards;
    public ArrayList<mail_card> mailCards;
    public ArrayList<Card> played_cards;
    ArrayList<Card> trash;
    private int outcome=-1; //0=xaneis , 1=tipota ,2=kerdizeis

    /**
     *Setter for the outcome
     * @param outcome
     *
     */
    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }


    /**
     *Getter for the outcome
     * @return outcome
     *
     */
    public int getOutcome() {
        return outcome;
    }

    /**
     *Setter for the current dice roll
     * @param current_dice_roll
     *
     */
    public void setCurrent_dice_roll(int current_dice_roll) {
        this.current_dice_roll = current_dice_roll;
    }

    /**
     *Setter for the played_cards
     * @param played_cards
     *
     */
    public void setPlayed_cards(ArrayList<Card> played_cards) {
        this.played_cards = played_cards;
    }

    /**
     *Getter for the played_cards
     * @return played_cards
     *
     */
    public ArrayList<Card> getPlayed_cards() {
        return played_cards;
    }

    /**
     *Getter for the current_dice_roll
     * @return current_dice_roll
     *
     */
    public int getCurrent_dice_roll() {
        return current_dice_roll;
    }

    /**
     *Getter for the current_day
     * @return current_day
     *
     */
    public String getCurrent_day() {
        return current_day;
    }

    /**
     *Setter for the string current day
     * @param current_day
     *
     */
    public void setCurrent_day(String current_day) {
        this.current_day = current_day;
    }

    /**
     *Getter for the array list DealCards
     * @return dealCards
     *
     */
    public ArrayList<deal_card> getDealCards() {
        return dealCards;
    }

    /**
     *Setter for the array list DealCards
     * @param dealCards
     *
     */
    public void setDealCards(ArrayList<deal_card> dealCards) {
        this.dealCards = dealCards;
    }

    /**
     *Getter for the array list mailCards
     * @return  mailCards
     *
     */
    public ArrayList<mail_card> getMailCards() {
        return mailCards;
    }


    /**
     *Setter for the array list mailCards
     * @param mailCards
     *
     */
    public void setMailCards(ArrayList<mail_card> mailCards) {
        this.mailCards = mailCards;
    }

    /**
     *Adds a mail card c to the array list mail cards
     * @post added card to the array list mail cards
     * @param c
     *
     */
    public void addMailCard(mail_card c){
        mailCards.add(c);
    }

    /**
     *Adds a deal card c to the array list deal cards
     * @post added card to the array list deal cards
     * @param c
     *
     */
    public void addDealCard(deal_card c){
       dealCards.add(c);
    }

    /**
     * Plays the card then removes it from stack ort adds it to personal deck of the player. Population of said card is decreased by one.
     * @post card is played and it is no longer available
     * @param c
     */
    public void play_card(Card c){
        played_cards.add(c);
    }

    /**
     *This method is specifically designed to be applied to deal cards as they are going to be the ones that the player is going to choose to buy and keep in his deck or deny
     * @post player denies card and card is removed from the game card is removed from its stack
     * @post player buys card and adds it to his personal deck and then sells for probable income. Card is removed from its stack
     *
     */
    public deal_card TakeDealCard(){
        if(dealCards.isEmpty()) {
            replace_cards(this.played_cards);
        }
        deal_card C=dealCards.get(0);
        dealCards.remove(0);
        return C;
    }

    /**
     *This method is specifically designed to be applied to mail cards as they are going to be the ones that the player is going to choose to buy and keep in his deck or deny
     * @post the game card is removed from its stack
     * @post player buys card and adds it to his personal deck and then sells for probable income. Card is removed from its stack
     * @param p
     */
    public mail_card TakeMailCard(Player p){
        if(mailCards.isEmpty()) {
            replace_cards(this.played_cards);
        }
       mail_card C=mailCards.get(0);
       mailCards.remove(0);
       if(C instanceof Bill) {
           p.player_cards.add(C);
       }else play_card(C);
       return C;
    }

    public void replace_cards(ArrayList<Card> played_cards){

            while (!played_cards.isEmpty()) {
                if (played_cards.get(0) instanceof deal_card) {
                    addDealCard((deal_card) played_cards.get(0));

                } else
                    addMailCard((mail_card) played_cards.get(0));
                played_cards.remove(0);
            }

            long s=System.nanoTime();
            Collections.shuffle(dealCards,new Random(s));
           Collections.shuffle(mailCards,new Random(s)); //shuffle cards
    }


    /**
     *This method is meant to enable the player located on a Sunday tile to bet on a said football game.Depending on his dice roll and his personal bet choice,the player is either rewarded with double his bet money or met with a total loss of said money.
     * @post outcome of the football game determines rise or loss of money for the player.
     * @param p
     * @param choice
     */
   public void football(Player p,int choice){
        p.money_subtraction(500);
        int dice_roll= (int)((Math.random() * (7 - 1)) + 1);
        if(choice==0&& (dice_roll==1||dice_roll==2)){
                 setOutcome(2);
                 p.money_addition(1000);
        }else if(choice==1&& (dice_roll==3||dice_roll==4)){
           setOutcome(2);
           p.money_addition(1000);
       }else if(choice==2&& (dice_roll==5||dice_roll==6)){
           setOutcome(2);
           p.money_addition(1000);
       }else if(choice==3){
            setOutcome(1);
            p.money_addition(500);
        }else setOutcome(0);
   }


    /**
     *This method allows the player to bet on the rise or fall of cryptocurrency.Depending on the dice roll and the player's own personal bet choice he can be met with either 0 income,loss of his already placed in bet money or doubling that and gaining income.
     * @post rise, idleness , or fall in player's bank account
     * @param p
     * @param choice
     * @param p
     */
    public int crypto(Player p,int choice){
        this.setOutcome(-1);
        if (choice==0){
            p.money_subtraction(300);
            int dice_roll= (int)((Math.random() * (7 - 1)) + 1);
            if(dice_roll<=2) {
                this.setOutcome(0);
            }
            if(dice_roll==3||dice_roll==4){
                this.setOutcome(1);
                p.money_addition(300);
            }
            if(dice_roll==5||dice_roll==6){
                this.setOutcome(2);
                p.money_addition(600);
            }
        }
        return outcome;
    }
}


