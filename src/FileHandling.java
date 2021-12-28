import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandling {

	public static void saveBoards(int fileNumber) throws Exception{
    	PrintWriter text2 = new PrintWriter("Grids"+fileNumber+".txt");
    	
    	//Prints the AI placement board
    	for (int i = 0; i <= 10; i++) {    
            for (int j = 1; j <= 10; j++) {
                if (i == 0) {
                    continue;
                } else {
                    Coordinate cur = Main.AIPlacementBoard[i][j];
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit();
                    if (isHit && isShip) {
                        text2.print("X ");
                    } else if (isHit && !isShip) {
                    	text2.print("M ");
                    } else if (!isHit && isShip) {
                    	text2.print("S ");
                    } else {
                    	text2.print("O ");
                    }
                }

            }
            text2.println();
        }
    	
    	//Prints the AI attack board
    	for (int i = 0; i <= 10; i++) {    
            for (int j = 1; j <= 10; j++) {
                if (i == 0) {
                    continue;
                } else {
                    Coordinate cur = Main.AIAttackBoard[i][j];
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit();
                    if (isHit && isShip) {
                        text2.print("X ");
                    } else if (isHit && !isShip) {
                    	text2.print("M ");
                    } else if (!isHit && isShip) {
                    	text2.print("S ");
                    } else {
                    	text2.print("O ");
                    }
                }

            }
            text2.println();
    	}
    	
    	
    	//Prints the Player home board

    	
    	//Prints the AI attack board

    	
    	// Finish printing to battleship1
    	
    	
    	//ADD FILE PARINT PLACEMENT ARRAY METHOD KMSKMSKMSKMS
    	
    	
    	text2.close();
    	
    }
    /*
     * Format for saving game
     * 1st line will have number of AI shot, hit and miss   EX. 10 1 9
     * 2nd line will have number of Player shot hit and miss
     * 3rd line will have types of ships alive for AI
     * 4th type of ship alive for player
     * 
     *            BELOW WILL HAVE DIFFERENT TEXT FILES CALLED 1 2 3 
     * 5th will have AI attack board
     * 6th will have Player Home board
     * 7th will have AI attack board
    
    */
    
 public static void filePlacementArray(Coordinate array[][]) throws Exception{      //USER ATTACK BOARD
	 
 		PrintWriter text = new PrintWriter("Battleship.txt");

    	
        for (int i = 0; i <= 10; i++) {
            char ind = (char) ('A' + i - 1);
            if (i == 0) {
                for (int j = 0; j <= 10; j++) {
                    System.out.print(j + " ");
                }
            }
            if (i > 0) {
                System.out.print(ind + " ");
            }
            for (int j = 1; j <= 10; j++) {
                if (i == 0) {
                    continue;
                } else {
                    Coordinate cur = array[i][j];
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit();
                    if (isHit && isShip) {
                        System.out.print("X ");
                    } else if (isHit && !isShip) {
                        System.out.print("M ");
                    } else if (!isHit && isShip) {
                        System.out.print("S ");
                    } else {
                        System.out.print("O ");
                    }
                }

            }
            System.out.println();
        }
    }	
	
 public static void resumeGame(int fileNumber) throws Exception{
 	System.out.println("Please choose a file number to resume from");
 	File infoFile = new java.io.File("/Users/ellenzhu/eclipse-workspace/ics4u_battleship/info"+fileNumber+".txt");                                                   //miht not work for diff comps
		Scanner fsc = new Scanner(infoFile);
		Main.AIShot= fsc.nextInt();
		Main.AIHit=fsc.nextInt();
		Main.AIMiss=fsc.nextInt();
		Main.PlayerShot=fsc.nextInt();
		Main.PlayerHit=fsc.nextInt();
		Main.PlayerMiss=fsc.nextInt();
		
		while(fsc.hasNext()) {
			String name=fsc.next();
			boolean alive;
			if (fsc.next().equals("ALIVE")) {
				alive=true;
			}
			else {
				alive=false;
			}
			int size=fsc.nextInt();
			int xCoord=fsc.nextInt();
			int yCoord=fsc.nextInt();
			boolean vertical=fsc.nextBoolean();
			int timesHit=fsc.nextInt();
			
			Coordinate coord=new Coordinate(xCoord, yCoord);
			
			Ship ship=new Ship( vertical, size, coord);

		}
		
		
	//	Ship.getList().add();
		
 	

 }
 
	public static void resumeBoards(int fileNumber) throws Exception {
		File gridFile = new java.io.File("/Users/ellenzhu/eclipse-workspace/ics4u_battleship/Grids"+fileNumber+".txt ");													//same with this
		Scanner gridFileScan = new Scanner(gridFile);
	}
	
}
