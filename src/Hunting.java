import java.util.*;

public class Hunting {
    // queue hit points (only queue if they are different ships)
    // get the coordinate of the hit (and what ship it is)
    // put 4 coordinates around hit into queue
    // sum probability
    // hit the point where probability greatest
    // if point is same ship, remove points that are not in the orientation
    // queue 2 points on either side and repeat algo
    //if point is hit and not same ship

    static Scanner sc = new Scanner(System.in);
    //end when initial ship  + any other found sunk (when both queues are empty)
    static ArrayList<Coordinate> uniqueHitPoints = new ArrayList<Coordinate>();
    static Coordinate huntingProbability[][] = new Coordinate[11][11];
    static ArrayList<Coordinate> hitPointQueue = new ArrayList<Coordinate>();
    static ArrayList<String> shipsHit = new ArrayList<String>();
    static boolean directionConfirmed = false;
    
    
    public static void hunt(Coordinate h, String ship) {
        int shipSize = Ship.getSize(ship);
        System.out.println(shipSize);
        if (!directionConfirmed) {
            sumArray(h, shipSize);
            Coordinate nextHit = max(h.getY(), h.getX(), shipSize);
            getInput(nextHit);
        } else {
            Coordinate nextHit = max(h.getY(), h.getX(), shipSize);
            getInput(nextHit);
        }
        if (!directionConfirmed) {
            AI.resetArray();
        }
        // hit max (set the coordinate as hit)
        // Main.AIAttackBoard[nextHit.getY()][nextHit.getX()].setIsHit(true);
        // ask user if this point is hit miss sunk
        // clear the arraylist and continue until sunk, if sunk set ishunting as false 
        
    }

    public static void getInput(Coordinate hit) {
        // ArrayList<Ship> shipsAlive = Ship.getList();
        while (true) {
            
            int y = hit.getY();
            int x = hit.getX();
            System.out.printf("The AI hit coordinate %c%d\n", hit.columnIndex(y), x);
            System.out.println("Is it a hit or miss or sink?");
            String input = sc.nextLine();
            if (input.equals("MISS")) {
                Main.AIAttackBoard[y][x].setIsHit(true);
                // isHunting = false;
                break;
            } else if (input.substring(0, 3).equals("HIT")) {
                String ship = input.substring(5);
                //if we hit a new ship point, add new ship to list
                if (AI.checkValidShip(ship) && !ship.equals(shipsHit.get(0))) {
                    // isHunting = true;
                    Main.AIAttackBoard[y][x].setIsShip(true);
                    Main.AIAttackBoard[y][x].setIsHit(true);
                    Hunting.uniqueHitPoints.add(hit);
                    Hunting.shipsHit.add(ship);
                //same ship point, set is hit
            } else if (AI.checkValidShip(ship)) {
                    Main.AIAttackBoard[y][x].setIsHit(true);
                    Main.AIAttackBoard[y][x].setIsShip(true);
                    
                }
                break;
            } else if (input.substring(0, 4).equals("SUNK")) {
                directionConfirmed = false;
                String ship = input.substring(6);
                if (AI.checkValidShip(ship)) {
                    Main.AIAttackBoard[y][x].setIsHit(true);
                    Main.AIAttackBoard[y][x].setIsShip(true);
                    System.out.println(ship);
                    Ship.getPlayerListOfShipsAlive().remove(ship);
                    uniqueHitPoints.remove(0);
                    shipsHit.remove(ship);
                }
                if (uniqueHitPoints.size() == 0) {
                    AI.isHunting = false;
                }
                // SUNK, CARRIER
                break;
            } else {
                System.out.println("that is not a valid input. Is it a hit or miss?");
            }
        }
    }
    
    public static void sumArray(Coordinate h, int shipSize) {
        // sum vertically and horizontally
        // VERTICAL
        // start point is hit point + 1 - shipsize (check if in bounds)
        int y = h.getY();
        int x = h.getX();
        for (int i = (y + 1 - shipSize); i <= y; i++) {
            boolean ok = true;
            if (i < 1) {
                continue;
            }
            for (int j = i; j < i + shipSize; j++) {
                if (j > 10 || Main.AIAttackBoard[j][x].getIsHit() && j != y) {
                    ok = false;
                }
            }
            if (ok) {
                for (int j = i; j < i + shipSize; j++) {
                    if (j == y) {
                        continue;
                    }
                    int currentProbability = huntingProbability[j][x].getProbability();
                    huntingProbability[j][x].setProbability(currentProbability + 1);

                }
            }

        }
        // HORIZONTAL
        for (int i = (x + 1 - shipSize); i <= x; i++) {
            boolean ok = true;
            if (i < 1) {
                continue;
            }
            for (int j = i; j < i + shipSize; j++) {
                if (j > 10 || Main.AIAttackBoard[y][j].getIsHit() && j != x) {
                    ok = false;
                }
            }
            if (ok) {
                for (int j = i; j < i + shipSize; j++) {
                    if (j == x) {
                        continue;
                    }
                    int currentProbability = huntingProbability[y][j].getProbability();
                    huntingProbability[y][j].setProbability(currentProbability + 1);

                }
            }

        }
        
        AI.printArray(huntingProbability);
    }

    public static Coordinate max(int y, int x, int shipSize) {  
        // horizontal add possible hits to arraylist
        for (int i = 1; i <= 10; i++) {
            if(huntingProbability[y][i].getProbability()>0){
                hitPointQueue.add(huntingProbability[y][i]);
            }
        }

        // vertical add possible hits to arraylist
        for (int i = 1; i <= 10; i++) {
            if (huntingProbability[i][x].getProbability()>0) {
                hitPointQueue.add(huntingProbability[i][x]);
            }
        }
        Collections.sort(hitPointQueue,Collections.reverseOrder());
        
        if (hitPointQueue.size() == shipSize - 1) {
            
            directionConfirmed = true;
            // only hit what is left in the hitpoint queue, don't call hunt again (dont sum the array)


        }
        for (Coordinate i : hitPointQueue) {
            System.out.print(i.getProbability() + " ");
        }
        System.out.println();
        return hitPointQueue.remove(0);

    }

    public static void main(String[] args) {
        Main.initArrays();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                huntingProbability[i][j] = new Coordinate(i, j);

            }
        }
        Coordinate test = Main.AIAttackBoard[5][5];
        Main.AIAttackBoard[5][5].setIsHit(true);
        Main.AIAttackBoard[3][5].setIsHit(true);
        hunt(test, "CARRIER");

    }

    
}   