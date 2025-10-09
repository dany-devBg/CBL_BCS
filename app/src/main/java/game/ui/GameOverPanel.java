package game.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverPanel extends JPanel {
    public GameOverPanel(GameFrame frame) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(60));
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 72));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(gameOverLabel);

        this.add(Box.createVerticalStrut(150));
        JButton restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        restartButton.addActionListener(e -> frame.showCard("Game"));
        this.add(restartButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restartButton);
        this.add(buttonPanel);
    }
    
}
