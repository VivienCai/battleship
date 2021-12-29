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
		int counter2 =0;
		while(fsc.hasNext()) {                        //resuming playerhit board
			
			String name=fsc.next();
			if (name.equals("PLAYER")) {
				break;                                //detects when it is the player board
			}
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
			System.out.println(xCoord+" "+yCoord);
			
			Coordinate coord=new Coordinate(xCoord, yCoord);
			Ship ship=new Ship( vertical, size, coord);
			
			if (alive) {
				Main.getShipsAlive().add(counter2, ship);
			}
			
			
			for (int j = 1; j < size; j++) {
                coord.setIsShip(true);
                Main.AIPlacementBoard[yCoord][xCoord].setIsShip(true);
                Game.AIMapOfCoor.replace(Main.AIPlacementBoard[yCoord][xCoord].toString(), ship);
                // cur.setIsShip(true);
                // if the orientation is vertical
                if (vertical) {
                    Main.AIPlacementBoard[yCoord + j][xCoord].setIsShip(true);
                    int newY = yCoord + j;
                    String key = Game.getAccessKey(newY, xCoord);
                    Game.AIMapOfCoor.replace(key, ship);

                } else {
                    Main.AIPlacementBoard[yCoord][xCoord + j].setIsShip(true);
                    int newX = xCoord + j;
                    String key = Game.getAccessKey(yCoord, newX);
                    Game.AIMapOfCoor.replace(key, ship);

                }
            }
		}
		                                  //initialise player alive ships
		int counter=0;
		while (fsc.hasNext()) {                      
			String ship=fsc.next();
			Main.getPlayerShipsAlive().set(counter, ship);
			counter++;
		}
		
		
 	}
 
	public static void resumeBoards(int fileNumber) throws Exception {
		File gridFile = new java.io.File("/Users/ellenzhu/eclipse-workspace/ics4u_battleship/Grids"+fileNumber+".txt");													//same with this
		String testarr[][]=new String[11][11];
		Scanner fsc = new Scanner(gridFile);
		boolean isShip;
		boolean isHit;
		String next;
		for (int i=1;i<11;i++) {
			for (int j=1;j<11;j++) {
				next =fsc.next();
				if(next.equals("O")) {
					isShip=false;
					isHit=false;
					testarr[i][j]="O";

				} 
				else if(next.equals("M")) {
					isShip=false;
					isHit=true;
					testarr[i][j]="M";

				}
				else if(next.equals("S")) {
					isShip=true;
					isHit=false;
					testarr[i][j]="S";

				}
				else {                          //if hit
					isShip=true;
					isHit=true;
					testarr[i][j]="X";

				}
				Coordinate cur=new Coordinate(i,j);
				cur.setIsHit(isHit);
				cur.setIsShip(isShip);
				Main.playerAttackBoard[i][j]=cur;
			//	Main.playerAttackBoard;
			}
		}
		
		
		for (int i=1;i<11;i++) {
        	for(int j=1;j<11;j++) {
        		if(Main.playerAttackBoard[i][j].getIsShip()) {
        			Main.AIPlacementBoard[i][j].setIsShip(true);
        		}
        	}
        }
		
		
		for (int i=1;i<11;i++) {
			for (int j=1;j<11;j++) {
				System.out.print(testarr[i][j]);
			}
			System.out.println();
		}
        Game.printPlacementArray(Main.playerAttackBoard);
        System.out.println("hi");
		
	}
	
}
