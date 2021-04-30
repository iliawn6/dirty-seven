package dirtySeven;

import java.util.*;

/**
 * this class represents a general set of cards
 * @author Ilia
 */
public class Set extends haveCards{


    //constructor
    public Set(){
        super();
    }

    //create a set
    public void createSet(){
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("blue");
        colors.add("red");
        colors.add("yellow");
        colors.add("green");
        for (int i = 0; i<4 ;i++){
            for (int j = 2; j <= 10;j++){
                //char a = (char)j;
                Card card = new Card(colors.get(i),(char) (j + 47));
                super.addCard(card);
            }
        }
        for (int j = 0; j<4 ;j++){
            Card card = new Card(colors.get(j),'A');
            super.addCard(card);
        }
        for (int j = 0; j<4 ;j++){
            Card card = new Card(colors.get(j),'B');
            super.addCard(card);
        }
        for (int j = 0; j<4 ;j++){
            Card card = new Card(colors.get(j),'C');
            super.addCard(card);
        }
        for (int j = 0; j<4 ;j++){
            Card card = new Card(colors.get(j),'D');
            super.addCard(card);
        }
        for (int j = 0; j<4 ;j++){
            Card card = new Card(colors.get(j),'x');
            super.addCard(card);
        }
    }


}
