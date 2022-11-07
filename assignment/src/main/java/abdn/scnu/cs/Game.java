package abdn.scnu.cs;

import java.util.Objects;
import java.util.Random;

public class Game implements GameControls{
    int row;
    int column;
    int shipQuantity;

    PlayerGameGrid YourGrid;
    OpponentGameGrid OpponentGrid;

    //Generate and initialize player and opponent grids and ships
    public Game(int row,int column,int shipQuantity){
        this.row=row;
        this.column=column;
        this.shipQuantity=shipQuantity;
        YourGrid= new PlayerGameGrid(row,column,shipQuantity);
        OpponentGrid=new OpponentGameGrid(row,column,shipQuantity);
    }

    //Get the player's grid and ships
    @Override
    public AbstractGameGrid getPlayersGrid(){
        return YourGrid;
    }

    //Get your opponent's grid and ships
    @Override
    public  AbstractGameGrid getOpponentssGrid(){
        return OpponentGrid;
    }

    //Detects the input, and terminates the program if the input contains "exit"
    @Override
    public void exitGame(String input) {
        if(input.contains("exit")){
            waitAsecond();
            System.out.print("exit game - thank you for playing");
            waitAsecond();
            System.exit(0);
        }
    }

    //Check whether all ships of one party are currently destroyed. If so, output the game result and end the game
    @Override
    public boolean checkVictory() {
        //Count how many ships have been destroyed
        int OpponentAttack=0;
        int PlayerAttack=0;
        for(int i=0;i<shipQuantity;i++){
            if(YourGrid.ships[i].hits==3){
                OpponentAttack++;
            }
            if(OpponentGrid.ships[i].hits==3){
                PlayerAttack++;
            }
        }

        //Check the statistics to see if anyone has won
        if(OpponentAttack==shipQuantity){
            waitAsecond();
            System.out.println("You have lost!");
            return true;
        }
        else if(PlayerAttack==shipQuantity){
            waitAsecond();
            System.out.println("You have won!");
            return true;
        }
        return false;
    }

    //Complete a round of the game by executing both attacks and checking to see if anyone wins.
    @Override
    public void playRound(String inputCoordinates) {

        //Check that the coordinate input is valid
        exitGame(inputCoordinates);
        checkCoordinate(inputCoordinates);

        //Convert the input to coordinates
        String[] attackCoordinates=inputCoordinates.split(",");
        int attackX=Integer.parseInt(attackCoordinates[0]);
        int attackY=Integer.parseInt(attackCoordinates[1]);

        //Implement player and opponent attacks
        if(!Objects.equals(OpponentGrid.gameGrid[attackX][attackY], "X") && !Objects.equals(OpponentGrid.gameGrid[attackX][attackY], "%")){
            System.out.println("Player is attacking");
            waitAsecond();
            attack(attackX,attackY,OpponentGrid);
            waitAsecond();
            robotStrategy();
        }
        else {
            System.out.println("You cannot attack the same coordinates repeatedly.");
        }

        //Print the updated grid and check to see if anyone wins
        waitAsecond();
        System.out.println();
        YourGrid.printGrid();
        System.out.println();
        OpponentGrid.printGrid();
        if(checkVictory()){
            waitAsecond();
            System.exit(0);
        }
    }

    //Generate random coordinates to complete the opponent's attack on the player
    public void robotStrategy(){
        Random attackCoordinate=new Random();
        int attackX;
        int attackY;
        do{
            attackX=attackCoordinate.nextInt(YourGrid.height);
            attackY=attackCoordinate.nextInt(YourGrid.width);
        }while (Objects.equals(YourGrid.gameGrid[attackX][attackY], "X") | (Objects.equals(YourGrid.gameGrid[attackX][attackY], "%")));
        System.out.println("Opponent is attacking");
        waitAsecond();
        attack(attackX,attackY,YourGrid);
    }

    //Implement the attack and update the grid based on the input coordinates
    public void attack(int x,int y,GameGrid Grid){
        for(int i=0;i<this.shipQuantity;i++) {
            if (Grid.ships[i].checkAttack(x,y)) {
                System.out.println("HIT " + Grid.ships[i].name + "!!!");
                Grid.gameGrid[x][y] = "X";
            }
            else if (i == this.shipQuantity - 1){
                    System.out.println("Miss!!!");
                   Grid.gameGrid[x][y] = "%";
            }
        }
    }

    //Check that the coordinates are formatted
    public void checkCoordinate(String input){
        char[] simpleInput=input.toCharArray();
        if((simpleInput.length!=3) || !Character.isDigit(simpleInput[0]) || !Character.isDigit(simpleInput[2]) || !Character.toString(simpleInput[1]).equals(",")){
            throw new IllegalArgumentException();
        }
    }

    //Realize the waiting process of the game and enhance the player experience
    public void waitAsecond(){
        try {
            int a;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
