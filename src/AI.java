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
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                coorBoard[i][j] = new Coordinate(j, i);
            }
        }
    }

    public static void testPrint() {
        System.out.println("__________test________");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (coorBoard[i][j].getIsShip()) {
                    System.out.print("T");
                } else {
                    System.out.print("F");
                }
                System.out.print(board[i][j] + " ");

            }
            System.out.println();
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
        System.out.print(x + " ");
        System.out.print(y + " ");
        System.out.print(shipSize + " ");
        System.out.println(orientationV);

        return new Coordinate(y, x);
    }

    public static boolean anyGeneratedIsShip(Coordinate generate, boolean orientationV, int shipSize) {
        for (int j = 0; j < shipSize; j++) {
            if (orientationV) {
                if (coorBoard[generate.getY() + j][generate.getX()].getIsShip()) {
                    System.out.println("hello");
                    return true;
                }
            } else {
                if (coorBoard[generate.getY()][generate.getX() + j].getIsShip()) {
                    System.out.println("hello");
                    return true;
                }
            }
        }

        return false;

    }

    // placing algorithm
    public static void place() {

        for (int i = 2; i <= 5; i++) {
            int shipSize = i;

            // instantiating
            boolean orientationV = (int) ((Math.random() * 2) + 1) == 1 ? true : false;
            Coordinate generate = new Coordinate(0, 0);
            boolean isCoorUnique = false;
            generate = generatePoint(shipSize, orientationV);

            // while the coordinate isnt unique
            while (!isCoorUnique) {
                if (anyGeneratedIsShip(generate, orientationV, shipSize)) {

                    generate = generatePoint(shipSize, orientationV);
                } else {
                    isCoorUnique = true;
                }
            }
            Ship ship = new Ship(orientationV, shipSize, generate);

            for (int j = 1; j < shipSize; j++) {
                generate.setIsShip(true);
                coorBoard[generate.getY()][generate.getX()].setIsShip(true);
                board[generate.getY()][generate.getX()] = 'x';
                // if the orientation is vertical
                if (orientationV) {
                    // Coordinate nextShip = new Coordinate(generate.getY() + j, generate.getX());
                    // nextShip.setIsShip(true);
                    // listOfShip.add(nextShip);
                    coorBoard[generate.getY() + j][generate.getX()].setIsShip(true);
                    board[generate.getY() + j][generate.getX()] = 'x';
                } else {
                    coorBoard[generate.getY()][generate.getX() + j].setIsShip(true);
                    board[generate.getY()][generate.getX() + j] = 'x';
                    // listOfShip.add(nextShip);

                }
            }
            testPrint();
        }

    }

    public static void main(String[] args) {
        initBoard();

        printBoard();
        //
        place();
        printBoard();
        testPrint();

    }

}
