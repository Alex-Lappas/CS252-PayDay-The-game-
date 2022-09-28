package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.Player;
import mvc.model.board.Board;
import mvc.model.board.Jackpot;
import mvc.model.board.MyMonth;
import mvc.model.card.*;
import mvc.model.position.*;

/**
 *
 * @author AlexL
 */



public class Controller {

    ClassLoader cldr;
    public Position[] tabletop;
    public Player player1;
    public Player player2;
    public Jackpot jackpot;
    public Board board;
    public MyMonth rounds;
    public boolean GameStarted;
    public int Score1;
    public int Score2;

    /**
     * This method is meant to be used to  randomize the Position array . Find it here : https://www.delftstack.com/howto/java/shuffle-an-array-in-java/
     */
    static void randomize(Position[] array) {
        Random rd = new Random();

        for (int i = 31 - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = rd.nextInt(i + 1);
            if (j == 0) continue; //to avoid randomizing the start Position

            Position temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * This method is meant to be used to initialize the game's tabletop and randomize it
     */
    public void initialize_tabletop() {
        tabletop = new Position[32];
        tabletop[0] = new start("resources/images/start.png");
        tabletop[31] = new PayDay("resources/images/pay.png");
        for (int i = 1; i < 5; ++i) {
            tabletop[i] = new mail_position(0, Position.Days.Thursday, "resources/images/mc1.png", 1);

        }
        for (int i = 5; i < 9; ++i) {
            tabletop[i] = new mail_position(0, Position.Days.Thursday, "resources/images/mc2.png", 2);
        }
        for (int i = 9; i < 14; ++i) {
            tabletop[i] = new deal_position(0, Position.Days.Thursday, "resources/images/deal.png", false);

        }
        for (int i = 14; i < 16; ++i) {
            tabletop[i] = new sweep(0, Position.Days.Thursday, "resources/images/sweep.png");

        }
        for (int i = 16; i < 19; ++i) {
            tabletop[i] = new lottery_ticket(0, Position.Days.Thursday, "resources/images/lottery.png");

        }
        for (int i = 19; i < 21; ++i) {
            tabletop[i] = new RadioContest(1, Position.Days.Thursday, "resources/images/radio.png");

        }
        for (int i = 21; i < 27; ++i) {
            tabletop[i] = new buyer(0, Position.Days.Friday, "resources/images/buyer.png");

        }
        for (int i = 27; i < 29; ++i) {
            tabletop[i] = new Casino(0, Position.Days.Thursday, "resources/images/casino.png");

        }
        for (int i = 29; i < 31; ++i) {
            tabletop[i] = new YardSale(0, Position.Days.Thursday, "resources/images/yard.png");
        }

        tabletop[0].setPosition_on_table(0);
        tabletop[31].setDay(Position.Days.Wednesday);
        tabletop[31].setPosition_on_table(31);

        randomize(tabletop);

        for (int i = 1; i < 31; i++) {
            tabletop[i].setPosition_on_table(i);
            switch (i % 7) {
                case 0:
                    tabletop[i].setDay(Position.Days.Sunday);
                    break;
                case 1:
                    tabletop[i].setDay(Position.Days.Monday);
                    break;
                case 2:
                    tabletop[i].setDay(Position.Days.Tuesday);
                    break;
                case 3:
                    tabletop[i].setDay(Position.Days.Wednesday);
                    break;
                case 4:
                    tabletop[i].setDay(Position.Days.Thursday);
                    break;
                case 5:
                    tabletop[i].setDay(Position.Days.Friday);
                    break;
                case 6:
                    tabletop[i].setDay(Position.Days.Saturday);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This is the constructor of this class
     */
    public Controller() {
        rounds= new MyMonth();
        this.GameStarted=true;
        cldr = this.getClass().getClassLoader();
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        player1.player_cards = new ArrayList<Card>();
        player2.player_cards= new ArrayList<Card>();
        tabletop = new Position[32];
        board = new Board();
        board.played_cards = new ArrayList<>();
        board.dealCards = new ArrayList<deal_card>();
        board.mailCards = new ArrayList<mail_card>();
        jackpot = new Jackpot();
        randomTurnGen();
    }


    /**
     * This method is given to us in order to be able to read from the CSV files.
     */
    public String[][] readFile(String path, String type) {
        String[][] mailCards = new String[48][4];
        String[][] dealCards = new String[20][8];

        BufferedReader br = null;
        String sCurrentLine;
        try {
            String fullPath = cldr.getResource(path).getPath();
            br = new BufferedReader(new FileReader(fullPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        int splitCount = 0;
        HashMap<Integer, String> domainsMap = new HashMap<>();
        try {
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (type.equals("Mail")) {
                    mailCards[count++] = sCurrentLine.split(",");
                } else {

                    dealCards[count++] = sCurrentLine.split(",");
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (type.equals("Mail")) {
            return mailCards;
        } else {
            return dealCards;
        }
    }

    /**
     * This method is meant to be used in order to check if the game has finished or not
     */
    public boolean checkIfGameFinished() {
        return true;
    }




    /**
     * This method is meant to be used to initialize the stack of deal cards
     * @pre player is on a mail card tile and gets a mail card
     * @post mail cards are initialized
     */
    public void MailCardIn() {
        String[][] mailCards = readFile("resources/mailCards.csv", "Mail");
        for (int i = 0; i < 48; i++) {
            if (Objects.equals(mailCards[i][1], "Advertisement"))
                board.mailCards.add(new Advertisement(mailCards[i][2], mailCards[i][5], Integer.parseInt(mailCards[i][4])));
            else if (Objects.equals(mailCards[i][1], "Bill"))
                board.mailCards.add(new Bill(mailCards[i][2], mailCards[i][5], Integer.parseInt(mailCards[i][4])));
            else if (Objects.equals(mailCards[i][1], "Charity"))
                board.mailCards.add(new Charity(mailCards[i][2], mailCards[i][5], Integer.parseInt(mailCards[i][4])));
            else if (Objects.equals(mailCards[i][1], "PayTheNeighbor"))
                board.mailCards.add(new PayTheNeighbor(mailCards[i][2], mailCards[i][5], Integer.parseInt(mailCards[i][4])));
            else if (Objects.equals(mailCards[i][1], "MadMoney"))
                board.mailCards.add(new GetMoneyFromNeighbor(mailCards[i][2], mailCards[i][5], Integer.parseInt(mailCards[i][4])));
            else if (Objects.equals(mailCards[i][1], "MoveToDealBuyer"))
                board.mailCards.add(new MoveToSpecifiedSpot(mailCards[i][2], mailCards[i][5]));


            Collections.shuffle(board.mailCards); //https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/
        }
    }

        /**
         * This method is meant to be used to initialize the stack of deal cards
         * @pre game has started
         * @post deal cards are initialized
         */
        public void dealCardIn(){
            String[][] dealCards = readFile("resources/dealCards.csv", "Deal");

            for (int i = 0; i < 20; i++) {
                int buy_cost = Integer.parseInt(dealCards[i][3]);
                int sell_cost = Integer.parseInt(dealCards[i][4]);
                String mess = dealCards[i][2];
                String img = dealCards[i][5];
                board.dealCards.add(new deal_card(mess, img, buy_cost, sell_cost));
            }
            Collections.shuffle(board.dealCards);
        }

    /**
     * This method is meant to be used to generate a random boolean value to produce a random turn
     * @pre game has started
     * @post a player starts randomly
     */
        public void randomTurnGen(){
            Random rd = new Random(); // creating Random  object https://www.tutorialspoint.com/generate-random-boolean-in-java
            boolean temp = rd.nextBoolean();
            player1.setTurn(temp);
            player1.getOpponent().setTurn(!temp);
        }
}



