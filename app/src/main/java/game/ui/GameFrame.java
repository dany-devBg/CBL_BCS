package game.ui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 900;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private GameOverPanel gameOverPanel;

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

        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        gameOverPanel = new GameOverPanel(this);


        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(gamePanel, "Game");
        mainPanel.add(gameOverPanel, "GameOver");


        this.add(mainPanel);

        cardLayout.show(mainPanel, "Menu");

    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    public void showGame() {
        cardLayout.show(mainPanel, "Game");
        gamePanel.startGame();
    }

}
