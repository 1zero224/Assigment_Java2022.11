package abdn.scnu.cs;
import java.util.Objects;
import java.util.Random;

public class BattleShip extends AbstractBattleShip{

    protected Random randomIndex = new Random();

    //Initialize a ship by naming it and randomly generating its orientation
    public BattleShip(String name){
        this.name = name;
        String[] randomOrientation = {"horizontal","vertical"};
        this.shipOrientation = randomOrientation[randomIndex.nextInt(2)];
    }

    //Get the name of the ship
    @Override
    public String getName() {
        return this.name;
    }

    //Gets the number of times the ship was hit
    @Override
    public int getHits() {
        return this.hits;
    }

    //Get the ship's orientation
    @Override
    public String getShipOrientation() {
        return this.shipOrientation;
    }

    //Set the number of times a ship is attacked
    @Override
    public void setHits(int numberOfHits) {
        this.hits = numberOfHits;
    }

    //Get ship coordinates
    @Override
    public int[][] getShipCoordinates() {
        return shipCoordinates;
    }

    //Set ship coordinates
    @Override
    public void setShipCoordinates(int[][] coordinates) {
        this.shipCoordinates = coordinates;
    }

    //Check the coordinates to see if the ship was hit
    @Override
    public boolean checkAttack(int row, int column) {
        int[] diff={-1,0,1};
        if (this.hits < 3) {
            for(int i=0;i<3;i++) {
                if(Objects.equals(this.shipOrientation, "horizontal") && this.shipCoordinates[0][0]==row && this.shipCoordinates[0][1]+diff[i]==column) {
                        hits++;
                        return true;
                }
                else if(this.shipCoordinates[0][0]+diff[i]==row && this.shipCoordinates[0][1]==column){
                    hits++;
                    return true;
                }
                }
            return false;
            }
        return false;
        }
    }

