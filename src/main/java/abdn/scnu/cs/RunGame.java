package abdn.scnu.cs;

import java.util.Scanner;

public class RunGame {
    public static void main(String[] args){
        if(args.length==3){
            Game newround=new Game(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
            newround.YourGrid.printGrid();
            newround.OpponentGrid.printGrid();
            Scanner input=new Scanner(System.in);
            do {
                String Guess=input.next();
                newround.exitGame(Guess);
                newround.playRound(Guess);
            }while (!newround.checkVictory());
            newround.exitGame("exit");
        }
    }
}
