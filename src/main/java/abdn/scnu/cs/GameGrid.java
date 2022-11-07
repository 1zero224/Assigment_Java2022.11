package abdn.scnu.cs;

import java.util.Objects;
import java.util.Random;

public class GameGrid extends AbstractGameGrid {
    protected int width;
    protected int height;
    protected int shipQuantity;

    public GameGrid(int height, int width, int shipQuantity) {
        this.width = width;
        this.height = height;
        this.shipQuantity = shipQuantity;

        gameGrid = new String[height][width];

        initializeGrid();

        generateShips(shipQuantity);
        for (int i = 0; i < shipQuantity; i++) {
            placeShip(ships[i]);
        }
    }

    @Override
    public void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.gameGrid[i][j] = ".";
            }
        }
    }

    @Override
    public void generateShips(int numberOfShips) {
        ships = new AbstractBattleShip[numberOfShips];
        for (int i = 0; i < numberOfShips; i++) {
            ships[i] = new BattleShip("Ship " + (i + 1));
        }
    }

    @Override
    public void placeShip(AbstractBattleShip ship) {
        Random randomLocation = new Random();
        int[][] Coordinates = new int[3][2];
        int row;
        int column;


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
