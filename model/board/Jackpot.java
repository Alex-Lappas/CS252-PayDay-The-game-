package mvc.model.board;

public class Jackpot {
    private int jackpot=0;

    /**
     *setter for pop up message
     * @param jackpot
     *
     */
    public void setJackpot(int jackpot) {
        this.jackpot = jackpot;
    }

    /**
     *getter for jackpot
     * @return Jackpot
     *
     */
    public int getJackpot() {
        return jackpot;
    }

    public void addToJackpot(int money){
        this.jackpot+=money;
    }

    public int winJackpot(){
        int money_won=this.jackpot;
        this.jackpot=0;
        return money_won;
    }
}
