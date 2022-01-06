import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class InitGUI {
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
    public static void AIHitInit(JLabel AIHit) {
        AIHit.setFont(GUI.customFont[16]);
        AIHit.setVisible(true);
        AIHit.setBounds(JLabel.CENTER + 200, 545, 600, 30);

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
        nextBtn.setBounds(790, 700, 140, 45);
        nextBtn.setFont(GUI.customFont[16]);
        nextBtn.setForeground(Color.black);
        nextBtn.setBackground(GUI.accent);
        nextBtn.setBorder(new RoundedBorder(30));
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
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(Color.WHITE);
        window.setLocationRelativeTo(null);
    }
}