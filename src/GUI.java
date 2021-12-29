import java.awt.*;
import javax.swing.*;

public class GUI {
    // private volatile boolean isImageVisible;
    private JFrame frame;

    public GUI() {

    }

    public void setUpWindow() throws Exception {
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

    public void startGame() throws Exception{		
		MainMenu startMenu = new MainMenu(frame);
        startMenu.loadTitleScreen();
        
        // startMenu.
		while(startMenu.isImageVisible()){}
		
		// Ship[] p1Ships = initializeShipCreation(true);
		// Ship[] p2Ships = initializeShipCreation(false);
		
		// Grid grid = new Grid(chooseShipPositions(p1Ships));
		// SmallGrid small = new SmallGrid(chooseShipPositions(p2Ships));
		// small.setLocation(grid.getWidth()+10, grid.getY());
		
		//panel.setLayout(null);
		
		// int windowWidth = small.getX() + small.getWidth() + 10;
		// frame.setPreferredSize(new Dimension(windowWidth, frame.getHeight())); 
		// frame.setSize(frame.getPreferredSize());
		// frame.pack();
		
		// frame.getContentPane().add(grid); // adds the grids to the window
		// frame.getContentPane().add(small);
		// frame.addMouseListener(grid);
		// frame.setVisible(true);

		// gameLoop(p1Ships, p2Ships, grid, small);
	}
}




















