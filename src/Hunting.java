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
    ArrayList<Coordinate> uniqueHitPoints = new ArrayList<Coordinate>();
    static Coordinate huntingProbability[][] = new Coordinate[11][11];
    static ArrayList<Coordinate> hitPointQueue = new ArrayList<Coordinate>();
    
    
    public static void hunt(Coordinate h, String ship) {
        int shipSize = Ship.getSize(ship);
        sumArray(h, shipSize);
        
        // get input
        
        // sum accordingly
        
        // while(!isSunk){
            
        // }
        Coordinate nextHit = max(h.getY(), h.getX()); 
        // ask user if this point is hit miss sunk
        AI.getInput();
        // AI.resetArray();
        
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

    public static Coordinate max(int y, int x) {  
        // horizontal add possible hits to arraylist
        for (int i = 1; i <= 10; i++) {
            if(huntingProbability[y][i].getProbability()==0){
                continue;
            }
            hitPointQueue.add(Main.AIAttackBoard[y][i]);
        }

        // vertical add possible hits to arraylist
        for (int i = 1; i <= 10; i++) {
            if (huntingProbability[i][x].getProbability() == 0) {
                continue;
            }
            hitPointQueue.add(Main.AIAttackBoard[i][x]);
        }
        Collections.sort(hitPointQueue);
        return hitPointQueue.remove(0);

    }

    

    //  public static void getInput(){
    //     // ArrayList<Ship> shipsAlive = Ship.getList();
    //     while (true) {
    //         String input = sc.nextLine();
    //         String ship = input.substring(4);
            
    //         if (input.equals("MISS")) {
    //             // isHunting = false;
    //             break;
    //         }
    //         else if (input.substring(0, 3).equals("HIT")) {

    //             if (AI.checkValidShip(ship)) {
    //             //    isHunting = true;
    //             }
    //             break;
    //         } else if (input.substring(0, 4).equals("SUNK")){
    //             // SUNK, CARRIER
    //             String shipName = input.substring(5);
    //             if (AI.checkValidShip(shipName)){
    //                 System.out.println(shipName);
    //                 Ship.getPlayerListOfShipsAlive().remove(shipName);
    //             }
    //             break;
    //         } else{
    //             System.out.println("that is not a valid input. Is it a hit or miss?");
    //         }
    //     }
    // }


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