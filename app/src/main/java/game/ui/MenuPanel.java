package game.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel{
    public MenuPanel(GameFrame frame) {
        JLabel title = new JLabel("Tetris", SwingConstants.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        title.setFont(new Font("Arial", Font.BOLD, 72));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(60));
        this.add(title);

        this.add(Box.createVerticalStrut(150));

        JPanel buttoPanel = new JPanel();
        buttoPanel.setLayout(new BoxLayout(buttoPanel, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startButton.addActionListener((ActionEvent e) -> frame.showCard("Game"));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttoPanel.add(startButton);
        this.add(buttoPanel);
        this.add(Box.createVerticalGlue());
    }
    
}
