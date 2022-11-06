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
            System.out.print("exit game - thank you for playing");
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
            System.out.println("You have lost!");
            return true;
        }
        else if(PlayerAttack==shipQuantity){
            System.out.println("You have won!");
            return true;
        }
        return false;
    }

    @Override
    public void playRound(String inputCoordinates) {
        exitGame(inputCoordinates);
        String[] attackCoordinates=inputCoordinates.split(",");
        for(int i=0;i<this.shipQuantity;i++){
            if(this.OpponentGrid.ships[i].checkAttack(Integer.parseInt(attackCoordinates[0]),Integer.parseInt(attackCoordinates[1]))){
                System.out.println("HIT "+this.OpponentGrid.ships[i].name+"!!!");
                OpponentGrid.gameGrid[Integer.parseInt(attackCoordinates[0])][Integer.parseInt(attackCoordinates[1])]="X";
            }
            else{
                System.out.println("Miss!!!");
                OpponentGrid.gameGrid[Integer.parseInt(attackCoordinates[0])][Integer.parseInt(attackCoordinates[1])]="%";
            }
            YourGrid.printGrid();
            OpponentGrid.printGrid();
            checkVictory();
            robotStrategy();
            YourGrid.printGrid();
            OpponentGrid.printGrid();
            checkVictory();

        }
    }

    public void robotStrategy(){
        Random attackCoordinate=new Random();
        int attackX;
        int attackY;
        do{
            attackX=attackCoordinate.nextInt(YourGrid.height);
            attackY=attackCoordinate.nextInt(YourGrid.width);
        }while (!Objects.equals(YourGrid.gameGrid[attackX][attackY], "X") && !Objects.equals(YourGrid.gameGrid[attackX][attackY], "%"));
        for(int i=0;i<this.shipQuantity;i++){
            if(this.YourGrid.ships[i].checkAttack(attackX,attackY)){
                YourGrid.gameGrid[attackX][attackY]="X";
            }
            else{
                YourGrid.gameGrid[attackX][attackY]="%";
            }
        }
    }
}
