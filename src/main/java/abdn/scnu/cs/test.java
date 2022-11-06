package abdn.scnu.cs;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Game game = new Game (4,4,1);

        Scanner input=new Scanner(System.in);
        while (true){
            String round=input.next();
            game.playRound(round);
        }
    }
}
