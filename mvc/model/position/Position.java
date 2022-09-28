package mvc.model.position;

abstract public class Position {
    private  int position_on_table;
    private Days day;
    private String image;

    public enum Days { //https://www.tutorialspoint.com/enum-for-days-of-week-in-java
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    /**
     *Setter for th e variable day
     * @param day
     *
     */
    public void setDay(Days day) {
        this.day = day;
    }

    /**
     *Getter for the variable day
     * @return day
     *
     */
    public Days getDay() {
        return this.day;
    }

    /**
     *Setter for the variable image
     * @param image
     *
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *Getter for the variable image
     * @return image
     *
     */
    public String getImage() {
        return image;
    }

    /**
     *Setter for variable position on table
     * @param position_on_table
     *
     */
    public void setPosition_on_table(int position_on_table) {
        this.position_on_table = position_on_table;
    }

    /**
     *Getter for variable position on table
     * @return position on table
     *
     */
    public int getPosition_on_table() {
        return position_on_table;
    }

    /**
     *This is the constructor of this class
     * @param position_on_table
     * @param image
     * @param day
     *
     */
    public Position(int position_on_table,Days day,String image){
        this.day=day;
        this.position_on_table=position_on_table;
        this.image=image;
    }
    /**
     *This method checks if the current day is thursday
     *@pre game has started
     * @post we find out if it is thursday
     */
    public boolean checkThursday(){
        return getDay().equals(Days.Thursday);
    }

    /**
     *This method checks if the current day is sunday
     *@pre game has started
     *@post we find out if it is sunday
     */
    public boolean checkSunday(){
        return getDay().equals(Days.Sunday);
    }
}
