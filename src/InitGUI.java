/* Sarina Li, Vivien Cai, Jiaan Li
* Sun January 09
* ICS4U1
* InitGUI File
*/

/* IMPORTS 
* swing: For all graphics
* awt: For colours
* io: For throwing exceptions
*/

import java.awt.*;
import java.io.*;
import javax.swing.*;

// class used to organize and initialize all of our GUI components
public class InitGUI {

    // initializes the window whenever updates are made
    public static void initWindow(JFrame window) {
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.getContentPane().setBackground(Color.WHITE);
        window.setLocationRelativeTo(null);
    }

    // initializes our initial custom font uses a loop to
    public static void initFonts() {
        for (int i = 0; i < 49; i++) {
            File fontFile = new File("text_styles/SF-UI-Display-Bold.ttf");
            try {
                // create the font to use. Specify the size!
                Integer x = i;
                GUI.customFont[i] = Font.createFont(Font.TRUETYPE_FONT, fontFile)
                        .deriveFont(x.floatValue());
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                // register the font
                ge.registerFont(GUI.customFont[i]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        }
    }

    // initialize the label that tells the user where the AI hits and where the AI
    // hits
    public static void AIHitInit(JLabel AIHit) {
        AIHit.setFont(GUI.customFont[16]);
        AIHit.setVisible(true);
        AIHit.setBounds(JLabel.CENTER + 170, 545, 700, 30);

    }

    // initialize the 0/0/0/0 and labels it
    public static void initScore(JLabel score, int xPosition, int yPosition, boolean isAi) {
        String displayScore = "";
        if (isAi) {
            displayScore = "Total shots/Hits/Misses/Ships left: "
                    + String.valueOf(Main.AIShot + " / " + Main.AIHit + " / " + Main.AIMiss + " / "
                            + Main.getPlayerShipsAlive().size());
        } else {
            displayScore = "Total shots/Hits/Misses/Ships left: "
                    + String.valueOf(Main.PlayerShot + " / " + Main.PlayerHit + " / " + Main.PlayerMiss + " / "
                            + Main.shipsAlive.size());
        }
        score.setBounds(xPosition, yPosition, 500, 20);
        score.setText(displayScore);
        score.setFont(GUI.customFont[16]);
        score.setVisible(true);
    }

    // initializes the next button seen in the game screen
    public static void initNextBtn(JButton nextBtn) {
        GUI.setBackgroundButton(nextBtn);
        nextBtn.setBounds(795, 700, 140, 45);
        nextBtn.setFont(GUI.customFont[16]);
        nextBtn.setForeground(Color.black);
        nextBtn.setVisible(true);
    }

    // initializes the names of the arrays (labels at the top of our 2d arrays)
    public static void initArrayNames(JLabel arrayLabel, int xPosition, int yPosition, String text) {
        arrayLabel.setBounds(xPosition, yPosition, 200, 50);
        arrayLabel.setFont(GUI.customFont[18]);
        arrayLabel.setText(text);
        arrayLabel.setVisible(true);
    }

    // initializes label that has current turn
    public static void initCurrentTurn(JLabel currentTurn) {
        currentTurn.setFont(GUI.customFont[22]);
        currentTurn.setBounds(790, 660, 300, 30);
    }

    // initializes legend image
    public static void initLegend(JLabel legendImg) {
        legendImg.setBounds(30, 570, 220, 200);
    }

    // initializes timer
    public static void initTimer(JLabel displayedTimer) {
        displayedTimer.setBounds(800, 620, 300, 20);
        displayedTimer.setFont(GUI.customFont[22]);
    }

    public static void initIcons() {
        GUI.AIIcon.setBounds(350, 620, 150, 150);
        GUI.playerIcon.setBounds(500, 620, 150, 150);
        GUI.setBackgroundButton(GUI.AIIcon);
        GUI.setBackgroundButton(GUI.playerIcon);
    }

}