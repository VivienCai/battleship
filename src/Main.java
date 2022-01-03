import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import java.io.PrintWriter;

/* Sarina Li, Vivien Cai, Jiaan Li
* Mon December 20
* ICS4U1
* Main Class
*/

public class Main {
    // attributes
    private static boolean gamestate = true;
    protected static Scanner sc = new Scanner(System.in);
    protected static boolean AIFirst = false;
    protected static int AIShot, AIHit, AIMiss, PlayerShot, PlayerMiss, PlayerHit = 0;
    private static int counter = 1;
    protected static boolean easyMode = false;
    protected static boolean heads = false;
    protected static boolean isPlayersTurn;

    protected static Coordinate playerAttackBoard[][] = new Coordinate[11][11];
    protected static Coordinate AIPlacementBoard[][] = new Coordinate[11][11];
    protected static Coordinate AIAttackBoard[][] = new Coordinate[11][11];
    private static int fileCounter = 0;
    // getting ship list of AI and player
    protected static ArrayList<Ship> shipsAlive = Ship.getList();
    protected static ArrayList<String> playerShipsAlive = Ship.getPlayerListOfShipsAlive();

    public static JFrame mainFrame;
    // public static JPanel panel = new JPanel();

    public static void setUpMain() throws Exception {
        mainFrame = new JFrame();
        mainFrame.getContentPane().setLayout(null);
        mainFrame.getContentPane().setBackground(Color.WHITE);
        // mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(900, 615));
        mainFrame.setMinimumSize(new Dimension(900, 615));
        mainFrame.setResizable(false);
        // mainFrame.pack();
    }

