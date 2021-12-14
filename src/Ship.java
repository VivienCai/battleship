import java.util.*;

public class Ship {
    private String name;
    private boolean isSunk;
    private boolean isVertical;
    private int size;
    private int timesHit;
    private Coordinate placement;
    private ArrayList<Coordinate> coorsOccupied;

    private static ArrayList<Ship> listOfShips = new ArrayList<Ship>();

    public Ship(boolean isV, int s, Coordinate p) {
        int count = 0;
        switch (s) {
            case 2:
                name = "destroyer";
                break;
            case 3:
                if (count == 0) {
                    name = "submarine";
                    count++;
                } else {
                    name = "cruiser";
                }
                break;
            case 4:
                name = "battleship";
                break;
            case 5:
                name = "carrier";
            default:
                name = "default";
        }
        isVertical = isV;
        size = s;
        placement = p;

    }
    public static ArrayList<Ship> getList() {
        return listOfShips;
    }

    public boolean getVertical() {
        return isVertical;
    }
    
    public int getSize() {
        return size;
    }

    public Coordinate getHomeCoord() {
        return placement;
    }
}
