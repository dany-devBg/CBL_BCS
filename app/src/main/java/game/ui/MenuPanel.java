package game.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel {

    private int titleColorIndex = 0;
    private Color[] neonColors = {
            new Color(255, 20, 147), // Pink
            new Color(57, 255, 20), // Green
            new Color(0, 255, 255), // Cyan
            new Color(255, 165, 0), // Orange
            new Color(255, 255, 0), // Yellow
            new Color(148, 0, 211) // Purple
    };

    public MenuPanel(GameFrame frame) {

        this.setBackground(Color.BLACK);
        JLabel title = new JLabel("Tetris", SwingConstants.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        title.setFont(new Font("Arial", Font.BOLD, 72));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(neonColors[(++titleColorIndex) % neonColors.length]);
        title.setCursor(new Cursor(Cursor.HAND_CURSOR));

        title.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                title.setForeground(neonColors[(++titleColorIndex) % neonColors.length]);
            }
        });

        this.add(Box.createVerticalStrut(90));
        this.add(title);

        this.add(Box.createVerticalStrut(100));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.BLACK);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 32));
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.addActionListener((ActionEvent e) -> {
            frame.showGame();
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(Color.WHITE);
                startButton.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(Color.BLACK);
                startButton.setForeground(Color.WHITE);
            }
        });

        JButton scoreboardButton = new JButton("Scoreboard");
        scoreboardButton.setFont(new Font("Arial", Font.PLAIN, 32));
        scoreboardButton.setFocusPainted(false);
        scoreboardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        scoreboardButton.setForeground(Color.WHITE);
        scoreboardButton.setBackground(Color.BLACK);
        scoreboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreboardButton.addActionListener((ActionEvent e) -> frame.showCard("Scoreboard"));
        scoreboardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                scoreboardButton.setBackground(Color.WHITE);
                scoreboardButton.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                scoreboardButton.setBackground(Color.BLACK);
                scoreboardButton.setForeground(Color.WHITE);
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(45));
        buttonPanel.add(scoreboardButton);
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
    }

}
