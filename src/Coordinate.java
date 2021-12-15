public class Coordinate implements Comparable<Coordinate> {
    private int x_coor;
    private int y_coor;
    private boolean isShip = false;
    private boolean isHit = false;
    private int probability = 0;

    public int getProbability() {
        return probability;
    }
    
    public void setProbability(int newProbability) {
        probability = newProbability;
    }
    public Coordinate(int y, int x) {
        x_coor = x;
        y_coor = y;
    }

    public int getX() {
        return x_coor;
    }

    public void occupyShip() {
        isShip = true;
    }

    public void hitShip() {
        isHit = true;
    }

    public int getY() {
        return y_coor;
    }

    public void setX(int newX) {
        x_coor = newX;
    }

    public void sety(int newY) {
        y_coor = newY;
    }

    public char columnIndex(int y) {
        char asciiVal = (char) ((y - 1) + 'A');
        return asciiVal;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean newIsHit) {
        isHit = newIsHit;
    }

    public boolean getIsShip() {
        return isShip;
    }

    public void setIsShip(boolean newIsShip) {
        isShip = newIsShip;
    }
    

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


    public static char convertIntToChar(int ind) {
        return (char)(ind+'A'-1);
    }

    public String toString() { 
        return String.valueOf(convertIntToChar(y_coor)) + String.valueOf(x_coor);
    } 
}
