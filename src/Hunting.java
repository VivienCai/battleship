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

    
    //end when initial ship  + any other found sunk (when both queues are empty)
    ArrayList<Coordinate> uniqueHitPoints = new ArrayList<Coordinate>();
    static Coordinate huntingProbability[][] = new Coordinate[11][11];
    
    
    public static void hunt(Coordinate h, String ship) {
        int shipSize = Ship.getSize(ship);
        sumArray(h, shipSize);
        // Coordinate right = Main.AIAttackBoard[h.getY()][h.getX()+1];
        // Coordinate left = Main.AIAttackBoard[h.getY()][h.getX()-1];
        // Coordinate up = Main.AIAttackBoard[h.getY()-1][h.getX()];
        // Coordinate down = Main.AIAttackBoard[h.getY() + 1][h.getX()];


        
    }

    public static void sumArray(Coordinate h, int shipSize) {
        // sum vertically and horizontally
        // veritcal:
        // start point is hit point + 1 - shipsize (check if in bounds)
        int y = h.getY();
        int x = h.getX();
        for (int i = (y + 1 - shipSize); i < y; i++) {
            System.out.println("I am running");
            boolean ok = true;
            if (i < 1) {
                continue;
            }
            for (int j = i; j < i + shipSize; j++) {
                System.out.println(Main.AIAttackBoard[j][x].getIsHit());
                System.out.println(j > 10);
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
        
        AI.printArray(huntingProbability);
    }

    public static void main(String[] args) {
        // Coordinate test = new Coordinate(4, 5);
        Main.initArrays();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                huntingProbability[i][j] = new Coordinate(i, j);

            }
        }
        Coordinate test = Main.AIAttackBoard[4][5];
        Main.AIAttackBoard[4][5].setIsHit(true);
        hunt(test, "DESTROYER");

    }

    
}