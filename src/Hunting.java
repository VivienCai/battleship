import java.util.*;

public class Hunting {
    // pass in the hit 
    // sum the sides by all ships still alive 
    // create array list that stores a queue of points to hit and sort it by probability 
    // return the arraylist 
    static ArrayList<Coordinate> queuedHits = new ArrayList<Coordinate>();
    static int right_dir = 1;
    static int left_dir = -1;
    static int down_dir = -2;
    static int up_dir = 2;
    
    // general method with helpers
    public static void hunt(Coordinate hitPoint) {
        sumSides(hitPoint);

    }

    // sum the probability beside each point
    public static void sumSides(Coordinate hitPoint) {
        Coordinate up = Main.AIAttackBoard[hitPoint.getY() - 1][hitPoint.getX()];
        Coordinate down = Main.AIAttackBoard[hitPoint.getY()+1][hitPoint.getX()];
        Coordinate right = Main.AIAttackBoard[hitPoint.getY()][hitPoint.getX()+1];
        Coordinate left = Main.AIAttackBoard[hitPoint.getY()][hitPoint.getX()-1];


        for (Ship i : Ship.getList()) {
            sumCoor(up, i.getSize(), up_dir);
            sumCoor(down, i.getSize(), down_dir);
            sumCoor(right, i.getSize(), right_dir);
            sumCoor(left, i.getSize(), left_dir);
        }
        // if (up.getIsHit()==false) {
        //     // carry on with hit
        // } 
        
        // if (down.getIsHit() == false) {
        
        // }

        // if (right.getIsHit() == false) {
        
        // }

        // if (left.getIsHit() == false) {
        
        // }
    }

    public static void sumCoor(Coordinate c, int shipSize, int dir) {
        //if the coordinate is not a hit point
        if (c.getIsHit()) {
            return;
        }
        else {
            
        }
    }

    // add into the arraylist and sort at the end
    public static void getProbabilityAround(Coordinate hitPoint){
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        Coordinate up = Main.AIAttackBoard[hitPoint.getY() - 1][hitPoint.getX()];
        Coordinate down = Main.AIAttackBoard[hitPoint.getY() + 1][hitPoint.getX()];
        Coordinate right = Main.AIAttackBoard[hitPoint.getY()][hitPoint.getX() + 1];
        Coordinate left = Main.AIAttackBoard[hitPoint.getY()][hitPoint.getX() - 1];
        temp.add(up);
        temp.add(down);
        temp.add(right);
        temp.add(left);

        //looping for queued coordinates
        for (int i = 0; i < 3; i++) {
            //looping to find max probability out of remaining coordinates
            for (int j = 0; j < 3 - j; j++) {
                // how do we queue based on max prob cuz if were just sorting right its gonna queue the horizontal 
                // ones instead of going vertical 
                // can we add the points into the queue based on the highest probaiblity? would that work

                int maxIndex = 0;
                int max = up.getProbability();
                Coordinate maxP = up;
                if (temp.get(j).getProbability() > max) {
                    max = temp.get(j).getProbability();
                    maxP = temp.get(j);
                }
            }
            temp.remove(maxIndex);
            
        }


        
    }
    
}