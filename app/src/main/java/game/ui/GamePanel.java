package game.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private boolean[][] currentPieceShape = { { false, false, false, false }, { false, false, false, false },
            { false, false, false, false }, { true, true, true, true } };
    int cpX = 3;
    int cpY = 3;

    public GamePanel() {
        timer = new Timer(500, this);

        addKeyListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int size = Math.min(this.getWidth() - 4, this.getHeight() - 4) / 20;
        int width = this.getWidth() - (size * 2);
        int height = this.getHeight() - (size * 2);

        int y = (this.getHeight() - (size * 20)) / 2;
        for (int horz = 0; horz < 20; horz++) {
            int x = (this.getWidth() - (size * 20)) / 2;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update game state
        update();
        // Repaint the screen
        repaint();
    }

    private void update() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
