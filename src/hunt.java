import java.util.ArrayList;
import java.util.Scanner;

public class hunt extends AI{
    static int sum[][] = new int[11][11];
    static Scanner sc = new Scanner(System.in);
    static int max = 0;
    static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>();
    static int size;
  //  static Coordinate board[][];
    static Coordinate hitPoint;
    static boolean shipExists[]=new boolean[5];
	static int rangeL=size-1;
	static int rangeR=size-1;
	static int rangeU=size-1;
	static int rangeD=size-1;
    
    
    
	public static void hunt1(Coordinate hitPoint1, Coordinate board [][]) {
		hitPoint=hitPoint1;
		// O - not hit or occupied
	    // X - occupied by a ship, hit
	    // M - not occupied by a ship, hit
	    // S - occupied by a ship, not hit
	//	Coordinate board[][]=board1.clone();
		for (int i=0;i<5;i++) {
			if (shipExists[i]) {
				if (i==4) {
					size=3; //add some sum stuff
				}
				else {
					size=i+2;
				}
				
				
				
			}
		}
		
		
		
	}
	

    public static void sumX(int shipSize) {
    	nearby(false, false);                  //initialise range values. find nearby occupied spots
    	nearby(false,true);
    	
    	
    	int totalRange = rangeL+rangeR+1;
        for (int i = 1; i <= totalRange; i++) {
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

    public static void sumY(int shipSize) {
    	int totalRange = rangeU+rangeD+1;
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
	
	
	public static void nearby(boolean isV, boolean positive) {
		if (isV==false&&positive==true) {                                //any obstructions to the right
			for (int i=0;i<size;i++) {
				if (board[hitPoint.getX()+i+1][hitPoint.getY()]!='O') {  //if not open   
					rangeR=i;                                                                    //ADD EDGE DETECTION AFOISHEFIUSHFIKJSEF
				}
			}
		}
		else if (isV==false&&positive==false) {
			for (int i=0;i<size;i++) {                                //any obstructions to the left
				if (board[hitPoint.getX()-i-1][hitPoint.getY()]!='O') {  //if not open
					rangeL=i;
				}
			}
		}
		else if (isV==true&&positive==false) {
			for (int i=0;i<size;i++) {                                //any obstructions up
				if (board[hitPoint.getX()][hitPoint.getY()+i+1]!='O') {  //if not open
					rangeU=i;
				}
			}
		}
		else if (isV==true&&positive==true) {
			for (int i=0;i<size;i++) {                                //any obstructions down
				if (board[hitPoint.getX()][hitPoint.getY()-i-1]!='O') {  //if not open
					rangeD=i;
				}
			}
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
    
    
    public static void exist() {
    	for (int a=0;a<5;a++) {
    		shipExists[a]=true;
    	}
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

}
