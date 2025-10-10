package game.ui;

import game.input.InputHandler;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    public static final int ROWS = 20;
    public static final int COLS = 10;
    public static final int BOX_SIZE = 45;
    private int topLeftX;
    private int topLeftY;

    private Timer timer;
    private TetrisPiece currentPiece;
    private InputHandler inputHandler;

    public GamePanel() {

        this.topLeftX = (GameFrame.WINDOW_WIDTH - BOX_SIZE * COLS) / 2;
        this.topLeftY = (GameFrame.WINDOW_HEIGHT - BOX_SIZE * ROWS) / 2;

        currentPiece = TetrisPiece.randomPiece();
        timer = new Timer(1000, this);
        timer.start();

        inputHandler = new InputHandler(this);

        inputHandler.bindKey("RIGHT", "moveRightAction", () -> this.getCurrentPiece(), TetrisPiece::moveRight);

        inputHandler.bindKey("LEFT", "moveLeftAction", () -> this.getCurrentPiece(), TetrisPiece::moveLeft);

        inputHandler.bindKey("DOWN", "moveDownAction", () -> this.getCurrentPiece(), TetrisPiece::moveDown);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int y = topLeftY;
        for (int horz = 0; horz < 20; horz++) {
            int x = topLeftX;
            for (int vert = 0; vert < 10; vert++) {
                g2d.drawRect(x, y, BOX_SIZE, BOX_SIZE);
                x += BOX_SIZE;
            }
            y += BOX_SIZE;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (currentPiece.getShape()[i][j]) {
                    int pieceX = topLeftX + (currentPiece.getX() + j) * BOX_SIZE;
                    int pieceY = topLeftY + (currentPiece.getY() + i) * BOX_SIZE;

                    g2d.setColor(currentPiece.getColor());

                    g2d.fillRect(pieceX, pieceY, BOX_SIZE, BOX_SIZE);
                }
            }
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
        currentPiece.setY(currentPiece.getY() + 1);

    }

    public TetrisPiece getCurrentPiece() {
        return this.currentPiece;
    }
}
