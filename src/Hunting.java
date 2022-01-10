/* Sarina Li, Vivien Cai, Jiaan Li
* Sun January 09
* ICS4U1
* Battleship Hunting File
*/

/* IMPORTS:
* Util: For ArrayList and sorting 
*/
import java.util.*;

// inherits everything from AI Class, is an extension of AI behaviour
public class Hunting extends AI {

    /*
     * LOGIC:
     * Hunting is used when the AI has hit a ship from the probability grid. Like
     * the word suggests, the AI "hunts" for this ship until it is
     * sunk. Once the ship is sunk, we continue our hitting algorithm.
     *
     * First we get the size of the ship and then we sum the vertical and horizontal
     * by the ship size. Then it will take the highest probability
     * and hit in that position. We ask user for input and we continue this procces
     * until the ship is sunk.
     */

    // hunting method (terminal exclusive)
    public static void hunt(Coordinate h, String ship) {
        // get the size of the ship we hit
        int shipSize = Ship.getSize(ship);

        // sum the possible hits based on the size
        sumArray(h, shipSize, ship);

        // generate the next hit
        Coordinate nextHit = max(h.getY(), h.getX(), shipSize);

        // ask for user validation
        getInput(nextHit);

        // reset for future use
        resetArray();
    }

    // hunting method (GUI exclusive)
    public static Coordinate huntGUI(Coordinate h, String ship) {
        // get the size of ship hit
        int shipSize = Ship.getSize(ship);

        // sum appropriately
        sumArray(h, shipSize, ship);

        // generate next hit
        Coordinate nextHit = max(h.getY(), h.getX(), shipSize);

        // reset for future use
        resetArray();

        // return the hit point for future use
        return nextHit;
    }

    // getting user validation GUI exclusive
    public static void getInputGUI(Coordinate hit, int index, int shipGUIIndex) {

        // abbrv the x and y we will use
        int y = hit.getY();
        int x = hit.getX();

        // abbr current coordinate
        Coordinate curCoordinate = Main.AIAttackBoard[y][x];
        curCoordinate.setIsHit(true);

        // if we hit a ship point
        if (index == 0) {
            // get the ship name and size for indexing purposes
            String ship = GUI.getShips()[shipGUIIndex];
            int shipSize = Ship.getSize(ship);

            // the indexing of 3 ship for the duplicates
            int shipIndex = shipSize;
            if (shipSize == 3) {
                shipIndex = Ship.getIndexOfThreeShip(ship);
            }
            Main.AIHit++;
            Main.AIShot++;

            // if the ship is valid
            if (checkValidShip(ship)) {
                // if we hit another ship other than the one we are hunting it adds this
                // new point to the unique hit point queue. This means that the AI
                // continues to hunt this new unique point after the original ship is sunk.
                if (!ship.equals(shipsHit.get(0))) {

                    curCoordinate.setIsUnique(true);
                    uniqueHitPoints.add(hit);
                    shipsHit.add(ship);

                    // otherwise we have hit the same ship
                } else {

                    curCoordinate.setIsShip(true);
                }
                // either way we hit a spot, mark this as hit. Add the hit point into the
                // appropriate ship
                curCoordinate.setIsHit(true);
                pointsHit[shipIndex].add(curCoordinate);
                Game.playerSunkShips.get(ship).add(hit.toString());

            }
            // if the ship is sunk we want to mark it as sunk and ensure that all ship
            // points are marked as misses
            // so hit will not generate in those spots
        } else if (index == 2) {
            Main.AIHit++;
            Main.AIShot++;
            String ship = GUI.getShips()[shipGUIIndex];

            Game.playerSunkShips.get(ship).add(hit.toString());
            curCoordinate.setIsShip(true);

            // remove it from the list of ships alive
            Ship.getPlayerListOfShipsAlive().remove(ship);
            uniqueHitPoints.remove(0);
            shipsHit.remove(ship);
            // loop for the ship points that we recorded
            for (String i : Game.playerSunkShips.get(ship)) {

                int columnInd = Coordinate.columnIndexAsInt(i.charAt(0));
                int rowInd = Integer.parseInt(i.substring(1));
                Main.AIAttackBoard[columnInd][rowInd].setIsShip(false);
                Main.AIAttackBoard[columnInd][rowInd].setIsSunk(true);
            }

            // if everyhing we hit is sunk we can now resume hitting
            if (uniqueHitPoints.size() == 0) {
                isHunting = false;
            }

        }

    }

