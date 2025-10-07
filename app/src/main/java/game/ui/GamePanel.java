package game.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public GamePanel() {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int size = Math.min(getWidth() - 4, getHeight() - 4) / 20;
        int width = getWidth() - (size * 2);
        int height = getHeight() - (size * 2);

        int y = (getHeight() - (size * 20)) / 2;
        for (int horz = 0; horz < 20; horz++) {
            int x = (getWidth() - (size * 20)) / 2;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        g2d.dispose();
    }
}
