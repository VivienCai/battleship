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
        isParity = new ArrayList<Coordinate>();
        possibleHits = new ArrayList<Coordinate>();
        max = 0;
    }

    public static int getInput(String output) {
    	String input;
    	int xcoord=-1;
    	int finalOutput=-1;
    	while (true) {  //keeps on running until user makes a valid input
	    	System.out.println(output);
	    	System.out.println("Please enter your desired x coordinate, and then press enter.");
	    	input=sc.nextLine();
	    	if(isInt(input)) {
	    		xcoord=Integer.parseInt(input);
	    		if (xcoord>=1 &&xcoord<=10) {
	    			//do smtg to return x value
	    			break;
	    		}else {
	    			System.out.println("Please enter a valid input");
	    		}
	    		
	    	}
	    	else {
	    		System.out.println("Please enter a valid input");
	    	}
    	}
    	int ycoord;
    	while (true) {
	    	System.out.println(output);
	    	System.out.println("Please enter your desired y coordinate, and then press enter.");
    		input=sc.nextLine();
    		if(isInt(input)) {
    			ycoord=Integer.parseInt(input);
    			if (ycoord>=1 &&ycoord<=10) {
	    			//do smtg to return y value
	    			break;
    			}else {
	    			System.out.println("Please enter a valid input");
	    		}
	    		
	    	}
	    	else {
	    		System.out.println("Please enter a valid input");
	    	}
    	
    	}
    	return finalOutput;
    }
    	
    
    
    public static boolean isInt(String input) {
		try {Integer.parseInt(input); //checks if the user input is an int
	            return true; //If it is, return true
	        }
	        catch( Exception e ) {
	            return false;//else, return false
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
                }
            }

            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    if (sum[i][j] == max) {
                        if (isOdd(i, j) == initialIsOdd) {
                            isParity.add(new Coordinate(i,j));
                        } else {
                            possibleHits.add(new Coordinate(i, j));
                        }
                    }
                }
            }


            // for (Coordinate i : possibleHits)  {
            //     System.out.printf("%d, %d ", i.getY(), i.getX());
            // }
            // for (Coordinate i : isParity)  {
            //     System.out.printf("%d, %d ", i.getY(), i.getX());
            // }
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
