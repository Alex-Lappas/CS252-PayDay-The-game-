package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import mvc.controller.Controller;
import mvc.model.Player;
import mvc.model.card.*;
import mvc.model.position.*;
import static java.lang.System.exit;

/**
 *
 * @author AlexL
 */
public class View extends JFrame {

    Controller game;


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) Math.round(screenSize.getWidth()) - 200;        //a way of getting the resolution of screen the game runs on and always adjusting to it.
    private final int height = (int) Math.round(screenSize.getHeight()) - 200;

    private JLayeredPane player1, player2, jackPotPanel, tabletop;
    private ClassLoader cldr;

    private JButton DealCards, MailCards, GetLoan1, DealCards1, EndTurn1, Dice1, EndTurn2, GetLoan2, DealCards2, Dice2;

    private JTextField p1_info, p2_info, turn, info_header;
    private JTextField Money1Text, Money2Text, Loan1Text, Loan2Text, Bills1Text, Bills2Text, jackPotText, monthsLeft, command;

    private JLabel jackPotLabel, pawn1, PayDayImage, PlayerName1, PlayerName2, action, DiceImage1, DiceImage2;
    private JDesktopPane InfoBox;
    private JLayeredPaneExtension[] pawn_position;

    private JLayeredPaneExtension basic_panel;
    private URL imageURL;

    private Image image;
    private JDesktopPane[] position;
    ImageIcon ins;
    Image to_scale, rand_pawn;
    String randmess;
    ArrayList<String> cards1 = new ArrayList<>();
    ArrayList<String> cards2 = new ArrayList<>();

    /**
     * This is a ready method to scale images found here: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
     *
     * @return
     */
    private ImageIcon getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return new ImageIcon(resizedImg);

    }

    /**
     * Setter of variable randmess which is used to enable the view to print the command in the infobox
     */
    public void setRandmess(String randmess) {
        this.randmess = randmess;
    }

    /**
     * This is the default constructor of this class
     */
    public View() {
        game = new Controller();
        cldr = this.getClass().getClassLoader();
        player1 = new JDesktopPane();
        player2 = new JDesktopPane();

        tabletop = new JDesktopPane();
        pawn_position = new JLayeredPaneExtension[32];
        jackPotPanel = new JDesktopPane();
        jackPotText = new JTextField();
        jackPotLabel = new JLabel();

        InfoBox = new JDesktopPane();
        info_header = new JTextField("Info Box");
        info_header.setFont(new Font(null, Font.BOLD, 15));

        DiceImage1 = new JLabel();
        DiceImage2 = new JLabel();

        Money1Text = new JTextField();
        Loan1Text = new JTextField();
        Bills1Text = new JTextField();

        Loan2Text = new JTextField();
        Bills2Text = new JTextField();
        Money2Text = new JTextField();

        PayDayImage = new JLabel();

        PlayerName1 = new JLabel("Player 1");
        PlayerName1.setFont(new Font(null, Font.BOLD, 20));

        PlayerName2 = new JLabel("Player 2");
        PlayerName2.setFont(new Font(null, Font.BOLD, 20));

        p1_info = new JTextField("Info Box");
        p2_info = new JTextField("Info Box");
        p1_info.setFont(new Font(null, Font.BOLD, 15));
        p2_info.setFont(new Font(null, Font.BOLD, 15));
        turn = new JTextField();
        monthsLeft = new JTextField();
        command = new JTextField();

        DealCards = new JButton();
        MailCards = new JButton();

        GetLoan1 = new JButton("Get Loan");
        DealCards1 = new JButton("My Deal Cards");
        EndTurn1 = new JButton("End turn");
        Dice1 = new JButton("Roll Dice");

        EndTurn2 = new JButton("End turn");
        GetLoan2 = new JButton("Get Loan");
        DealCards2 = new JButton("My Deal Cards");
        Dice2 = new JButton("Roll Dice");

        position = new JDesktopPane[32];

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 102, 0)); //set using intellij color choice assistant.To be kept for the rest of the game
        this.setResizable(false);
        this.setTitle("PayDay");
        URL imageURL = cldr.getResource("resources/images/logo.png");
        assert imageURL != null;
        this.setIconImage(new ImageIcon(imageURL).getImage());
        this.setVisible(true);

        SetDetails();
    }

    /**
     * This is used to initialize all details used above.
     * @pre game has started
     * @post details are set
     */
    public void SetDetails() {

        DealCards.setSize(width * (2 / 16) - 40, height * 1 / 7);
        imageURL = cldr.getResource("resources/images/dealCard.png");
        assert imageURL != null;
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width * (2 / 16) - 20, height * 1 / 7 - 15, Image.SCALE_SMOOTH);
        DealCards.setIcon(new ImageIcon(image));

        MailCards.setSize(width * (2 / 16) - 50, height * 1 / 7);
        imageURL = cldr.getResource("resources/images/mailCard.png");
        assert imageURL != null;
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width * (2 / 16) - 20, height * 1 / 7 - 15, Image.SCALE_SMOOTH);
        MailCards.setIcon(new ImageIcon(image));

        DealCards.setEnabled(false);
        MailCards.setEnabled(false);

        imageURL = cldr.getResource("resources/images/bg_green.png");
        assert imageURL != null;
        image = new ImageIcon(imageURL).getImage();
        basic_panel = new JLayeredPaneExtension(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));

        this.setPreferredSize(new Dimension(width, height));
        this.add(basic_panel);

        imageURL = cldr.getResource("resources/images/logo.png");
        assert imageURL != null;
        to_scale = new ImageIcon(imageURL).getImage();
        PayDayImage.setIcon((Icon) getScaledImage(to_scale, width * 6 / 8, height * 1 / 5));
        PayDayImage.setBounds(0, 0, width * 6 / 8, height * 1 / 5);
        basic_panel.add(PayDayImage);

        player1.setSize(width * 2 / 8 - 50, height * 2 / 7);
        player1.setBounds((width * 6 / 8) + 30, 10, width * 2 / 8 - 50, height * 2 / 7);
        player1.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.YELLOW));

        player2.setSize(width * 2 / 8 - 50, height * 2 / 7);
        player2.setBounds((width * 6 / 8) + 30, height * 5 / 7 - 40, width * 2 / 8 - 50, height * 2 / 7 - 20);
        player2.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.cyan));

        MailCards.setBounds((width * 6 / 8) + 30, height * 1 / 2 + 20, width * 2 / 16 - 40, height * 1 / 10);
        DealCards.setBounds(width * 7 / 8 + 20, height * 1 / 2 + 20, width * 2 / 16 - 40, height * 1 / 10);


        basic_panel.add(DealCards);
        basic_panel.add(MailCards);

