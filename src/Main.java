import java.awt.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

/* Sarina Li, Vivien Cai, Jiaan Li
* Mon December 20
* ICS4U1
* Main Class
*/

public class Main {
	
    // -----------ATTRIBUTES-----------------------
    private static boolean gamestate = true;
    protected static Scanner sc = new Scanner(System.in);
    protected static boolean AIFirst = false;
    protected static int AIShot, AIHit, AIMiss, PlayerShot, PlayerMiss, PlayerHit = 0;
    private static int counter = 1;
    protected static boolean easyMode, roundOver = false;
    protected static boolean heads = false;
    protected static boolean isPlayersTurn;
    protected static int count = 0;
    protected static ArrayList<Integer> playerShipTimesHit = new ArrayList<Integer>();
    protected static Coordinate playerAttackBoard[][] = new Coordinate[11][11];
    protected static Coordinate AIPlacementBoard[][] = new Coordinate[11][11];
    protected static Coordinate AIAttackBoard[][] = new Coordinate[11][11];
    protected static ArrayList<Ship> shipsAlive = Ship.getList();
    protected static ArrayList<String> playerShipsAlive = Ship.getPlayerListOfShipsAlive();
    public static JFrame mainFrame;

    //Main Method
    public static void main(String[] args) throws Exception {      
        Game.initHitPoint();
        introPrompt();
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
                
            } else {   //If hard mode
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
                System.out.println("enter save game number");
                int numb = sc.nextInt();
                FileHandling.saveGame(numb);
                break;
            }
        }
    }
    
    
    public static void setUpMain() throws Exception {
        mainFrame = new JFrame();
        mainFrame.getContentPane().setLayout(null);
        mainFrame.getContentPane().setBackground(Color.WHITE);
        mainFrame.setPreferredSize(new Dimension(900, 615));
        mainFrame.setMinimumSize(new Dimension(900, 615));
        mainFrame.setResizable(false);
    }

    public static void runMain() throws Exception {
        setUpMain();
        introPrompt(); //For coin flip, saved games, easy mode
        promptEnterKey();

        // Keeps on looping until either the AI or player loses
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
            	//2 scenarios, player first or AI first
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
                System.out.println("enter save game number");
                int numb = sc.nextInt();
                FileHandling.saveGame(numb);
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

        for (int i = 0; i < 5; i++) {
            playerShipTimesHit.add(0);
        }
    }

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
        
        //Sees if the user wants to resume or start a new game
        if (input.equals("RESUME")) {
            System.out.println(
                    "Which save file would you like to resume from? Please enter the save file number you saved to.");
            int temp = sc.nextInt(); 
            FileHandling.resumeGame(temp);
            FileHandling.resumeBoards(temp);
        } else {
            System.out.println("Would you like to play on easy or hard AI mode?");
            Game.easyOrHard();
            
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

    public static void printShipsAlive() {
        System.out.println("________________________________");
        System.out.println("AI ships alive: ");
        //Loops through all the alive ships
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
