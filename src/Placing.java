/* Sarina Li, Vivien Cai, Jiaan Li
* Fri December 24
* ICS4U1
* Placing file
*/

public class Placing {

    // generates the home coordinate for ship
    public static Coordinate generatePoint(int shipSize, boolean orientationV) {
        int x;
        int y;
        // if vertical
        // RANDOM PLACING AT EDGES
        // if (orientationV) {
        // x = (int) ((Math.random() * 2) + 1);
        // if (x == 2) {
        // x = 10;
        // }
        // y = (int) ((Math.random() * (11 - shipSize)) + 1);

        // } // if horizontal
        // else {
        // x = (int) ((Math.random() * (11 - shipSize)) + 1);
        // y = (int) ((Math.random() * 2) + 1);
        // if (y==2) {
        // y=10;
        // }
        // }
        if (orientationV) {
            switch (shipSize) {
                case 3:
                    x = 2;
                    y = 7;
                    break;
                case 5:
                    x = 1;
                    y = 1;
                    break;
                default:
                    x = 0;
                    y = 0; // should be error
            }
        } else {
            switch (shipSize) {
                case 2:
                    x = 7;
                    y = 9;
                    break;
                case 3:
                    x = 3;
                    y = 2;
                    break;
                case 4:
                    x = 7;
                    y = 1;
                    break;
                default:
                    x = 0;
                    y = 0; // should be error
            }
        }
        return new Coordinate(y, x);
    }

    // checks if the ship will overlap any other coordinates that are already
    // occupied
    public static boolean anyGeneratedIsShip(Coordinate home, boolean orientationV, int shipSize,
            Coordinate coorBoard[][]) {
        for (int j = 0; j < shipSize; j++) {
            if (orientationV) {
                if (coorBoard[home.getY() + j][home.getX()].getIsShip()) {
                    return true;
                }
            } else {
                if (coorBoard[home.getY()][home.getX() + j].getIsShip()) {
                    return true;
                }
            }
        }
        return false;
    }

    // PLACING ALGORITHM
    public static void place(Coordinate coorBoard[][]) {
        // loops from 2 - 6 since there are 2 ships of length 3
        for (int i = 2; i <= 6; i++) {
            int shipSize = i;
            if (i == 6) {
                shipSize = 3;
            }
            // instantiating
            // boolean orientationV = (int) ((Math.random() * 2) + 1) == 1 ? true : false;
            boolean orientationV;
            if (i >= 5) {
                orientationV = true;
            } else {
                orientationV = false;
            }
            boolean isCoorUnique = false;

            // generating a new point (home coordinate of ship)
            Coordinate home = generatePoint(shipSize, orientationV);

            // while the coordinate isnt unique
            while (!isCoorUnique) {
                // if the ship intersects with any other ship that already occupied with another
                // ship, generate a new point
                if (anyGeneratedIsShip(home, orientationV, shipSize, coorBoard)) {
                    home = generatePoint(shipSize, orientationV);
                } else {
                    isCoorUnique = true;
                }
            }

            Ship ship = new Ship(orientationV, shipSize, home);
            Ship.getList().add(ship);

            // once ship is placeable
            int y = home.getY();
            int x = home.getX();
            for (int j = 1; j < shipSize; j++) {
                Coordinate currentCoordinate = coorBoard[y][x];
                home.setIsShip(true);
                currentCoordinate.setIsShip(true);
                Game.AIMapOfCoor.replace(currentCoordinate.toString(), ship);
                String shipName = ship.toString();
                Game.AIMapOfShip.get(shipName).add(currentCoordinate.toString());

                // if the orientation is vertical
                if (orientationV) {
                    coorBoard[y + j][x].setIsShip(true);
                    int newY = y + j;
                    String key = Game.getAccessKey(newY, x);
                    Game.AIMapOfCoor.replace(key, ship);
                    Game.AIMapOfShip.get(shipName).add(key);

                } else {
                    coorBoard[y][x + j].setIsShip(true);
                    int newX = x + j;
                    String key = Game.getAccessKey(y, newX);
                    Game.AIMapOfCoor.replace(key, ship);
                    Game.AIMapOfShip.get(shipName).add(key);

                }
            }
        }

    }
}