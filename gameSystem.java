package dirtySeven;
import java.util.*;

/**
 * the gameSystem class is the game operator.
 * @author Ilia
 */
public class gameSystem {

    //keeps all cards
    private Set set;

    //keeps all players
    private ArrayList<Player> players;

    //background card
    private Card card;

    //determine rotation
    private char clockWise;

    private int turn;


    //constuctor
    public gameSystem(int n){
        set = new Set();
        set.createSet();
        turn = 0;
        players = new ArrayList<Player>();
        for (int i=1; i<=n ; i++){
            Player player = new Player("player" + i,set);
            players.add(player);
        }
        clockWise = '+';
    }

    private Card randColor(){
        Random rand = new Random();
        int randNumber = rand.nextInt(4);
        String color;
        if (randNumber == 0){
            color = "blue";
        }
        else if (randNumber == 1){
            color = "red";
        }
        else if (randNumber == 2){
            color = "green";
        }
        else {
            color = "yellow";
        }
        Card card = new Card(color,'-');
        return card;
    }

    //create set and distribute cards
    private void begin(){
        for (int i = 0; i < players.size(); i++){
            players.get(i).setDeck();
        }
        Random rand = new Random();
        Card[] cardsNumbers;
        int randNumber;
        while (true){
            cardsNumbers = set.getCards().toArray(new Card[set.getSize()]);
            randNumber = rand.nextInt(set.getSize());
            if (!cardsNumbers[randNumber].haveAction()){
                card = cardsNumbers[randNumber];
                set.removeCard(cardsNumbers[randNumber]);
                break;
            }
        }

    }

    //two action
    private void twoAct(Player donor, Player receiver){
        for (int i =0; i < 2; i++){
            Card rand = donor.random();
            donor.removeCardNotFromSet(rand);
            receiver.addCardNotFromSet(rand);
        }
    }

    //seven action
    private void sevenAct(Player player,int bank){
        Card[] cardsNumbers;
        Random rand = new Random();
        int randNumber;
        cardsNumbers = set.getCards().toArray(new Card[set.getSize()]);
        for (int i = 0 ;i < bank; i++){
            randNumber = rand.nextInt(set.getSize());
            player.addCard(cardsNumbers[randNumber]);
        }

    }



