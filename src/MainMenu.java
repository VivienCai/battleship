

/* Sarina Li, Vivien Cai, Jiaan Li
* Sun January 09
* ICS4U1
* MainMenu File
*/

/* IMPORTS 
* swing: For all graphics
* awt: For colours
* awt.event: For action listeners on buttons
* io: For throwing exceptions
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainMenu {

    // -----------ATTRIBUTES-----------------------
    protected static JFrame window;

    // Opening screen graphics
    private JLabel openingScreenImg = new JLabel(new ImageIcon("assets/openingscreen.png"));
    private JButton startGame = new JButton(new ImageIcon("assets/play-100x100.png"));
    private JButton easyModeBtn;
    private JButton resumeGame = new JButton(new ImageIcon("assets/save-100x100.png"));;
    private ImageIcon easyMode = new ImageIcon("assets/easymode-100x100.png");
    private ImageIcon hardMode = new ImageIcon("assets/hardmode-100x100.png");

    // Turn picking screen graphics
    private JLabel turnpickImg = new JLabel(new ImageIcon("assets/turnpicking.png"));
    private JButton AIFirst = new JButton(new ImageIcon("assets/ai-150x150.png"));
    private JButton playerFirst = new JButton(new ImageIcon("assets/person-150x150.png"));
    private JButton submitBtn = new JButton(new ImageIcon("assets/submit.png"));
    private boolean selected = false;

    // -----------METHODS-----------------------
    // Constructor
    public MainMenu(JFrame theWindow) {
        window = theWindow;
    }

    // Loading opening screen
    public void loadTitleScreen() throws Exception {
        openingScreenImg.setSize(window.getContentPane().getWidth(), window.getContentPane().getHeight() + 30);
        openingScreenImg.setLocation(0, 20);

        startGame.setBounds(150, 375, 100, 100);
        GUI.setBackgroundButton(startGame);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.getContentPane().removeAll();
                InitGUI.initWindow(window);
                loadTurnPick();
            }
        });

        easyModeBtn = new JButton(hardMode);
        easyModeBtn.setBounds(150, 495, 100, 100);
        GUI.setBackgroundButton(easyModeBtn);
        easyModeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.easyMode = !Main.easyMode;
                if (Main.easyMode) {
                    easyModeBtn.setIcon(easyMode);
                } else {
                    easyModeBtn.setIcon(hardMode);
                }
                System.out.println("Easy mode is now set to : " + Main.easyMode);
            }
        });

        resumeGame.setBounds(150, 615, 100, 100);
        GUI.setBackgroundButton(resumeGame);
        resumeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int inputInt = 0;
                boolean isValid = false;
                String input = JOptionPane.showInputDialog(null,
                        "Please the save file number you want to resume from. ");
                try {
                    inputInt = Integer.parseInt(input);
                    isValid = true;
                } catch (Exception e) {
                    isValid = false;
                }

                if (isValid) {
                    try {
                        FileHandling.resumeGame(inputInt);
                        FileHandling.resumeBoards(inputInt);

                        window.getContentPane().removeAll();
                        window.getContentPane().revalidate();
                        window.getContentPane().repaint();
                        window.getContentPane().setBackground(Color.WHITE);
                        window.setLocationRelativeTo(null);
                        try {
                            GUI.display(MainMenu.window);
                        } catch (IOException e) {
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

        window.getContentPane().add(startGame);
        window.getContentPane().add(easyModeBtn);
        window.getContentPane().add(resumeGame);
        window.getContentPane().add(openingScreenImg);
        window.setVisible(true);
        InitGUI.initWindow(window);
    }

    // Loading turn picking screen where we pick who goes first
    public void loadTurnPick() {
        turnpickImg.setSize(window.getContentPane().getWidth(), window.getContentPane().getHeight() + 30);
        turnpickImg.setLocation(0, 20);

        AIFirst.setBounds(300, 220, 150, 150);
        GUI.setBackgroundButton(AIFirst);
        AIFirst.addActionListener(e -> {
            selected = true;
            AIFirst.setEnabled(false);
            submitBtn.setEnabled(true);
            playerFirst.setEnabled(true);
            Main.isPlayersTurn = false;
            Main.AIFirst = true;
        });

        playerFirst.setBounds(545, 220, 150, 150);
        GUI.setBackgroundButton(playerFirst);
        playerFirst.addActionListener(e -> {
            selected = true;
            playerFirst.setEnabled(false);
            submitBtn.setEnabled(true);
            AIFirst.setEnabled(true);
            Main.isPlayersTurn = true;
            Main.AIFirst = false;
        });

        submitBtn.setBounds(200, 500, 600, 100);
        GUI.setBackgroundButton(submitBtn);
        submitBtn.setEnabled(false);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (selected) {
                    window.getContentPane().removeAll();
                    InitGUI.initWindow(window);
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
        AIFirst.setVisible(true);
        playerFirst.setVisible(true);
        turnpickImg.setVisible(true);

        window.getContentPane().add(submitBtn);
        window.getContentPane().add(AIFirst);
        window.getContentPane().add(playerFirst);
        window.getContentPane().add(turnpickImg);
        InitGUI.initWindow(window);
    }
}