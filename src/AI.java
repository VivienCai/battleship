import java.util.*;

public class AI {
    boolean isHunting;
    Coordinate test;
    static char board[][] = new char[11][11];
    static Coordinate coorBoard[][] = new Coordinate[11][11];

    static ArrayList<Coordinate> listOfShip = new ArrayList<Coordinate>();

    // initialize board
    public static void initBoard() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                board[i][j] = 'o';
            }
        }
    }

    public static void printBoard() {
        System.out.println("_____________________________");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }
    // helper methods

    public static Coordinate generatepointToPlace(int shipSize, boolean orientationV) {
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
        System.out.print(x + " ");
        System.out.print(y + " ");
        System.out.println(shipSize + " ");

        return new Coordinate(y, x);
    }

    public static boolean anyGeneratedIsShip(Coordinate generate, boolean orientationV, int shipSize) {
        Coordinate nextShip;
        for (int j = 1; j < shipSize; j++) {
            if (orientationV) {
                nextShip = new Coordinate(generate.getY() + j, generate.getX());
                if(int i =0;i<listOfShip.size;i++){

                }

            } else {
                nextShip = new Coordinate(generate.getY(), generate.getX() + j);

            }
            if (nextShip.getIsShip()) {
                return true;
            }

        }
        return false;

    }

    // placing algorithm
    public static void place() {

        for (int i = 2; i <= 5; i++) {
            int shipSize = i;
            boolean orientationV = (int) ((Math.random() * 2) + 1) == 1 ? true : false;
            Coordinate generate = new Coordinate(0, 0);
            boolean isCoorUnique = false;
            // if the
            while (!isCoorUnique) {
                generate = generatepointToPlace(shipSize, orientationV);
                if (anyGeneratedIsShip(generate, orientationV, shipSize)) {
                    generate = generatepointToPlace(shipSize, orientationV);
                } else {
                    isCoorUnique = true;
                }
            }
            Ship ship = new Ship(orientationV, shipSize, generate);
            generate.setIsShip(true);
            board[generate.getY()][generate.getX()] = 'x';

            for (int j = 1; j < shipSize; j++) {
                // if the orientation is vertical
                if (orientationV) {
                    Coordinate nextShip = new Coordinate(generate.getY() + j, generate.getX());
                    nextShip.setIsShip(true);
                    listOfShip.add(nextShip);
                    board[generate.getY() + j][generate.getX()] = 'x';
                } else {
                    Coordinate nextShip = new Coordinate(generate.getY(), generate.getX() + j);
                    nextShip.setIsShip(true);
                    listOfShip.add(nextShip);
                    board[generate.getY()][generate.getX() + j] = 'x';

                }
            }
        }

    }

    public static void main(String[] args) {
        initBoard();

        printBoard();
        //
        place();
        printBoard();

    }

}
