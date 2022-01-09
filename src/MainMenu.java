import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class MainMenu {

    protected static JFrame window;
    private ImageIcon backgroundImageIcon;

    private JButton startGame;
    private JButton resumeGame;
    private volatile boolean isImageVisible;
    private JButton easyModeBtn;
    private JButton headsBtn;
    private JButton submitBtn;
    private JLabel battleshipTitle;
    private JLabel openingScreenImg;
    private JLabel turnpickImg;

    private JButton nextBtn;
    private JTextPane results = new JTextPane();

    private JButton AIFirst = new JButton(new ImageIcon("assets/ai-150x150.png"));
    private JButton playerFirst = new JButton(new ImageIcon("assets/person-150x150.png"));
    private ImageIcon easyMode = new ImageIcon("assets/easymode-100x100.png");
    private ImageIcon hardMode = new ImageIcon("assets/hardmode-100x100.png");

    private boolean selected = false;

    public MainMenu(JFrame theWindow) {
        window = theWindow;
        isImageVisible = true;
        
    }

    public void loadTitleScreen() throws Exception {

        // battleshipTitle = new JLabel("Battleship");
        // battleshipTitle.setFont(GUI.customFont[48]);
        // battleshipTitle.setBounds(300, 150, 300, 200);

        // bkgImageContainer.setSize(window.getContentPane().getWidth(),
        // window.getContentPane().getHeight() / 2);
        // bkgImageContainer.setLocation(0, 0);
        // bkgImageContainer.setVisible(true);

        openingScreenImg = new JLabel(new ImageIcon("assets/openingscreen.png"));
        openingScreenImg.setSize(window.getContentPane().getWidth(),
                window.getContentPane().getHeight() + 30);
        openingScreenImg.setLocation(0, 20);

        startGame = new JButton(new ImageIcon("assets/play-100x100.png"));
        startGame.setSize(100, 100);
        startGame.setBorderPainted(false);
        startGame.setBackground(Color.WHITE);

        // TODO: CHANGE POSITION
        startGame.setLocation(150, 375);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Am running");
                // window.getContentPane().remove(startGame);
                window.getContentPane().remove(openingScreenImg);
                // window.getContentPane().remove(easyModeBtn);
                window.getContentPane().removeAll();
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                window.getContentPane().setBackground(Color.WHITE);
                isImageVisible = false;
                loadCoinFlip();
                // return;
            }
        });

        easyModeBtn = new JButton(hardMode);
        easyModeBtn.setSize(100, 100);
        easyModeBtn.setLocation(150, 495);
        easyModeBtn.setBorderPainted(false);
        easyModeBtn.setBackground(Color.WHITE);

        easyModeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.easyMode = !Main.easyMode;
                if (Main.easyMode) {
                    easyModeBtn.setIcon(easyMode);
                } else {
                    easyModeBtn.setIcon(hardMode);
                }
                System.out.println(Main.easyMode);
            }
        });

        resumeGame = new JButton(new ImageIcon("assets/save-100x100.png"));
        resumeGame.setSize(100, 100);
        resumeGame.setLocation(150, 615);
        resumeGame.setBorderPainted(false);
        resumeGame.setBackground(Color.WHITE);

        resumeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int inputInt=0;
                boolean isValid=false;
                
                String input = JOptionPane.showInputDialog(null,
                		"Please the save file number you want to resume from. ");
                try {
                	inputInt = Integer.parseInt(input);
                	isValid=true;
                } catch (Exception e) {

                    isValid=false;
                }
                
                
                if (isValid) {
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
                        JOptionPane.showMessageDialog(GUI.frame, "Sorry, we cannot find the file you are looking for.");
                		e.printStackTrace();
                	}
                }	
                
            }
        });

        startGame.setVisible(true);
        easyModeBtn.setVisible(true);
        resumeGame.setVisible(true);
        openingScreenImg.setVisible(true);
        // battleshipTitle.setVisible(true);

        window.getContentPane().add(startGame);
        window.getContentPane().add(easyModeBtn);
        window.getContentPane().add(resumeGame);
        window.getContentPane().add(openingScreenImg);

        // window.getContentPane().add(bkgImageContainer);
        // window.getContentPane().add(battleshipTitle);

        window.setVisible(true);
        InitGUI.initWindow(window);

    }

    public void loadCoinFlip() {
        turnpickImg = new JLabel(new ImageIcon("assets/turnpicking.png"));
        turnpickImg.setSize(window.getContentPane().getWidth(),
                window.getContentPane().getHeight() + 30);
        turnpickImg.setLocation(0, 20);

        AIFirst.setBounds(300, 220, 150, 150);
        AIFirst.setBorderPainted(false);
        AIFirst.setBackground(Color.WHITE);
        AIFirst.addActionListener(e -> {
            submitBtn.setEnabled(true);
            selected = true;
            AIFirst.setEnabled(false);
            playerFirst.setEnabled(true);
            Main.isPlayersTurn = false;
            Main.AIFirst = true;
        });

        playerFirst.setBounds(545, 220, 150, 150);
        playerFirst.setBorderPainted(false);
        playerFirst.setBackground(Color.WHITE);
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

        submitBtn = new JButton(new ImageIcon("assets/submit.png"));
        submitBtn.setBorderPainted(false);
        submitBtn.setBackground(Color.WHITE);
        submitBtn.setSize(600, 100);
        submitBtn.setLocation(200, 500);
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
        turnpickImg.setVisible(true);

        window.getContentPane().add(submitBtn);
        // window.getContentPane().add(headsBtn);
        window.getContentPane().add(AIFirst);
        window.getContentPane().add(playerFirst);
        window.getContentPane().add(turnpickImg);

        InitGUI.initWindow(window);
    }

}