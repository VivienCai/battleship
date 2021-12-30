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

    public static void startGame() throws Exception{		
		MainMenu startMenu = new MainMenu(frame);
        startMenu.loadTitleScreen();
        
        // // startMenu.
		// while(startMenu.isImageVisible()){}
	}

    public static void displayArray(Coordinate array[][], int yPosition, int xPosition, JFrame window ) {
        // create an array of buttons 
        // red = hit
        // grey = sunk
        // white = miss
        // blue = neutral
        JButton displayedArray[][] = new JButton[11][11];
        int sizeOfButton = 50;
        int xPositionSum = xPosition;
        // instatiatee
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                displayedArray[i][j] = new JButton("h");
            }
        }
        for (int i = 1; i <= 10; i++) { 
            yPosition += 50;
            for (int j = 1; j <= 10; j++) {
                Coordinate cur = array[i][j];
                JButton current = displayedArray[i][j];
                current.setBounds(xPositionSum, yPosition, sizeOfButton, sizeOfButton);
                current.setOpaque(true);
                current.setBackground(Color.RED);
                current.setForeground(Color.BLACK);
                // if (cur.getIsHit() && !cur.getIsShip()) {
                //     // jbutton is white
                //     current.setBackground(Color.white);

                // } else if (cur.getIsHit() && cur.getIsShip()) {
                //     current.setBackground(Color.red);
                // } else if (cur.getIsSunk()) {
                //     current.setBackground(Color.lightGray);
                // } else {
                //     current.setBackground(Color.blue);
                // }
                current.setFocusable(false);
                current.setVisible(true);
                window.getContentPane().add(current);   
                xPositionSum += 50;

            }
            xPositionSum = xPosition;
        }
        window.setVisible(true);
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.setLocationRelativeTo(null);
    }
}




















