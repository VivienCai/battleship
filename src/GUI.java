import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.util.*;

public class GUI {
    // private volatile boolean isImageVisible;
    private static JFrame frame;
    private static boolean gamestate = true;
    private static JButton displayArrayAIAttack[][] = new JButton[11][11];
    private static JButton displayArrayPlayerAttack[][] = new JButton[11][11];
    protected static Font customFont[] = new Font[49];
    private static boolean alreadyFired = false;
    private static Coordinate h;

    // protected static String[] ships = { "CARRIER", "BATTLESHIP", "CRUISER",
    // "SUBMARINE", "DESTROYER" };

    protected static String[] ships = { "CARRIER", "BATTLESHIP", "CRUISER", "SUBMARINE", "DESTROYER" };

    static Scanner sc = new Scanner(System.in);

    public GUI() {

    }

    public static String[] getShips() {
        String[] ships = new String[Main.playerShipsAlive.size()];
        for (int i = 0; i < Main.playerShipsAlive.size(); i++) {
            ships[i] = Main.playerShipsAlive.get(i);
        }
        return ships;
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
        for (int i = 0; i < 49; i++) {
            try {
                // create the font to use. Specify the size!
                Integer x = i;
                customFont[i] = Font.createFont(Font.TRUETYPE_FONT, new File("SF-UI-Display-Bold.ttf"))
                        .deriveFont(x.floatValue());
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                // register the font
                ge.registerFont(customFont[i]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }

            MainMenu startMenu = new MainMenu(frame);
            startMenu.loadTitleScreen();
        }

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

        JLabel currentTurn = new JLabel();
        JLabel AIHit = new JLabel();
        currentTurn.setBounds(50, 10, 300, 30);

        JButton nextBtn = new JButton("Next turn?");
        nextBtn.setBounds(150, 10, 200, 50);

        displayArray(Main.playerAttackBoard, displayArrayPlayerAttack, 50, 33, window);
        displayArray(Main.AIAttackBoard, displayArrayAIAttack, 50, 466, window);

        if (!Main.isPlayersTurn) {
            currentTurn.setText("AI turn rn");
        } else {
            currentTurn.setText("Players turn rn");
        }
        AIHit.setVisible(true);
        nextBtn.setVisible(true);
        currentTurn.setVisible(true);

        window.getContentPane().add(AIHit);
        window.getContentPane().add(nextBtn);
        window.getContentPane().add(currentTurn);

        // AI's TURN
        if (!Main.isPlayersTurn) {
            alreadyFired = false;
            if (AI.isHunting) {
                // to determine if there are "unique" points (points that are of different
                // ships)
                if (AI.uniqueHitPoints.size() > 0) {
                    Coordinate hello = AI.uniqueHitPoints.get(0);
                    String ship = AI.shipsHit.get(0);
                    h = Hunting.huntGUI(hello, ship);
                } else {
                    System.out.println("Some error occured ur so fcked hahsldkfjalsdkjf");
                }
                // otherwise run the hit algorithm which is the one that uses probability
                // density
            } else {
                // ai generate a hit using hit or hunt
                h = Hitting.findProbabilityGUI();
            }

            int y = h.getY();
            int x = h.getX();
            String AIHitString = JLabelCoordinateString(y, x) + ". Is it a hit, miss, or sunk?";
            AIHit.setBounds(300, 500, 300, 30);
            AIHit.setText(AIHitString);
            String[] hitOrMiss = { "hit", "miss", "sunk" };
            int index = JOptionPane.showOptionDialog(window, AIHitString, "AI Hit", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, hitOrMiss, hitOrMiss[0]);
            if (index == 0 || index == 2) {
                System.out.println("was a hit");

                int shipIndex = JOptionPane.showOptionDialog(window, "What ship did the AI hit?", "What ship?",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, getShips(), getShips()[0]);

                if (!AI.isHunting) {
                    Hitting.getInputGUI(h, index, shipIndex);

                } else {
                    Hunting.getInputGUI(h, index, shipIndex);

                }

            } else if (index == 1) {
                Main.AIAttackBoard[y][x].setIsHit(true);
                Main.AIMiss++;
                Main.AIShot++;
                System.out.println("was a miss");
            }
        } else {

        }

        nextBtn.addActionListener(e -> {
            Main.isPlayersTurn = !Main.isPlayersTurn;
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
        JLabel columnIndex[] = new JLabel[10];
        JLabel rowIndex[] = new JLabel[10];

        int sizeOfButton = 40;
        int xPositionSum = xPosition;
        // instatiatee
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                display[i][j] = new JButton();
            }
        }

        for (int i = 0; i < 10; i++) {
            columnIndex[i] = new JLabel();
            rowIndex[i] = new JLabel();
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
                                // System.out.println("y: " + k + "x: " + g);
                                // y = k, x = g
                                String yInd = String.valueOf(Coordinate.columnIndex(k));
                                String xInd = String.valueOf(g);
                                // call player's turn method
                                if (Main.isPlayersTurn) {
                                    if (!alreadyFired) {
                                        // are u sure
                                        String[] confirmation = { "yes", "no" };
                                        int index = JOptionPane.showOptionDialog(window,
                                                "Are you sure you want to hit " + yInd + xInd, "AI Hit",
                                                JOptionPane.DEFAULT_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE, null, confirmation, confirmation[0]);
                                        if (index == 0) {
                                            alreadyFired = true;
                                            // String bruhman =
                                            Game.firePoint(g, k, Main.shipsAlive, Main.playerShipsAlive);
                                            // String AIHitString = "You hit " + JLabelCoordinateString(k, g) + ". "
                                            // + bruhman;
                                            // AIHit.setBounds(300, 500, 300, 30);
                                            // AIHit.setText(AIHitString);
                                            // Main.isPlayersTurn = false;
                                        }
                                    } else {
                                        // if already fired
                                        JOptionPane.showMessageDialog(frame,
                                                "You already fired! Please press next turn.",
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            }
                        }
                    }

                });
                current.setBounds(xPositionSum, yPosition, sizeOfButton, sizeOfButton);
                current.setOpaque(true);
                // current.setBackground(Color.RED);
                // current.setForeground(Color.BLACK);
                if (cur.getIsSunk()) {
                    current.setBackground(new Color(0xB80000));

                } else if (cur.getIsHit() && !cur.getIsShip()) {
                    // miss
                    // System.out.println("TESTING HLLOE");
                    current.setBackground(Color.GRAY);

                } else if (cur.getIsHit() && cur.getIsShip()) {
                    // hit
                    current.setBackground(Color.RED);
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
