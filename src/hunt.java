// import java.util.ArrayList;
// import java.util.Scanner;

public class hunt extends AI{
   private static int sum[][] = new int[11][11];
   private  static Scanner sc = new Scanner(System.in);
   private static int max = 0;
   private static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>();
   private static int size;
  //  static Coordinate board[][];
   private static Coordinate hitPoint;
   private static boolean shipExists[]=new boolean[5];
   private	static int rangeL=size-1;
   private	static int rangeR=size-1;
   private	static int rangeU=size-1;
   private	static int rangeD=size-1;
   private  static char board[][]=new char[11][11];
   private static int timesHit=0;
   private static int timesMissed=0;
   private static boolean didHit;
   private static boolean sunk=false;
    	public static void main(String args[]) {  //testing stuff
		Coordinate hitTest=new Coordinate(3,2);                              //CHANGE THIS TO FIND WHICH POINT YOU WANT TO HIT
		initBoard();
		board[5][4]='y';                                                       //CHANGE THIS TO SIMULATE ALREADY HIT OR MISSED POINTS


		printBoard();
		hunt1(hitTest, board);
		printArray();
	}

	
    //actual hunt code
	public static void hunt1(Coordinate hitPoint1, char board [][]) {
		initSum();
		hitPoint=hitPoint1;
		// O - not hit or occupied
	    // X - occupied by a ship, hit
	    // M - not occupied by a ship, hit
	    // S - occupied by a ship, not hit
	//	Coordinate board[][]=board1.clone();
		exist();  //sets all ships to exist
		for (int i=0;i<5;i++) {
			if (shipExists[i]) {
				if (i==4) {
					size=3;              //sums the possible ship locations

					sumX(size);
					sumY(size);
				}
				else {
					size=i+2;
					sumX(size);
					sumY(size);
				}	
			}
		}
		sum[hitPoint.getY()][hitPoint.getX()]=0;   //makes the value of hit point=0 so ai wont hit there again
		
		for (int i = 1; i <= 10; i++) {     //find the max number of the array
			for (int j = 1; j <= 10; j++) {
				max = Math.max(max, sum[i][j]);
				System.out.println(max);
				}
			}
		  for (int i = 1; i <= 10; i++) {
			  for (int j = 1; j <= 10; j++) {
				  if (sum[i][j] == max) {
					  possibleHits.add(new Coordinate(i, j));	  
				  }
			  }
		  }	
      int randIndex = (int) (Math.random() * possibleHits.size());
      System.out.println(randIndex);
      Coordinate hit = possibleHits.get(randIndex);
      System.out.printf("The AI hit coordinate %c%d\n", hit.columnIndex(hit.getY()), hit.getX()); //displaying hit place
      
      System.out.println("Did your ship get hit? Please enter yes or no");
      String temp=sc.nextLine();
	  if (temp.equals("yes")) {
		  didHit=true;
	  }
	  else {
		  didHit=false;
	  }
	  
	  //start of 2nd or more time hitting
	  while (!sunk) {  //will keep on hitting until ship is sunk
		  
		  
		  
	  }
	  
}
	
	
	
	

//     public static void sumX(int shipSize) {
//     	nearby(false, false);                  //initialise range values. find nearby occupied spots
//     	nearby(false,true);
    	
