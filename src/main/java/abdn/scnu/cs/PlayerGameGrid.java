package abdn.scnu.cs;

public class PlayerGameGrid extends GameGrid{

    //Initialization of the PlayerGameGrid
    public PlayerGameGrid(int height, int width, int shipQuantity) {
        super(height, width, shipQuantity);
    }

    //Get the player grid and print it directly
    protected void printGrid(){
        System.out.println("Player’s grid");
        for(int i=0;i<this.width;i++){
            for(int j=0;j<this.height;j++){
                System.out.print(gameGrid[i][j]+" ");
                if(j==this.height-1){
                    System.out.println();
                }
            }
        }
    }
}