    // prompts user for input (terminal exclusive)
    public static void getInput(Coordinate hit) {
        while (true) {

            // abbrv the x and y
            int y = hit.getY();
            int x = hit.getX();

            // prompt
            System.out.printf("The AI hit coordinate %c%d\n", Coordinate.columnIndex(y), x);
            System.out.println("Is it a hit or miss or sink?");
            String input = sc.nextLine();

            Coordinate curCoordinate = Main.AIAttackBoard[y][x];
            curCoordinate.setIsHit(true);

            // checking for valid input
            if (input.length() < 4) {
                System.out.println("that is not a valid input. Is it a hit, miss, or sink?");
                // if we miss
            } else if (input.equals("MISS")) {
                Main.AIShot++;
                Main.AIMiss++;
                break;
                // if we miss
            } else if (input.substring(0, 3).equals("HIT")) {
                // get the ship
                String ship = input.substring(5);
                int shipSize = Ship.getSize(ship);
                int shipIndex = shipSize;
                // doule three ship
                if (shipSize == 3) {
                    shipIndex = Ship.getIndexOfThreeShip(ship);
                }
                Main.AIHit++;
                Main.AIShot++;
                // if the ship is valid
                if (checkValidShip(ship)) {
                    Game.playerSunkShips.put(ship, new ArrayList<String>());
                    // if we hit a new ship point, add new ship to list
                    if (!ship.equals(shipsHit.get(0))) {
                        Game.playerSunkShips.get(ship).add(hit.toString());
                        uniqueHitPoints.add(hit);
                        shipsHit.add(ship);
                        curCoordinate.setIsUnique(true);
                        // if we hit the same ship
                    } else {
                        curCoordinate.setIsShip(true);
                    }
                    // add it to the points that this specific ship occupies
                    pointsHit[shipIndex].add(curCoordinate);
                    break;
                } else {
                    System.out.println("That is not a valid ship entered. Please try again.");
                }
                // if we sunk a ship
            } else if (input.substring(0, 4).equals("SUNK")) {
                Main.AIHit++;
                Main.AIShot++;
                // get the ship
                String ship = input.substring(6);
                // check for a valid ship
                if (checkValidShip(ship)) {
                    // get the ship and set the appropriate booleans
                    Game.playerSunkShips.get(ship).add(hit.toString());
                    curCoordinate.setIsShip(true);

                    Ship.getPlayerListOfShipsAlive().remove(ship);
                    uniqueHitPoints.remove(0);
                    shipsHit.remove(ship);

                    // for the ship points recorded for a sunk ship set each one as a miss for
                    // hitting
                    for (String i : Game.playerSunkShips.get(ship)) {

                        int columnInd = Coordinate.columnIndexAsInt(i.charAt(0));
                        int rowInd = Integer.parseInt(i.substring(1));
                        Main.AIAttackBoard[columnInd][rowInd].setIsShip(false);
                        Main.AIAttackBoard[columnInd][rowInd].setIsSunk(true);
                    }
                    // invalid input
                } else {
                    System.out.println("That is not a valid ship entered. Please try again.");
                }
                // if we are done hunting all unique ships
                if (uniqueHitPoints.size() == 0) {
                    isHunting = false;
                }
                break;
            } else {
                System.out.println("that is not a valid input. Is it a hit or miss?");
            }
        }
    }

