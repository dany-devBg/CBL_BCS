package game.ui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

    public static final int WINDOW_WIDTH = 700;
    public static final int WINDOW_HEIGHT = 1000;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GameFrame() {
        // Game title
        this.setTitle("Tetris");

        // Exit App on close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make window non-resizable
        this.setResizable(false);

        // Set default window size
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Center window on screen
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new MenuPanel(this), "Menu");
        mainPanel.add(new GamePanel(), "Game");
        mainPanel.add(new GameOverPanel(this), "GameOver");

        this.add(mainPanel);

        cardLayout.show(mainPanel, "Menu");

    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

}
