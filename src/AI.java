/* Sarina Li, Vivien Cai, Jiaan Li
* Mon December 20
* ICS4U1
* AI Class
*/

/* IMPORTS 
* Util: For arrayList 
*/

import java.util.*;

// AI class, root of all AI behaviour in our program
public class AI {

    // attributes of our AI Class
    static Scanner sc = new Scanner(System.in);
    static int max = 0;
    static boolean isHunting = false;
    static boolean initialIsOdd = false;

    static Coordinate huntingProbability[][] = new Coordinate[11][11];

    // all arraylists 
    static ArrayList<Coordinate> listOfShip = new ArrayList<Coordinate>();
    static ArrayList<Coordinate> uniqueHitPoints = new ArrayList<Coordinate>();
    static ArrayList<Coordinate> hitPointQueue = new ArrayList<Coordinate>();
    static ArrayList<String> shipsHit = new ArrayList<String>();
    static ArrayList<Coordinate> inParity = new ArrayList<Coordinate>();
    static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>(), isParity = new ArrayList<Coordinate>();
    static ArrayList<Coordinate> pointsHit[] = new ArrayList[7];

    // used to check if a certain square is "odd" or even (for parity)
    public static boolean isOdd(int column, int row) {
        if ((column + row) % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    // for testing, used to print the arrays in good formatting
    public static void printArray(Coordinate array[][]) {
        System.out.println("PROBABILITY GRID: ");
        System.out.print("  ");

        for (int i = 1; i < 11; i++) { 
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

   
    // checks if the input is an integer
    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input); // checks if the user input is an int
            return true; // If it is, return true
        } catch (Exception e) {
            return false;// else, return false
        }
    }

    // checking for a valid ship (must be the following strings for it to be valid)
    public static boolean checkValidShip(String ship) {
        if (ship.equals("BATTLESHIP") || ship.equals("CARRIER") || ship.equals("SUBMARINE") || ship.equals("DESTROYER")
                || ship.equals("CRUISER")) {
            return true;
        }
        return false;
    }

    // reset the array and max, set that coordinate probability to 0, reinitalize all of our arraylists
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
