import java.util.*;

public class Hit extends AI{
    static Scanner sc = new Scanner(System.in);

    static int sum[][] = new int[11][11];
    // static char board[][] = new char[11][11];
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
            coord = (char)((c - 'A') + 1);
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

    // static char playerBoard[][] = new char[11][11];
    // static char playerHits[][] = new char[11][11];

}