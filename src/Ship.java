import java.util.*;

/* Sarina Li, Vivien Cai, Jiaan Li
* Mon December 20
* ICS4U1
* Ship Class
*/

public class Ship {
    //Attributes of ship
    private String name;
    private boolean isSunk;
    private boolean isVertical;
    private int size;
    private int timesHit;
    private Coordinate homeCoor;
    private static int count = 0;

    // lists of ships alive for user and AI
    private static ArrayList<Ship> listOfShips = new ArrayList<Ship>();
    private static ArrayList<String> playerListOfShipsAlive = new ArrayList<String>(
  Arrays.asList("DESTROYER", "SUBMARINE", "CRUISER", "BATTLESHIP", "CARRIER"));

    // default constructor 
    public Ship() {
    }

    // constructor 
    public Ship(boolean isV, int s, Coordinate p) {
        switch (s) {
            case 0:
                name = "empty";
                break;
            case 2:
                name = "DESTROYER";
                break;
            case 3:
                if (count == 0) {
                    name = "SUBMARINE";
                    count++;
                } else {
                    name = "CRUISER";
                }
                break;
            case 4:
                name = "BATTLESHIP";
                break;
            case 5:
                name = "CARRIER";
                break;
            default:
                name = "DEFAULT";
        }
        isVertical = isV;
        size = s;
        homeCoor = p;
    }

    public static int getSize(String name) {
        switch (name) {
            case "DESTROYER":
                return 2;
            case "SUBMARINE":
                return 3;
            case "CRUISER":
                return 3;
            case "BATTLESHIP":
                return 4;
            case "CARRIER":
                return 5;
        }
        return 0;
    }
    
    // getters 
    public String getName() {
        return name;
    }

    public boolean getIsSunk() {
        if (timesHit == size) {
            isSunk = true;
        }
        isSunk = false;
        return isSunk;
    }

    public boolean getVertical() {
        return isVertical;
    }

    public int getSize() {
        return size;
    }

    public int getTimesHit() {
        return timesHit;
    }

    public Coordinate getHomeCoord() {
        return homeCoor;
    }

    public static ArrayList<Ship> getList() {
        return listOfShips;
    }

    public static ArrayList<String> getPlayerListOfShipsAlive() {
        return playerListOfShipsAlive;
    }
    
    public static int getIndexOfThreeShip(String ship) {
        // submarine is index 6
        if (ship.equals("SUBMARINE")) {
            return 6;
            // cruiser is index 3
        } else {
            return 3;
        }
    }

    // general helper methods
    public String toString() {
        return name;
    }

    public void addTimesHit() {
        timesHit++;
    }
    
    
}
