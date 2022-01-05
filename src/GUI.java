import java.awt.*;
import java.awt.image.BufferedImage;
// import java.io.File;
import java.io.*;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

public class GUI {
    // private volatile boolean isImageVisible;
    private static JFrame frame;
    // private static boolean gamestate = true;
    private static JButton displayArrayAIAttack[][] = new JButton[11][11];
    private static JButton displayArrayPlayerAttack[][] = new JButton[11][11];
    protected static Font customFont[] = new Font[49];
    private static boolean alreadyFired = false;
    private static Coordinate h;
    private static JLabel AIHit = new JLabel();
    // protected static String[] ships = { "CARRIER", "BATTLESHIP", "CRUISER",
    // "SUBMARINE", "DESTROYER" };

    protected static String[] ships = { "CARRIER", "BATTLESHIP", "CRUISER", "SUBMARINE", "DESTROYER" };

    static Scanner sc = new Scanner(System.in);

    static Color accent = new Color(0xd6d6d6);

    public GUI() {

    }

    public static String[] getShips() {
        String[] ships = new String[Main.playerShipsAlive.size()];
        for (int i = 0; i < Main.playerShipsAlive.size(); i++) {
            ships[i] = Main.playerShipsAlive.get(i);
        }
        return ships;
    }

    public static void initNextBtn(JButton nextBtn) {
        nextBtn.setBounds(790, 600, 140, 45);
        nextBtn.setFont(customFont[16]);
        nextBtn.setForeground(Color.black);
        nextBtn.setBackground(accent);
        nextBtn.setBorder(new RoundedBorder(30));
        nextBtn.setVisible(true);
    }

    public static void setUpWindow() throws Exception {

        frame = new JFrame();

        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setMinimumSize(new Dimension(1000, 700));
        frame.setResizable(false);
        frame.pack();

        startGame();

    }

    public static void initArrayNames(JLabel arrayLabel, int xPosition, int yPosition, String text) {
        arrayLabel.setBounds(xPosition, yPosition, 200, 50);
        arrayLabel.setFont(customFont[18]);
        arrayLabel.setText(text);
        arrayLabel.setVisible(true);
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
    }

