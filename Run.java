package dirtySeven;

import java.util.Scanner;

public class Run {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static void main(String[] args){



        System.out.println(ANSI_CYAN + "----------dirty seven----------\n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "enter the number of players(3,4,5):" + ANSI_RESET);
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        gameSystem operator = new gameSystem(input);
        Player winner = operator.game();
        System.out.println(ANSI_CYAN + "-------the winner is:" + winner.getUserName() + "-------" + ANSI_RESET);




    }
}
