package dirtySeven;

/**
 * this class represents a player
 * @Ilia
 */

import java.util.*;

public class Player extends haveCards {

    //player username
    private String userName;
    //player hand
    private Set set;


    //constructor
    public Player(String userName,Set set){
        super();
        this.userName = userName;
        this.set = set;
    }

    /**
     *
     * @return a random card from general set
     */
    public Card random(){
        Card[] cardsNumbers;
        Random rand;
        int randNumber;
        cardsNumbers = set.getCards().toArray(new Card[set.getSize()]);
        rand = new Random();
        randNumber = rand.nextInt(set.getSize());
        return cardsNumbers[randNumber];
    }


    /**
     * create a hand for player
     */
    public void setDeck(){
        for (int i = 0; i < 7 ;i++){
            Card rand = random();
            super.addCard(rand);
            set.removeCard(rand);
        }
    }

    /**
     * print all cards in player hand
     */
    public void displayHand(){
        Card[] cardArray = super.getCards().toArray(new Card[super.getSize()]);
        for (int i = 1;i <= super.getSize();i++){
            System.out.print(i);
            System.out.print(".");
            super.display(cardArray[i-1]);
        }
    }

    /**
     * this method is for bot playing
     * @param card is background card
     * @return intended card in player hand
     */
    public Card botPlay(Card card){
        Card[] cardsNumbers = super.getCards().toArray(new Card[super.getSize()]);
        Random random = new Random();
        int randNumber;
        Card rand;
        for (int i=0; i<super.getSize(); i++){
            //rand = random();
            randNumber = random.nextInt(super.getSize());
            rand = cardsNumbers[randNumber];

            if (rand.getColor().equals(card.getColor()) || rand.getSign() == card.getSign()){
                super.removeCard(rand);
                return rand;
            }
        }
        super.addCard(random());
        Card newCrd = new Card("blue",'/');
        return newCrd;
    }

    /**
     * this method is for human player
     * @param card is background card
     * @return intended card in player hand
     */
    public Card humanPlayer(Card card){

        Card[] cardArray = super.getCards().toArray(new Card[super.getSize()]);
        boolean check = false;

        for (int i = 1;i <= super.getSize();i++){
            System.out.print(i);
            System.out.print(".");
            super.display(cardArray[i-1]);
        }

        for (int j = 0;j < super.getSize() ;j++){
            if (cardArray[j].getSign() == card.getSign() || cardArray[j].getColor().equals(card.getColor())){
                check = true;
                break;
            }
        }

        if (check){
            while (true){
                Scanner sc = new Scanner(System.in);
                int input;
                System.out.println("enter the card number:");
                input = sc.nextInt();
                if (cardArray[input-1].getSign() == card.getSign() || cardArray[input-1].getColor().equals(card.getColor())){
                    //check kon 2 khalte zir ro
                    super.removeCard(cardArray[input-1]);
                    return cardArray[input-1];
                }
                else {
                    System.out.println("invalid input!!!");
                    //System.out.println();
                }
            }
        }
        else {
            Card newCrd = new Card("blue",'/');
            return newCrd;
        }
    }

    /**
     *
     * @return true if the game is over
     */
    public boolean finish(){
        if (super.getSize() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return the number of cards
     */
    public int getNumber(){
        return super.getSize();
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        set.removeCard(card);
    }

    @Override
    public void removeCard(Card card) {
        super.removeCard(card);
        set.addCard(card);
    }

    /**
     * add card to someone
     * @param card intended card
     */
    public void addCardNotFromSet(Card card){
        super.addCard(card);
    }

    /**
     * remove card from someone hand
     * @param card intended card
     */
    public void removeCardNotFromSet(Card card){
        super.removeCard(card);
    }

    /**
     *
     * @return player earned point
     */
    public int point(){
        int earned = 0;
        Card[] cardsNumbers = super.getCards().toArray(new Card[super.getSize()]);
        for (int i = 0;i < cardsNumbers.length - 1;i++){
            if (cardsNumbers[i].getSign() == '3'){
                earned += 3;
            }
            else if (cardsNumbers[i].getSign() == '4'){
                earned += 4;
            }
            else if (cardsNumbers[i].getSign() == '5'){
                earned += 5;
            }
            else if (cardsNumbers[i].getSign() == '6'){
                earned += 6;
            }
            else if (cardsNumbers[i].getSign() == '9'){
                earned += 9;
            }
            else if (cardsNumbers[i].getSign() == 'C'){
                earned += 12;
            }
            else if (cardsNumbers[i].getSign() == 'B'){
                earned += 12;
            }
            else if (cardsNumbers[i].getSign() == 'D'){
                earned += 13;
            }
            else if (cardsNumbers[i].getSign() == '2'){
                earned += 2;
            }
            else if (cardsNumbers[i].getSign() == 'x'){
                earned += 10;
            }
            else if (cardsNumbers[i].getSign() == 'A'){
                earned += 11;
            }
            else if (cardsNumbers[i].getSign() == '8'){
                earned += 8;
            }
            else if (cardsNumbers[i].getSign() == '7' && cardsNumbers[i].getColor().equals("yellow")){
                earned += 15;
            }
            else if (cardsNumbers[i].getSign() == '7' && !cardsNumbers[i].getColor().equals("yellow")){
                earned += 10;
            }
        }
        return earned;
    }
}
