import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu {
    
    private JFrame window;
    private ImageIcon backgroundImageIcon;
    private JLabel bkgImageContainer;
    private JButton startGame;
    private volatile boolean isImageVisible;
    private JButton easyModeBtn;
    private JButton headsBtn;
    private JButton submitBtn;

    private JButton nextBtn;
    private JTextPane results = new JTextPane();


    public MainMenu(JFrame theWindow) {
        window = theWindow;
        backgroundImageIcon = new ImageIcon("Title.png");
        bkgImageContainer = new JLabel(backgroundImageIcon);
        isImageVisible = true;
    }

    public void loadTitleScreen() throws Exception {
        bkgImageContainer.setSize(window.getContentPane().getWidth(),
                window.getContentPane().getHeight() / 2);
        bkgImageContainer.setLocation(0, 0);
        bkgImageContainer.setVisible(true);

        startGame = new JButton("Start Game");
        startGame.setSize(600, 100);
        startGame.setLocation(150, bkgImageContainer.getHeight() + 50);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.getContentPane().remove(startGame);
                window.getContentPane().remove(bkgImageContainer);
                window.getContentPane().remove(easyModeBtn);
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                window.getContentPane().setBackground(Color.WHITE);
                isImageVisible = false;
                loadCoinFlip();
                // // Main main = new Main();
                // try {
                //     Main.hello();
                // } catch (Exception e) {
                //     // TODO Auto-generated catch block
                //     e.printStackTrace();
                // }
            }
        });

        easyModeBtn = new JButton("Easy Mode is now " + Main.easyMode);
        easyModeBtn.setSize(600, 100);
        easyModeBtn.setLocation(150, bkgImageContainer.getHeight() + startGame.getHeight() + 50);
        easyModeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.easyMode = !Main.easyMode;
                easyModeBtn.setText("Easy Mode: " + Main.easyMode);
                System.out.println(Main.easyMode);
            }
        });

        startGame.setVisible(true);
        easyModeBtn.setVisible(true);

        window.getContentPane().add(startGame);
        window.getContentPane().add(easyModeBtn);
        window.getContentPane().add(bkgImageContainer);

        window.getContentPane().setBackground(Color.BLACK);
        window.setVisible(true);
        window.getContentPane().revalidate();
        window.getContentPane().repaint();

    }

    public void loadCoinFlip() {
        backgroundImageIcon = new ImageIcon("coinflip.jpeg");

        // backgroundImageIcon = new ImageIcon("Title.png");
        bkgImageContainer = new JLabel(backgroundImageIcon);
        bkgImageContainer.setSize(window.getContentPane().getWidth(),
                window.getContentPane().getHeight());
        bkgImageContainer.setLocation(0, 0);

        headsBtn = new JButton("Heads or Tails?");
        headsBtn.setSize(600, 100);
        headsBtn.setLocation(150, 150);
        System.out.println("testing");
        headsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.heads = !Main.heads;
                if (!Main.heads) {
                    headsBtn.setText("You have selected tails");
                } else {
                    headsBtn.setText("You have selected heads");
                }
                System.out.println(Main.heads);
            }
        });

        submitBtn = new JButton("Submit");
        submitBtn.setSize(600, 100);
        submitBtn.setLocation(150, 400);
        // System.out.println("testing");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.getContentPane().remove(submitBtn);
                window.getContentPane().remove(bkgImageContainer);
                window.getContentPane().remove(headsBtn);
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                window.getContentPane().setBackground(Color.WHITE);
                isImageVisible = false;
                coinFlipResults();

            }
        });

        submitBtn.setVisible(true);
        headsBtn.setVisible(true);
        bkgImageContainer.setVisible(true);

        window.getContentPane().add(submitBtn);
        window.getContentPane().add(headsBtn);
        window.getContentPane().add(bkgImageContainer);

        window.getContentPane().setBackground(Color.BLACK);
        window.setVisible(true);
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
    }
    
    public void coinFlipResults() {
        // backgroundImageIcon = new ImageIcon("Title.png");
        // bkgImageContainer = new JLabel(backgroundImageIcon);
        // bkgImageContainer.setSize(window.getContentPane().getWidth(),
        //         window.getContentPane().getHeight());
        // bkgImageContainer.setLocation(0, 0);
        
        String textSet = Game.coinFlipReturn();
        results.setText(textSet);
        // System.out.println(Game.coinFlipReturn());
        results.setEditable(false);
        results.setBounds(400, 300, 550, 200);
        // System.out.println("testing");
     
        nextBtn = new JButton("Next");
        nextBtn.setSize(600, 100);      
        nextBtn.setLocation(150, 400);
        // System.out.println("testing");
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // window.getContentPane().remove(submitBtn);
                // window.getContentPane().remove(bkgImageContainer);
                // window.getContentPane().remove(headsBtn);
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                window.getContentPane().setBackground(Color.WHITE);
                isImageVisible = false;
            }
        });

        nextBtn.setVisible(true);
        results.setVisible(true);
        // bkgImageContainer.setVisible(true);

        window.getContentPane().add(nextBtn);
        window.getContentPane().add(results);
        // window.getContentPane().add(bkgImageContainer);

        window.getContentPane().setBackground(Color.WHITE);
        window.setVisible(true);
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
    }

    public boolean isImageVisible(){
		return isImageVisible;
	}
}