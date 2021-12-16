import java.util.*;

public class AI {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Coordinate> listOfShip = new ArrayList<Coordinate>();
    static boolean isHunting = false;

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

    // checks if the ship will overlap any other coordinates that are already occupied
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
            int y = home.getY();
            int x = home.getX();
            for (int j = 1; j < shipSize; j++) {
                home.setIsShip(true);
                coorBoard[y][x].setIsShip(true);
                Game.AIMapOfCoor.replace(coorBoard[y][x].toString(), ship);
                // cur.setIsShip(true);
                // if the orientation is vertical
                if (orientationV) {
                    coorBoard[y + j][x].setIsShip(true);
                    int newY = y+j;
                    String key = Game.getAccessKey(newY, x);
                    Game.AIMapOfCoor.replace(key, ship);

                } else {
                    coorBoard[y][x + j].setIsShip(true);
                    int newX= x+j;
                    String key = Game.getAccessKey(y, newX);
                    Game.AIMapOfCoor.replace(key, ship);

                }
            }
        }

    }
    //HITTING ALGORITHM
    // static int sum[][] = new int[11][11];
    static int max = 0;
    static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>(), isParity = new ArrayList<Coordinate>();
    static boolean initialIsOdd = false;
    static ArrayList<Coordinate> inParity = new ArrayList<Coordinate>();

    public static void findProbability() {
        for (int i = 2; i <= 5; i++) {
            sumColumns(i);
            sumRows(i);
        }
        // printArray();
        AIhit();
        printArray();
        resetArray();
    }

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
                Coordinate cur = Main.AIAttackBoard[i][j];
                if (cur.getProbability() < 10) {
                    System.out.print("0" + cur.getProbability() + " ");
                } else {
                    System.out.print(cur.getProbability() + " ");
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
                    Coordinate cur = Main.AIAttackBoard[i][g];
                    if (cur.getIsHit()) { // if missed point
                        ok = false;
                    }
                }
                if (ok) {
                    if (shipSize == 3) {
                        for (int g = j; g < j + shipSize; g ++) {
                            Main.AIAttackBoard[i][g].setProbability(Main.AIAttackBoard[i][g].getProbability() + 2);
                        }
                    } else {
                        for (int g = j; g < j + shipSize; g++) {
                            Main.AIAttackBoard[i][g].setProbability(Main.AIAttackBoard[i][g].getProbability() + 1);
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
                    if (Main.AIAttackBoard[g][i].getIsHit()) {
                        ok = false;
                    }
                }
                if (ok) {
                    if (shipSize == 3) {
                        for (int g = j; g < j + shipSize; g++) {
                            Main.AIAttackBoard[g][i].setProbability(Main.AIAttackBoard[g][i].getProbability()+2);
                        }
                    } else {
                        for (int g = j; g < j + shipSize; g++) {
                            Main.AIAttackBoard[g][i].setProbability(Main.AIAttackBoard[g][i].getProbability()+1);
                        }
                    }
                }
            }
        }
    }

    public static void resetArray() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Main.AIAttackBoard[i][j].setProbability(0);
            }
        }
        isParity = new ArrayList<Coordinate>();
        possibleHits = new ArrayList<Coordinate>();
        max = 0;
    }

    public static int getInputX() {
        String input;
        int coord = -1;
        while (true) { // keeps on running until user makes a valid input
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

    public static char getInputY() {
        // String input;
        char c;
        char coord = 'x';
        while (true) { // keeps on running until user makes a valid input
            // System.out.println(output);
            c = sc.nextLine().charAt(0);
            coord = (char) ((c - 'A') + 1);
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
    // summing ships from length 2 to 5

    // printArray();

    public static void AIhit() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = Main.AIAttackBoard[i][j];
                max = Math.max(max, cur.getProbability());
            }
        }
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = Main.AIAttackBoard[i][j];
                if (cur.getProbability() == max) {
                    if (isOdd(i, j) == initialIsOdd) {
                        isParity.add(new Coordinate(i, j));
                    } else {
                        possibleHits.add(new Coordinate(i, j));
                    }
                }
            }
        }

        if (isParity.size() > 0) {
            int randIndex = (int) (Math.random() * isParity.size());
            Coordinate hit = isParity.get(randIndex);
            int y = hit.getY(), x = hit.getX();
            Main.AIAttackBoard[y][x].setIsHit(true);
            System.out.printf("The AI hit coordinate %c%d\n", hit.columnIndex(y), x);
            System.out.println("Is it a hit or miss?");
            getInput();

        } else {
            int randIndex = (int) (Math.random() * possibleHits.size());
            Coordinate hit = possibleHits.get(randIndex);
            int y = hit.getY(), x = hit.getX();
            Main.AIAttackBoard[y][x].setIsHit(true);
            System.out.printf("The AI hit coordinate %c%d\n", hit.columnIndex(y), x);
            System.out.println("Is it a hit or miss?");
            getInput();
        }
    }
    public static void getInput(){
        while (true) {
            
            String input = sc.nextLine();
            String ship = input.substring(4);
            
            if (input.equals("MISS")) {
                isHunting = false;
                break;
            }
            else if (input.substring(0, 3).equals("HIT")) {
                if (checkValidShip(ship)) {
                   isHunting = true;
                }
                break;
            }
            else{
                System.out.println("that is not a valid input. Is it a hit or miss?");
            }
        }
    }

    private static boolean checkValidShip(String ship) {
        if (ship.equals("BATTLESHIP")|| ship.equals("CARRIER") || ship.equals("SUBMARINE") || ship.equals("DESTROYER") || ship.equals("CRUISER") ){
            return true;
        } 
        return false;
    }



}
