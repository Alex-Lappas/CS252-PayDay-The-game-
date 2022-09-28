package mvc.view;

import javax.swing.*;

public class loanDialogue {
    /**
     *This method is meant to be used to create a visual menu for the get loan button when the player willingly gets a loan .This menu is going to present him some choices and he is going to be able to choose one
     * @pre player chooses to get a loan
     * @post player is presented with the choice menu and gets a loan
     */
    public static int GetLoanDialog() {
        JFrame frame = new JFrame();
        Object[] options = {"1000", "2000", "3000", "5000" ,"10000"};
        String temp = (String) JOptionPane.showInputDialog(frame, "Choose amount of loan", "Loan to get", JOptionPane.PLAIN_MESSAGE,null, options, "1");
        return Integer.parseInt(temp);
    }
}
