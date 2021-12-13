public class AI {
    boolean isHunting;
    Coordinate test;
    

    // helper methods

    public static Coordinate generatepointToPlace(int shipSize, boolean orientationV) {
        int x;
        int y;
        if (orientationV) {
            x = (int) ((Math.random() * 10) + 1);
            y = (int) ((Math.random() * (11 - shipSize)) + 1);

        } else {
            x = (int) ((Math.random() * (11 - shipSize)) + 1);
            y = (int) ((Math.random() * 10) + 1);
        }
        return new Coordinate(y, x);
    }

    // placing algorithm
    public static void place() {
        // for every ship, generating a point
        for (int shipSize = 2; shipSize <= 5; shipSize++) {
            boolean orientationV = (int) ((Math.random() * 2) + 1) == 1 ? true : false;
            Coordinate generate = new Coordinate(0, 0);
            boolean isCoorUnique = false;
            //if the 
            while (!isCoorUnique) {
                generate = generatepointToPlace(shipSize, orientationV);
                if (generate.getIsShip()) {
                    generate = generatepointToPlace(shipSize, orientationV);
                } else {
                    isCoorUnique = true;
                }
            }
            Ship ship = new Ship(orientationV, shipSize, generate);
        }

    }

    public static void main(String[] args) {
        place();
    }

}
