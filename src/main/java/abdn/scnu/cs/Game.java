package abdn.scnu.cs;

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
    public void exitGame() {
        System.out.print("exit game - thank you for playing");
        System.exit(0);
    }

    @Override
    public void checkVictory() {
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
        }
        else if(PlayerAttack==shipQuantity){
            System.out.println("You have won!");
        }
    }

    @Override
    public void playRound(String inputCoordinates) {
        if(inputCoordinates.contains("exit")){
            exitGame();
        }
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
            OpponentGrid.printGrid();
            checkVictory();
        }
    }
}
