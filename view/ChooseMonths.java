package mvc.view;

import javax.swing.*;

/**
 *
 * dialogue box to give the player the choice of months(rounds) that the game will last.
 */
public class ChooseMonths {
    public int chooseMonthDialog() {
        JFrame frame = new JFrame();
        Object[] options = {"1", "2", "3"};
        String temp = (String) JOptionPane.showInputDialog(frame, "Choose number of months", "Months to be played", JOptionPane.PLAIN_MESSAGE, null, options, "1");
        return Integer.parseInt(temp);
    }
}