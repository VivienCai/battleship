import java.io.IOException;
import java.util.*;

/* Sarina Li, Vivien Cai, Jiaan Li
* Mon December 20
* ICS4U1
* Main Class
*/

public class Main {
    private static boolean gamestate = true;
    protected static Scanner sc = new Scanner(System.in);
    protected static boolean AIFirst = false;

    protected static Coordinate playerAttackBoard[][] = new Coordinate[11][11];
    protected static Coordinate AIPlacementBoard[][] = new Coordinate[11][11];
    protected static Coordinate AIAttackBoard[][] = new Coordinate[11][11];
   
    //getting ship list of AI and player
    private static ArrayList<Ship> shipsAlive = Ship.getList();
    private static ArrayList<String> playerShipsAlive = Ship.getPlayerListOfShipsAlive();

    public static void main(String[] args) {
        // instantiating Coordinate for boards
        initArrays();
        //intro message, coin flip, and instruction for input
        introPrompt();
        // prompt user to "press enter to continue"
        promptEnterKey();

        // while the player or AI still has ships alive
        while (gamestate) {
            if (shipsAlive.size() == 0) {
                System.out.println("AI lost, player wins");
                break;
            }
            if (playerShipsAlive.size() == 0) {
                System.out.println("AI won, player lost");
                break;
            }

            // prints the ships still alive for AI and user
            printShipsAlive();

            if (AIFirst == true) {
                Game.AIMoves(shipsAlive, playerShipsAlive);
                Game.playerMoves(shipsAlive, playerShipsAlive);
            } else {
                Game.playerMoves(shipsAlive, playerShipsAlive);
                Game.AIMoves(shipsAlive, playerShipsAlive);
            }
        }
        
    }

    // Method that initilalzies our game boards so they do not have null values
    public static void initArrays() {
        // default constrctor for a ship denoting that there that coordinate is empty
        Ship emptyShip = new Ship();

        // instantiate every object in the 2d arrays
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                // giving each key a ship value so when we reference E4 we can just check the hashmap for the key E4 and it will 
                // return the ship name
                char keyChar = Coordinate.columnIndex(i);
                String key = String.valueOf(keyChar) + String.valueOf(j);
                Game.AIMapOfCoor.put(key, emptyShip);
                // instantiating coordinate objects for each array
                playerAttackBoard[i][j] = new Coordinate(i, j);
                AIPlacementBoard[i][j] = new Coordinate(i, j);
                AIAttackBoard[i][j] = new Coordinate(i, j);
                Hunting.huntingProbability[i][j] = new Coordinate(i, j);
            }
        }
        //initializing arrays for coordinate that have been hit for each ship
        for (int i = 0; i < 7; i++) {
            Hunting.pointsHit[i] = new ArrayList<Coordinate>();
        }   
        
    }
    // copied from stack overflow LMAO
    public static void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void introPrompt() {
        System.out.println("Hello, welcome to Sarina, Vivien, and Jiaan's battleship game.");
        System.out.println("______________________________________________________________");

        // Determining who goes first
        System.out.println("To determine who goes first, lets do a coin flip!");
        Game.coinFlip();

        // generating random placement for AI PlacementBoard
        AI.place(AIPlacementBoard);
        System.out.println("AI Placement Board: ");
        Game.printPlacementArray(AIPlacementBoard);

        System.out.println("When inputting things in the code, make sure to use all capitals. ");
        System.out.println("If the AI missed, type \"HIT\".");
        System.out.println("If one of your ships were hit, type \"HIT, [SHIPTYPE]\". EX. HIT, BATTLESHIP ");
        System.out.println("If one of your ships were sunk, type \"SUNK, [SHIPTYPE]\". EX. SUNK, BATTLESHIP ");
    }

    public static void printShipsAlive() {
        System.out.println("________________________________");
        System.out.println("AI ships alive: ");
        for (Ship aliveShip : shipsAlive) {
            System.out.println(aliveShip);
        }
        System.out.println();
        System.out.println("Player ships alive: ");
        for (String aliveShip : playerShipsAlive) {
            System.out.println(aliveShip);
        }
    }
    
}
