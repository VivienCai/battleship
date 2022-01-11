/* IMPORTS 
* Util: For arrayList and sorting 
*/

import java.util.*;

/* Sarina Li, Vivien Cai, Jiaan Li
* Fri December 24
* ICS4U1
* Hitting class
*/

// inherits everything from AI Class, is an extension of AI behaviour
public class Hitting extends AI {

    /*
     * LOGIC:
     * Loops through the entire AI Attack Board with all of the probabilities summed
     * up and then takes the maximum. Add all of the highest probability hitpoints
     * into a list and sort it in descending order.
     * 
     * To use the parity strategy, we use a math trick where we add the row and
     * column
     * index to get whether the square is odd or not. We try to hit squares with the
     * same parity early on but in the else case, if this is not possible we will
     * just hit the square with the largest probability.
     */

    // method used to hit the coordinate with the highest probability
    public static Coordinate AIhit() {

        // loops through the arrays to find the maximum probabilities, adds to
        // appropriate arraylists
        findMax();

        // if we have shots within our parity
        if (isParity.size() > 0) {
            // this method generates a random hit inside of the maximum probabilities and
            // returns it
            Coordinate hit = promptHitPoint(isParity);

            // prompt user (terminal game exclusive)
            System.out.printf("The AI hit coordinate %c%d\n", Coordinate.columnIndex(hit.getY()), hit.getX());
            System.out.println("Is it a hit, miss, or sink?");

            // ask for user validation
            getInput(hit);

            // return the Coordinate for outside uses
            return Main.AIAttackBoard[hit.getY()][hit.getX()];

            // if we cannot hit in parity we simply hit the max.
        } else {
            // generate the hit
            Coordinate hit = promptHitPoint(possibleHits);

            // prompt
            System.out.printf("The AI hit coordinate %c%d\n", Coordinate.columnIndex(hit.getY()), hit.getX());
            System.out.println("Is it a hit, miss, or sink?");

            // ask for validation
            getInput(hit);

            // return Coordinate for future use
            return Main.AIAttackBoard[hit.getY()][hit.getX()];
        }
    }

    // executes the same logic as the AIHit method but is specific to the GUI
    public static Coordinate AIhitGUI() {
        // loops through the 2d array and adds points to arraylists and finds max
        // probability
        findMax();

        // if possible shots are in parity
        if (isParity.size() > 0) {
            // generate the hit
            Coordinate hit = promptHitPoint(isParity);

            // return for future use
            return Main.AIAttackBoard[hit.getY()][hit.getX()];
            // if no shots in parity just hit the max
        } else {
            // generate the hit
            Coordinate hit = promptHitPoint(possibleHits);

            // return for future use
            return Main.AIAttackBoard[hit.getY()][hit.getX()];
        }
    }

