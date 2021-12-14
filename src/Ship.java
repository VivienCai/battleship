import java.util.*;

public class Ship {
    private String name;
    private boolean isSunk;
    private boolean isVertical;
    private int size;
    private int timesHit;
    private Coordinate homeCoor;
    private ArrayList<Coordinate> coorsOccupied;
    static Coordinate coorBoard[][] = new Coordinate[11][11];

    private static ArrayList<Ship> listOfShips = new ArrayList<Ship>();

    public Ship(boolean isV, int s, Coordinate p) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                coorBoard[i][j] = new Coordinate(j, i);
            }
        }
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
        homeCoor = p;

    }
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
        return homeCoor;
    }
}
