import java.util.*;

/* Sarina Li, Vivien Cai, Jiaan Li
* Mon December 20
* ICS4U1
* Battleship AI Class
*/

public class AI {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Coordinate> listOfShip = new ArrayList<Coordinate>();
    static boolean isHunting = false;

    static int max = 0;
    static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>(), isParity = new ArrayList<Coordinate>();
    static boolean initialIsOdd = false;
    static ArrayList<Coordinate> inParity = new ArrayList<Coordinate>();

    //end when initial ship  + any other found sunk (when both queues are empty)
    static ArrayList<Coordinate> uniqueHitPoints = new ArrayList<Coordinate>();
    static Coordinate huntingProbability[][] = new Coordinate[11][11];
    static ArrayList<Coordinate> hitPointQueue = new ArrayList<Coordinate>();
    static ArrayList<String> shipsHit = new ArrayList<String>();
    // submarine is index 6
    static ArrayList<Coordinate> pointsHit[] = new ArrayList[7];

    public static boolean isOdd(int column, int row) {
        if ((column + row) % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void printArray(Coordinate array[][]) {
        System.out.println("PROBABILITY GRID: ");
        System.out.print("  ");

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
                Coordinate cur = array[i][j];
                if (cur.getProbability() < 10) {
                    System.out.print("0" + cur.getProbability() + " ");
                } else {
                    System.out.print(cur.getProbability() + " ");
                }
            }
            System.out.println();
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

    

    public static boolean checkValidShip(String ship) {
        if (ship.equals("BATTLESHIP") || ship.equals("CARRIER") || ship.equals("SUBMARINE") || ship.equals("DESTROYER")
                || ship.equals("CRUISER")) {
            return true;
        }
        return false;
    }
    public static void resetArray() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Main.AIAttackBoard[i][j].setProbability(0);
                AI.huntingProbability[i][j].setProbability(0);
            }
        }
        isParity = new ArrayList<Coordinate>();
        AI.hitPointQueue = new ArrayList<Coordinate>();
        possibleHits = new ArrayList<Coordinate>();
        max = 0;
    }

}
