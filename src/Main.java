import java.util.*;

// note: add a game class for cleaner code

public class Main {
    static Scanner sc =new Scanner(System.in);
    static boolean gamestate = true;
    public static Coordinate playerPlacementBoard[][] = new Coordinate[11][11];
    public static Coordinate playerAttackBoard[][] = new Coordinate[11][11];
    
    public static Coordinate AIPlacementBoard[][] = new Coordinate[11][11];
    public static Coordinate AIAttackBoard[][] = new Coordinate[11][11];
    
    public static void main(String[] args) {
        // ask player to place ships
        System.out.println("Please place your ships. "); //INSERT JIAAN LI CODE



        // generate a random placement
        //instantiating Coordinate for boards
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                playerPlacementBoard[i][j] = new Coordinate(i, j);
                playerAttackBoard[i][j] = new Coordinate(i, j);
                AIPlacementBoard[i][j] = new Coordinate(i, j);
                AIAttackBoard[i][j] = new Coordinate(i, j);
            }
        }
        //generating random placement for AI PlacementBoard
        AI.place(AIPlacementBoard);
        AI.printBoard(AIPlacementBoard);
        // initliaze the player board and ai board (should have 2?)
        while (gamestate) {
            // ask player for coordinate to attack

            //print the placement array of the player and the hit array of the player
            // System.out.println("Your placement board: ");
            // Game.printPlacementArray(playerPlacementBoard);
            // System.out.println("AI's board: ");
            // Game.printPlacementArray(playerAttackBoard);
            
            // System.out.println("Please enter a letter from A-J for the vertical part of your coordinate: ");
            // char inputy = Hit.getInputY();
            // System.out.println("Please enter a number from 1-10 for the horizontal part of your coordinate: ");
            // int inputx = Hit.getInputX();

            // if (playerAttackBoard[inputy][inputx].getIsHit()) {
            //     continue;
            // }
            
            // // check if the players hit hits a ship and mark it on the ai placement board 
            // // if 
            
            // if (AIPlacementBoard[inputy][inputx].getIsShip()) {
            //     System.out.println("You hit a ship point!");
            //     AIPlacementBoard[inputy][inputx].setIsHit(true);
            //     playerAttackBoard[inputy][inputx].setIsHit(true);
            //     playerAttackBoard[inputy][inputx].setIsShip(true);
            // } else {
            //     System.out.println("You did not hit a ship point.");
            //     playerAttackBoard[inputy][inputx].setIsHit(true);
            // }
            playerStuff.getCoords(playerPlacementBoard);

            
            // ai generate a hit using hit or hunt


        }



 
        // loop through all 5 ships
        // limit the range where they can place the ships based on its size
        // ask for vertical vs horizontal orientation and "home coordinate"
        // place each ship and assign the home coordinate for the ship and check if
        // there are any ships where it is trying to be placed
        // create the ship class and put it in the arraylist

        Coordinate testar[][] = new Coordinate[11][11];
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                testar[i][j] = new Coordinate(i, j);
            }
        }

        Ship testing = new Ship(true, 3, new Coordinate(1, 3));
        Ship.getList().add(testing);

        // occupyArray(testar);

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (testar[i][j].getIsShip() == true) {
                    System.out.printf("x ");
                } else {
                    System.out.printf("o ");
                }

            }
            System.out.println();
        }

    }

    
}
