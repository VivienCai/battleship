// package ics4u_battleship;

// note: add a game class for cleaner code

public class Main {
    // testing array
    static int sum[][] = new int[11][11];
    static char board[][] = new char[11][11];
    static int max = 0;
    
    public static boolean isOdd(int column, int row) {
        if ((column + row)%2==1) {
            return true;
        } else {
            return false;
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

        // trying summing with 3 ship

        // vertical then horiztonal
        // board[5][5] = 'x';
        // board[6][6] = 'x';
        // board[4][7] = 'x';
        // board[7][4] = 'x';
        // board[7][8] = 'x';
        // board[3][4] = 'x';
        // board[4][3] = 'x';
        // board[8][7] = 'x';
        // board[5][9] = 'x';

        // summing ships from length 2 to 5
        for (int k = 2; k <= 5; k++) {
            // HORIZONTAL
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10 - k + 1; j++) {
                    // int shipsize = 3;
                    boolean ok = true;
                    for (int g = j; g < j + k; g++) {
                        if (board[i][g] == 'x') { // if missed point
                            ok = false;
                        }
                    }
                    if (ok) {
                        for (int g = j; g < j + k; g++) {
                            sum[i][g]++;
                        }
                    }
                }
            }

            // VERTICAL
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10 - k + 1; j++) {
                    // int shipsize = 3;
                    boolean ok = true;
                    for (int g = j; g < j + k; g++) {
                        if (board[g][i] == 'x') {
                            ok = false;
                        }
                    }
                    if (ok) {
                        for (int g = j; g < j + k; g++) {
                            sum[g][i]++;
                        }
                    }
                }
            }
        }
        // since there are 2 ships of length 3
        // horizontal
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10 - 3 + 1; j++) {
                // int shipsize = 3;
                boolean ok = true;
                for (int g = j; g < j + 3; g++) {
                    if (board[i][g] == 'x') {
                        ok = false;
                    }
                }
                if (ok) {
                    for (int g = j; g < j + 3; g++) {
                        sum[i][g]++;
                    }
                }
            }
        }

        // VERTICAL
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10 - 3 + 1; j++) {
                // int shipsize = 3;
                boolean ok = true;
                for (int g = j; g < j + 3; g++) {
                    if (board[g][i] == 'x') {
                        ok = false;
                    }
                }
                if (ok) {
                    for (int g = j; g < j + 3; g++) {
                        sum[g][i]++;
                    }
                }
            }
        }
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

}
