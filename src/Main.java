import java.util.*;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static boolean gamestate = true;
    // public static Coordinate playerPlacementBoard[][] = new Coordinate[11][11];
    public static Coordinate playerAttackBoard[][] = new Coordinate[11][11];

    public static Coordinate AIPlacementBoard[][] = new Coordinate[11][11];
    public static Coordinate AIAttackBoard[][] = new Coordinate[11][11];

    public static void main(String[] args) {
        
        // instantiate each coordinate in the hashmap with empty for now
        // when plaueer places the ship assign each occupied coordinate with the ship
        // name

        // instantiating Coordinate for boards
        Ship emptyShip = new Ship(false, 0, new Coordinate(0,0));

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                char keyChar = Coordinate.convertIntToChar(i);
                String key = String.valueOf(keyChar) + String.valueOf(j);
                Game.AIMapOfCoor.put(key, emptyShip);
                // playerPlacementBoard[i][j] = new Coordinate(i, j);
                playerAttackBoard[i][j] = new Coordinate(i, j);
                AIPlacementBoard[i][j] = new Coordinate(i, j);
                AIAttackBoard[i][j] = new Coordinate(i, j);
            }
        }
        // ask player to place ships
        System.out.println("Hello, welcome to Sarina, Vivien, and Jiaan's battleship game.");
        // System.out.println("Please place your ships. ");

        // generating random placement for AI PlacementBoard
        AI.place(AIPlacementBoard);
        System.out.println("AI Placement Board: ");
        Game.printPlacementArray(AIPlacementBoard);

        while (gamestate) {
            for (Ship aliveShip : Ship.getList()) {
                System.out.println(aliveShip);
            }

            //print the placement array of the player and the hit array of the player
            System.out.println("Your attack board: ");
            Game.printPlacementArray(playerAttackBoard);

            System.out.println("Please enter a letter from A-J for the vertical part of your coordinate: ");
            char inputy = AI.getInputY();
            System.out.println("Please enter a number from 1-10 for the horizontal part of your coordinate: ");
            int inputx = AI.getInputX();

            if (playerAttackBoard[inputy][inputx].getIsHit()) {
                continue;
            }
            // check if the players hit hits a ship and mark it on the ai placement board
            // if
            Coordinate cur = AIPlacementBoard[inputy][inputx];
            Coordinate curPlayer = playerAttackBoard[inputy][inputx];
            if (cur.getIsShip()) {
                String key = Game.getAccessKey(inputy, inputx);
                Ship shipHit = Game.AIMapOfCoor.get(key);
                shipHit.addTimesHit();
                String shipHitName = shipHit.getName();
                
                if (shipHit.getTimesHit() == shipHit.getSize()) {
                    Ship remove = new Ship();
                    // Ship.getList().
                    for (Ship aliveShip : Ship.getList()) {
                        if (aliveShip.getName().equals(shipHitName)) {
                            remove = aliveShip;
                        }
                    }
                    Ship.getList().remove(remove);
                    System.out.printf("SUNK, %s\n", shipHit.getName());
                } else {
                    System.out.printf("HIT, %s\n", shipHit.getName());
                }
                cur.setIsHit(true);
                curPlayer.setIsHit(true);
                curPlayer.setIsShip(true);
            } else {
                System.out.println("MISS");
                curPlayer.setIsHit(true);

            }

            // ai generate a hit using hit or hunt
            System.out.println("AI's turn");
            // generate a hit

            if (AI.isHunting) {
                // call hunt
            } else {
                AI.findProbability();
            }
            // verify if hit or not
            // print the probability board and AIATtackBoard

        }

        // loop through all 5 ships
        // limit the range where they can place the ships based on its size
        // ask for vertical vs horizontal orientation and "home coordinate"
        // place each ship and assign the home coordinate for the ship and check if
        // there are any ships where it is trying to be placed
        // create the ship class and put it in the arraylist

    }

}
