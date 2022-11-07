package abdn.scnu.cs;

import java.util.Objects;
import java.util.Random;

public class GameGrid extends AbstractGameGrid {
    protected int width;
    protected int height;
    protected int shipQuantity;

    //Initialize the game, generate player and opponent grids and ships, and place ships on the grid
    public GameGrid(int height, int width, int shipQuantity) {
        this.width = width;
        this.height = height;
        this.shipQuantity = shipQuantity;

        //Generate a grid based on the height and width of the input
        gameGrid = new String[height][width];

        //Initialize the grid
        initializeGrid();

        //Generate ships and place ships in the grid
        generateShips(shipQuantity);
        for (int i = 0; i < shipQuantity; i++) {
            placeShip(ships[i]);
        }
    }

    //Initialize everything in the grid to "."
    @Override
    public void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.gameGrid[i][j] = ".";
            }
        }
    }

    //Generate the corresponding number of ships as entered and name them in sequence
    @Override
    public void generateShips(int numberOfShips) {
        ships = new AbstractBattleShip[numberOfShips];
        for (int i = 0; i < numberOfShips; i++) {
            ships[i] = new BattleShip("Ship " + (i + 1));
        }
    }

    //Generate the orientation and coordinates of the ship and place it in the grid
    @Override
    public void placeShip(AbstractBattleShip ship) {
        Random randomLocation = new Random();
        int[][] Coordinates = new int[3][2];
        int row;
        int column;

        //Randomly generate ship coordinates based on the direction of the ship
        if (Objects.equals(ship.getShipOrientation(), "horizontal")) {
            row = randomLocation.nextInt(height);
            if(width==3){column=1;}
            else {column = randomLocation.nextInt(width-3)+1;}
        } else {
            if(height==3) {row=1;}
            else {row = randomLocation.nextInt(height-3)+1;}
            column = randomLocation.nextInt(width);
        }
        Coordinates[0][0] = row;
        Coordinates[0][1] = column;
        ship.setShipCoordinates(Coordinates);

        //Place the ship in the grid according to the ship's coordinates
        this.gameGrid[Coordinates[0][0]][Coordinates[0][1]] = "*";
        if (Objects.equals(ship.getShipOrientation(), "horizontal")) {
            this.gameGrid[Coordinates[0][0]][Coordinates[0][1]-1] = "*";
            this.gameGrid[Coordinates[0][0]][Coordinates[0][1]+1] = "*";
        } else {
            this.gameGrid[Coordinates[0][0]-1][Coordinates[0][1]] = "*";
            this.gameGrid[Coordinates[0][0]+1][Coordinates[0][1]] = "*";
        }
    }
}
