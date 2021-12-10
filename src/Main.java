import java.util.*;

// note: add a game class for cleaner code

public class Main {
    // testing array
    static int sum[][] = new int[11][11];
    static char board[][] = new char[11][11];
    static int max = 0;
    static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>(), isParity = new ArrayList<Coordinate>();
    static boolean initialIsOdd = false;
    // static ArrayList<Coordinate> inParity = new ArrayList<Coordinate>();
    

    
    public static boolean isOdd(int column, int row) {
        if ((column + row)%2==1) {
            return true;
        } else {
            return false;
        }
    }

    public static void printArray() {
        for (int i = 1; i <= 10; i++) {
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
                            sum[i][g]+=2;
                        }
                    }else {
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
                    if (shipSize==3) {
                        for (int g = j; g < j + shipSize; g++) {
                            sum[g][i]+=2;
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
    }

    public static void main(String[] args) {
        // general execution goes here

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                board[i][j] = 'o';
                sum[i][j] = 0;
            }
        }

        // for (int k = 2; k <= 5; k++) {
        //     sumRows(k);
        //     sumColumns(k);
        // }

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the coordinate you want to hit. Enter -1");
            int y= sc.nextInt(),  x = sc.nextInt();
            if (y == -1) {
                break;
            }
            board[y][x] = 'x';
            for (int k = 2; k <= 5; k++) {
                sumRows(k);
                sumColumns(k);
            }
            printArray();
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    max = Math.max(max, sum[i][j]);
                    if (isOdd(i, j) == initialIsOdd) {
                        isParity.add(new Coordinate(i,j));
                    } else {
                        possibleHits.add(new Coordinate(i, j));
                    }
                }
            }
            if (isParity.size() > 0) {
                int randIndex = (int)(Math.random() * isParity.size());
                Coordinate hit = isParity.get(randIndex);
                System.out.printf("You should hit square %c %d.\n", hit.columnIndex(hit.getY()), hit.getX());
            } else {
                int randIndex = (int)(Math.random() * possibleHits.size());
                Coordinate hit = possibleHits.get(randIndex);
                System.out.printf("You should hit square %c %d.\n",hit.columnIndex(hit.getY()), hit.getX());
            }
            resetArray();
        }

        // summing ships from length 2 to 5
        // for (int k = 2; k <= 5; k++) {
        //     sumRows(k);
        //     sumColumns(k);
        // }
        // printArray();

        // for (int i = 1; i <= 10; i++) {
        //     for (int j = 1; j <= 10; j++) {
        //         max = Math.max(max, sum[i][j]);
        //         if (isOdd(i, j) == initialIsOdd) {
        //             isParity.add(new Coordinate(i,j));
        //         } else {
        //             possibleHits.add(new Coordinate(i, j));
        //         }
        //     }
        // }
        // if (isParity.size() > 0) {
        //     int randIndex = (int)(Math.random() * isParity.size());
        //     Coordinate hit = isParity.get(randIndex);
        //     System.out.printf("You should hit square %c %d.\n", hit.columnIndex(hit.getY()), hit.getX());
        // } else {
        //     int randIndex = (int)(Math.random() * possibleHits.size());
        //     Coordinate hit = possibleHits.get(randIndex);
        //     System.out.printf("You should hit square %c %d.\n",hit.columnIndex(hit.getY()), hit.getX());
        // }

    }

}