    //	 System.out.println(rangeL+" "+rangeR);   	
    //	rangeR=0;
    	for(int j=hitPoint.getX()-rangeL;j<hitPoint.getX()+rangeR-size+2;j++) {  //runs for the number of open squares 
    		for (int g=j;g<j+shipSize;g++) {     //i have no clue what this does it was copied from sarinas code and its supposed to sum stuff
    			if (g>10) {
    				break;
    			}
    			sum[hitPoint.getY()][g] +=1;

        		}
    	}	    
    }

    public static void sumY(int shipSize) {
    	nearby(true, false);                
    	nearby(true,true);
    	       
    	for(int j=hitPoint.getY()-rangeU;j<(hitPoint.getY()+rangeD)-size+2;j++) {  //runs for the number of open squares 
    		for (int g=j;g<j+shipSize;g++) {
    			if (g>10) {
    				break;
    			}

       			sum[g][hitPoint.getX()] +=1;

    			}
    		}
    }
	
    
    
    
	//below code could be shortened, I am just too lazy
	public static void nearby(boolean isV, boolean positive) {
		char boardValue;
		int boardAscii;
		int targetAscii=79;
		if (isV==false&&positive==true) {                                //any obstructions to the right
			for (int i=1;i<size;i++) {
				if (hitPoint.getX()+i>10) { //makes sure code doesnt crash
					rangeR=i-1;
					break;
				}
				boardValue=board[hitPoint.getY()][hitPoint.getX()+i];
				boardAscii=boardValue;
				if (targetAscii==boardAscii) {  //if not open  	                                                 //ADD EDGE DETECTION AFOISHEFIUSHFIKJSEF
					rangeR=size-1;
				}
				else {
					rangeR=i-1;
					break;
				}
			}
		}
		else if (isV==false&&positive==false) {
			
			for (int i=1;i<size;i++) {  //any obstructions to the left
				if (hitPoint.getX()-i<1) {
					rangeL=i-1;
					break;
				}
				boardValue=board[hitPoint.getY()][hitPoint.getX()-i];
				boardAscii=boardValue;
				if (targetAscii==boardAscii) {  //if not open  	                                                 //ADD EDGE DETECTION AFOISHEFIUSHFIKJSEF
					rangeL=size-1;
				}
				else {
					rangeL=i-1;
					break;

				}
			}
		}
		else if (isV==true&&positive==true) {
			
			for (int i=1;i<size;i++) {                                //any obstructions up
				if (hitPoint.getY()-i<1) {
					rangeU=i-1;
					break;
				}
				boardValue=board[hitPoint.getY()-i][hitPoint.getX()];
				boardAscii=boardValue;
				if (targetAscii==boardAscii) {  //if not open  	                                                 //ADD EDGE DETECTION AFOISHEFIUSHFIKJSEF
					rangeU=size-1;

				}
				else {
					rangeU=i-1;
					break;

				}
			}
		}
		else if (isV==true&&positive==false) {
			
			for (int i=1;i<size;i++) {    //any obstructions down
				if (hitPoint.getY()+i>10) {
					rangeD=i-1;
					break;
				}
				boardValue=board[hitPoint.getY()+i][hitPoint.getX()];
				boardAscii=boardValue;
				if (targetAscii==boardAscii) {  //if not open  	                                                 //ADD EDGE DETECTION AFOISHEFIUSHFIKJSEF
					rangeD=size-1;

				}
				else {
					rangeD=i-1;
					break;

				}
			}
		}	
	}
	
//     public static void printArray() {
//         System.out.print("   ");

//         for (int i = 1; i < 11; i++) { // for the bar at the top
//             System.out.print(i + ("  "));
//         }
//         System.out.println();
//         System.out.println("_______________________________");
//         char c = 'a';
//         for (int i = 1; i <= 10; i++) {

//             System.out.print(c + " ");
//             c++;
//             for (int j = 1; j <= 10; j++) {
//                 if (sum[i][j] < 10) {
//                     System.out.print("0" + sum[i][j] + " ");
//                 } else {
//                     System.out.print(sum[i][j] + " ");
//                 }
//             }
//             System.out.println();
//         }
//     }
    
    
    public static void exist() {
    	for (int a=0;a<5;a++) {
    		shipExists[a]=true;
    	}
    }
    
    public static void initBoard() {
    	for (int i=0;i<11;i++) {
    		for (int j=0;j<11;j++) {
    			board[i][j]='O';
    		}
    	}
    }
    
    public static void initSum() {
    	for (int i=0;i<11;i++) {
    		for (int j=0;j<11;j++) {
    			sum[i][j]=0;
    		}
    	}
    }
    public static void copyArray() {
    	
    }
    public static void printBoard() {
    	for (int i=1;i<11;i++) {
    		for(int j=1;j<11;j++) {
    			System.out.print(board[i][j]+" ");
    		}
    		System.out.println();
    	}
    }

// }
