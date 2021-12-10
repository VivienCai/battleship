public class Coordinate {
    private int x_coor;
    private int y_coor;

    public Coordinate(int x, int y) {
        x_coor = x;
        y_coor = y;
    }

    public int getX() {
        return x_coor;
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
}