//start of players ---------------------------------------------------------------------------------------------------------------------------
        createPlayers();
//end of players---------------------------------------------------------------------------------------------------------------------------

//infobox stuff----------------------------------------------------------------------------------------------------------------------------
        createInfobox();

//infobox end---------------------------------------------------------------------------------------------------------------------------

//positions on the board---------------------------------------------------------------------------------------------------------------------------
       createTabletop();

        //including jackpot as a position
        jackpotPosition();

//end of positions---------------------------------------------------------------------------------------------------------------------------

//pawn details-------------------------------------------------------------------------------------------------------------------------------
        updatePawn(0, "Player1");
        updatePawn(0, "Player2");

//pawn end------------------------------------------------------------------------------------------------------------------------------------

//general functions of the game like action listeners and events------------------------------------------------------------------------------
        createActions();

    }
//-----------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Initialize jackpot
     * @pre game has started
     * @post initialize jackpot
     *
     */
    public void jackpotPosition(){
        jackPotText.setEditable(false);
        jackPotText.setOpaque(false);
        jackPotText.setHorizontalAlignment(JTextField.CENTER);
        jackPotText.setFont(new Font(null, Font.BOLD, 12));
        jackPotText.setForeground(Color.white);
        jackPotText.setBorder(null);
        jackPotPanel.setLayout(new BoxLayout(jackPotPanel, BoxLayout.Y_AXIS));
        jackPotPanel.setOpaque(false);
        imageURL = cldr.getResource("resources/images/jackpot.png");
        assert imageURL != null;
        Image randomOne = new ImageIcon(imageURL).getImage();
        ImageIcon ins = getScaledImage(randomOne, width * 1 / 9, height * 1 / 9);
        jackPotLabel.setIcon(ins);
        update_jackpot();
        jackPotPanel.add(jackPotLabel);
        jackPotPanel.add(jackPotText);
        JLabel nainai = new JLabel();
        tabletop.add(nainai);
        tabletop.add(jackPotPanel);
        basic_panel.add(tabletop);
    }

    /**
     * Initialize tabletop
     * @pre game has started
     * @post initialize tabletop
     *
     */
    public void createTabletop(){
        tabletop.setBounds(10, height * 1 / 5 + 10, PayDayImage.getWidth() - 10, height - PayDayImage.getHeight() - 80);
        tabletop.setSize(PayDayImage.getWidth() - 10, height - PayDayImage.getHeight() - 70);
        GridLayout grid = new GridLayout(0, 7);
        tabletop.setOpaque(false);
        tabletop.setLayout(grid);

        game.initialize_tabletop();
        for (int i = 0; i < 32; i++) {
            position[i] = new JDesktopPane();
            JTextField temp11 = new JTextField();//

            if (i == 0) {
                temp11.setText("Start");
            } else
                temp11.setText(game.tabletop[i].getDay() + " " + game.tabletop[i].getPosition_on_table());

            imageURL = cldr.getResource(game.tabletop[i].getImage());
            assert imageURL != null;
            Image allhmiaphoto = new ImageIcon(imageURL).getImage();

            pawn_position[i] = new JLayeredPaneExtension(allhmiaphoto.getScaledInstance(width * 1 / 9, height * 1 / 9, Image.SCALE_SMOOTH));

            pawn_position[i].setLayout(new FlowLayout());

            temp11.setEditable(false);
            temp11.setOpaque(true);
            temp11.setBackground(Color.YELLOW);
            temp11.setFont(new Font(null, Font.BOLD, 12));
            temp11.setMaximumSize(new Dimension(width * 1 / 9, 20));
            position[i].setLayout(new BoxLayout(position[i], BoxLayout.Y_AXIS));
            position[i].add(temp11);
            position[i].add(pawn_position[i]);
            tabletop.add(position[i]);
        }
    }

    /**
     * Initialize infobox
     * @pre game has started
     * @post initialize infobox
     *
     */

    public void createInfobox(){
        InfoBox.setBounds((width * 6 / 8) + 30, height * 1 / 3, width * 2 / 8 - 50, player1.getHeight() / 2 + 20);
        InfoBox.setSize(width * 2 / 8 - 50, player1.getHeight() / 2 + 20);
        InfoBox.setLayout(new BoxLayout(InfoBox, BoxLayout.Y_AXIS));
        InfoBox.setBackground(Color.WHITE);
        InfoBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black), BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())));

        info_header.setEditable(false);
        info_header.setBorder(null);
        info_header.setOpaque(false);

        turn.setEditable(false);
        turn.setOpaque(false);
        turn.setBorder(null);

        command.setEditable(false);
        command.setOpaque(false);
        command.setBorder(null);

        monthsLeft.setEditable(false);
        monthsLeft.setOpaque(false);
        monthsLeft.setBorder(null);

        updateInfoBox();
        InfoBox.add(info_header);
        InfoBox.add(monthsLeft);
        InfoBox.add(turn);
        InfoBox.add(command);
        basic_panel.add(InfoBox);
    }
    /**
     * Initialize players
     * @pre game has started
     * @post initialize players
     *
     */
    public void createPlayers(){
        updateInfoPlayers();

        //PLAYER1 INITIALIZE
        player1.setLayout(new BorderLayout());
        player1.add(PlayerName1, BorderLayout.NORTH);

        JDesktopPane temp0 = new JDesktopPane();
        temp0.setLayout(new BoxLayout(temp0, BoxLayout.Y_AXIS));

        Money1Text.setEditable(false);
        Money1Text.setOpaque(false);
        Money1Text.setBorder(null);
        temp0.add(Money1Text);

        Loan1Text.setEditable(false);
        Loan1Text.setOpaque(false);
        Loan1Text.setBorder(null);
        temp0.add(Loan1Text);

        Bills1Text.setOpaque(false);
        Bills1Text.setBorder(null);
        Bills1Text.setEditable(false);
        temp0.add(Bills1Text);
        temp0.add(DealCards1);
        player1.add(temp0, BorderLayout.CENTER);

        JDesktopPane temp1 = new JDesktopPane();
        temp1.setLayout(new BoxLayout(temp1, BoxLayout.X_AXIS));
        temp1.add(GetLoan1);
        temp1.add(EndTurn1);
        temp1.add(Dice1);
        player1.add(temp1, BorderLayout.SOUTH);
        DiceImage1.setBackground(Color.WHITE);
        DiceImage1.setBorder(null);

        dice_update(1, game.player1);
        player1.add(DiceImage1, BorderLayout.EAST);


        //PLAYER2 INITIALISE
        player2.setLayout(new BorderLayout());
        player2.add(PlayerName2, BorderLayout.NORTH);

        JDesktopPane temp2 = new JDesktopPane();
        temp2.setLayout(new BoxLayout(temp2, BoxLayout.Y_AXIS));

        Money2Text.setEditable(false);
        Money2Text.setOpaque(false);
        Money2Text.setBorder(null);
        temp2.add(Money2Text);

        Loan2Text.setEditable(false);
        Loan2Text.setOpaque(false);
        Loan2Text.setBorder(null);
        temp2.add(Loan2Text);

        Bills2Text.setOpaque(false);
        Bills2Text.setBorder(null);
        Bills2Text.setEditable(false);
        temp2.add(Bills2Text);

        temp2.add(DealCards2);
        player2.add(temp2, BorderLayout.CENTER);

        JDesktopPane temp3 = new JDesktopPane();

        temp3.setLayout(new BoxLayout(temp3, BoxLayout.X_AXIS));
        temp3.add(GetLoan2);
        temp3.add(EndTurn2);
        DiceImage2.setBackground(Color.WHITE);
        DiceImage2.setBorder(null);
        temp3.add(Dice2);
        dice_update(1, game.player2);
        player2.add(DiceImage2, BorderLayout.EAST);
        player2.add(temp3, BorderLayout.SOUTH);

        basic_panel.add(player1);
        basic_panel.add(player2);
    }

    /**
     * Initialize actions
     * @pre game has started
     * @post initialize actions
     *
     */
    public void createActions(){
        setRandmess("Let's play!");
        set_rounds();
        game.dealCardIn();
        game.MailCardIn();
        Dice1.addActionListener(new RollDice1());
        Dice2.addActionListener(new RollDice2());
        EndTurn1.addActionListener(new EndTurn1());
        EndTurn2.addActionListener(new EndTurn2());
        GetLoan1.addActionListener(new getLoan1());
        GetLoan2.addActionListener(new getLoan2());
        DealCards.addActionListener(new DealCardListener());
        MailCards.addActionListener(new MailCardListener());
        DealCards1.addActionListener(new MyDealCards1());
        DealCards2.addActionListener(new MyDealCards2());
        pack();
    }


    /**
     * setter for the action
     *
     * @param action
     */
    public void setAction(JLabel action) {
        this.action = action;
    }

    /**
     * setter for the remaining of the months of the game based on the leading player's position
     *
     * @param monthsLeft
     */
    public void setMonthsLeft(JTextField monthsLeft) {
        this.monthsLeft = monthsLeft;
    }

    /**
     * setter for the turn in info box
     *
     * @param turn
     */
    public void setTurn(JTextField turn) {
        this.turn = turn;
    }

    /**
     * This method is meant to be used to show the deal cards of the game
     *
     * @param
     * @param c
     */
    public int showDealCard(deal_card c) {
        Object[] options = {"Agorase", "Agnohse"};
        URL imageURL = cldr.getResource("resources/images/" + c.getImage()); //image
        assert imageURL != null;
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();
        int k = JOptionPane.showOptionDialog(this,
                c.getPopup_msg() + "\nΤιμή Αγοράς: " + c.getBuy_cost() + " Ευρώ \nΤιμή Πώλησης: " + c.getSell_cost() + " Ευρώ \n",
                "Symfwnia",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
        return k;
    }

    /**
     * This method is meant to be used in order to show the mail cards of the game
     *
     * @param
     * @param c
     */
    public int showMailCard(mail_card c ) {
        String title = "";
        String buttontext="";
        if (c instanceof PayTheNeighbor) {
             title= "Plhrwse to geitona";
             buttontext= "Plhrwse " + c.getMoney() + " Eurw sto geitona";
        } else if (c instanceof GetMoneyFromNeighbor) {
            title = "Pare lefta apo to geitona";
            buttontext = "Pare " + c.getMoney() + " Eurw apo ton antipalo";
        } else if (c instanceof Charity) {
            title = "Filanthropia";
            buttontext = "Plhrwse " + c.getMoney() + " Eurw sto jackpot";
        } else if (c instanceof Bill) {
            title = "Eksoflish logariasmou";
            buttontext = "Krata to logariasmo";
        } else if (c instanceof MoveToSpecifiedSpot) {
            title = "Metakinhsh se thesh Sumfwnias/Agorasth";
            buttontext = "Entaksei";
        } else if (c instanceof Advertisement) {
            title = "Diafhmish";
            buttontext= "Entaksei";
        }

        URL imageURL2;
        imageURL2 = cldr.getResource("resources/images/" + c.getImage());
        Image image = null;
        assert imageURL2 != null;
        image = new ImageIcon(imageURL2).getImage();//see the bug
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();
        Object[] options = {buttontext};
        int n = p.showOptionDialog(this,
               c.getPopup_msg(),
                title,
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);

        return n;
    }

    /**
     * This method is meant to be used to show the pop-up for the cryptocurrency event of the game
     */
    public int ShowCrypto() {

        JOptionPane q = new JOptionPane();
        URL imageURL1 = cldr.getResource("resources/images/crypto.jpg");
        assert imageURL1 != null;
        Image image2 = new ImageIcon(imageURL1).getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {"Πόνταρε στο κρυπτονόμισμα", "Παράβλεψε το ποντάρισμα"};

        int k = q.showOptionDialog(this,
                "Ποντάρισμα σε κρυπτονομίσματα",
                "Crypto Thursday", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);
        return k;
    }

    /**
     * This method is meant to be used in order to be triggered after the cryptocurrency event occurs and show the pop-up
     *
     * @pre cryptocurrency event has occurred
     * @post certain wanted pop-up is shown
     */
    public void chain_reaction_crypto(int outcome) {
        if (outcome == 0) {
            JOptionPane.showMessageDialog(null, "You lost !", "Crypto Thursday ", JOptionPane.INFORMATION_MESSAGE);
        }
        if (outcome == 1) {
            JOptionPane.showMessageDialog(null, "You got your money back !", "Crypto Thursday ", JOptionPane.INFORMATION_MESSAGE);
        }
        if (outcome == 2) {
            JOptionPane.showMessageDialog(null, "You doubled  your money!", "Crypto Thursday ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is meant to be used to show the pop-up for the football event of the game
     */
    public int showFootballGame() {
        JOptionPane q = new JOptionPane();
        URL imageURL1 = cldr.getResource("resources/images/Barcelona_Real.jpg");
        assert imageURL1 != null;
        Image image2 = new ImageIcon(imageURL1).getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {"Νίκη Μπαρτσελόνα", "Ισοπαλία", "Νίκη Ρεάλ", "Δέν θέλω να κάνω πρόβλεψη"};

        int k = q.showOptionDialog(this,
                "Ποδοσφαιρικός αγώνας Κυριακής",
                "Ποδοσφαιρικός αγώνας Κυριακής", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);
        return k;
    }


    /**
     * This method is meant to be used in order to be triggered after the football event occurs and show the pop-up
     *
     * @pre football event has occurred
     * @post certain wanted pop-up is shown
     */
    public void chain_reaction_football(int outcome) {
        if (outcome == 0) {
            JOptionPane.showMessageDialog(null, "You lost !", "Ποδοσφαιρικός αγώνας Κυριακής ", JOptionPane.INFORMATION_MESSAGE);
        }
        if (outcome == 2) {
            JOptionPane.showMessageDialog(null, "You doubled  your money!", "Ποδοσφαιρικός αγώνας Κυριακής ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is meant to be used as a way of updating the number of money in the jackpot visually
     *
     * @pre a change has occurred in the money of the jackpot
     * visual number is updated and changed
     */
    public void update_jackpot() {
        jackPotText.setText("Jackpot: " + game.jackpot.getJackpot() + " Euros");
        jackPotText.setEditable(false);
    }


    /**
     * This method is meant to be used to update the visual representation of the dice on the board
     *
     * @param dice_roll
     * @param p
     * @pre a new dice roll has taken place
     * @post visual dice is updated
     */
    public void dice_update(int dice_roll, Player p) {
        if (p.getPlayer_name().equals("Player1")) {
            imageURL = cldr.getResource("resources/images/dice-" + dice_roll + ".jpg");
            assert imageURL != null;
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            DiceImage1.setIcon(new ImageIcon(image));
        } else {
            imageURL = cldr.getResource("resources/images/dice-" + dice_roll + ".jpg");
            assert imageURL != null;
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            DiceImage2.setIcon(new ImageIcon(image));
            DiceImage2.setBorder(null);
        }
    }


    /**
     * This method is meant to be used to update the pawns of each player after a certain movement
     *
     * @pre player has rolled a die and is able to move
     * @post new position on board for player
     */
    public void updatePawn(int pos, String player_id) {
        pawn1 = new JLabel();
        if (player_id.equals("Player1")) {
            imageURL = cldr.getResource("resources/images/pawn_yellow.png");
        } else {
            imageURL = cldr.getResource("resources/images/pawn_blue.png");
        }
        assert imageURL != null;
        rand_pawn = new ImageIcon(imageURL).getImage();
        ins = getScaledImage(rand_pawn, 50, 50);
        this.pawn1.setIcon(ins);
        pawn_position[pos].add(pawn1);
        Graphics g1 = position[pos].getGraphics();
        position[pos].paintComponents(g1);
        position[pos].repaint();
    }

    /**
     * This method is meant to be used to update the information of both players at the same time
     *
     * @pre a change of information of the player has occurred
     * @post updated information
     */
    public void updateInfoPlayers() {
        Money1Text.setText("Money: " + game.player1.getMoney() + " Euro");
        Money2Text.setText("Money: " + game.player2.getMoney() + " Euro");
        Loan1Text.setText("Loan: " + game.player1.getLoan() + " Euro");
        Loan2Text.setText("Loan: " + game.player2.getLoan() + " Euro");
        Bills1Text.setText("Bills: " + game.player1.getBills() + " Euro");
        Bills2Text.setText("Bills: " + game.player2.getBills() + " Euro");
    }

    /**
     * This method is meant to be used to update the information of the infobox
     *
     * @pre a change of information of the player has occurred
     * @post new infobox
     */
    public void updateInfoBox() {
        System.out.println("Gyros"+game.rounds.getGeneral_month());
        System.out.println("pl1"+game.player1.getMonth());
        System.out.println("pl2"+game.player2.getMonth());
        if (game.player1.GetTurn()) {
            if ((game.rounds.getGeneral_month() - game.player1.getMonth()) == 1) {
                monthsLeft.setText((game.rounds.getGeneral_month() - game.player1.getMonth()) + " Month Left");
            } else monthsLeft.setText((game.rounds.getGeneral_month() - game.player1.getMonth()) + " Months Left");

            turn.setText("Turn: " + game.player1.getPlayer_name());
        } else {
            if ((game.rounds.getGeneral_month() - game.player2.getMonth()) == 1) {
                monthsLeft.setText((game.rounds.getGeneral_month() - game.player2.getMonth()) + " Month Left");
            } else monthsLeft.setText((game.rounds.getGeneral_month() - game.player2.getMonth()) + " Months Left");
            turn.setText("Turn: " + game.player2.getPlayer_name());
        }
        command.setText("-->" + this.randmess);
    }

    /**
     * This method is meant to be used to allow the player to determine the duration of the game
     *
     * @pre game starts
     * @post duration is set
     */
    public void set_rounds() {
        ChooseMonths c = new ChooseMonths();
        game.rounds.setGeneral_month(c.chooseMonthDialog());
        monthsLeft.setText(String.valueOf((game.rounds.getGeneral_month() - game.player1.getMonth()) + 1));
        updateInfoBox();
    }

    /**
     * This method is meant to be used top show the winning player after the game has finished
     *
     * @pre game has finished
     * @post winner is shown
     */
    public void showWinningMessage(Player p1 , Player p2) {
        game.Score1= p1.getMoney()-p1.getLoan()-p1.getBills();
        game.Score2= p2.getMoney()-p2.getLoan()-p2.getBills();
        if (game.Score1 > game.Score2){
            JOptionPane.showMessageDialog(null, "Player1 wins!", "Results", JOptionPane.INFORMATION_MESSAGE);
        }else if(game.Score2 > game.Score1){
            JOptionPane.showMessageDialog(null, "Player2 wins!", "Results", JOptionPane.INFORMATION_MESSAGE);
        }else  JOptionPane.showMessageDialog(null, "We have a draw!", "Results", JOptionPane.INFORMATION_MESSAGE);
        exit(1);
    }

    //implementing all action listeners for the buttons at this point-----------------------------------------------------------------
    private class RollDice1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.player1.GetTurn()) {
                if (game.player1.getPosition() == game.player2.getPosition()) {
                    pawn_position[game.player1.getPosition()].removeAll();
                    pawn_position[game.player1.getPosition()].repaint();
                    updatePawn(game.player2.getPosition(), "Player2");
                } else {
                    pawn_position[game.player1.getPosition()].removeAll();
                    pawn_position[game.player1.getPosition()].repaint();
                }
                game.player1.Roll_the_dice(game.jackpot);
                int die= game.player1.getDice_roll();
                if (game.player1.getDice_roll() == 6) {
                    update_jackpot();
                }
                int pos=game.player1.movePosition(die);
                actionOnPosition(game.player1.getPosition(), game.player1);
                dice_update(game.player1.getDice_roll(), game.player1);
                if(game.player2.getPosition()!=31) {
                    updatePawn(pos, "Player1");
                }                                       //to avoid weird duplication bug for pawns at payday
                pawn_position[31].removeAll();
                pawn_position[31].repaint();
                updateInfoBox();
                updateInfoPlayers();
            }
        }
    }

    private class RollDice2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.player2.GetTurn()) {
                if (game.player1.getPosition() == game.player2.getPosition()) {
                    pawn_position[game.player2.getPosition()].removeAll();
                    pawn_position[game.player2.getPosition()].repaint();
                    updatePawn(game.player1.getPosition(), "Player1");
                } else {
                    pawn_position[game.player2.getPosition()].removeAll();
                    pawn_position[game.player2.getPosition()].repaint();
                }
                game.player2.Roll_the_dice(game.jackpot);
                int die= game.player2.getDice_roll();
                if (game.player2.getDice_roll() == 6) {
                    update_jackpot();
                }
                int pos=game.player2.movePosition(die);
                actionOnPosition(game.player2.getPosition(), game.player2);
                dice_update(game.player2.getDice_roll(), game.player2);
                if(game.player2.getPosition()!=31) {
                    updatePawn(pos, "Player2");
                }
                pawn_position[31].removeAll();
                pawn_position[31].repaint();        //to avoid weird duplication bug for pawns at payday
                updateInfoBox();
                updateInfoPlayers();
            }
        }
    }

    class EndTurn1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.player1.GetTurn()) {
                game.player1.setTurn(false);
                game.player1.opponent.setTurn(true);
                updateInfoBox();
            }
        }
    }

    class EndTurn2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.player2.GetTurn()) {
                game.player2.setTurn(false);
                game.player2.opponent.setTurn(true);
                updateInfoBox();
            }
        }
    }

    private class getLoan1 implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.player1.GetTurn()) {
                loanDialogue d = new loanDialogue();
                int loan;
                loan = d.GetLoanDialog();
                game.player1.get_loan(loan);
                updateInfoPlayers();
                updateInfoBox();
            }
        }
    }

    private class getLoan2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.player2.GetTurn()) {
                loanDialogue d = new loanDialogue();
                int loan;
                loan = d.GetLoanDialog();
                game.player2.get_loan(loan);
                updateInfoPlayers();
                updateInfoBox();
            }
        }
    }


    private class MyDealCards1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i= 0; i<game.player1.getPlayer_cards().size();++i){
                String tmp=(game.player1.player_cards.get(i).getPopup_msg()) + "\n";
              if(!cards1.contains(tmp)){
                  cards1.add(tmp);
              }
            }
            JOptionPane.showMessageDialog(null, cards1, "Your cards player1:", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class MyDealCards2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i= 0; i<game.player2.getPlayer_cards().size();++i){
                String tmp=(game.player2.player_cards.get(i).getPopup_msg()) + "\n";
                if(!cards2.contains(tmp)){
                    cards2.add(tmp);
                }
            }
            JOptionPane.showMessageDialog(null, cards2, "Your cards player1:", JOptionPane.INFORMATION_MESSAGE);
        }
    }




    private class DealCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int choice;
            deal_card karta = game.board.TakeDealCard();
            choice = showDealCard(karta);
            if (game.player1.GetTurn() && game.tabletop[game.player1.getPosition()] instanceof deal_position) {
                ((deal_position) game.tabletop[game.player1.getPosition()]).setChoice(choice == 0);
                ((deal_position) game.tabletop[game.player1.getPosition()]).getCards(game.board, game.player1,karta);
            } else {
                if (game.player2.GetTurn() && game.tabletop[game.player2.getPosition()] instanceof deal_position) {
                    ((deal_position) game.tabletop[game.player2.getPosition()]).setChoice(choice == 0);
                    ((deal_position) game.tabletop[game.player2.getPosition()]).getCards(game.board, game.player2,karta);
                }
            }
            updateInfoPlayers();
            DealCards.setEnabled(false);
        }
    }

    private class MailCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice;
            mail_card karta;
            if(game.player1.GetTurn()){
                karta=game.board.TakeMailCard(game.player1);
                choice = showMailCard(karta);
                if(karta instanceof Advertisement){
                    karta.player_action(game.player1);
                }
                if(karta instanceof  Bill){
                  karta.player_action(game.player1);
                }
                if(karta instanceof  Charity){
                    ((Charity) karta).action1(game.player1,game.jackpot);
                }
                if(karta instanceof  GetMoneyFromNeighbor){
                    karta.player_action(game.player1);
                }
                if(karta instanceof PayTheNeighbor){
                    karta.player_action(game.player1);
                }
                if(karta instanceof MoveToSpecifiedSpot){
                    int ep;
                    ep=((mail_position)game.tabletop[game.player1.getPosition()]).getPick_number();
                    karta.player_action(game.player1);
                }
            }else{
                karta=game.board.TakeMailCard(game.player2);
                choice = showMailCard(karta);
                if(karta instanceof Advertisement){
                    karta.player_action(game.player2);
                }
                if(karta instanceof  Bill){
                    karta.player_action(game.player2);
                }
                if(karta instanceof  Charity){
                    ((Charity) karta).action1(game.player2, game.jackpot);
                }
                if(karta instanceof  GetMoneyFromNeighbor){
                    karta.player_action(game.player2);
                }
                if(karta instanceof PayTheNeighbor){
                    karta.player_action(game.player2);
                }
                if(karta instanceof MoveToSpecifiedSpot){
                    int ep;
                    ep=((mail_position)game.tabletop[game.player2.getPosition()]).getPick_number();
                    karta.player_action(game.player2);
                }
            }
            updateInfoPlayers();
            update_jackpot();
        }
    }

        /**
         * This method is meant to be used to execute the action for the player in the certain position he is in
         *
         * @param p
         * @param pos
         * @pre player is on a tile with an action(every tile besides start)
         * @post action is executed
         */
        public void actionOnPosition(int pos, Player p) {
            if (game.tabletop[pos] instanceof deal_position) {
                setRandmess("Pick a deal card");
                DealCards.setEnabled(true);
            }else DealCards.setEnabled(false);
            if (game.tabletop[pos] instanceof mail_position) {
                if (((mail_position) game.tabletop[pos]).getPick_number() == 2) {
                    setRandmess("Pick 2 mail cards");
                    MailCards.setEnabled(true);
                } else{
                    setRandmess("Pick 1 mail card");
                    MailCards.setEnabled(true);
                }
            }else MailCards.setEnabled(false);
            if (game.tabletop[pos] instanceof buyer) {
                int choice1 = 0;
                setRandmess("Sell this guy something");
                if (game.player1.GetTurn()) {
                    ArrayList<Card> tmp = game.player1.getPlayer_cards();
                    if(!tmp.isEmpty()) {
                        int max = ((deal_card) tmp.get(0)).getSell_cost();
                        for (int i = 1; i < tmp.size(); ++i) {
                            if (max < ((deal_card) tmp.get(0)).getSell_cost()) {
                                max = ((deal_card) tmp.get(0)).getSell_cost();
                                choice1 = i;
                            }
                        }
                        ((buyer) game.tabletop[pos]).performAction(game.player1, choice1);
                    }
                } else {
                    ArrayList<Card> tmp = game.player2.getPlayer_cards();
                    if(!tmp.isEmpty()) {
                        int max = ((deal_card) tmp.get(0)).getSell_cost();
                        for (int i = 1; i < tmp.size(); ++i) {
                            if (max < ((deal_card) tmp.get(0)).getSell_cost()) {
                                max = ((deal_card) tmp.get(0)).getSell_cost();
                                choice1 = i;
                            }
                        }
                        ((buyer) game.tabletop[pos]).performAction(game.player2, choice1);
                    }
                }
            }
            if (game.tabletop[pos] instanceof Casino) {
                int die=0;
                setRandmess("Welcome to the Casino");
                if(game.player1.GetTurn()){
                    die=game.player1.getDice_roll();
                    ((Casino)game.tabletop[pos]).performAction1(game.player1,die,game.jackpot);
                    JOptionPane.showMessageDialog(null, "Casino time! You entered with a: " +die , "Casino", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    die=game.player2.getDice_roll();
                    ((Casino)game.tabletop[pos]).performAction1(game.player2,die,game.jackpot);
                    JOptionPane.showMessageDialog(null, "Casino time! You entered with a: " +die , "Casino", JOptionPane.INFORMATION_MESSAGE);

                }
                updateInfoPlayers();
                update_jackpot();
            }
            if (game.tabletop[pos] instanceof lottery_ticket) {
                setRandmess("Feeling lucky?");
                int[] choice = new int[2];
                lotteryDialogue lot = new lotteryDialogue();
                if(game.player1.GetTurn()){
                    choice= lot.lotteryDialog(game.player1);
                    ((lottery_ticket)game.tabletop[pos]).performAction(game.player1,
                            choice[0],
                            choice[1]);
                }else{
                    choice= lot.lotteryDialog(game.player2);
                    ((lottery_ticket)game.tabletop[pos]).performAction(game.player2,
                            choice[0],
                            choice[1]);
                }
                JOptionPane.showMessageDialog(null, "Lottery! Winning number is: "+ ((lottery_ticket) game.tabletop[pos]).getRand() ," Lottery!", JOptionPane.INFORMATION_MESSAGE);
                updateInfoPlayers();
            }
            if (game.tabletop[pos] instanceof PayDay) {
                setRandmess("It's PayDay!!");
                PayDayDialogue pdd = new PayDayDialogue();
                if(game.player1.GetTurn()){
                    ((PayDay)game.tabletop[pos]).performAction(game.player1, game.rounds, game.board);
                    if(game.player1.getLoan()>0){
                        pdd.payday(game.player1);
                    }
                }else{  ((PayDay)game.tabletop[pos]).performAction(game.player2, game.rounds, game.board);
                    if(game.player2.getLoan()>0){
                        pdd.payday(game.player2);
                    }
                }
                updateInfoPlayers();
                if(game.player1.getHasFinished()&&game.player2.getHasFinished()){
                    updateInfoPlayers();
                    showWinningMessage(game.player1,game.player2);
                }
            }
            if (game.tabletop[pos] instanceof RadioContest) {
                setRandmess("A contest on the radio has appeared");
                int die1, die2;
                die1=game.player1.Roll_the_dice_auto();
                die2=game.player2.Roll_the_dice_auto();
                while(die1==die2){ die1=game.player1.Roll_the_dice_auto();
                    die2=game.player2.Roll_the_dice_auto();}
                ((RadioContest)game.tabletop[pos]).performAction(game.player1,die1,die2);
                updateInfoPlayers();
                JOptionPane.showMessageDialog(null, "Radio Contest Player1 rolled a: "+ die1 + " Player2 rolled a: "+ die2, "Radio Contest!", JOptionPane.INFORMATION_MESSAGE);
                updateInfoPlayers();
            }
            if (game.tabletop[pos] instanceof start) {
                setRandmess("Let's Play!");
                updateInfoPlayers();
            }
            if (game.tabletop[pos] instanceof sweep) {
                setRandmess("Sweeeeeeeeeeeeep!");
                int die;
                if(game.player1.GetTurn()){
                    die=game.player1.Roll_the_dice_auto();
                    ((sweep)game.tabletop[pos]).performAction(game.player1,die);
                    JOptionPane.showMessageDialog(null, "Sweepstakes! you rolled a: "+ die, "Sweepstakes!", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    die=game.player2.Roll_the_dice_auto();
                    ((sweep)game.tabletop[pos]).performAction(game.player2,die);
                    JOptionPane.showMessageDialog(null, "Sweepstakes! you rolled a: "+ die, "Sweepstakes!", JOptionPane.INFORMATION_MESSAGE);
                }
                updateInfoPlayers();
            }
            if (game.tabletop[pos] instanceof YardSale) {
                setRandmess("A yard Sale! What a deal");
                int die;
                if(game.player1.GetTurn()){
                    die=game.player1.Roll_the_dice_auto();
                    ((YardSale)game.tabletop[pos]).performAction1(game.player1,game.board,die);
                    JOptionPane.showMessageDialog(null, "Yard sale! you rolled a: "+ die, "Yard sale!", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    die=game.player2.Roll_the_dice_auto();
                    ((YardSale)game.tabletop[pos]).performAction1(game.player2,game.board,die);
                    JOptionPane.showMessageDialog(null, "Yard sale! you rolled a: "+ die, "Yard sale!", JOptionPane.INFORMATION_MESSAGE);
                }
                updateInfoPlayers();
            }
            if (game.tabletop[pos].checkThursday()) {
                int choice = ShowCrypto();
                game.board.crypto(p, choice);
                chain_reaction_crypto(game.board.getOutcome());
                updateInfoPlayers();
            }
            if (game.tabletop[pos].checkSunday()) {
                int choice = showFootballGame();
                game.board.football(p, choice);
                chain_reaction_football(game.board.getOutcome());
                updateInfoPlayers();
            }
        }
    }
