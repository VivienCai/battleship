import java.util.*;

// note: add a game class for cleaner code

public class Main {
    static Scanner sc = new Scanner(System.in);
    static boolean gamestate = true;
    public static Coordinate playerPlacementBoard[][] = new Coordinate[11][11];
    public static Coordinate playerAttackBoard[][] = new Coordinate[11][11];

    public static Coordinate AIPlacementBoard[][] = new Coordinate[11][11];
    public static Coordinate AIAttackBoard[][] = new Coordinate[11][11];

    public static void main(String[] args) {
        // instantiate each coordinate in the hashmap with empty for now
        // when plaueer places the ship assign each occupied coordinate with the ship
        // name

        // instantiating Coordinate for boards
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                Game.playerMapOfCoor.put(new Coordinate(i,j), "empty");
                playerPlacementBoard[i][j] = new Coordinate(i,j);
                playerAttackBoard[i][j] = new Coordinate(i, j);
                AIPlacementBoard[i][j] = new Coordinate(i,j);
                AIAttackBoard[i][j] = new Coordinate(i,j);
            }
        }
        // ask player to place ships
        System.out.println("Hello, welcome to Sarina, Vivien, and Jiaan's battleship game.");
        System.out.println("Please place your ships. ");
        // Game.placeShip();

        // generate a random placement
        AI.place(AIAttackBoard);
        // generating random placement for AI PlacementBoard
        // AI.place(AIPlacementBoard);
        System.out.println("AI Placement Board: ");
        Game.printPlacementArray(playerPlacementBoard);
        // initliaze the player board and ai board (should have 2?)
        // while (gamestate) {
        // // check if all ships are alive

        // // ask player for coordinate to attack

        // //print the placement array of the player and the hit array of the player
        // System.out.println("Your placement board: ");
        // Game.printPlacementArray(playerPlacementBoard);
        // System.out.println("Your attack board: ");
        // Game.printPlacementArray(playerAttackBoard);

        // System.out.println("Enter a y (A-J) coordinate to check if there is a ship
        // there.");
        // int inputy = AI.getInputY();
        // System.out.println("Enter an x (1-10) coordinate to chekc if there is a ship
        // there.");
        // int inputx = AI.getInputX();

        // if(playerPlacementBoard[inputy][inputx].getIsShip()){
        // System.out.println("You hit a ship. The ship you hit was:");
        // String shipHit =
        // Game.playerMapOfCoor.get(playerPlacementBoard[inputy][inputx]);
        // System.out.println(shipHit);
        // }

        // System.out.println("Please enter a letter from A-J for the vertical part of
        // your coordinate: ");
        // char inputy = AI.getInputY();
        // System.out.println("Please enter a number from 1-10 for the horizontal part
        // of your coordinate: ");
        // int inputx = AI.getInputX();

        // if (playerAttackBoard[inputy][inputx].getIsHit()) {
        // continue;
        // }

        // // check if the players hit hits a ship and mark it on the ai placement board
        // // if

        // if (AIPlacementBoard[inputy][inputx].getIsShip()) {
        // System.out.println("You hit a ship point!");
        // AIPlacementBoard[inputy][inputx].setIsHit(true);
        // playerAttackBoard[inputy][inputx].setIsHit(true);
        // playerAttackBoard[inputy][inputx].setIsShip(true);
        // } else {
        // System.out.println("You did not hit a ship point.");
        // playerAttackBoard[inputy][inputx].setIsHit(true);

        // }

        // // ai generate a hit using hit or hunt
        // System.out.println("AI's turn");
        // // generate a hit
        // AI.findProbability();

        // // verify if hit or not
        // // print the probability board and AIATtackBoard

        // }

        // loop through all 5 ships
        // limit the range where they can place the ships based on its size
        // ask for vertical vs horizontal orientation and "home coordinate"
        // place each ship and assign the home coordinate for the ship and check if
        // there are any ships where it is trying to be placed
        // create the ship class and put it in the arraylist

    }

}
