package dirtySeven;

import java.util.*;

/**
 * this class is a super class for all classes which have a set of cards
 * for example player hand
 * @author Ilia
 */

public class haveCards {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    //this field keeps cards
    private HashSet<Card> cards;

    //constructor
    public haveCards(){
        cards = new HashSet<Card>();
    }

    /**
     *
     * @return set of cards
     */
    public HashSet<Card> getCards() {
        return cards;
    }

    /**
     *
     * @param card add to set
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     *
     * @param card remove from set
     */
    public void removeCard(Card card){
        cards.remove(card);
    }

    /**
     *
     * @return the size of set
     */
    public int getSize(){
        return cards.size();
    }

    /**
     * print the card with color and sign
     * @param card intended card
     */
    public void display(Card card){

        if (card.getColor().equals("blue")){
            System.out.println(ANSI_BLUE + card.getSign() +ANSI_RESET);
        }
        else if(card.getColor().equals("red")){
            System.out.println(ANSI_RED + card.getSign() +ANSI_RESET);
        }
        else if(card.getColor().equals("green")){
            System.out.println(ANSI_GREEN + card.getSign() +ANSI_RESET);
        }
        else if(card.getColor().equals("yellow")){
            System.out.println(ANSI_YELLOW + card.getSign() +ANSI_RESET);
        }



    }
}
