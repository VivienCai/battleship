import java.awt.*;
import javax.swing.*;

public class GUI {
    // private volatile boolean isImageVisible;
    private static JFrame frame;

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

        // // startMenu.
        // while(startMenu.isImageVisible()){}
    }
    
    public static void display(JFrame window) {
        JLabel currentTurn = new JLabel();
        // displays the player attack and ai
        displayArray(Main.playerAttackBoard, 50, 33, window);
        displayArray(Main.AIAttackBoard, 50, 466, window);
        currentTurn.setBounds(350, 10, 300, 50);
    
        if (Main.isPlayersTurn) {
            currentTurn.setText("Players turn rn");
        } else {
            currentTurn.setText("AIs turn rn");
        }
    }

    public static void displayArray(Coordinate array[][], int yPosition, int xPosition, JFrame window) {
        // create an array of buttons
        // red = hit
        // grey = sunk
        // white = miss
        // blue = neutral
        JButton displayedArray[][] = new JButton[11][11];
        int sizeOfButton = 40;
        int xPositionSum = xPosition;
        // instatiatee
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                displayedArray[i][j] = new JButton();
            }
        }


        for (int i = 1; i <= 10; i++) {
            yPosition += sizeOfButton;
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = array[i][j];
                JButton current = displayedArray[i][j];
                current.addActionListener(e -> {
                    Object button = e.getSource();
                    for (int k = 1; k<=10; k++) {
                        for (int g = 1; g <= 10; g++) {
                            if (displayedArray[k][g].equals(button)) {
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
                    current.setBackground(Color.GRAY);

                } else if (cur.getIsHit() && cur.getIsShip()) {
                    // hit
                    current.setBackground(Color.RED);
                } else if (cur.getIsHit()) {
                    // sink
                    current.setBackground(new Color(0xB80000));
                } else if (cur.getIsShip()) {
                    // neutral
                    current.setBackground(Color.BLUE);
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
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.setLocationRelativeTo(null);
    }
}