    public static void runMain() throws Exception {
        setUpMain();
        // instantiating Coordinate for boards
        // intro message, coin flip, and instruction for input
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
            if (shipsAlive.size() == 0 && playerShipsAlive.size() == 0) {
                System.out.println("AI and player tie.");
                break;
            }

            // prints the ships still alive for AI and user
            printShipsAlive();
            if (easyMode) {
                if (AIFirst == true) {
                    isPlayersTurn = false;
                    Game.AIMovesEasy(shipsAlive, playerShipsAlive);
                    isPlayersTurn = true;
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                } else {
                    isPlayersTurn = true;
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                    isPlayersTurn = false;
                    Game.AIMovesEasy(shipsAlive, playerShipsAlive);
                }
            } else {
                if (AIFirst == true) {
                    isPlayersTurn = false;
                    Game.AIMoves(shipsAlive, playerShipsAlive);
                    isPlayersTurn = true;
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                } else {
                    isPlayersTurn = true;
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                    isPlayersTurn = false;
                    Game.AIMoves(shipsAlive, playerShipsAlive);
                }
            }
            printHitsMisses();
            System.out.println("Round " + counter
                    + " is over. If you would like to stop playing and save, please enter \"SAVE\".");
            counter++;
            String temp = sc.nextLine();
            if (temp.equals("SAVE")) { // sees if the user wants to save the game
                saveGame();
                break;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        // runMain();
        // public static void hello() throws Exception{
        // instantiating Coordinate for boards
        // intro message, coin flip, and instruction for input
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
            if (shipsAlive.size() == 0 && playerShipsAlive.size() == 0) {
                System.out.println("AI and player tie.");
                break;
            }

            // prints the ships still alive for AI and user
            printShipsAlive();
            if (easyMode) {
                if (AIFirst == true) {
                    Game.AIMovesEasy(shipsAlive, playerShipsAlive);
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                } else {
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                    Game.AIMovesEasy(shipsAlive, playerShipsAlive);
                }
            } else {
                if (AIFirst == true) {
                    Game.AIMoves(shipsAlive, playerShipsAlive);
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                } else {
                    Game.playerMoves(shipsAlive, playerShipsAlive);
                    Game.AIMoves(shipsAlive, playerShipsAlive);
                }
            }
            printHitsMisses();
            System.out.println("Round " + counter
                    + " is over. If you would like to stop playing and save, please enter \"SAVE\".");
            counter++;
            String temp = sc.nextLine();
            if (temp.equals("SAVE")) { // sees if the user wants to save the game
                saveGame();
                break;
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
                // giving each key a ship value so when we reference E4 we can just check the
                // hashmap for the key E4 and it will
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
        // initializing arrays for coordinate that have been hit for each ship
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

    public static void introPrompt() throws Exception {
        System.out.println("Hello, welcome to Sarina, Vivien, and Jiaan's battleship game.");
        System.out.println("Would you like to start a new game or resume an old one? ");
        System.out.println("Please enter RESUME if you want to continue and anything else for a new game.");
        System.out.println("______________________________________________________________");
        String input = sc.nextLine();
        initArrays();

        if (input.equals("RESUME")) {
            System.out.println("Which save file would you like to resume from? Please enter a positive number");
            int temp = sc.nextInt();                                                                              // add crash prevention
            FileHandling.resumeGame(temp);
            FileHandling.resumeBoards(temp);
        } else {
            System.out.println("Would you like to play on easy or hard AI mode?");
            Game.easyOrHard();
            // System.out.println("easy mode: " + Main.easyMode);

            // Determining who goes first
            System.out.println("To determine who goes first, lets do a coin flip!");
            Game.coinFlip();
            Placing.place(AIPlacementBoard);

        }
        System.out.println("AI Placement Board: ");
        Game.printPlacementArray(AIPlacementBoard);

        System.out.println("When inputting things in the code, make sure to use all capitals. ");
        System.out.println("If the AI missed, type \"HIT\".");
        System.out.println("If one of your ships were hit, type \"HIT, [SHIPTYPE]\". EX. HIT, BATTLESHIP ");
        System.out.println("If one of your ships were sunk, type \"SUNK, [SHIPTYPE]\". EX. SUNK, BATTLESHIP ");
    }

    public static void saveGame() throws Exception {
        System.out.println("Which save file would you like to save to? Please enter a number greater than 1.");
        int fileNumber = sc.nextInt(); // ADD SMTG TO STOP CODE FROM CRASHING IF STRING

        PrintWriter text = new PrintWriter("Info" + fileNumber + ".txt");

        text.println(AIFirst);
        text.println(AI.isHunting);
        text.println(AIShot + " " + AIHit + " " + AIMiss);
        text.println(PlayerShot + " " + PlayerHit + " " + PlayerMiss);

        for (int i = 0; i < Ship.getList().size(); i++) {
            text.print(Ship.getList().get(i).getName() + " ");
            if (Ship.getList().get(i).getIsSunk()) { // If sunk
                text.print("SUNK ");
            } else { // if not sunk
                text.print("ALIVE ");
            }
            text.print(Ship.getList().get(i).getSize() + " ");
            text.print(Ship.getList().get(i).getHomeCoord().getX() + " "); // Prints AI ships
            text.print(Ship.getList().get(i).getHomeCoord().getY() + " ");
            text.print(Ship.getList().get(i).getVertical() + " ");
            text.print(Ship.getList().get(i).getTimesHit() + " ");
            text.println();

        } // name, dead/alive, size, xcoord, y coord, vertical, timeshit

        // 2,3,4,5,3
        text.println("PLAYER");

        for (int i = 0; i < Ship.getPlayerListOfShipsAlive().size(); i++) {
            text.print(Ship.getPlayerListOfShipsAlive().get(i) + " "); // Prints player ships
        }
        text.println();
        text.println("UNIQUEHITPOINTS");
        //printing isHunting
        text.println(AI.isHunting);
        
        //printing uniquehitpoints
        text.println(AI.uniqueHitPoints.size() );
        
        for (int i=0;i<AI.uniqueHitPoints.size();i++) {    //uniquiehitpoint in Hitting line 70  ONLY NEED TO STORE X AND Y COORD
        	 text.print(AI.uniqueHitPoints.get(i).getX()+" ");
        	 text.println(AI.uniqueHitPoints.get(i).getY());
        }
        
        //add shipsHit
        for (int i=0;i<AI.shipsHit.size();i++) {
        	text.print(AI.shipsHit.get(i)+" ");
        }
        text.println();
        text.println("HASHMAP");

        //Print hasmap of playerShipsAlive
        for (String i : Game.playerSunkShips.keySet()) {
            //ship
            text.print(i+" ");
            for (String point : Game.playerSunkShips.get(i)) {
                text.print(point + " ");
            }
        text.println();
        }
        
        
        
        text.close();

        FileHandling.saveBoards(fileNumber);
        // Finish printing to battleship1

        // ADD FILE PARINT PLACEMENT ARRAY METHOD KMSKMSKMSKMS

        System.out.println(
                "Thank you for playing our battleship game and we are sorry to see you go. Please come back soon");
        System.out.println("Your files have been saved in a file called Info" + fileNumber + ".txt and Grids"
                + fileNumber + ".txt"); // add save number
        System.out.println("Please DO NOT tamper with the two files or your data may be permanently lost");

    }
    /*
     * Format for saving game
     * 1st line will have number of AI shot, hit and miss EX. 10 1 9
     * 2nd line will have number of Player shot hit and miss
     * 3rd line will have types of ships alive for AI
     * 4th type of ship alive for player
     * 
     * BELOW WILL HAVE A DIFFERENT TEXT FILE
     * 1st grid is AI home board
     * 2nd grid is Player Home board
     * 3rd grid is AI attack board
     * 
     * 
     * 
     * 
     * things to save
     * 1. ship locations
     * 
     */

    public static void filePlacementArray(Coordinate array[][]) throws Exception { // USER ATTACK BOARD

        PrintWriter text = new PrintWriter("Battleship.txt");

        for (int i = 0; i <= 10; i++) {
            char ind = (char) ('A' + i - 1);
            if (i == 0) {
                for (int j = 0; j <= 10; j++) {
                    System.out.print(j + " ");
                }
            }
            if (i > 0) {
                System.out.print(ind + " ");
            }
            for (int j = 1; j <= 10; j++) {
                if (i == 0) {
                    continue;
                } else {
                    Coordinate cur = array[i][j];
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit();
                    if (isHit && isShip) {
                        System.out.print("X ");
                    } else if (isHit && !isShip) {
                        System.out.print("M ");
                    } else if (!isHit && isShip) {
                        System.out.print("S ");
                    } else {
                        System.out.print("O ");
                    }
                }

            }
            System.out.println();
        }
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

    public static ArrayList<Ship> getShipsAlive() {
        return shipsAlive;
    }

    public static ArrayList<String> getPlayerShipsAlive() {
        return playerShipsAlive;
    }

    public static void printHitsMisses() {
        System.out.println("Number of AI shots/ Number of AI misses/ Number of AI hits");
        System.out.println(AIShot + " / " + AIMiss + " / " + AIHit);
        System.out.println("Number of player shots/ Number of player misses/ Number of player hits");
        System.out.println(PlayerShot + " / " + PlayerMiss + " / " + PlayerHit);
    }
}