    //game
    public Player game(){
        begin();
        int sevenBank = 0;
        Card secondCard;
        int secCardNum = 0;

        while (true){

            while (clockWise == '+'){
                turn = 0;
                while (turn < players.size()){
                    if (secCardNum != 0){
                        Card trash = new Card(card.getColor(),card.getSign());
                        set.addCard(trash);
                    }
                    System.out.print("background card:");
                    set.display(card);
                    System.out.println();


                    if (turn == 0){
                        System.out.println("player" + (turn+1));
                        Card humanTurn = players.get(turn).humanPlayer(card);
                        if (humanTurn.getSign() != '/'){
                            card = humanTurn;
                            System.out.println();
                        }
                    }
                    else {
                        System.out.println("player" + (turn+1) + ":");
                        //players.get(turn).displayHand();
                        Card botTurn = players.get(turn).botPlay(card);
                        if (botTurn.getSign() != '/'){
                            card = botTurn;
                            System.out.println();
                        }
                    }



                    if (players.get(turn).finish()){
                        for (int i=0;i< players.size();i++){
                            if (players.get(i).equals(players.get(turn))){
                                continue;
                            }
                            System.out.println("player" + (i+1) + ":" + players.get(i).point());
                        }
                        return players.get(turn);
                    }

                    if (sevenBank != 0 && card.getSign() != '7'){
                        sevenAct(players.get(turn),sevenBank);
                        sevenBank = 0;
                    }



                    if (card.getSign() == 'A'){
                        if (turn == players.size()-1){
                            turn = 0;
                        }
                        else {
                            turn++;
                        }
                    }
                    else if (card.getSign() == '2'){
                        if (players.get(turn).getNumber() < 3){
                            for (int i=0;i< players.size();i++){
                                if (players.get(i).equals(players.get(turn))){
                                    continue;
                                }
                                System.out.println("player" + (i+1) + ":" + players.get(i).point());
                            }
                            return players.get(turn);
                        }

                        if (turn == 0){
                            System.out.println("enter the number of player who you want to give card:");
                            Scanner sc = new Scanner(System.in);
                            int num = sc.nextInt();
                            twoAct(players.get(turn),players.get(num-1));
                        }
                        else{
                            int randNumber;
                            while (true){
                                Random rand = new Random();
                                randNumber = rand.nextInt(players.size());
                                if (randNumber != turn){
                                    break;
                                }
                            }
                            twoAct(players.get(turn),players.get(randNumber));
                        }

                    }

                    else if (card.getSign() == '7'){
                        sevenBank += 4;
                    }

                    else if (card.getSign() == 'x'){
                        clockWise = '-';
                        if (turn == 0){
                            turn = players.size()-1;
                        }
                        else {
                            turn = turn -1;
                        }
                        break;
                    }

                    else if (card.getSign() == '8'){
                        continue;
                    }

                    else if(card.getSign() == 'B') {
                        if (turn == 0){
                            System.out.println("write the color:");
                            Scanner sc = new Scanner(System.in);
                            String color = sc.nextLine();
                            Card newColorCard = new Card(color,'_');
                            card = newColorCard;
                        }
                        else {
                            card = randColor();
                        }

                    }

                    turn++;
                    secCardNum++;

                }
            }



            while (clockWise == '-'){
                if (turn < 0){
                    turn = players.size()-1;
                }
                while (turn > 0){
                    Card trash = new Card(card.getColor(),card.getSign());
                    set.addCard(trash);

                    System.out.println("background cart:");
                    set.display(card);
                    System.out.println();
                    System.out.println("player" + (turn + 1) + ":\n");


                    if (turn == 0){
                        System.out.println("player" + (turn+1));
                        Card humanTurn = players.get(turn).humanPlayer(card);
                        if (humanTurn.getSign() != '/'){
                            card = humanTurn;
                            System.out.println();
                        }
                    }
                    else {
                        System.out.println("player" + (turn+1) + ":");
                        //players.get(turn).displayHand();
                        Card botTurn = players.get(turn).botPlay(card);
                        if (botTurn.getSign() != '/'){
                            card = botTurn;
                            System.out.println();
                        }
                    }

                    if (players.get(turn).finish()){
                        for (int i=0;i< players.size();i++){
                            if (players.get(i).equals(players.get(turn))){
                                continue;
                            }
                            System.out.println("player" + (i+1) + ":" + players.get(i).point());
                        }
                        return players.get(turn);
                    }

                    if (sevenBank != 0 && card.getSign() != '7'){
                        sevenAct(players.get(turn),sevenBank);
                        sevenBank = 0;
                    }


                    if (card.getSign() == 'x'){
                        clockWise = '+';
                        if (turn == players.size()-1){
                            turn = 0;
                        }
                        else {
                            turn = turn + 1;
                        }
                        break;
                    }

                    else if (card.getSign() == 'A'){
                        if (turn == players.size()-1){
                            turn = 0;
                        }
                        else {
                            turn++;
                        }
                    }

                    else if (card.getSign() == '2'){
                        if (players.get(turn).getNumber() < 3){
                            for (int i=0;i< players.size();i++){
                                if (players.get(i).equals(players.get(turn))){
                                    continue;
                                }
                                System.out.println("player" + (i+1) + ":" + players.get(i).point());
                            }
                            return players.get(turn);
                        }

                        if (turn == 0){
                            System.out.println("enter the number of player who you want to give card:");
                            Scanner sc = new Scanner(System.in);
                            int num = sc.nextInt();
                            twoAct(players.get(turn),players.get(num-1));
                        }
                        else{
                            int randNumber;
                            while (true){
                                Random rand = new Random();
                                randNumber = rand.nextInt(players.size());
                                if (randNumber != turn){
                                    break;
                                }
                            }
                            twoAct(players.get(turn),players.get(randNumber));
                        }

                    }

                    else if (card.getSign() == '7'){
                        sevenBank += 4;
                    }

                    else if (card.getSign() == '8'){
                        continue;
                    }

                    else if(card.getSign() == 'B') {
                        if (turn == 0){
                            System.out.println("write the color:");
                            Scanner sc = new Scanner(System.in);
                            String color = sc.nextLine();
                            Card newColorCard = new Card(color,'_');
                            card = newColorCard;
                        }
                        else {
                            card = randColor();
                        }
                    }

                    turn--;
                    secCardNum++;
                }
            }

        }
    }
}
