package game.ui;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

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

        GamePanel panel = new GamePanel();

        this.add(panel);

    }

}
