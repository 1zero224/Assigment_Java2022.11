package abdn.scnu.cs;

import java.util.Scanner;

public class RunGame {

    public static void main(String[] args){
        try{
            if(!(args.length ==3)){
                throw new IllegalArgumentException();
            }
            else {
                for(int i=0;i<3;i++){
                    if(!(args[i].length()==1) || !Character.isDigit(args[i].charAt(0))){
                        throw new IllegalArgumentException();
                    }
                }
            }

            Game newRound=new Game(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
            newRound.YourGrid.printGrid();
            System.out.println();
            newRound.OpponentGrid.printGrid();
            Scanner input=new Scanner(System.in);
            String Guess;
            for(int i=0;i<newRound.row* newRound.column;i++) {
                newRound.waitAsecond();
                System.out.println("\nPlease enter the position you wish to attack");
                try {
                    Guess = input.nextLine();
                    newRound.playRound(Guess);
                    newRound.exitGame(Guess);
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                    newRound.YourGrid.printGrid();
                    System.out.println();
                    newRound.OpponentGrid.printGrid();
                }
            }
        }catch (Exception e){
            System.out.println("Incorrect input. Please input game grid width, height, and number of ships, separated by Spaces.");
        }
    }


}