    /*
     * LOGIC:
     * This method is vital to the hunt logic, this is used to generate the highest
     * probability points in a hunt
     *
     * The idea is the ship can only be in a vertical or horizontal orientation from
     * the intial point that was hit.
     * Extending this idea is that misses around the original point rule out the
     * ship possibly being in that direction.150x
     */
    public static void sumArray(Coordinate h, int shipSize, String shipHitName) {
        int shipIndex = shipSize;
        if (shipSize == 3) {
            shipIndex = Ship.getIndexOfThreeShip(shipHitName);
        }
        // VERTICAL
        // start point is hit point + 1 - shipsize (check if in bounds)
        int isVertical = 0;

        // If 2 points have been hit, check if they are aligned vertically or
        // horizontally
        if (pointsHit[shipIndex].size() >= 2) {
            Coordinate cur = pointsHit[shipIndex].get(0);
            Coordinate next = pointsHit[shipIndex].get(1);
            if (next.getY() == cur.getY()) {
                isVertical = 1;
            } else {
                isVertical = 2;
            }
        }
        // Only 1 point has been hit
        if (isVertical == 0) {
            sumVertical(shipSize, h);
            sumHorizontal(h, shipSize);
            // 2 points have been hit horizontally
        } else if (isVertical == 1) {
            sumHorizontal(h, shipSize);
            // 2 points have been hit horizontally
        } else {
            sumVertical(shipSize, h);
        }

        printArray(huntingProbability); // do we still need this?
    }

    public static Coordinate max(int y, int x, int shipSize) {
        // Finds the max points and adds then to a queue (horizontally)
        for (int i = 1; i <= 10; i++) {
            if (huntingProbability[y][i].getProbability() > 0) {
                hitPointQueue.add(huntingProbability[y][i]);
            }
        }

        // Finds the max points and adds then to a queue (vertically)
        for (int i = 1; i <= 10; i++) {
            if (huntingProbability[i][x].getProbability() > 0) {
                hitPointQueue.add(huntingProbability[i][x]);
            }
        }
        Collections.sort(hitPointQueue, Collections.reverseOrder());

        return hitPointQueue.remove(0);
    }

    public static void sumVertical(int shipSize, Coordinate h) {
        int y = h.getY();
        int x = h.getX();
        for (int i = (y + 1 - shipSize); i <= y; i++) {
            boolean ok = true;
            if (i < 1) {
                continue;
            }

            // Checks if the area around the ship has been hit before
            for (int j = i; j < i + shipSize; j++) {
                if (j > 10
                        || (Main.AIAttackBoard[j][x].getIsHit() && !Main.AIAttackBoard[j][x].getIsShip() && j != y)) {
                    ok = false;
                }
            }

            if (ok) {
                for (int j = i; j < i + shipSize; j++) {
                    if (j == y || Main.AIAttackBoard[j][x].getIsShip()) {
                        continue;
                    }
                    int currentProbability = huntingProbability[j][x].getProbability();
                    huntingProbability[j][x].setProbability(currentProbability + 1);
                }
            }
        }
    }

    public static void sumHorizontal(Coordinate h, int shipSize) {
        int y = h.getY();
        int x = h.getX();
        for (int i = (x + 1 - shipSize); i <= x; i++) {
            boolean ok = true;
            if (i < 1) {
                continue;
            }
            for (int j = i; j < i + shipSize; j++) {
                if (j > 10
                        || (Main.AIAttackBoard[y][j].getIsHit() && !Main.AIAttackBoard[y][j].getIsShip() && j != x)) {
                    ok = false;
                }
            }
            if (ok) {
                for (int j = i; j < i + shipSize; j++) {
                    if (j == x || Main.AIAttackBoard[y][j].getIsShip()) {
                        continue;
                    }
                    int currentProbability = huntingProbability[y][j].getProbability();
                    huntingProbability[y][j].setProbability(currentProbability + 1);
                }
            }
        }
    }

    public static void printHashMap() {
        for (String i : Game.playerSunkShips.keySet()) {
            // ship
            System.out.print(i);
            for (String point : Game.playerSunkShips.get(i)) {
                System.out.print(point + " ");
            }
            System.out.println();
        }
    }

}