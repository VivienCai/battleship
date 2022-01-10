import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.Timer;

public class GUI {

    // -----------ATTRIBUTES-----------------------
    public static JFrame frame;

    protected static boolean AIwon;
    protected static boolean AITIE;
    private static boolean alreadyFired = false;

    protected static Font customFont[] = new Font[49];

    private static JButton displayArrayAIAttack[][] = new JButton[11][11];
    private static JButton displayArrayPlayerAttack[][] = new JButton[11][11];
    private static Coordinate h;
    private static JLabel AIHit = new JLabel();
    public static JButton saveGame = new JButton("Save Game");;
    private static JLabel legendImg = new JLabel(new ImageIcon("assets/legend.png"));
    private static JButton AIIcon = new JButton(new ImageIcon("assets/ai-150x150.png"));
    private static JButton playerIcon = new JButton(new ImageIcon("assets/person-150x150.png"));
    protected static JLabel displayedTimer = new JLabel("00:00");
    protected static JLabel AIScore = new JLabel();
    protected static JLabel playerAttack = new JLabel();
    protected static JLabel AIAttack = new JLabel();
    protected static JLabel playerScore = new JLabel();
    protected static JLabel currentTurn = new JLabel();
    protected static String[] ships = { "CARRIER", "BATTLESHIP", "CRUISER", "SUBMARINE", "DESTROYER" };
    static Color accent = new Color(0xd6d6d6);

