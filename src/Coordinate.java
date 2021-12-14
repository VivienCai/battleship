public class Coordinate {
    private int x_coor;
    private int y_coor;
    private boolean isShip = false;
    private boolean isHit = false;

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

}
