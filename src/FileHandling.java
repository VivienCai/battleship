/* IMPORTS 
* Util: For arrayList and sorting 
*/

/* Sarina Li, Vivien Cai, Jiaan Li
* Mon December 20
* ICS4U1
* File Handling class
*/


//Importing
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class FileHandling {
	public static boolean firstRound=true;
	public static void saveBoards(int fileNumber) throws Exception{
    	PrintWriter text2 = new PrintWriter("Grids"+fileNumber+".txt");
    	
    	//Prints the AI placement board by looping through each value and assigning a letter for it
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
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit(), isSunk = cur.getIsSunk(),
                            isUnique = cur.getIsUnique();
                    System.out.println("Testing isSunk/isShip"+cur.getIsSunk()+"/ "+isShip);
                    if (isSunk) {
                    	text2.print("D ");
                    } else if ((isHit && isShip && !isSunk) ||isUnique) {
                        text2.print("X ");
                    } else if (isHit && !isShip && !isSunk) {
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
	
	
	
	public static void resumeBoards(int fileNumber) throws Exception {
		File gridFile = new java.io.File("Grids" + fileNumber+".txt");						//same with this
		String testarr[][]=new String[11][11];
		String testarr2[][]=new String[11][11];

		Scanner fsc = new Scanner(gridFile);
		boolean isShip;
		boolean isHit;
		boolean isSunk=false;
		String next;
		
		//Resumes player attack board
		for (int i=1;i<11;i++) {
			for (int j=1;j<11;j++) {
				next =fsc.next();        
				if(next.equals("O")) {				//Checks each letter to see what state they are
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
				Main.playerAttackBoard[i][j]=cur;  //Assigns the states to the Main array
				Main.AIPlacementBoard[i][j]=cur;
			}
		}
		

		
		//gets info for AIHit board
		for (int i=1;i<11;i++) {
			for (int j=1;j<11;j++) {
				next =fsc.next();
				if(next.equals("O")) {
					isShip=false;
					isHit=false;
					testarr2[i][j]="O";
					isSunk=false;
				} 
				else if(next.equals("M")) {
					isShip=false;
					isHit=true;
					testarr2[i][j]="M";
					isSunk=false;	
				}
				else if(next.equals("D")) {
					isHit=true;
					isShip=true;
					isSunk=true;	
                }
				else {                          //if hit
					isShip=true;
					isHit=true;
					testarr2[i][j]="X";
					isSunk=false;
				}
				
				Coordinate cur=new Coordinate(i,j);
				cur.setIsHit(isHit);
				cur.setIsShip(isShip);
				cur.setIsSunk(isSunk);
				Main.AIAttackBoard[i][j]=cur;
			}
		}	
	}
	
	
	
	
 public static void resumeGame(int fileNumber) throws Exception{
	//resumes all info but boards
 	File infoFile = new java.io.File("info" + fileNumber+".txt");              //miht not work for diff comps
		Scanner fsc = new Scanner(infoFile);
		
		//Resuming all the easy to resume variables
		ScheduledTask.minute=fsc.nextInt();
		ScheduledTask.second=fsc.nextInt();
		Main.isPlayersTurn=fsc.nextBoolean();
		Main.easyMode=fsc.nextBoolean();
		Main.AIFirst=fsc.nextBoolean();
		AI.isHunting=fsc.nextBoolean();
		Main.AIShot= fsc.nextInt();
		Main.AIHit=fsc.nextInt();
		Main.AIMiss=fsc.nextInt();
		Main.PlayerShot=fsc.nextInt();
		Main.PlayerHit=fsc.nextInt();
		Main.PlayerMiss=fsc.nextInt();
		int counter2 =0;
		Main.playerShipTimesHit.clear();
		while (true) {
			int temp=fsc.nextInt();
			if (temp==4560) {
				break;
			}else {
				Main.playerShipTimesHit.add(temp);
				System.out.println(temp+" "+Main.playerShipTimesHit.size());
			}
        }
		
		//resuming playerhit board
		int homeCoordCounter=0;
		Ship.getList().clear();
		
		
		while(fsc.hasNext()) {                        
			
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
			

			
			//Gets the variables from the file and converts it into a Coordinate and Ship
			int size=fsc.nextInt();
			int xCoord=fsc.nextInt();
			int yCoord=fsc.nextInt();
			boolean vertical=fsc.nextBoolean();
			int timesHit=fsc.nextInt();			
			Coordinate coord=new Coordinate(xCoord, yCoord);
			Ship ship=new Ship( vertical, size, coord);

			if (alive) {
				Main.getShipsAlive().add(counter2, ship);
			}
			Ship.getList().get(homeCoordCounter).setHomeCoord(yCoord, xCoord);
			Ship.getList().get(homeCoordCounter).setIsSunk(!alive);
			Ship.getList().get(homeCoordCounter).setName(name);
			Ship.getList().get(homeCoordCounter).setShipSize(size);
			Ship.getList().get(homeCoordCounter).setIsVertical(vertical);
			Ship.getList().get(homeCoordCounter).setTimesHit(timesHit);
			
			//Resuming all ship locations from home point
			for (int j = 1; j < size; j++) {
                coord.setIsShip(true);
                Main.AIPlacementBoard[yCoord][xCoord].setIsShip(true);
                Game.AIMapOfCoor.replace(Main.AIPlacementBoard[yCoord][xCoord].toString(), ship);
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
		
		//Resume player alive ships
		int counter=0;
		Main.getPlayerShipsAlive().clear();
		while (fsc.hasNext()) {    
			String ship=fsc.next();
			if (ship.equals("UNIQUEHITPOINTS")) {
				break;
			}
			Main.getPlayerShipsAlive().add(counter, ship);
			counter++;
		}
		
		//Resume hunting algorithm 
		AI.isHunting=fsc.nextBoolean(); 
		int size=fsc.nextInt();
		
		for(int i=0;i<size;i++) {
			int xCoord=fsc.nextInt();
			int yCoord=fsc.nextInt();
			Coordinate coord=new Coordinate(yCoord, xCoord);
			AI.uniqueHitPoints.add(i,coord);
		}
		
		//adding shipsHit
		while (fsc.hasNext()) {
			String ship=fsc.next();
			if (ship.equals("POINTS")) {
				break;
			}
			AI.shipsHit.add(ship);
		}
		fsc.next();   //skip over the first point hit

		//adding pointsHit
		int shipCounter=0;
		while(true) {
			String nextX;
			while (true) {
				nextX=fsc.next();
				if (nextX.equals("POINTSHIT")||nextX.equals("HASHMAP")) {
					break;
				}
				int nextY=fsc.nextInt();
				int nextXInt=Integer.parseInt(nextX);
				Coordinate cur = new Coordinate(nextY,nextXInt);
				cur.setIsShip(true);
				AI.pointsHit[shipCounter].add(cur);	
			}
			shipCounter++;
			if(nextX.equals("HASHMAP")) {
				break;
			}
			else if(nextX.equals("POINTSHIT")) {
			}			
		}
	
//		 for (String i : Game.playerSunkShips.keySet()) {
//	            //ship
//	            text.print(i+" ");
//	            for (String point : Game.playerSunkShips.get(i)) {
//	                text.print(point + " ");
//	            }
//	        text.println();
//	        }
		
		//adding hashmaps
		while (fsc.hasNext()) {
			String nextShip=fsc.next();
			Game.playerSunkShips.put(nextShip, new ArrayList<String>());
			
			while(fsc.hasNext()) {
				String nextPoint=fsc.next();
				if (nextPoint.equals("CARRIER")||nextPoint.equals("BATTLESHIP")||nextPoint.equals("DESTROYER")||nextPoint.equals("SUBMARINE")||nextPoint.equals("CRUISER")) {
					break;   //if the next string is a ship value, move on to the next ship
				}
				else {
					Game.playerSunkShips.get(nextShip).add(nextPoint.toString());					
				}
			}
		}		
 	}
 
	
	
	
	
	public static void saveGame(int fileNumber) throws Exception {
		Scanner sc = new Scanner(System.in);
		//Determines which file to save to
        System.out.println("Which save file would you like to save to? Please enter a number greater than 1.");
        PrintWriter text = new PrintWriter("Info" + fileNumber + ".txt");
        
        text.println(ScheduledTask.minute+" "+ScheduledTask.second);
        text.println(!Main.isPlayersTurn);
        text.println(Main.easyMode);
        text.println(Main.AIFirst);
        text.println(AI.isHunting);
        text.println(Main.AIShot + " " + Main.AIHit + " " + Main.AIMiss);
        text.println(Main.PlayerShot + " " + Main.PlayerHit + " " + Main.PlayerMiss);

        //Save timesHit information
        for (int i=0;i<Main.playerShipTimesHit.size();i++) {
        	text.print(Main.playerShipTimesHit.get(i)+" ");
        }
        text.println("4560");
        
        //Save ship information
        for (int i = 0; i < Ship.getList().size(); i++) {
            text.print(Ship.getList().get(i).getName() + " ");
            if (Ship.getList().get(i).getIsSunk()) { // If sunk
                text.print("SUNK ");
            } else { // if not sunk
                text.print("ALIVE ");
            }
            text.print(Ship.getList().get(i).getSize() + " ");
            text.print(Ship.getList().get(i).getHomeCoord().getX() + " "); // Prints AI ships
            text.print(Ship.getList().get(i).getHomeCoord().getY() + " ");
            text.print(Ship.getList().get(i).getVertical() + " ");
            text.print(Ship.getList().get(i).getTimesHit() + " ");
            text.println();
        } 
        
        
        //Saves player ship information
        text.println("PLAYER");
        for (int i = 0; i < Ship.getPlayerListOfShipsAlive().size(); i++) {
            text.print(Ship.getPlayerListOfShipsAlive().get(i) + " "); // Prints player ships
        }
        
        text.println();
        text.println("UNIQUEHITPOINTS");
        text.println(AI.isHunting);     
        text.println(AI.uniqueHitPoints.size() );
        
        for (int i=0;i<AI.uniqueHitPoints.size();i++) {   
        	 text.print(AI.uniqueHitPoints.get(i).getX()+" ");
        	 text.println(AI.uniqueHitPoints.get(i).getY());
        }
        
        //add shipsHit
        for (int i=0;i<AI.shipsHit.size();i++) {
        	text.print(AI.shipsHit.get(i)+" ");
        }
        text.println();
        text.println("POINTS");

        
        for(int i=0;i<AI.pointsHit.length;i++) {
        	text.print("POINTSHIT ");
        	for(int j=0;j<AI.pointsHit[i].size();j++) {
        		text.print(AI.pointsHit[i].get(j).getX()+" ");
        		text.print(AI.pointsHit[i].get(j).getY()+" ");
        	}
        	text.println();
        }
        
        //add points hit
        text.println("HASHMAP");

        //Print hasHmap of playerShipsAlive
        for (String i : Game.playerSunkShips.keySet()) {
            //ship
            text.print(i+" ");
            for (String point : Game.playerSunkShips.get(i)) {
                text.print(point + " ");
            }
        text.println();
        }
        text.close();

        //Saves the boards
        FileHandling.saveBoards(fileNumber);
        Game.printPlacementArray(Main.AIAttackBoard);     
    }
	
	

	
	public static void promptSaveGame() {
		int inputInt;
		 while (true) {   //Keeps asking for input until player gives correct one
             String input = JOptionPane.showInputDialog(null,
                     "Before we get started, please enter an integer to determine where your file will be saved (this can be changed later.) ");
             
             try {
                 inputInt = Integer.parseInt(input);
                 break;
             } catch (Exception e) {
             }
         }
		 try {
     		FileHandling.saveGame(inputInt);
     	    JOptionPane.showMessageDialog(GUI.frame, "Your game has been successfully saved. Press OK to continue");

     	} catch (Exception e1) {
     		// TODO Auto-generated catch block
     		e1.printStackTrace();
     	}
		 
	}
	

	public static void saveGameButton(){	
		
		//Creating the button
		GUI.saveGame = new JButton("Save Game");
        GUI.saveGame.setBounds(760, 25, 200, 45);
        GUI.saveGame.setFont(GUI.customFont[16]);
        GUI.saveGame.setForeground(Color.black);
        GUI.saveGame.setBackground(GUI.accent);
        GUI.saveGame.setVisible(true);	
        
        //What happens when player presses button
        GUI.saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int inputInt = 0;
                boolean save=false;
                int result = JOptionPane.showConfirmDialog(null,
                        "Do you want to save your game? Please make sure you are saving your game after you or the AI moved");
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        System.out.println("Yes");
                        
                        while (true) {   //Keeps asking for input until player gives correct one
                            String input = JOptionPane.showInputDialog(null,
                                    "Please the save file number you want to save to.");
                            
                            try {
                                inputInt = Integer.parseInt(input);
                                save=true;
                                break;
                            } catch (Exception e) {
                            }
                        }
                        
                        if (save) {
                        	try {
                        		FileHandling.saveGame(inputInt);
                        	} catch (Exception e1) {
                        		// TODO Auto-generated catch block
                        		e1.printStackTrace();
                        	}
                        	Game.printPlacementArray(Main.AIAttackBoard);
                        	JOptionPane.showMessageDialog(GUI.frame,
                                "Your game has been saved. You may either exit out or keep on playing.");
                        	JOptionPane.showMessageDialog(GUI.frame, "Your files have been saved in two filescalled Info"
                                + inputInt + ".txt and Grids" + inputInt + ".txt");
                        	JOptionPane.showMessageDialog(GUI.frame,
                                "Please DO NOT tamper with the two filesor your data may be PERMANENTLY lost");
                        }
                    case JOptionPane.NO_OPTION:
                        System.out.println("No");
                        break;
                }

            }
        });	
	}
}