    // -----------METHODS-----------------------
    // Sets up initial window
    public static void setUpWindow() throws Exception {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 850));
        frame.setMinimumSize(new Dimension(1000, 850));
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.pack();
        startGame();
    }

    // starts the game
    public static void startGame() throws Exception {
        InitGUI.initFonts();
        MainMenu startMenu = new MainMenu(frame);
        startMenu.loadTitleScreen();
        System.out.println("Done initializing!");
    }

    //displays the board
    public static void display(JFrame window) throws IOException {

        //next button must be put here or the program breaks
        JButton nextBtn = new JButton(new ImageIcon("assets/nxtturn.png"));
  

        if (Main.count == 0) {
            Timer time = new Timer(); // Instantiate Timer Object
            ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
            time.schedule(st, 0, 1000); // Create Repetitively task for every 1 secs
            Main.count++;
        }

        if (Main.shipsAlive.size() == 0) {
            // if (Main.AIShot == 1) {
            System.out.println("AI lost, player wins");
            AIwon = false;
            AITIE = false;
            endingScreen(window);
            MusicPlayer.playSound("disappointed.wav", false);
            new Music().start();
            return;
        } else if (Main.playerShipsAlive.size() == 0) {
            // } else if (Main.PlayerShot == 1) {
            System.out.println("AI won, player lost");
            AIwon = true;
            AITIE = false;
            endingScreen(window);
            MusicPlayer.playSound("victory.wav", false);
            new Music().start();
            return;
        } else if (Main.shipsAlive.size() == 0 && Main.playerShipsAlive.size() == 0) {
            System.out.println("AI and player tie.");
            AITIE = true;
            AIwon = true;
            endingScreen(window);
            new Music().start();
            return;
        }

        FileHandling.saveGameButton();

        currentTurn.setFont(customFont[22]);
        currentTurn.setBounds(790, 660, 300, 30);

        legendImg.setBounds(30, 570, 220, 200);

        AIIcon.setBorderPainted(false);
        AIIcon.setBounds(350, 620, 150, 150);
        AIIcon.setBackground(Color.WHITE);

        playerIcon.setBounds(500, 620, 150, 150);
        playerIcon.setBorderPainted(false);
        playerIcon.setBackground(Color.WHITE);

        displayedTimer.setBounds(800, 620, 300, 20);
        displayedTimer.setFont(customFont[22]);

        InitGUI.initNextBtn(nextBtn);
        InitGUI.AIHitInit(AIHit);

        displayArray(Main.playerAttackBoard, displayArrayPlayerAttack, 100, 55, window, true, nextBtn);
        displayArray(Main.AIAttackBoard, displayArrayAIAttack, 100, 540, window, false, nextBtn);

        InitGUI.initArrayNames(AIAttack, 540, 45, "Your Home Board");
        InitGUI.initArrayNames(playerAttack, 55, 45, "Your Attack Board");

        InitGUI.initScore(AIScore, 540, 85, true);
        InitGUI.initScore(playerScore, 55, 85, false);

        currentTurn.setVisible(true);
        legendImg.setVisible(true);
        AIIcon.setVisible(true);
        playerIcon.setVisible(true);
        displayedTimer.setVisible(true);

        window.getContentPane().add(AIIcon);
        window.getContentPane().add(playerIcon);
        window.getContentPane().add(legendImg);
        window.getContentPane().add(AIHit);
        window.getContentPane().add(saveGame);
        window.getContentPane().add(nextBtn);
        window.getContentPane().add(currentTurn);
        window.getContentPane().add(AIAttack);
        window.getContentPane().add(playerAttack);
        window.getContentPane().add(AIScore);
        window.getContentPane().add(playerScore);
        window.getContentPane().add(displayedTimer);

        nextBtn.addActionListener(e -> {
            reInitFrame(window, nextBtn, currentTurn, AIAttack, playerAttack, AIScore, playerScore);
        });
        nextBtn.setEnabled(false);

        if (FileHandling.firstRound) {
            FileHandling.promptSaveGame();
            FileHandling.firstRound = false;
        }

        if (!Main.isPlayersTurn) {
            currentTurn.setText("It is AIsha's turn.");
            playerIcon.setEnabled(false);
            AIIcon.setEnabled(true);

            alreadyFired = false;

            if (AI.isHunting) {
                // to determine if there are "unique" points (points that are of different
                // ships)
                if (AI.uniqueHitPoints.size() > 0) {
                    Coordinate huntPoint = AI.uniqueHitPoints.get(0);
                    String ship = AI.shipsHit.get(0);
                    h = Hunting.huntGUI(huntPoint, ship);
                } else {
                    System.out.println("Some error occured ur so fcked hahsldkfjalsdkjf");
                }
                // otherwise run the hit algorithm which is the one that uses probability
                // density
            } else {
                // ai generate a hit using hit or hunt

                if (Main.easyMode) {
                    h = Hitting.findProbabilityGUIEasy();

                } else {
                    h = Hitting.findProbabilityGUI();
                }

            }

            // get x and y

            int y = h.getY(), x = h.getX();

            String AIHitString = "AIsha hit " + JLabelCoordinateString(y, x) + ". Is it a hit, miss, or sink?";
            JLabel label = new JLabel(AIHitString);

            label.setFont(customFont[14]);

            AIHit.setHorizontalAlignment(JLabel.CENTER);
            AIHit.setText(AIHitString);

            String[] hitOrMiss = { "Hit", "Miss", "Sink" };

            // while loop
            boolean supposedToBeSunk;
            boolean supposedToBeHit;

            do {
                supposedToBeSunk = false;
                supposedToBeHit = false;

                int index = JOptionPane.showOptionDialog(window, label, "AIsha Hit", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, hitOrMiss, hitOrMiss[0]);
                if (index == 0 || index == 2) {
                    System.out.println("was a hit");

                    JLabel label2 = new JLabel("What ship did AIsha hit?");
                    label2.setFont(customFont[16]);
                    int shipIndex = JOptionPane.showOptionDialog(window, label2, "What ship?",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, getShips(), getShips()[0]);
                    System.out.println("ShipIndex:" + shipIndex);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MusicPlayer.playSound("explosion.wav", false);
                        }
                    }).start();
                    if (index == 0) { // Hit a point

                        // If the amount of times hit is equal to size
                        if (Main.playerShipTimesHit.get(shipIndex) + 1 == Ship
                                .getSize(Main.playerShipsAlive.get(shipIndex))) {
                            JOptionPane.showMessageDialog(GUI.frame,
                                    "Your input is not possible. Please try again.");
                            supposedToBeSunk = true;

                        } else {
                            AIHit.setText("AIsha hit " + JLabelCoordinateString(y, x)
                                    + ". It hit your " + Game.shipOf(shipIndex)
                                    + ". Please click next turn to continue or save game to save.");
                            Main.playerShipTimesHit.set(shipIndex, Main.playerShipTimesHit.get(shipIndex) + 1); // add 1
                        }
                    } else {

                        if (Main.playerShipTimesHit.get(shipIndex) + 1 != Ship
                                .getSize(Main.playerShipsAlive.get(shipIndex))) {
                            JOptionPane.showMessageDialog(GUI.frame,
                                    "Your input is not possible. Please try again.");
                            supposedToBeHit = true;
                        } else {
                            AIHit.setText("AIsha hit " + JLabelCoordinateString(y, x)
                                    + ". It sunk your " + Game.shipOf(shipIndex)
                                    + ". Please click next turn to continue or save game to save.");
                            Main.playerShipTimesHit.remove(shipIndex);
                        }
                    }

                    if (!supposedToBeSunk && !supposedToBeHit) { // only triggers if input is correct
                        if (!AI.isHunting) {
                            Hitting.getInputGUI(h, index, shipIndex);
                        } else {
                            Hunting.getInputGUI(h, index, shipIndex);
                        }
                    }

                } else if (index == 1) { // Hit missed
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MusicPlayer.playSound("miss.wav", false);
                        }
                    }).start();
                    Main.AIAttackBoard[y][x].setIsHit(true);
                    Main.AIMiss++;
                    Main.AIShot++;
                    AIHit.setText("AIsha hit " + JLabelCoordinateString(y, x)
                            + ". It missed. Please click \"next turn\" to continue or \"save game\" to save.");
                }

                nextBtn.setEnabled(true);

            } while (supposedToBeSunk || supposedToBeHit);

            // Player's turn
        } else {
            currentTurn.setText("It is your turn.");

            playerIcon.setEnabled(true);

            AIIcon.setEnabled(false);

            AIHit.setText("Pick a point to fire at on the attack board.");
            AIHit.setHorizontalAlignment(JLabel.CENTER);
        }

        // loop end

        Main.roundOver = !Main.roundOver;

    }

    public static void displayArray(Coordinate array[][], JButton display[][], int yPosition, int xPosition,
            JFrame window, boolean isEnabled, JButton nextBtn) {

        JLabel columnIndex[] = new JLabel[10];
        JLabel rowIndex[] = new JLabel[10];

        int sizeOfButton = 40;
        int xPositionSum = xPosition;

        int xIndexingSum = xPosition;
        int yIndexingSum = yPosition + sizeOfButton;
        // instatiate
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
                    // while(true) { //Loops until player enters a valid input
                    Object button = e.getSource();
                    for (int k = 1; k <= 10; k++) {
                        for (int g = 1; g <= 10; g++) {
                            if (display[k][g].equals(button)) {
                                // System.out.println("y: " + k + "x: " + g);
                                // y = k, x = g
                                String yInd = String.valueOf(Coordinate.columnIndex(k));
                                String xInd = String.valueOf(g);
                                // call player's turn method
                                // boolean hitBefore=Main.playerAttackBoard[yInd][xInd].getIsHit();
                                if (Main.isPlayersTurn) {
                                    if (!alreadyFired) {
                                        // asks for confirmation
                                        String[] confirmation = { "Yes", "No" };
                                        JLabel confirmHit = new JLabel(
                                                "Are you sure you want to hit " + yInd + xInd + "?");
                                        confirmHit.setFont(customFont[14]);
                                        int index = JOptionPane.showOptionDialog(window,
                                                confirmHit, "AIsha hit",
                                                JOptionPane.DEFAULT_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE, null, confirmation, confirmation[0]);
                                        if (index == 0) {
                                            Main.PlayerShot++;
                                            alreadyFired = true;
                                            String result = Game.firePoint(g, k, Main.shipsAlive,
                                                    Main.playerShipsAlive);
                                            String AIHitString = "You hit " + JLabelCoordinateString(k, g) + ". "
                                                    + result + ". Please press next turn to continue.";
                                            AIHit.setText(AIHitString);
                                            InitGUI.AIHitInit(AIHit);
                                            nextBtn.setEnabled(true);
                                        }
                                    } else {
                                        // if already fired
                                        JOptionPane.showMessageDialog(frame,
                                                "You already fired! Please press next turn.",
                                                "Warning!", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            }
                        }
                    }

                });

                current.setBounds(xPositionSum, yPosition, sizeOfButton, sizeOfButton);
                current.setOpaque(true);

                /*
                 * Legend:
                 * neutral = white
                 * hit = red
                 * miss = gray
                 * sunk = dark red
                 */
                if (cur.getIsSunk()) {
                    // sunk
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
        window.setLocationRelativeTo(null);
    }

    public static void endingScreen(JFrame window) {

        window.getContentPane().removeAll();
        // InitGUI.initWindow(window);

        ImageIcon backgroundImageIcon;
        if (AIwon && !AITIE) {
            backgroundImageIcon = new ImageIcon("assets/AIWON.png");
        } else if (!AIwon && !AITIE) {
            backgroundImageIcon = new ImageIcon("assets/PLAYERWIN.png");
        } else {
            backgroundImageIcon = new ImageIcon("assets/TIE.png");
        }

        JLabel bkgImageContainer = new JLabel(backgroundImageIcon);
        bkgImageContainer.setSize(window.getContentPane().getWidth(),
                window.getContentPane().getHeight());
        bkgImageContainer.setLocation(0, 20);
        bkgImageContainer.setVisible(true);

        JTextPane AIstats = new JTextPane();
        AIstats.setText("Total shots: " + Main.AIShot + "\nHits:  " + Main.AIHit + " \nMisses: " + Main.AIMiss
                + "\nNumber of ships left: " + Main.shipsAlive.size());
        AIstats.setBounds(695, 320, 250, 190);
        AIstats.setEditable(false);
        AIstats.setFont(GUI.customFont[20]);

        JTextPane playerStats = new JTextPane();
        playerStats.setText(
                "Total shots: " + Main.PlayerShot + "\nHits:  " + Main.PlayerHit + " \nMisses: " + Main.PlayerMiss
                        + "\nNumber of ships left: "
                        + Main.getPlayerShipsAlive().size());
        playerStats.setEditable(false);
        playerStats.setBounds(425, 320, 250, 190);
        playerStats.setFont(GUI.customFont[20]);

        JLabel time = new JLabel("Game time:" + ScheduledTask.currentTime);
        time.setBounds(560, 500, 300, 50);
        time.setFont(customFont[20]);

        time.setVisible(true);
        AIstats.setVisible(true);
        playerStats.setVisible(true);
        bkgImageContainer.setVisible(true);

        window.getContentPane().add(time);
        window.getContentPane().add(AIstats);
        window.getContentPane().add(playerStats);
        window.getContentPane().add(bkgImageContainer);

        window.setVisible(true);
        InitGUI.initWindow(window);
        // return;
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
        window.getContentPane().remove(legendImg);
        window.getContentPane().remove(AIIcon);
        window.getContentPane().remove(playerIcon);
        window.getContentPane().remove(displayedTimer);
        // legendImg.setVisible(false);
        AIHit.setVisible(false);
        nextBtn.setVisible(false);
        currentTurn.setVisible(false);
        displayedTimer.setVisible(false);
        // AIScore.setVisible(false);
        // AIScore.setVisible(false);
        InitGUI.initWindow(window);
        removeArray(displayArrayAIAttack, window);
        removeArray(displayArrayPlayerAttack, window);
        try {
            display(window);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    // -----------HELPER METHODS-----------------------

    public static void setBackgroundButton(JButton button) {
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);
    }

    public static String[] getShips() {
        String[] ships = new String[Main.playerShipsAlive.size()];
        for (int i = 0; i < Main.playerShipsAlive.size(); i++) {
            ships[i] = Main.playerShipsAlive.get(i);
        }
        return ships;
    }

    public static String JLabelCoordinateString(int y, int x) {
        return Coordinate.columnIndex(y) + "" + x + "\n";
    }

    public static void removeArray(JButton displayArray[][], JFrame window) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                JButton cur = displayArray[i][j];
                cur.setEnabled(false);
                cur.setVisible(false);
                window.getContentPane().remove(cur);
            }
        }
        InitGUI.initWindow(window);

    }
}
