import java.util.*;

public class Game {
    // O - not hit or occupied
    // X - occupied by a ship, hit
    // M - not occupied by a ship, hit
    // S - occupied by a ship, not hit

    public static HashMap<Coordinate, String> playerMapOfCoor = new HashMap<Coordinate, String>();

    public static void printPlacementArray(Coordinate array[][]) {
        for (int i = 0; i <= 10; i++) {
            char ind = (char) ('A' + i - 1);
            if (i == 0) {
                for (int j = 0; j <= 10; j++) {
                    System.out.print(j + " ");
                }
            }
            if (i > 0) {
                System.out.print(ind + " ");
            }
            for (int j = 1; j <= 10; j++) {
                if (i == 0) {
                    continue;
                } else {
                    Coordinate cur = array[i][j];
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit();
                    if (isHit && isShip) {
                        System.out.print("X ");
                    } else if (isHit && !isShip) {
                        System.out.print("M ");
                    } else if (!isHit && isShip) {
                        System.out.print("S ");
                    } else {
                        System.out.print("O ");
                    }
                }

            }
            System.out.println();
        }
    }

    // loop through all 5 ships
    // limit the range where they can place the ships based on its size
    // ask for vertical vs horizontal orientation and "home coordinate"
    // place each ship and assign the home coordinate for the ship and check if
    // there are any ships where it is trying to be placed
    // create the ship class and put it in the arraylist
    static Scanner sc = new Scanner(System.in);

    public static void placeShip() {
        for (int i = 2; i <= 6; i++) {
            int shipSize = i;
            if (i == 6) {
                shipSize = 3;
            }
            System.out.printf("You are currently placing a ship that is %d units long.\n", shipSize);
            System.out.println(
                    "What orientation do you want the ship to be? Write 1 for vertical and anything else for horizontal.");
            boolean isV;
            char input = sc.next().charAt(0);
            if (input == '1') {
                isV = true;
            } else {
                isV = false;
            }

            System.out.println("Please enter an uppercase letter from A-J for your y coordinate. ");
            int y = AI.getInputY();
            System.out.println("Please enter a number from 1-10 for your x coordinate. ");
            int x = AI.getInputX();
            boolean inBounds = false;

            if (isV) {
                inBounds = boundsCheck(y, isV, shipSize);
            } else {
                inBounds = boundsCheck(x, isV, shipSize);
            }

            if (!inBounds) {
                System.out.println("Your ship is out of bounds. Please choose another point to place your ship.");
                i--;
                continue;
            } else {

                Coordinate home = Main.playerPlacementBoard[y][x];
                if (AI.anyGeneratedIsShip(home, isV, shipSize, Main.playerPlacementBoard)) {
                    System.out.println(
                            "Your ship overlaps a previous placed ship. Please choose another point to place your ship.");
                    i--;
                } else {
                    Ship ship = new Ship(isV, shipSize, home);
                    playerPlaceShip(home, shipSize, isV, ship);
                    for (int j = 1; j < shipSize; j++) {
                        home.setIsShip(true);
                        playerMapOfCoor.replace(home, ship.getName());
                        Main.playerPlacementBoard[home.getY()][home.getX()].setIsShip(true);

                        // if the orientation is vertical
                        if (isV) {
                            Coordinate cur = Main.playerPlacementBoard[home.getY() + j][home.getX()];
                            playerMapOfCoor.replace(cur, ship.getName());
                            cur.setIsShip(true);

                        } else {
                            Coordinate cur = Main.playerPlacementBoard[home.getY()][home.getX() + j];
                            playerMapOfCoor.replace(cur, ship.getName());
                            cur.setIsShip(true);
                        }
                    }
                }
            }
            System.out.println("Your placement board:");
            printPlacementArray(Main.playerPlacementBoard);
        }

    }

    public static boolean boundsCheck(int n, boolean isV, int size) {
        if (n <= 11 - size) {
            return true;
        } else {
            return false;
        }

    }

    public static void playerPlaceShip(Coordinate homeCoord, int size, boolean isV, Ship ship) {
        Ship.getList().add(ship);

    }

}