    // getting input/validation from user after hitting a point on the grid
    // (terminal exclusive)
    public static void getInput(Coordinate hit) {

        // continues asking until the input is valid
        while (true) {

            // get input
            String input = sc.nextLine();

            // abbreviating the name of the coordinate we hit
            Coordinate curCoordinate = Main.AIAttackBoard[hit.getY()][hit.getX()];

            // ensure valid input
            if (input.length() < 4) {
                System.out.println("that is not a valid input. Is it a hit, miss, or sink?");
            } else if (input.equals("MISS")) {
                Main.AIMiss++;
                Main.AIShot++;
                break;
                // if user enters hit
            } else if (input.substring(0, 3).equals("HIT")) {
                String ship = input.substring(5);
                if (checkValidShip(ship)) {
                    // notes that the AI has hit a point and is now hunting
                    isHunting = true;

                    // setting appropriate booleans
                    curCoordinate.setIsShip(true);
                    AI.uniqueHitPoints.add(hit);
                    AI.shipsHit.add(ship);

                    // takes the ship that was hit, gives it a new arraylist to store all of its hit
                    // points
                    // adds this hit point to the ship (recorded for hunting)
                    Game.playerSunkShips.put(ship, new ArrayList<String>());
                    Game.playerSunkShips.get(ship).add(hit.toString());
                    // note: there are 2 three ships and thus the indexing system has to use a
                    // special method
                    // to differentiate between the two
                    int shipSize = Ship.getSize(ship);
                    if (shipSize == 3) {
                        shipSize = Ship.getIndexOfThreeShip(ship);
                    }

                    AI.pointsHit[shipSize].add(curCoordinate);
                    Main.AIHit++;
                    Main.AIShot++;
                    break;
                    // invalid ship
                } else {
                    System.out.println("That is not a valid ship entered. Please try again.");
                }
                // if we have sunk a ship
            } else if (input.substring(0, 4).equals("SUNK")) {
                // we are no longer hunting
                isHunting = false;
                // set this hit coordinate as a non ship point so if user hits again will mark
                // as miss
                curCoordinate.setIsShip(true);
                String shipName = input.substring(5);

                // remove the ship from ships alive
                if (checkValidShip(shipName)) {
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

    // user validation method specific to GUI
    public static void getInputGUI(Coordinate hit, int index, int shipIndex) {

        // abbr. the coordinate that we are targetting
        Coordinate curCoordinate = Main.AIAttackBoard[hit.getY()][hit.getX()];

        // if the point was hit
        if (index == 0) {
            String ship = GUI.getShips()[shipIndex];
            // AI recognizes that it hits a point, we are now hunting
            isHunting = true;
            curCoordinate.setIsShip(true);
            AI.uniqueHitPoints.add(hit);
            AI.shipsHit.add(ship);
            // adds the ship to the map that stores what points are hit for each ship
            Game.playerSunkShips.put(ship, new ArrayList<String>());
            Game.playerSunkShips.get(ship).add(hit.toString());

            // for the 2 three ships (index specific)
            int shipSize = Ship.getSize(ship);
            if (shipSize == 3) {
                shipSize = Ship.getIndexOfThreeShip(ship);
            }
            AI.pointsHit[shipSize].add(curCoordinate);
            Main.AIHit++;
            Main.AIShot++;
            // if a ship is sunk
        } else if (index == 2) {
            // AI no longer is hunting
            isHunting = false;

            // if the player hits again this point will show as a miss
            curCoordinate.setIsShip(true);
            String shipName = GUI.getShips()[shipIndex];

            // remove from list of ships alive
            Ship.getPlayerListOfShipsAlive().remove(shipName);
            Main.AIHit++;
            Main.AIShot++;

        }
    }

    /*
     * LOGIC:
     * Imagine this: each orientation of ship occupies the "size" squares. For each
     * of these squares
     * for every orientation that a ship can be in that square you add one to that
     * square
     * as in that square has 1 possible orientation.
     * 
     * Now you can repeat this proccess for each ship alive and you have an
     * algorithm that effectively
     * finds the square with the highest number of orientations possible in a
     * particular square.
     * 
     * To achieve this, you must sum the vertical and horizontal separately. 
     */

    // Terminal specific, finds the probability of a ship being in a certain
    // position
    public static void findProbability() {
        skewProbabilityPrompt();
        // for each ship alive
        for (String i : Main.playerShipsAlive) {
            // summing will be based on the size of the ship
            int size = Ship.getSize(i);
            sumColumns(size);
            sumRows(size);
        }

        // hit the point
        AIhit();
        // display
        printArray(Main.AIAttackBoard);
        // reseet to compensate for new misses
        resetArray();
    }

    // gui specific finding probability
    public static Coordinate findProbabilityGUI() {
        // for every single ship alive sum vertically and horizontally 
        skewProbabilityPrompt();
        for (String i : Main.playerShipsAlive) {
            int size = Ship.getSize(i);
            sumColumns(size);
            sumRows(size);

        }
        // use the method to hit 
        Coordinate h = AIhitGUI();
        printArray(Main.AIAttackBoard);
        // reset for future use
        resetArray();
        return h;
    }

    // easy mode for GUI
    public static Coordinate findProbabilityGUIEasy() {

        // make an instance of random
        Random rand = new Random();

        while (true) {
            // Creates a random x and y integer
            int xCoord = rand.nextInt(0 + 10) + 1; 
            int yCoord = rand.nextInt(0 + 10) + 1;

            // abbr for current coordinate
            Coordinate curCoordinate = Main.AIAttackBoard[yCoord][xCoord];
            // Checks if created point has already been hit
            if (!curCoordinate.getIsHit()) { 
                // set hit as true, return the hit for future purposes
                Main.AIAttackBoard[yCoord][xCoord].setIsHit(true);
                return Main.AIAttackBoard[yCoord][xCoord];
            }
        }
    }

    public static void skewProbabilityPrompt() {
        while (true) {

            System.out.println("Skew Sides? (type y/n.)");
            String input = sc.nextLine();
            if (!input.equals("")) {
                if (input.equals("y")) {
                    skewProbability();
                    System.out.println("Skewed.");
                    break;
                } else if (input.equals("n")) {
                    System.out.println("Carrying on with regular hitting.");
                    break;
                }
            } 
            System.out.println("Invalid input, try again.");
        }
        System.out.println("Blank input, try again.");
    }

    public static void skewProbability() {
        for (int i = 1; i <= 10; i++) {
            if (!Main.AIAttackBoard[1][i].getIsHit()) {
                Main.AIAttackBoard[1][i].setProbability(20);

            }
            if (!Main.AIAttackBoard[10][i].getIsHit()) {
                Main.AIAttackBoard[10][i].setProbability(20);
            }
        }

        for (int i = 2; i <= 9; i++) {
            if (!Main.AIAttackBoard[i][1].getIsHit()) {
                Main.AIAttackBoard[i][1].setProbability(20);
            }
            if (!Main.AIAttackBoard[i][10].getIsHit()) {
                Main.AIAttackBoard[i][10].setProbability(20);
            }
        }
    }

    /* LOGIC: 
    The idea is we have to only sum these next shipsize squares if they are a) valid and not out of bounds
    and b) not occupied by a miss in ANY of the squares. 

    If they do not satisfy a or b, we can sum these next squares. 
     */
    // method to sum rows
    public static void sumRows(int shipSize) {
        // loop in the 2d array

        for (int i = 1; i <= 10; i++) {
            // subtract the ship size to make sure that we are always in bounds
            for (int j = 1; j <= 10 - shipSize + 1; j++) {
                // our flag boolean is initally set as ok, you can sum
                boolean flag = true;
                // making sure that it is not a miss
                for (int g = j; g < j + shipSize; g++) {
                    Coordinate cur = Main.AIAttackBoard[i][g];
                    if (cur.getIsHit()) { 
                        // set the flag as false, we cannot sum anymore.
                        flag = false;
                    }
                }
                // if we can sum
                if (flag) {
                    for (int g = j; g < j + shipSize; g++) {
                        Main.AIAttackBoard[i][g].setProbability(Main.AIAttackBoard[i][g].getProbability() + 1);
                    }

                }
            }
        }
    }

    // see sumRows method for detailed explanation. This is essentially the same method, 
    // just vertically (SEE LINE 295)
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

    // getting input for the X component of the player hit (terminal exclusive)
    public static int getInputX() {
        String input;
        int coord = -1;

        // keeps on running until user makes a valid input
        while (true) { 
            input = sc.nextLine();
            if (isInt(input)) {
                coord = Integer.parseInt(input);
                
                // if its in bounds
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

    // asks user for Y component of the point they want to hit. Also converts character to useable index.
    public static int getInputY() {
        char c;
        int coord = 0;
        // continue asking for input until it is valid
        while (true) { 
            c = sc.nextLine().charAt(0);
            // subtract the "base" ASCII value of 'A' to get the index, add 1 because our arrays are 1 indexed
            coord = (char) ((c - 'A') + 1);
            if (coord >= 1 && coord <= 10) {
                return coord;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // generate a hit, set it as a hit and return the point for further use
    public static Coordinate promptHitPoint(ArrayList<Coordinate> list) {
        int randIndex = (int) (Math.random() * list.size());
        Coordinate hit = list.get(randIndex);
        int y = hit.getY(), x = hit.getX();
        Main.AIAttackBoard[y][x].setIsHit(true);
        return hit;
    }

    // loop through the array and get the maximum. set the global variable as the max
    public static void findMax() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = Main.AIAttackBoard[i][j];
                max = Math.max(max, cur.getProbability());

            }
        }
        // second loop adds based on the parity to two separate lists
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
    }
}