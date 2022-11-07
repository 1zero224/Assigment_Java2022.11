package abdn.scnu.cs;

import java.util.Objects;
import java.util.Random;

public class Game implements GameControls{
    int row;
    int column;
    int shipQuantity;

    PlayerGameGrid YourGrid;
    OpponentGameGrid OpponentGrid;

    public Game(int row,int column,int shipQuantity){
        this.row=row;
        this.column=column;
        this.shipQuantity=shipQuantity;
        YourGrid= new PlayerGameGrid(row,column,shipQuantity);
        OpponentGrid=new OpponentGameGrid(row,column,shipQuantity);
    }

    @Override
    public AbstractGameGrid getPlayersGrid(){
        return YourGrid;
    }

    @Override
    public  AbstractGameGrid getOpponentssGrid(){
        return OpponentGrid;
    }

    @Override
    public void exitGame(String input) {
        if(input.contains("exit")){
            waitAsecond();
            System.out.print("exit game - thank you for playing");
            waitAsecond();
            System.exit(0);
        }
    }

    @Override
    public boolean checkVictory() {
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

    @Override
    public void playRound(String inputCoordinates) {
        exitGame(inputCoordinates);
        checkCoordinate(inputCoordinates);
        String[] attackCoordinates=inputCoordinates.split(",");
        int attackX=Integer.parseInt(attackCoordinates[0]);
        int attackY=Integer.parseInt(attackCoordinates[1]);
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

    public void checkCoordinate(String input){
        char[] simpleInput=input.toCharArray();
        if((simpleInput.length!=3) || !Character.isDigit(simpleInput[0]) || !Character.isDigit(simpleInput[2]) || !Character.toString(simpleInput[1]).equals(",")){
            throw new IllegalArgumentException();
        }
    }

    public void waitAsecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
