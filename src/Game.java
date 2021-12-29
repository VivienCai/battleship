import java.util.*;

public class Game {
    // O - not hit or occupied
    // X - occupied by a ship, hit
    // M - not occupied by a ship, hit
    // S - occupied by a ship, not hit

    // maps that reference a coordinate name to a ship or a ship to a list of the
    // coordinates it occupies
    // (e.g E4 -> Destroyer)
    public static HashMap<String, Ship> AIMapOfCoor = new HashMap<String, Ship>();
    // (e.g Destroyer -> E4, E5)
    public static HashMap<String, ArrayList<String>> playerSunkShips = new HashMap<>();

    // for printing user and AI displays using the naming conventions
    public static void printPlacementArray(Coordinate array[][]) { // USER ATTACK BOARD

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
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit(), isSunk = cur.getIsSunk();
                    if ((isHit && isShip) || (isHit && isSunk)) {
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

    public static boolean boundsCheck(int n, boolean isV, int size) {
        if (n <= 11 - size) {
            return true;
        } else {
            return false;
        }
    }

    public static String getAccessKey(int y, int x) {
        char keyChar = Coordinate.columnIndex(y);
        String accessKey = String.valueOf(keyChar) + String.valueOf(x);
        return accessKey;
    }

    public static void playerMoves(ArrayList<Ship> shipsAlive, ArrayList<String> playerShipsAlive) {
        // print the placement array of the player and the hit array of the player
        System.out.println("________________________________");
        System.out.println("Your attack board: ");
        Game.printPlacementArray(Main.playerAttackBoard);

        // prompting for user input of next firing point
        System.out.println("It is your turn.");
        System.out.println("Please enter a letter from A-J for the vertical part of your coordinate: ");
        int inputy = Hitting.getInputY();
        // convert coordinate y to character
        char y = Coordinate.columnIndex(inputy);
        // get the x input
        System.out.println("Please enter a number from 1-10 for the horizontal part of your coordinate: ");
        int inputx = Hitting.getInputX();

        // inform user where they fired
        System.out.printf("You fired at coordinate %c%d.\n", y, inputx);

        // check if the players hit hits a ship and mark it on the ai placement board,
        // cur is a placeholder variable name
        Coordinate cur = Main.AIPlacementBoard[inputy][inputx];
        Coordinate curPlayer = Main.playerAttackBoard[inputy][inputx];

        // check if it is a ship point and if it has not been hit before
        if (cur.getIsShip() && !cur.getIsHit()) {
            // setting it as hit for both boards
            cur.setIsHit(true);
            curPlayer.setIsHit(true);
            // key is the coordinate we hit (e.g E4 -> CARRIER)
            String key = Game.getAccessKey(inputy, inputx);
            // getting the ship and adding times hit by 1
            Ship shipHit = Game.AIMapOfCoor.get(key);
            shipHit.addTimesHit();
            String shipHitName = shipHit.toString();

            // if the ship has been hit the size it is (indicates it to be sunk)
            if (shipHit.getTimesHit() == shipHit.getSize()) {
                // must have a default constructor so we can remove the name of the ship from
                // the list of ships alive
                Ship remove = new Ship();
                // looping through AI's player
                for (Ship aliveShip : shipsAlive) {
                    if (aliveShip.getName().equals(shipHitName)) {
                        remove = aliveShip;
                    }
                }

                shipsAlive.remove(remove);
                System.out.printf("YOU SUNK THE AI'S %s.\n", shipHit.getName());
            } else {
                System.out.printf("YOU HIT THE AI'S %s.\n", shipHit.getName());

            }
            curPlayer.setIsShip(true);
            Main.PlayerHit++;
        }
        // if it has been hit before and is a ship, or is not a ship point
        else if (cur.getIsShip() && cur.getIsHit() || !cur.getIsShip()) {
            // setting it as hit for both boards
            cur.setIsHit(true);
            curPlayer.setIsHit(true);
            System.out.println("YOU MISSED.");
            Main.PlayerMiss++;
        }
        Main.PlayerShot++; // adds one to shot
    }

    public static void AIMoves(ArrayList<Ship> shipsAlive, ArrayList<String> playerShipsAlive) {

        System.out.println("________________________________");
        System.out.println("It is the AI's turn");

        // to determine if we have hit a ship point and the AI is to target the points
        // around the hitpoint
        if (AI.isHunting) {
            // to determine if there are "unique" points (points that are of different
            // ships)
            if (AI.uniqueHitPoints.size() > 0) {
                Coordinate h = AI.uniqueHitPoints.get(0);
                String ship = AI.shipsHit.get(0);
                Hunting.hunt(h, ship);
            } else {
                System.out.println("Some error occured ur so fcked hahsldkfjalsdkjf");
            }
            // otherwise run the hit algorithm which is the one that uses probability
            // density
        } else {
            // ai generate a hit using hit or hunt
            Hitting.findProbability();
        }
        // print the attack board
        System.out.println("________________________________");
        System.out.println("AI ATTACK BOARD:");
        printPlacementArray(Main.AIAttackBoard);
    }

    public static void AIMovesEasy(ArrayList<Ship> shipsAlive, ArrayList<String> playerShipsAlive) {
        System.out.println("________________________________");
        System.out.println("It is the AI's turn");
        Hitting.findProbability();
        // print the attack board
        System.out.println("________________________________");
        System.out.println("AI ATTACK BOARD:");
        printPlacementArray(Main.AIAttackBoard);
    }

    // coin flip method to determines who goes first
    public static void coinFlip() {
        String coin;
        int iCoin;
        int coinToss;
        boolean coinValid = false;
        while (!coinValid) {
            System.out.println("Please enter 1 if you want to pick heads and 2 if you want to pick tails.");

            coin = Main.sc.next();
            coinToss = (int) (Math.random() * 2 + 1);

            // if user does not enter 1 or 2
            if (!coin.equals("1") && !coin.equals("2")) {
                System.out.println("You did not enter a valid input. Please try again.");
            } else {
                iCoin = Integer.parseInt(coin);
                if (iCoin == 1 && iCoin == coinToss) {
                    System.out.print("You picked heads.");
                    System.out.println(" The coin landed on heads.");
                    System.out.println("You are going first!");
                    Main.AIFirst = false;
                } else if (iCoin == coinToss) {
                    System.out.print("You picked tails.");
                    System.out.println(" The coin landed on tails.");
                    System.out.println("You are going first!");
                    Main.AIFirst = false;
                } else if (iCoin == 1) {
                    System.out.print("You picked heads.");
                    System.out.println(" The coin landed on tails.");
                    System.out.println("The AI is going first.");
                    Main.AIFirst = true;
                } else {
                    System.out.print("You picked tails.");
                    System.out.println(" The coin landed on heads.");
                    System.out.println("The AI is going first.");
                    Main.AIFirst = true;
                }
                coinValid = true;
            }

        }
        Main.promptEnterKey();
    }

    //method to determine easy or hard mode
    public static void easyOrHard() {
        String input;
        boolean inputValid = false;
        while (!inputValid) {
            System.out.println("Please enter 1 if you want to pick easy mode and 2 if you want to pick hard mode.");

            input = Main.sc.next();

            // if user does not enter 1 or 2
            if (!input.equals("1") && !input.equals("2")) {
                System.out.println("You did not enter a valid input. Please try again.");
            } else {
                if (input.equals("1")) {
                    System.out.println("You picked easy mode.");
                    Main.easyMode = true;
                } else {
                    System.out.println("You picked hard mode.");
                    Main.easyMode = false;
                }
                inputValid = true;
            }

        }

    }
}