    // TODO: ORGANIZE AND MAKE IT LESS BAD
    public static void display(JFrame window) throws IOException {

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
        // JLabel AIHit = new JLabel();
        currentTurn.setBounds(790, 560, 300, 30);
        currentTurn.setFont(customFont[22]);

        JButton nextBtn = new JButton("Next turn");
        // JButton nextBtn;
        // BufferedImage buttonIcon = ImageIO.read(new File("vsj.png"));
        // Image img = icon.getImage();
        // Image newimg = img.getScaledInstance(NEW_WIDTH, NEW_HEIGHT,
        // java.awt.Image.SCALE_SMOOTH);
        // icon = new ImageIcon(newimg);

        // nextBtn = new JButton(new ImageIcon(buttonIcon));

        initNextBtn(nextBtn);
        AIHitInit();
        // nextBtn.setBorderPainted(false);

        displayArray(Main.playerAttackBoard, displayArrayPlayerAttack, 100, 55, window, true, nextBtn);
        displayArray(Main.AIAttackBoard, displayArrayAIAttack, 100, 540, window, false, nextBtn);

        JLabel AIAttack = new JLabel();
        initArrayNames(AIAttack, 540, 45, "Your Home Board");
        JLabel playerAttack = new JLabel();
        initArrayNames(playerAttack, 55, 45, "Your Attack Board");

        JLabel AIScore = new JLabel();
        initScore(AIScore, 540, 85, true);

        JLabel playerScore = new JLabel();
        initScore(playerScore, 55, 85, false);

        if (!Main.isPlayersTurn) {
            currentTurn.setText("It is AI's turn.");
        } else {
            currentTurn.setText("It is your turn.");
            AIHit.setText("Pick a point to fire at on the attack board.");
        }

        currentTurn.setVisible(true);

        window.getContentPane().add(AIHit);
        window.getContentPane().add(nextBtn);
        window.getContentPane().add(currentTurn);
        window.getContentPane().add(AIAttack);
        window.getContentPane().add(playerAttack);
        window.getContentPane().add(AIScore);
        window.getContentPane().add(playerScore);

        nextBtn.setEnabled(false);

        // AI's TURN
        if (!Main.isPlayersTurn) {
            alreadyFired = false;
            if (AI.isHunting ) {
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
            	if (Main.easyMode) {
            		h = Hitting.findProbabilityGUIEasy();

            	}else {
            		h = Hitting.findProbabilityGUI();
            	}
            	
            }

            int y = h.getY();
            int x = h.getX();
            String AIHitString = "The AI hit " + JLabelCoordinateString(y, x) + ". Is it a hit, miss, or sink?";
            JLabel label = new JLabel(AIHitString);
            label.setFont(customFont[14]);

            // AIHit.setBounds(x, y, width, height);
            AIHit.setHorizontalAlignment(JLabel.CENTER);
            // AIHit.setVerticalAlignment(600);

            AIHit.setText(AIHitString);
            String[] hitOrMiss = { "Hit", "Miss", "Sink" };
            int index = JOptionPane.showOptionDialog(window, label, "AI Hit", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, hitOrMiss, hitOrMiss[0]);
            if (index == 0 || index == 2) {
                System.out.println("was a hit");

                JLabel label2 = new JLabel("What ship did the AI hit?");
                label2.setFont(customFont[16]);
                int shipIndex = JOptionPane.showOptionDialog(window, label2, "What ship?",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, getShips(), getShips()[0]);

                if (index == 0) {
                    AIHit.setText("The AI hit " + JLabelCoordinateString(y, x)
                            + ". It hit your " + Game.shipOf(shipIndex) + ". Please click next turn to continue.");
                } else {
                    AIHit.setText("The AI hit " + JLabelCoordinateString(y, x)
                            + ". It sunk your " + Game.shipOf(shipIndex) + ". Please click next turn to continue.");
                }

                if (!AI.isHunting) {
                    Hitting.getInputGUI(h, index, shipIndex);

                } else {
                    Hunting.getInputGUI(h, index, shipIndex);

                }
            } else if (index == 1) {
                Main.AIAttackBoard[y][x].setIsHit(true);
                Main.AIMiss++;
                Main.AIShot++;
                AIHit.setText("The AI hit " + JLabelCoordinateString(y, x)
                        + ". It missed. Please click next turn to continue.");
            }
            nextBtn.setEnabled(true);
        } else {

        }
        
        //saving game
        if(!Main.roundOver) {    //save only triggers at the end of each round
            int inputInt;
        	int result = JOptionPane.showConfirmDialog(null, "Do you want to save your game?");
            switch (result) {
               case JOptionPane.YES_OPTION:
               System.out.println("Yes");
               while(true) {
               String input = JOptionPane.showInputDialog(null, "Please the save file number you want to save to.");
               		try {
               			inputInt=Integer.parseInt(input);
               			break;
               		}catch(Exception e){
               			
               		}
               }
               
               
				try {
					FileHandling.saveGame(inputInt);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Game.printPlacementArray(Main.AIAttackBoard);
			    JOptionPane.showMessageDialog(frame, "Thank you for playing our battleship game and we are sorry to see you go. Please come back soon");
			    JOptionPane.showMessageDialog(frame, "Your files have been saved in two files called Info" + inputInt + ".txt and Grids"+inputInt+".txt");
			    JOptionPane.showMessageDialog(frame, "Please DO NOT tamper with the two files or your data may be PERMANENTLY lost");

               
               break;
               case JOptionPane.NO_OPTION:
               System.out.println("No");
               break;
            }        	
 	        
        }
        
        Main.roundOver=!Main.roundOver;	

        
        
        nextBtn.addActionListener(e -> {
            reInitFrame(window, nextBtn, currentTurn, AIAttack, playerAttack, AIScore, playerScore);
        });
    }

    public static void initScore(JLabel score, int xPosition, int yPosition, boolean isAi) {
        String displayScore = "";
        if (isAi) {
            displayScore = "Hits/Misses/Ships left: "
                    + String.valueOf(Main.AIHit + " / " + Main.AIMiss + " / " + Main.getPlayerShipsAlive().size());
        } else {
            displayScore = "Hits/Misses/Ships left: "
                    + String.valueOf(Main.PlayerHit + " / " + Main.PlayerMiss + " / " + Main.shipsAlive.size());
        }
        score.setBounds(xPosition, yPosition, 300, 20);
        score.setText(displayScore);
        score.setFont(customFont[18]);
        score.setVisible(true);

    }

    public static void AIHitInit() {
        AIHit.setFont(customFont[16]);
        AIHit.setVisible(true);
        AIHit.setBounds(JLabel.CENTER + 200, 540, 600, 30);
        // AIHit.setPreferredSize(new Dimension(500, 30));
        // AIHit.setVerticalAlignment(530);
        // AIHit.setHorizontalAlignment(JLabel.CENTER);

    }

    public static void displayArray(Coordinate array[][], JButton display[][], int yPosition, int xPosition,
            JFrame window, boolean isEnabled, JButton nextBtn) {
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

        int xIndexingSum = xPosition;
        int yIndexingSum = yPosition + sizeOfButton;
        // instatiatee
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                display[i][j] = new JButton();
            }
        }

