package mvc.view;

import mvc.model.Player;
import javax.swing.*;
import java.util.ArrayList;

public class PayDayDialogue {
    JFrame frame = new JFrame();
    int counter;
    ArrayList<Integer> options_help= new ArrayList<>();
    public void payday(Player p) {
        if(p.getMoney()<p.getLoan()) {
            if(p.getMoney()<1000) {
                JOptionPane.showMessageDialog(null, "Not sufficient money to pay loan.", "sorry", JOptionPane.INFORMATION_MESSAGE);
            }
            if(p.getMoney()>1000&&p.getMoney()<p.getLoan()){
                for(int i=1000; i<=p.getMoney();i+=1000){
                    options_help.add(i);
                }
                counter= options_help.size();
                Object[] options = new Object[counter];
                for(int i=0;i<counter; ++i ){
                    options[i]= String.valueOf(options_help.get(i));
                }
                String money = (String) JOptionPane.showInputDialog(frame, " Choose a number to pay!" , "Choose a number", JOptionPane.PLAIN_MESSAGE,null, options, "1");
                p.pay_loan(Integer.parseInt(money));
            }
        }else if(p.getMoney()>p.getLoan()){
            for(int i=1000; i<=p.getLoan();i+=1000){
                options_help.add(i);
            }
            counter= options_help.size();
            Object[] options = new Object[counter];
            for(int i=0;i<counter; ++i ){
                options[i]= String.valueOf(options_help.get(i));
            }
            String money = (String) JOptionPane.showInputDialog(frame, " Choose a number to pay!" , "Choose a number", JOptionPane.PLAIN_MESSAGE,null, options, "1");
            p.pay_loan(Integer.parseInt(money));
        }
    }
}