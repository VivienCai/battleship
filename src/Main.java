import java.util.*;

// note: add a game class for cleaner code

public class Main {
    // testing array
    static Scanner sc = new Scanner(System.in);

    static int sum[][] = new int[11][11];
    static char board[][] = new char[11][11];
    static int max = 0;
    static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>(), isParity = new ArrayList<Coordinate>();
    static boolean initialIsOdd = false;
    // static ArrayList<Coordinate> inParity = new ArrayList<Coordinate>();

    public static boolean isOdd(int column, int row) {
        if ((column + row) % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void printArray() {
        System.out.print("   ");

        for (int i = 1; i < 11; i++) { // for the bar at the top
            System.out.print(i + ("  "));
        }
        System.out.println();
        System.out.println("_______________________________");
        char c = 'a';
        for (int i = 1; i <= 10; i++) {

            System.out.print(c + " ");
            c++;
            for (int j = 1; j <= 10; j++) {
                if (sum[i][j] < 10) {
                    System.out.print("0" + sum[i][j] + " ");
                } else {
                    System.out.print(sum[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void sumRows(int shipSize) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10 - shipSize + 1; j++) {
                // int shipsize = 3;
                boolean ok = true;
                for (int g = j; g < j + shipSize; g++) {
                    if (board[i][g] == 'x') { // if missed point
                        ok = false;
                    }
                }
                if (ok) {
                    if (shipSize == 3) {
                        for (int g = j; g < j + shipSize; g++) {
                            sum[i][g] += 2;
                        }
                    } else {
                        for (int g = j; g < j + shipSize; g++) {
                            sum[i][g]++;
                        }
                    }
                }
            }
        }
    }

    public static void sumColumns(int shipSize) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10 - shipSize + 1; j++) {
                boolean ok = true;
                for (int g = j; g < j + shipSize; g++) {
                    if (board[g][i] == 'x') {
                        ok = false;
                    }
                }
                if (ok) {
                    if (shipSize == 3) {
                        for (int g = j; g < j + shipSize; g++) {
                            sum[g][i] += 2;
                        }
                    } else {
                        for (int g = j; g < j + shipSize; g++) {
                            sum[g][i]++;
                        }
                    }
                }
            }
        }
    }

    public static void resetArray() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                sum[i][j] = 0;
            }
        }
        isParity = new ArrayList<Coordinate>();
        possibleHits = new ArrayList<Coordinate>();
        max = 0;
    }

    public static int getInputx(String output) {
        String input;
        int coord = -1;
        while (true) { // keeps on running until user makes a valid input
            System.out.println(output);
            input = sc.nextLine();
            if (isInt(input)) {
                coord = Integer.parseInt(input);
                if (coord >= 1 && coord <= 10) {
                    return coord;
                } else {
                    System.out.println("Please enter a valid input");
                }

            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    public static int getInputy(String output) {
        String input;
        char c;
        int coord = -1;
        while (true) { // keeps on running until user makes a valid input
            System.out.println(output);
            input = sc.nextLine();
            c = input.charAt(0);
            coord = (c - 'a') + 1;
            if (coord >= 1 && coord <= 10) {
                return coord;
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input); // checks if the user input is an int
            return true; // If it is, return true
        } catch (Exception e) {
            return false;// else, return false
        }
    }

    static char playerBoard[][] = new char[11][11];
    static char playerHits[][] = new char[11][11];

    public static void main(String[] args) {
        // general execution goes here

        // for (int i = 1; i <= 10; i++) {
        // for (int j = 1; j <= 10; j++) {
        // board[i][j] = 'o';
        // sum[i][j] = 0;
        // }
        // }

        // // for (int k = 2; k <= 5; k++) {
        // // sumRows(k);
        // // sumColumns(k);
        // // }

        // while (true) {
        // int y = getInputy("Please enter your desired y value (lowercase letter)");
        // int x= getInputx("Please enter your desired x value (number)");
        // if (y == -1) {
        // break;
        // }
        // board[y][x] = 'x';
        // for (int k = 2; k <= 5; k++) {
        // sumRows(k);
        // sumColumns(k);
        // }
        // printArray();
        // for (int i = 1; i <= 10; i++) {
        // for (int j = 1; j <= 10; j++) {
        // max = Math.max(max, sum[i][j]);
        // }
        // }

        // for (int i = 1; i <= 10; i++) {
        // for (int j = 1; j <= 10; j++) {
        // if (sum[i][j] == max) {
        // if (isOdd(i, j) == initialIsOdd) {
        // isParity.add(new Coordinate(i,j));
        // } else {
        // possibleHits.add(new Coordinate(i, j));
        // }
        // }
        // }
        // }

        // // for (Coordinate i : possibleHits) {
        // // System.out.printf("%d, %d ", i.getY(), i.getX());
        // // }
        // // for (Coordinate i : isParity) {
        // // System.out.printf("%d, %d ", i.getY(), i.getX());
        // // }
        // if (isParity.size() > 0) {
        // int randIndex = (int)(Math.random() * isParity.size());
        // Coordinate hit = isParity.get(randIndex);
        // System.out.printf("You should hit square %c %d.\n",
        // hit.columnIndex(hit.getY()), hit.getX());
        // } else {
        // int randIndex = (int)(Math.random() * possibleHits.size());
        // Coordinate hit = possibleHits.get(randIndex);
        // System.out.printf("You should hit square %c
        // %d.\n",hit.columnIndex(hit.getY()), hit.getX());
        // }
        // resetArray();
        // }

        // loop through all 5 ships
        // limit the range where they can place the ships based on its size
        // ask for vertical vs horizontal orientation and "home coordinate"
        // place each ship and assign the home coordinate for the ship and check if
        // there are any ships where it is trying to be placed
        // create the ship class and put it in the arraylist

        Coordinate testar[][] = new Coordinate[11][11];
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                testar[i][j] = new Coordinate(i, j);
            }
        }

        Ship testing = new Ship(true, 3, new Coordinate(1, 3));
        Ship.getList().add(testing);

        occupyArray(testar);

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (testar[i][j].getIsShip() == true) {
                    System.out.printf("x ");
                } else {
                    System.out.printf("o ");
                }

            }
            System.out.println();
        }

    }

    public void playerPlaceShip(Coordinate homeCoord, int size, boolean isV) {
        Ship.getList().add(new Ship(isV, size, homeCoord));
    }

    public static void occupyArray(Coordinate playerPlacement[][]) {
        for (Ship currentShip : Ship.getList()) {
            Coordinate homeCoord = currentShip.getHomeCoord();
            int homeY = homeCoord.getY();
            int homeX = homeCoord.getX();
            int size = currentShip.getSize();
            if (currentShip.getVertical() == false) {
                for (int j = homeX; j < homeX + size; j++) {
                    Coordinate current = new Coordinate(homeY, j);
                    current.occupyShip();
                    playerPlacement[homeY][j] = current;
                }

            } else {
                for (int j = homeY; j < homeY + size; j++) {
                    Coordinate current = new Coordinate(j, homeX);
                    current.occupyShip();
                    playerPlacement[j][homeX] = current;
                }
            }
        }
    }

}
