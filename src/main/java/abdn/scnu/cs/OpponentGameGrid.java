package abdn.scnu.cs;

import java.util.Objects;

public class OpponentGameGrid extends GameGrid{

    public OpponentGameGrid(int height, int width, int shipQuantity) {
        super(height, width, shipQuantity);
    }

    protected void printGrid(){
        System.out.println("Opponent’s grid");
        for(int i=0;i<this.width;i++){
            for(int j=0;j<this.height;j++){
                if(Objects.equals(gameGrid[i][j], "X") | Objects.equals(gameGrid[i][j], "%")){
                    System.out.print(gameGrid[i][j]);
                }
                else{
                    System.out.print(".");
                }
                if(j==this.width-1){
                    System.out.println();
                }
            }
        }
    }
}
