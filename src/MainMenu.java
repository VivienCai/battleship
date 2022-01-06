import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainMenu {

    protected static JFrame window;
    private ImageIcon backgroundImageIcon;
    private JLabel bkgImageContainer;
    private JButton startGame;
    private JButton resumeGame;
    private volatile boolean isImageVisible;
    private JButton easyModeBtn;
    private JButton headsBtn;
    private JButton submitBtn;
    private JLabel battleshipTitle;

    private JButton nextBtn;
    private JTextPane results = new JTextPane();

    private JButton AIFirst = new JButton("AIsha First");;
    private JButton playerFirst = new JButton("Player First");

    private boolean selected = false;

    public MainMenu(JFrame theWindow) {
        window = theWindow;
        backgroundImageIcon = new ImageIcon("Title.png");
        bkgImageContainer = new JLabel(backgroundImageIcon);
        isImageVisible = true;
    }

    public void loadTitleScreen() throws Exception {

        battleshipTitle = new JLabel("Battleship");
        battleshipTitle.setFont(GUI.customFont[48]);
        battleshipTitle.setBounds(300, 150, 300, 200);

        // bkgImageContainer.setSize(window.getContentPane().getWidth(),
        // window.getContentPane().getHeight() / 2);
        // bkgImageContainer.setLocation(0, 0);
        // bkgImageContainer.setVisible(true);

        startGame = new JButton("Start Game");
        startGame.setSize(600, 100);
        startGame.setLocation(150, bkgImageContainer.getHeight() + 50);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // window.getContentPane().remove(startGame);
                // window.getContentPane().remove(bkgImageContainer);
                // window.getContentPane().remove(easyModeBtn);
                window.getContentPane().removeAll();
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                window.getContentPane().setBackground(Color.WHITE);
                isImageVisible = false;
                loadCoinFlip();
                // // Main main = new Main();
                // try {
                // Main.hello();
                // } catch (Exception e) {
                // e.printStackTrace();
                // }
            }
        });

        easyModeBtn = new JButton("Easy Mode is now " + Main.easyMode);
        easyModeBtn.setSize(600, 100);
        easyModeBtn.setLocation(150, 300);

        easyModeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.easyMode = !Main.easyMode;
                easyModeBtn.setText("Easy Mode: " + Main.easyMode);
                System.out.println(Main.easyMode);
            }
        });

        resumeGame = new JButton("Resume Game");
        resumeGame.setSize(600, 100);
        resumeGame.setLocation(150, 500);
        resumeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int inputInt;
                while (true) {
                    String input = JOptionPane.showInputDialog(null,
                            "Please the save file number you want to resume from.");
                    try {
                        inputInt = Integer.parseInt(input);
                        break;
                    } catch (Exception e) {
                    }
                }

                try {
                    FileHandling.resumeGame(inputInt);
                    FileHandling.resumeBoards(inputInt);

                    window.getContentPane().removeAll();
                    // window.getContentPane().remove(results);
                    // window.getContentPane().remove(headsBtn);
                    window.getContentPane().revalidate();
                    window.getContentPane().repaint();
                    window.getContentPane().setBackground(Color.WHITE);
                    window.setLocationRelativeTo(null);
                    isImageVisible = false;
                    try {

                        GUI.display(MainMenu.window);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        startGame.setVisible(true);
        easyModeBtn.setVisible(true);
        resumeGame.setVisible(true);
        battleshipTitle.setVisible(true);

        window.getContentPane().add(startGame);
        window.getContentPane().add(easyModeBtn);
        window.getContentPane().add(resumeGame);

        // window.getContentPane().add(bkgImageContainer);
        window.getContentPane().add(battleshipTitle);

        window.getContentPane().setBackground(Color.WHITE);
        window.setVisible(true);
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.setLocationRelativeTo(null);

    }

    public void loadCoinFlip() {

        backgroundImageIcon = new ImageIcon("coinflip.jpeg");

        // backgroundImageIcon = new ImageIcon("Title.png");
        bkgImageContainer = new JLabel(backgroundImageIcon);
        bkgImageContainer.setSize(window.getContentPane().getWidth(),
                window.getContentPane().getHeight());
        bkgImageContainer.setLocation(0, 0);

        AIFirst.setBounds(10, 10, 100, 100);
        AIFirst.setFont(GUI.customFont[12]);
        AIFirst.addActionListener(e -> {
            submitBtn.setEnabled(true);
            selected = true;
            AIFirst.setEnabled(false);
            playerFirst.setEnabled(true);
            Main.AIFirst = true;
        });

        playerFirst.setBounds(110, 10, 100, 100);
        playerFirst.setFont(GUI.customFont[12]);
        playerFirst.addActionListener(e -> {
            submitBtn.setEnabled(true);
            selected = true;
            playerFirst.setEnabled(false);
            AIFirst.setEnabled(true);
            Main.isPlayersTurn = true;
            Main.AIFirst = false;
        });

        // headsBtn = new JButton("Heads or Tails?");
        // headsBtn.setEnabled(false);
        // headsBtn.setSize(600, 100);
        // headsBtn.setLocation(150, 150);
        // headsBtn.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent arg0) {
        // Main.heads = !Main.heads;
        // if (!Main.heads) {
        // headsBtn.setText("You have selected tails");
        // } else {
        // headsBtn.setText("You have selected heads");
        // }
        // System.out.println(Main.heads);
        // }
        // });

        submitBtn = new JButton("Submit");
        submitBtn.setSize(600, 100);
        submitBtn.setLocation(150, 400);
        submitBtn.setEnabled(false);
        // System.out.println("testing");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (selected) {
                    window.getContentPane().removeAll();
                    window.getContentPane().revalidate();
                    window.getContentPane().repaint();
                    window.getContentPane().setBackground(Color.WHITE);
                    window.setLocationRelativeTo(null);
                    isImageVisible = false;
                    // coinFlipResults();
                    // GUI.endingScreen(window, false, true);
                    try {
                        Placing.place(Main.AIPlacementBoard);
                        Game.printPlacementArray(Main.AIPlacementBoard);
                        GUI.display(window);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        submitBtn.setVisible(true);
        // headsBtn.setVisible(true);
        AIFirst.setVisible(true);
        playerFirst.setVisible(true);
        bkgImageContainer.setVisible(true);

        window.getContentPane().add(submitBtn);
        // window.getContentPane().add(headsBtn);
        window.getContentPane().add(AIFirst);
        window.getContentPane().add(playerFirst);
        window.getContentPane().add(bkgImageContainer);

        window.getContentPane().setBackground(Color.WHITE);
        window.setVisible(true);
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.setLocationRelativeTo(null);
    }

    public void coinFlipResults() {
        // backgroundImageIcon = new ImageIcon("Title.png");
        // bkgImageContainer = new JLabel(backgroundImageIcon);
        // bkgImageContainer.setSize(window.getContentPane().getWidth(),
        // window.getContentPane().getHeight());
        // bkgImageContainer.setLocation(0, 0);

        nextBtn = new JButton("Next");
        nextBtn.setSize(600, 100);
        nextBtn.setLocation(150, 400);
        // System.out.println("testing");
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // window.getContentPane().remove(nextBtn);
                window.getContentPane().removeAll();
                // window.getContentPane().remove(results);
                // window.getContentPane().remove(headsBtn);
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                window.getContentPane().setBackground(Color.WHITE);
                window.setLocationRelativeTo(null);
                isImageVisible = false;
                try {
                    Placing.place(Main.AIPlacementBoard);
                    Game.printPlacementArray(Main.AIPlacementBoard);
                    GUI.display(MainMenu.window);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // GUI.display(window);
                // Main.initArrays();
                // Placing.place(Main.AIPlacementBoard);
                // Game.printPlacementArray(Main.AIPlacementBoard);
                // GUI.displayArray(Main.AIPlacementBoard, 0, 0, window );

            }
        });

        String textSet = Game.coinFlipReturn();
        results.setText(textSet);
        // System.out.println(Game.coinFlipReturn());
        results.setEditable(false);
        results.setBounds(370, 200, 200, 100);
        // System.out.println("testing");
        // bkgImageContainer.setVisible(true);
        results.setVisible(true);
        nextBtn.setVisible(true);

        window.getContentPane().add(results);
        window.getContentPane().add(nextBtn);

        window.getContentPane().setBackground(Color.GRAY);
        window.setVisible(true);
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.setLocationRelativeTo(null);
    }

    public boolean isImageVisible() {
        return isImageVisible;
    }
}