        for (int i = 0; i < 10; i++) {
            columnIndex[i] = new JLabel();
            columnIndex[i].setText(String.valueOf(i + 1));
            columnIndex[i].setBounds(xIndexingSum + 5, yPosition, sizeOfButton, sizeOfButton);
            columnIndex[i].setFont(customFont[16]);
            window.getContentPane().add(columnIndex[i]);
            columnIndex[i].setVisible(true);
            xIndexingSum += sizeOfButton;

            rowIndex[i] = new JLabel();
            rowIndex[i].setText(String.valueOf(Coordinate.columnIndex(i + 1)));
            rowIndex[i].setBounds(xPosition - ((int) sizeOfButton / 2), yIndexingSum, sizeOfButton, sizeOfButton);
            rowIndex[i].setFont(customFont[16]);
            window.getContentPane().add(rowIndex[i]);
            rowIndex[i].setVisible(true);
            yIndexingSum += sizeOfButton;
        }

        for (int i = 1; i <= 10; i++) {
            yPosition += sizeOfButton;
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = array[i][j];
                JButton current = display[i][j];
                current.setEnabled(isEnabled);
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
                                        String[] confirmation = { "Yes", "No" };
                                        JLabel label3 = new JLabel("Are you sure you want to hit " + yInd + xInd + "?");
                                        label3.setFont(customFont[14]);
                                        int index = JOptionPane.showOptionDialog(window,
                                                label3, "AI hit",
                                                JOptionPane.DEFAULT_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE, null, confirmation, confirmation[0]);
                                        if (index == 0) {
                                            Main.PlayerShot++;
                                            alreadyFired = true;
                                            String bruhman = Game.firePoint(g, k, Main.shipsAlive,
                                                    Main.playerShipsAlive);
                                            String AIHitString = "You hit " + JLabelCoordinateString(k, g) + ". "
                                                    + bruhman + ". Please press next turn to continue.";
                                            AIHitInit();
                                            AIHit.setText(AIHitString);
                                            // AIHit.setHorizontalAlignment(JLabel.CENTER);
                                            // Main.isPlayersTurn = false;
                                        }
                                        nextBtn.setEnabled(true);
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

                } else if (cur.getIsHit() && cur.getIsShip() || cur.getIsUnique()) {
                    // hit
                    current.setBackground(Color.RED);

                } else if (cur.getIsHit() && !cur.getIsShip()) {
                    // miss

                    current.setBackground(Color.GRAY);

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
        return Coordinate.columnIndex(y) + "" + x + "\n";
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

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public static void reInitFrame(JFrame window, JButton nextBtn, JLabel currentTurn, JLabel AIAttack,
            JLabel playerAttack, JLabel AIScore, JLabel PlayerScore) {
        Main.isPlayersTurn = !Main.isPlayersTurn;
        window.getContentPane().remove(currentTurn);
        window.getContentPane().remove(AIHit);
        window.getContentPane().remove(AIAttack);
        window.getContentPane().remove(playerAttack);
        window.getContentPane().remove(AIScore);
        window.getContentPane().remove(PlayerScore);

        AIHit.setVisible(false);
        nextBtn.setVisible(false);
        currentTurn.setVisible(false);
        AIScore.setVisible(false);
        AIScore.setVisible(false);
        window.getContentPane().validate();
        window.getContentPane().repaint();
        window.getContentPane().setBackground(Color.WHITE);
        window.setLocationRelativeTo(null);
        removeArray(displayArrayAIAttack, window);
        removeArray(displayArrayPlayerAttack, window);
        try {
            display(window);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

}
