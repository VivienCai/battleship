import java.util.ArrayList;
import java.util.Random;

/* Sarina Li, Vivien Cai, Jiaan Li
* Fri December 24
* ICS4U1
* Hitting class
*/

public class Hitting extends AI {
	
	
	
    public static Coordinate AIhit() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = Main.AIAttackBoard[i][j];
                max = Math.max(max, cur.getProbability());

            }
        }
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = Main.AIAttackBoard[i][j];
                if (cur.getProbability() == max) {
                    if (isOdd(i, j) == initialIsOdd) {
                        isParity.add(new Coordinate(i, j));
                    } else {
                        possibleHits.add(new Coordinate(i, j));
                    }
                }
            }
        }

        if (isParity.size() > 0) {
            int randIndex = (int) (Math.random() * isParity.size());
            Coordinate hit = isParity.get(randIndex);
            int y = hit.getY(), x = hit.getX();
            Main.AIAttackBoard[y][x].setIsHit(true);
            System.out.printf("The AI hit coordinate %c%d\n", Coordinate.columnIndex(y), x);
            System.out.println("Is it a hit or miss or sink?");
            getInput(hit);
            return Main.AIAttackBoard[y][x];
        } else {
            int randIndex = (int) (Math.random() * possibleHits.size());
            Coordinate hit = possibleHits.get(randIndex);
            int y = hit.getY(), x = hit.getX();
            Main.AIAttackBoard[y][x].setIsHit(true);
            System.out.printf("The AI hit coordinate %c%d\n", Coordinate.columnIndex(y), x);
            System.out.println("Is it a hit, miss, or sink?");
            getInput(hit);
            return Main.AIAttackBoard[y][x];
        }
    }

    
    
    public static Coordinate AIhitGUI() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = Main.AIAttackBoard[i][j];
                max = Math.max(max, cur.getProbability());

            }
        }
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = Main.AIAttackBoard[i][j];
                if (cur.getProbability() == max) {
                    if (isOdd(i, j) == initialIsOdd) {
                        isParity.add(new Coordinate(i, j));
                    } else {
                        possibleHits.add(new Coordinate(i, j));
                    }
                }
            }
        }

        if (isParity.size() > 0) {
            int randIndex = (int) (Math.random() * isParity.size());
            Coordinate hit = isParity.get(randIndex);
            int y = hit.getY(), x = hit.getX();
            Main.AIAttackBoard[y][x].setIsHit(true);
            // System.out.printf("The AI hit coordinate %c%d\n", Coordinate.columnIndex(y),
            // x);
            // System.out.println("Is it a hit or miss or sink?");
            // getInput(hit);
            return Main.AIAttackBoard[y][x];
        } else {
            int randIndex = (int) (Math.random() * possibleHits.size());
            Coordinate hit = possibleHits.get(randIndex);
            int y = hit.getY(), x = hit.getX();
            Main.AIAttackBoard[y][x].setIsHit(true);
            // System.out.printf("The AI hit coordinate %c%d\n", Coordinate.columnIndex(y),
            // x);
            // System.out.println("Is it a hit, miss, or sink?");
            // getInput(hit);
            return Main.AIAttackBoard[y][x];
        }
    }

    public static void getInput(Coordinate hit) {
        // ArrayList<Ship> shipsAlive = Ship.getList();
        while (true) {

            String input = sc.nextLine();
            Coordinate cur = Main.AIAttackBoard[hit.getY()][hit.getX()];
            if (input.length() < 4) {
                System.out.println("that is not a valid input. Is it a hit, miss, or sink?");
            } else if (input.equals("MISS")) {
                Main.AIMiss++;
                Main.AIShot++;
                // Adds one
                break;
            } else if (input.substring(0, 3).equals("HIT")) {
                String ship = input.substring(5);
                if (checkValidShip(ship)) {
                    isHunting = true;
                    // #J_Favourites
                    cur.setIsShip(true);
                    AI.uniqueHitPoints.add(hit);
                    AI.shipsHit.add(ship);
                    Game.playerSunkShips.put(ship, new ArrayList<String>());
                    Game.playerSunkShips.get(ship).add(hit.toString());
                    int shipSize = Ship.getSize(ship);
                    if (shipSize == 3) {
                        shipSize = Ship.getIndexOfThreeShip(ship);
                    }
                    AI.pointsHit[shipSize].add(cur);
                    Main.AIHit++;
                    Main.AIShot++;
                    break;

                } else {
                    System.out.println("That is not a valid ship entered. Please try again.");
                }
            } else if (input.substring(0, 4).equals("SUNK")) {
                isHunting = false;
                cur.setIsShip(true);
                // SUNK, CARRIER
                String shipName = input.substring(5);
                if (checkValidShip(shipName)) {
                    System.out.println(shipName);
                    Ship.getPlayerListOfShipsAlive().remove(shipName);
                    Main.AIHit++;
                    Main.AIShot++;
                    break;
                } else {
                    System.out.println("That is not a valid ship entered. Please try again.");
                }
            } else {
                System.out.println("that is not a valid input. Is it a hit, miss or sink?");
            }

        }
    }

    
    
    
    
    public static void getInputGUI(Coordinate hit, int index, int shipIndex) {
        // ArrayList<Ship> shipsAlive = Ship.getList();
        while (true) {
            Coordinate cur = Main.AIAttackBoard[hit.getY()][hit.getX()];

            // if the point was hit
            if (index == 0) {
                String ship = GUI.getShips()[shipIndex];
                isHunting = true;
                cur.setIsShip(true);
                AI.uniqueHitPoints.add(hit);
                AI.shipsHit.add(ship);
                Game.playerSunkShips.put(ship, new ArrayList<String>());
                Game.playerSunkShips.get(ship).add(hit.toString());
                int shipSize = Ship.getSize(ship);
                if (shipSize == 3) {
                    shipSize = Ship.getIndexOfThreeShip(ship);
                }
                AI.pointsHit[shipSize].add(cur);
                Main.AIHit++;
                Main.AIShot++;
                break;
            } else if (index == 2) {
                isHunting = false;
                cur.setIsShip(true);
                String shipName = GUI.getShips()[shipIndex];
                // SUNK, CARRIER
            
                System.out.println(shipName);
                Ship.getPlayerListOfShipsAlive().remove(shipName);
                Main.AIHit++;
                Main.AIShot++;
                break;
                
            }

        }
    }

    // no gui dont touch
    public static void findProbability() {
        for (String i : Main.playerShipsAlive) {
            int size = Ship.getSize(i);
            sumColumns(size);
            sumRows(size);
        }
        // printArray();
        AIhit();
        printArray(Main.AIAttackBoard);
        resetArray();
    }

    // gui
    public static Coordinate findProbabilityGUI() {
        for (String i : Main.playerShipsAlive) {
            int size = Ship.getSize(i);
            sumColumns(size);
            sumRows(size);

        }
        // printArray();
        Coordinate h = AIhitGUI();
        printArray(Main.AIAttackBoard);
        resetArray();
        return h;
    }

    public static Coordinate findProbabilityGUIEasy() {
    	Random rand = new Random();
    	
    	while(true) {
    		int xCoord =  rand.nextInt(0+10)+1;
    		int yCoord =  rand.nextInt(0+10)+1;
    		
    		Coordinate c = Main.AIAttackBoard[yCoord][xCoord];
    		if (!c.getIsHit()) {  //if it is not already hit
    			Main.AIAttackBoard[yCoord][xCoord].setIsHit(true);
                return Main.AIAttackBoard[yCoord][xCoord];
    		} 	
    	}	
    }
    
    
    public static void sumRows(int shipSize) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10 - shipSize + 1; j++) {
                // int shipsize = 3;
                boolean ok = true;
                for (int g = j; g < j + shipSize; g++) {
                    Coordinate cur = Main.AIAttackBoard[i][g];
                    if (cur.getIsHit()) { // if missed point
                        ok = false;
                    }
                }
                if (ok) {

                    for (int g = j; g < j + shipSize; g++) {
                        Main.AIAttackBoard[i][g].setProbability(Main.AIAttackBoard[i][g].getProbability() + 1);
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
                    if (Main.AIAttackBoard[g][i].getIsHit()) {
                        ok = false;
                    }
                }
                if (ok) {

                    for (int g = j; g < j + shipSize; g++) {
                        Main.AIAttackBoard[g][i].setProbability(Main.AIAttackBoard[g][i].getProbability() + 1);
                    }

                }
            }
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
                    System.out.println("Please enter a valid input.");
                }

            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    public static int getInputY() {
        // String input;
        char c;
        // char coord = 'x';
        int coord = 0;
        while (true) { // keeps on running until user makes a valid input
            // System.out.println(output);
            c = sc.nextLine().charAt(0);
            coord = (char) ((c - 'A') + 1);
            if (coord >= 1 && coord <= 10) {
                return coord;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }
}