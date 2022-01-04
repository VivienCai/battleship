
// coordinates need to be sorted by their probability so we implemented a comparator so it can be sorted/compared
public class Coordinate implements Comparable<Coordinate> {
   
    // attributes 
    private int x_coor;
    private int y_coor;
    private boolean isHit = false;
    private boolean isShip = false;
    private boolean isSunk = false;
    private boolean isUnique = false;
    private int probability = 0;

    // constructor
    public Coordinate(int y, int x) {
        x_coor = x;
        y_coor = y;
    }
    
    // getters 
    public int getX() {
        return x_coor;
    }
    
    public int getY() {
        return y_coor;
    }
    
    public boolean getIsHit() {
        return isHit;
    }

    public boolean getIsShip() {
        return isShip;
    }

    public boolean getIsSunk() {
        return isSunk;
    }

    public int getProbability() {
        return probability;
    }

    public boolean getIsUnique() {
        return isUnique;
    }

    //setters
    public void setX(int newX) {
        x_coor = newX;
    }

    public void sety(int newY) {
        y_coor = newY;
    }

    public void setIsHit(boolean newIsHit) {
        isHit = newIsHit;
    }

    public void setIsShip(boolean newIsShip) {
        isShip = newIsShip;
    }
    
    public void setIsSunk(boolean newIsSunk) {
        isSunk = newIsSunk;
    }

    public void setProbability(int newProbability) {
        probability = newProbability;
    }

    public void setIsUnique(boolean newIsUnique) {
        isUnique = newIsUnique;
    }
    
    // general purpose methods 
    // comparator that compares coordinates based on their probability 
    @Override
    public int compareTo(Coordinate cur) {
        if (this.probability > cur.probability) {
            // if current object is greater,then return 1
            return 1;
        }
        else if (this.probability < cur.probability) {
            // if current object is greater,then return -1
            return -1;
        }
        else {
            // if current object is equal to o,then return 0
            return 0;
        }
    }

    //toString method that formats it (ex: G7)
    public String toString() { 
        return String.valueOf(columnIndex(y_coor)) + String.valueOf(x_coor);
    }
    
    // returns the column index as a character using ASCII 
    // adds the ASCII value to convert an integer to its respective character value 
    public static char columnIndex(int y) {
        char asciiVal = (char) ((y - 1) + 'A');
        return asciiVal;
    }

    //returns column index as integer
    public static int columnIndexAsInt(char y) {
        int asciiVal = (int) ((y - 'A'+1));
        return asciiVal;
    }

}
