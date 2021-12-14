import java.util.*;

public class AI {
    static char board[][] = new char[11][11];
    static ArrayList<Coordinate> listOfShip = new ArrayList<Coordinate>();

    public static void printBoard(Coordinate coorBoard[][]) {
        System.out.println("_____________________________");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (coorBoard[i][j].getIsShip()) {
                    System.out.print("X ");
                }
                else{
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    // generates the home coordinate for ship
    public static Coordinate generatePoint(int shipSize, boolean orientationV) {
        int x;
        int y;
        // if vertical
        if (orientationV) {
            x = (int) ((Math.random() * 10) + 1);
            y = (int) ((Math.random() * (11 - shipSize)) + 1);

        } // if horizontal
        else {
            x = (int) ((Math.random() * (11 - shipSize)) + 1);
            y = (int) ((Math.random() * 10) + 1);
        }
        return new Coordinate(y, x);
    }

    // checks if the ship will overlap any other coordinates that are already
    // occupied
    public static boolean anyGeneratedIsShip(Coordinate home, boolean orientationV, int shipSize, Coordinate coorBoard[][]) {
        for (int j = 0; j < shipSize; j++) {
            if (orientationV) {
                if (coorBoard[home.getY() + j][home.getX()].getIsShip()) {
                    return true;
                }
            } else {
                if (coorBoard[home.getY()][home.getX() + j].getIsShip()) {
                    return true;
                }
            }
        }
        return false;
    }

    // PLACING ALGORITHM
    public static void place(Coordinate coorBoard[][]) {
        // loops from 2 - 6 since there are 2 ships of length 3
        for (int i = 2; i <= 6; i++) {
            int shipSize = i;
            if (i == 6) {
                shipSize = 3;
            }

            // instantiating
            boolean orientationV = (int) ((Math.random() * 2) + 1) == 1 ? true : false;
            boolean isCoorUnique = false;

            // generating a new point (home coordinate of ship)
            Coordinate home = generatePoint(shipSize, orientationV);

            // while the coordinate isnt unique
            while (!isCoorUnique) {
                // if the ship intersects with any other ship that already occupied with another
                // ship, generate a new point
                if (anyGeneratedIsShip(home, orientationV, shipSize, coorBoard)) {
                    home = generatePoint(shipSize, orientationV);
                } else {
                    isCoorUnique = true;
                }
            }
            Ship ship = new Ship(orientationV, shipSize, home);

            // once ship is placeable
            for (int j = 1; j < shipSize; j++) {
                home.setIsShip(true);
                coorBoard[home.getY()][home.getX()].setIsShip(true);
                board[home.getY()][home.getX()] = 'x';
                // if the orientation is vertical
                if (orientationV) {
                    coorBoard[home.getY() + j][home.getX()].setIsShip(true);
                    board[home.getY() + j][home.getX()] = 'x';
                } else {
                    coorBoard[home.getY()][home.getX() + j].setIsShip(true);
                    board[home.getY()][home.getX() + j] = 'x';

                }
            }
        }

    }

}
