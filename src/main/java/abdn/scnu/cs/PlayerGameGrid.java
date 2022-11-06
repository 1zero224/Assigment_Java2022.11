package abdn.scnu.cs;

public class PlayerGameGrid extends GameGrid{

    public PlayerGameGrid(int height, int width, int shipQuantity) {
        super(height, width, shipQuantity);
    }

    protected void printGrid(){
        System.out.println("Player’s grid");
        for(int i=0;i<this.width;i++){
            for(int j=0;j<this.height;j++){
                System.out.print(this.gameGrid[i][j]+" ");
                if(j==this.height-1){
                    System.out.println();
                }
            }
        }
    }
}
