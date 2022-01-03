import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GUI {
    // private volatile boolean isImageVisible;
    private static JFrame frame;
    private static boolean gamestate = true;
    private static JButton displayArrayAIAttack[][] = new JButton[11][11];
    private static JButton displayArrayPlayerAttack[][] = new JButton[11][11];

    static Scanner sc = new Scanner(System.in);

    public GUI() {

    }

    public static void setUpWindow() throws Exception {
        frame = new JFrame();

        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 615));
        frame.setMinimumSize(new Dimension(900, 615));
        frame.setResizable(false);
        frame.pack();
        startGame();

    }

    public static void startGame() throws Exception {
        MainMenu startMenu = new MainMenu(frame);
        startMenu.loadTitleScreen();

        // display(MainMenu.window);
        // prints the ships still alive for AI and user
        // printShipsAlive();
        // if (easyMode) {
        // if (AIFirst == true) {
        // Game.AIMovesEasy(shipsAlive, playerShipsAlive);
        // Game.playerMoves(shipsAlive, playerShipsAlive);
        // } else {
        // Game.playerMoves(shipsAlive, playerShipsAlive);
        // Game.AIMovesEasy(shipsAlive, playerShipsAlive);
        // }
        // } else {
        // if (AIFirst == true) {
        // Game.AIMoves(shipsAlive, playerShipsAlive);
        // Game.playerMoves(shipsAlive, playerShipsAlive);
        // } else {
        // Game.playerMoves(shipsAlive, playerShipsAlive);
        // Game.AIMoves(shipsAlive, playerShipsAlive);
        // }
        // }
        // printHitsMisses();
        // System.out.println("Round " + counter
        // + " is over. If you would like to stop playing and save, please enter
        // \"SAVE\".");
        // counter++;
        // String temp = sc.nextLine();
        // if (temp.equals("SAVE")) { // sees if the user wants to save the game
        // saveGame();
        // break;
        // }

    }

    // TODO: ORGANIZE AND MAKE IT LESS BAD
    public static void display(JFrame window) {
        JLabel currentTurn = new JLabel();
        JLabel AIHit = new JLabel();
        // displays the player attack and ai
        currentTurn.setBounds(50, 10, 300, 30);

        JButton nextBtn = new JButton("Next turn?");
        nextBtn.setBounds(150, 10, 200, 50);
        // AIHit.setLocation(200, 500);
        // while (gamestate) {

        if (Main.shipsAlive.size() == 0) {
            System.out.println("AI lost, player wins");
            // break;
        }
        if (Main.playerShipsAlive.size() == 0) {
            System.out.println("AI won, player lost");
            // break;
        }
        if (Main.shipsAlive.size() == 0 && Main.playerShipsAlive.size() == 0) {
            System.out.println("AI and player tie.");
            // break;
        }

        if (!Main.isPlayersTurn) {
            currentTurn.setText("AIs turn rn");
            AIHit.setBounds(300, 500, 300, 30);
            Coordinate h = Hitting.findProbabilityGUI();
            int y = h.getY();
            int x = h.getX();
            AIHit.setText(JLabelCoordinateString(y, x));
            // displayArray(Main.AIAttackBoard, 50, 466, window);

        } else {
            currentTurn.setText("Players turn rn");
        }

        displayArray(Main.playerAttackBoard, displayArrayPlayerAttack, 50, 33, window);
        displayArray(Main.AIAttackBoard, displayArrayAIAttack, 50, 466, window);

        AIHit.setVisible(true);
        nextBtn.setVisible(true);
        currentTurn.setVisible(true);

        window.getContentPane().add(AIHit);
        window.getContentPane().add(nextBtn);
        window.getContentPane().add(currentTurn);

        // System.out.println(Main.AIAttackBoard[4][4].getIsHit());
        // System.out.println(Main.AIAttackBoard[4][4].getIsShip());

        nextBtn.addActionListener(e -> {
            Main.isPlayersTurn = !Main.isPlayersTurn;
            if (Main.isPlayersTurn) {
                currentTurn.setText("Players turn rn");
            } else {
                currentTurn.setText("AIs turn rn");
            }
            window.getContentPane().remove(currentTurn);
            window.getContentPane().remove(AIHit);
            AIHit.setVisible(false);
            nextBtn.setVisible(false);
            currentTurn.setVisible(false);
            window.getContentPane().revalidate();
            window.getContentPane().repaint();
            window.getContentPane().setBackground(Color.WHITE);
            window.setLocationRelativeTo(null);
            removeArray(displayArrayAIAttack, window);
            removeArray(displayArrayPlayerAttack, window);
            display(window);
        });

        // }
    }

    public static void displayArray(Coordinate array[][], JButton display[][], int yPosition, int xPosition,
            JFrame window) {
        // create an array of buttons
        // red = hit
        // grey = sunk
        // white = miss
        // blue = neutral
        // JButton displayedArray[][] = display;

        int sizeOfButton = 40;
        int xPositionSum = xPosition;
        // instatiatee
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                display[i][j] = new JButton();
            }
        }

        for (int i = 1; i <= 10; i++) {
            yPosition += sizeOfButton;
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = array[i][j];
                JButton current = display[i][j];
                current.addActionListener(e -> {
                    Object button = e.getSource();
                    for (int k = 1; k <= 10; k++) {
                        for (int g = 1; g <= 10; g++) {
                            if (display[k][g].equals(button)) {
                                System.out.println("y: " + k + "x: " + g);
                            }
                        }
                    }

                });
                current.setBounds(xPositionSum, yPosition, sizeOfButton, sizeOfButton);
                current.setOpaque(true);
                // current.setBackground(Color.RED);
                // current.setForeground(Color.BLACK);
                if (cur.getIsHit() && !cur.getIsShip()) {
                    // miss
                    // System.out.println("TESTING HLLOE");
                    current.setBackground(Color.GRAY);

                } else if (cur.getIsHit() && cur.getIsShip()) {
                    // hit
                    current.setBackground(Color.RED);
                } else if (cur.getIsSunk()) {
                    // sink
                    current.setBackground(new Color(0xB80000));
                } else {
                    current.setBackground(Color.WHITE);
                }
                current.setFocusable(false);
                current.setVisible(true);
                window.getContentPane().add(current);
                xPositionSum += sizeOfButton;

            }
            xPositionSum = xPosition;
        }

        window.setVisible(true);
        // window.getContentPane().revalidate();
        // window.getContentPane().repaint();
        window.setLocationRelativeTo(null);
    }

    public static String JLabelCoordinateString(int y, int x) {
        return "AI hit " + Coordinate.columnIndex(y) + " " + x + "\n";
    }

    public static void removeArray(JButton displayArray[][], JFrame window) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                JButton cur = displayArray[i][j];
                window.getContentPane().remove(cur);
                cur.setVisible(false);
            }
        }
        // window.getContentPane().revalidate();
        // window.getContentPane().repaint();
        window.getContentPane().setBackground(Color.WHITE);
        window.setLocationRelativeTo(null);
    }
}
