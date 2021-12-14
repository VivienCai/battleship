// import java.util.ArrayList;
// import java.util.Scanner;

// public class hunt extends AI{
//     private static int sum[][] = new int[11][11];
//    private  static Scanner sc = new Scanner(System.in);
//    private static int max = 0;
//    private static ArrayList<Coordinate> possibleHits = new ArrayList<Coordinate>();
//    private static int size;
//   //  static Coordinate board[][];
//    private static Coordinate hitPoint;
//    private static boolean shipExists[]=new boolean[5];
//    private	static int rangeL=size-1;
//    private	static int rangeR=size-1;
//    private	static int rangeU=size-1;
//    private	static int rangeD=size-1;
//    private   static char huntBoard[][]=new char[11][11];
//    static int tempsum=0;
   
//     	public static void main(String args[]) {
// 		Coordinate hitTest=new Coordinate(5,5);
// 		initBoard();
// 		initSum();
// 		hunt1(hitTest, huntBoard);
// 	}

	
    
// 	public static void hunt1(Coordinate hitPoint1, char board [][]) {
// 		hitPoint=hitPoint1;
// 		// O - not hit or occupied
// 	    // X - occupied by a ship, hit
// 	    // M - not occupied by a ship, hit
// 	    // S - occupied by a ship, not hit
// 	//	Coordinate board[][]=board1.clone();
// 		exist();  //sets all ships to exist
// 		for (int i=0;i<5;i++) {
// 			if (shipExists[i]) {
// 				if (i==4) {
// 					size=3; //add some sum stuff
//         			System.out.print("hi1");

// 					sumX(size);
// 					sumY(size);
// 				}
// 				else {
// 					size=i+2;
// 					sumX(size);
// 					sumY(size);
// 				}	
// 			}
// 		}
	
// 	}
	

//     public static void sumX(int shipSize) {
//     	nearby(false, false);                  //initialise range values. find nearby occupied spots
//     	nearby(false,true);
    	
    	    	
    	
//     	for(int j=hitPoint.getX()-rangeL;j<(hitPoint.getX()+rangeR)-size;j++) {  //runs for the number of open squares 
//     		if(shipSize==3) {
//     			for (int g=j;g<j;g++) {
//         			sum[g][hitPoint.getY()] +=2;  //i have no clue what this does it was copied from sarinas code and its supposed to sum stuff
//         		}
//     			printArray();
//     			System.out.print("hi2");

//     		}
//     		else{
//     			for (int g=j;g<j;g++) {
//         			sum[g][hitPoint.getY()] +=1;
//         			printArray();
//         			System.out.print("hi");

//         		}
    			
//     		}	
//     	}	    
//     }

//     public static void sumY(int shipSize) {
//     	nearby(true, false);                
//     	nearby(true,true);
    	
    	       
//     	for(int j=hitPoint.getY()-rangeU;j<(hitPoint.getY()+rangeD)-size;j++) {  //runs for the number of open squares 
// 			System.out.println("joe1");

//     		if(size==3) {
//     			for (int g=j;g<j;g++) {
//         			sum[hitPoint.getX()][g] +=2;  //i have no clue what this does it was copied from sarinas code and its supposed to sum stuff
//         			System.out.println("joe2");
//         			tempsum++;
//     			}
//     		}
//     		else{
//     			for (int g=j;g<j;g++) {
//     				System.out.println("joe3");
//         			sum[hitPoint.getX()][g] +=1;
//         			tempsum++;

//     			}
//     		}
    		
//     	}
//     	printArray();
//     	System.out.println(hitPoint.getX());
//     	System.out.println(sum[5][5]);
//     	System.out.println(tempsum);
//     }
	
	
// 	public static void nearby(boolean isV, boolean positive) {
// 		if (isV==false&&positive==true) {                                //any obstructions to the right
// 			for (int i=0;i<size;i++) {
// 				if (board[hitPoint.getX()+i+1][hitPoint.getY()]!='O') {  //if not open   
// 					rangeR=i;                                                                    //ADD EDGE DETECTION AFOISHEFIUSHFIKJSEF
// 				}
// 			}
// 		}
// 		else if (isV==false&&positive==false) {
// 			for (int i=0;i<size;i++) {                                //any obstructions to the left
// 				if (board[hitPoint.getX()-i-1][hitPoint.getY()]!='O') {  //if not open
// 					rangeL=i;
// 				}
// 			}
// 		}
// 		else if (isV==true&&positive==false) {
// 			for (int i=0;i<size;i++) {                                //any obstructions up
// 				if (board[hitPoint.getX()][hitPoint.getY()+i+1]!='O') {  //if not open
// 					rangeU=i;
// 				}
// 			}
// 		}
// 		else if (isV==true&&positive==true) {
// 			for (int i=0;i<size;i++) {                                //any obstructions down
// 				if (board[hitPoint.getX()][hitPoint.getY()-i-1]!='O') {  //if not open
// 					rangeD=i;
// 				}
// 			}
// 		}
		
		
// 	}
	
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
    
    
//     public static void exist() {
//     	for (int a=0;a<5;a++) {
//     		shipExists[a]=true;
//     	}
//     }
//     public static int getInputX() {
//         String input;
//         int coord = -1;
//         while (true) { // keeps on running until user makes a valid input
//             input = sc.nextLine();
//             if (isInt(input)) {
//                 coord = Integer.parseInt(input);
//                 if (coord >= 1 && coord <= 10) {
//                     return coord;
//                 } else {
//                     System.out.println("Please enter a valid input");
//                 }

//             } else {
//                 System.out.println("Please enter a valid input");
//             }
//         }
//     }

//     public static char getInputY() {
//         // String input;
//         char c;
//         char coord = 'x';
//         while (true) { // keeps on running until user makes a valid input
//             // System.out.println(output);
//             c = sc.nextLine().charAt(0);
//             coord = (char)((c - 'A') + 1);
//             if (coord >= 1 && coord <= 10) {
//                 return coord;
//             } else {
//                 System.out.println("Please enter a valid input");
//             }
//         }
//     }

//     public static boolean isInt(String input) {
//         try {
//             Integer.parseInt(input); // checks if the user input is an int
//             return true; // If it is, return true
//         } catch (Exception e) {
//             return false;// else, return false
//         }
//     }
    
//     public static void initBoard() {
//     	for (int i=0;i<11;i++) {
//     		for (int j=0;j<11;j++) {
//     			huntBoard[i][j]='O';
//     		}
//     	}
//     }
    
//     public static void initSum() {
//     	for (int i=0;i<11;i++) {
//     		for (int j=0;j<11;j++) {
//     			sum[i][j]=0;
//     		}
//     	}
//     }

// }
