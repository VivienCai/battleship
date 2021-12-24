public class testingfile {
    public static void main(String[] args) {
        // TESTING CODE DUMP
        System.out.println("Enter a y (A-J) coordinate to check if there is a ship there.");
        int inputy = AI.getInputY();
        System.out.println("Enter an x (1-10) coordinate to check if there is a ship there.");
        int inputx = AI.getInputX();

        char keyChar = Coordinate.columnIndex(inputy);
        String accessKey = String.valueOf(keyChar) + String.valueOf(inputx);
        System.out.println(Game.AIMapOfCoor.get(accessKey));

        // AI.place(playerPlacementBoard);
        // Game.printPlacementArray(playerPlacementBoard);

        // if (Main.playerPlacementBoard[y][x].getIsShip()) {
        //     String accessKey = Game.getAccessKey(y, x);

        //     Ship shipHit = Game.playerMapOfCoor.get(accessKey);
        //     shipHit.addTimesHit();

        //     System.out.println("The AI hit one of your ships. It hit your: " + shipHit);
        //     System.out.println("Your " + shipHit + " has been hit " + shipHit.getTimesHit() + " times.");
        // }

        // if (Main.playerPlacementBoard[y][x].getIsShip()) {
        //     String accessKey = Game.getAccessKey(y, x);

        //     Ship shipHit = Game.playerMapOfCoor.get(accessKey);
        //     shipHit.addTimesHit();

        //     System.out.println("The AI hit one of your ships. It hit your: " + shipHit);
        //     System.out.println("Your " + shipHit + " has been hit " + shipHit.getTimesHit() + " times.");
        // }
        // else {
        //     System.out.println("The AI missed.");
        // }


        // initliaze the player board and ai board (should have 2?)
        // Prompting user to place ships
        // Game.placeShip();

        // temp... getting AI to place players ship bc we are lazy
        // AI.place(playerPlacementBoard);
        // Game.printPlacementArray(playerPlacementBoard);

        // loop through all 5 ships
    // limit the range where they can place the ships based on its size
    // ask for vertical vs horizontal orientation and "home coordinate"
    // place each ship and assign the home coordinate for the ship and check if
    // there are any ships where it is trying to be placed
    // create the ship class and put it in the arraylist
    // static Scanner sc = new Scanner(System.in);

    // public static void placeShip() {
    //     for (int i = 2; i <= 6; i++) {
    //         int shipSize = i;
    //         if (i == 6) {
    //             shipSize = 3;
    //         }
    //         System.out.printf("You are currently placing a ship that is %d units long.\n", shipSize);
    //         System.out.println(
    //                 "What orientation do you want the ship to be? Write 1 for vertical and anything else for horizontal.");
    //         boolean isV;
    //         char input = sc.next().charAt(0);
    //         if (input == '1') {
    //             isV = true;
    //         } else {
    //             isV = false;
    //         }

    //         System.out.println("Please enter an uppercase letter from A-J for your y coordinate. ");
    //         int y = AI.getInputY();
    //         System.out.println("Please enter a number from 1-10 for your x coordinate. ");
    //         int x = AI.getInputX();
    //         boolean inBounds = false;

    //         if (isV) {
    //             inBounds = boundsCheck(y, isV, shipSize);
    //         } else {
    //             inBounds = boundsCheck(x, isV, shipSize);
    //         }

    //         if (!inBounds) {
    //             System.out.println("Your ship is out of bounds. Please choose another point to place your ship.");
    //             i--;
    //             continue;
    //         } else {

    //             Coordinate home = Main.playerPlacementBoard[y][x];
    //             if (AI.anyGeneratedIsShip(home, isV, shipSize, Main.playerPlacementBoard)) {
    //                 System.out.println(
    //                         "Your ship overlaps a previous placed ship. Please choose another point to place your ship.");
    //                 i--;
    //             } else {
    //                 Ship ship = new Ship(isV, shipSize, home);
    //                 System.out.println("You placed a: " + ship);
    //                 playerPlaceShip(home, shipSize, isV, ship);
    //                 playerMapOfCoor.replace(home.toString(), ship);

    //                 for (int j = 1; j < shipSize; j++) {
    //                     home.setIsShip(true);
    //                     Main.playerPlacementBoard[home.getY()][home.getX()].setIsShip(true);

    //                     // if the orientation is vertical
    //                     if (isV) {
    //                         Coordinate cur = Main.playerPlacementBoard[home.getY() + j][home.getX()];
    //                         playerMapOfCoor.replace(cur.toString(), ship);
    //                         cur.setIsShip(true);

    //                     } else {
    //                         Coordinate cur = Main.playerPlacementBoard[home.getY()][home.getX() + j];
    //                         playerMapOfCoor.replace(cur.toString(), ship);
    //                         cur.setIsShip(true);
    //                     }
    //                 }
    //             }
    //         }
    //         System.out.println("Your placement board:");
    //         printPlacementArray(Main.playerPlacementBoard);

                // if (playerAttackBoard[inputy][inputx].getIsHit()) {
            //     continue;
            // }
    //     }

    // }
    // public static void hitNextPoint(){
    //     Coordinate hit = hitPointQueue.get(0);
    //     int y = hit.getY(), x = hit.getX();
    //     Main.AIAttackBoard[y][x].setIsHit(true);
        
        
    //     System.out.printf("The AI hit coordinate %c%d\n", hit.columnIndex(y), x);
    //     System.out.println("Is it a hit or miss or sink?");
    //     getInput();
    // }

    // for (Coordinate i : hitPointQueue) {
    //     System.out.print(i.getProbability() + " ");
    // }
    // System.out.println();


            // for (int i = 1; i <= 10; i++) {
        //     for (int j = 1; j <= 10; j++) {
        //         coorBoard[i][j] = new Coordinate(j, i);
        //     }
        // }
            // public void occupyShip() {
    //     isShip = true;
    // }

    // public void hitShip() {
    //     isHit = true;
    // }
        
    // public static char columnIndex(int ind) {
    //     return (char) (ind + 'A' - 1);
    // }

    // public static void removeShipFromGrid(Ship shipHit) {
    // int y = shipHit.getHomeCoord().getY();
    // int x = shipHit.getHomeCoord().getX();
    // // Main.AIPlacementBoard[y][x];

    // }

    }
}
