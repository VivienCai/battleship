import java.awt.*;
import java.io.*;
import javax.swing.*;

public class InitGUI {

    public static void AIHitInit(JLabel AIHit) {
        AIHit.setFont(GUI.customFont[16]);
        AIHit.setVisible(true);
        AIHit.setBounds(JLabel.CENTER + 200, 545, 700, 30);

    }

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

    public static void initNextBtn(JButton nextBtn) {
        GUI.setBackgroundButton(nextBtn);
        nextBtn.setBounds(795, 700, 140, 45);
        nextBtn.setFont(GUI.customFont[16]);
        nextBtn.setForeground(Color.black);
        nextBtn.setVisible(true);
    }

    public static void initArrayNames(JLabel arrayLabel, int xPosition, int yPosition, String text) {
        arrayLabel.setBounds(xPosition, yPosition, 200, 50);
        arrayLabel.setFont(GUI.customFont[18]);
        arrayLabel.setText(text);
        arrayLabel.setVisible(true);
    }

    public static void initWindow(JFrame window) {
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.getContentPane().setBackground(Color.WHITE);
        window.setLocationRelativeTo(null);
    }
//dam papa u a rare breed no comparing pl im gonna commit rn
    public static void initFonts() { 
        for (int i = 0; i < 49; i++) {
            File fontFile = new File("SF-UI-Display-Bold.ttf");
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
}