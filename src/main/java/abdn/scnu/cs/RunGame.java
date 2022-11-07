package abdn.scnu.cs;

import java.util.Scanner;

public class RunGame {
    public static void main(String[] args){
        if(args.length==3){
            Game newRound=new Game(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
            newRound.YourGrid.printGrid();
            newRound.OpponentGrid.printGrid();
            Scanner input=new Scanner(System.in);
            String Guess;
            for(int i=0;i<newRound.row* newRound.column;i++){
                System.out.println("\nPlease enter the position you wish to attack");
                try {
                    Guess=input.nextLine();
                    newRound.playRound(Guess);
                    newRound.exitGame(Guess);
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                    newRound.YourGrid.printGrid();
                    newRound.OpponentGrid.printGrid();
                    //return;
                }


            }
        }
    }


}
