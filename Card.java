package dirtySeven;


/**
 *the Card class represent a card
 * @author Ilia
 */


public class Card {


    //sign of card
    private char sign;
    //color of card
    private String color;


    //constructor
    public Card(String color,char sign){
        this.sign = sign;
        this.color = color;
    }


    /**
     *
     * @return the sign of card
     */
    public char getSign() {
        return sign;

    }

    /**
     *
     * @return the color of card
     */
    public String getColor() {
        return color;
    }


    /**
     *
     * @return true if card have special action
     */
    public boolean haveAction(){
        if (sign == '2' || sign == '7' || sign == '8' || sign == 'x' || sign == 'A' || sign == 'B' ){
            return true;
        }
        else {
            return false;
        }
    }



}
