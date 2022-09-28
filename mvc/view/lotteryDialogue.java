package mvc.view;

import mvc.model.Player;
import javax.swing.*;

 public class lotteryDialogue{
    /**
     *
     * dialogue box to give the player the choice of the lottery number.
     */
    public int[] lotteryDialog(Player p) {
        int[] player_choices = new int[2];
        JFrame frame = new JFrame();
        Object[] options = {"1", "2", "3", "4" ,"5","6"};
        String temp = (String) JOptionPane.showInputDialog(frame, "lottery! Choose a number " + p.getPlayer_name(), "Choose a number", JOptionPane.PLAIN_MESSAGE,null, options, "1");
        player_choices[0]= Integer.parseInt(temp);

        Object[] options1 = new Object[5];
        for(int i= 0; i<player_choices[0]-1; ++i){
            options1[i]=String.valueOf(i+1);
        }
        for(int j=player_choices[0]-1;j<5;++j){
            options1[j]=String.valueOf(j+2);
        }

        String temp1 = (String) JOptionPane.showInputDialog(frame, "lottery! Choose a number " + p.getOpponent().getPlayer_name(), "Choose a number", JOptionPane.PLAIN_MESSAGE,null, options1, "1");
        player_choices[1]=Integer.parseInt(temp1);

        return  player_choices;
    }
}


