// package ics4u_battleship;


// note: add a game class for cleaner code

public class Main {
    // testing array
    static int sum[][] = new int[11][11];
    static char board[][] = new char[11][11];

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
        board[5][5] = 'x';
        board[6][6] = 'x';
        board[4][7] = 'x';
        board[7][4] = 'x';
        board[7][8] = 'x';

        for (int k = 2; k <=5; k++) {
        // HORIZONTAL
            for (int i = 1; i <=10; i++) {
                for (int j = 1; j <= 10-k+1; j++) {
                    // int shipsize = 3;
                    boolean ok = true;
                    for (int g = j; g < j+k; g++) {
                        if (board[i][g]=='x') {
                            ok = false;
                        }
                    }
                    if (ok) {
                        for (int g = j; g < j+k; g++) {
                            sum[i][g]++;
                        }
                    }
                }
            }

            // VERTICAL
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10-k+1; j++) {
                    // int shipsize = 3;
                    boolean ok = true;
                    for (int g = j; g < j+k; g++) {
                        if (board[g][i]=='x') {
                            ok = false;
                        }
                    }
                    if (ok) {
                        for (int g = j; g < j+k; g++) {
                            sum[g][i]++;
                        }
                    }
                }
            }
        }
        for (int i = 1; i <=10; i++) {
            for (int j = 1; j <= 10-3+1; j++) {
                // int shipsize = 3;
                boolean ok = true;
                for (int g = j; g < j+3; g++) {
                    if (board[i][g]=='x') {
                        ok = false;
                    }
                }
                if (ok) {
                    for (int g = j; g < j+3; g++) {
                        sum[i][g]++;
                    }
                }
            }
        }

        // VERTICAL
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10-3+1; j++) {
                // int shipsize = 3;
                boolean ok = true;
                for (int g = j; g < j+3; g++) {
                    if (board[g][i]=='x') {
                        ok = false;
                    }
                }
                if (ok) {
                    for (int g = j; g < j+3; g++) {
                        sum[g][i]++;
                    }
                }
            }
        }
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <=10; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }  

    }

}
