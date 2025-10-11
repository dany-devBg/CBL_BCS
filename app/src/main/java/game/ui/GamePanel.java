package game.ui;

import game.input.InputHandler;
import game.logic.GameController;

import java.awt.Color;
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
    public static final int BOX_SIZE = 40;
    private int topLeftX;
    private int topLeftY;

    private Timer timer;
    private TetrisPiece currentPiece;
    private InputHandler inputHandler;
    GameController controller;
    private Board board;

    public GamePanel() {

        this.topLeftX = (GameFrame.WINDOW_WIDTH - BOX_SIZE * COLS) / 2;
        this.topLeftY = (GameFrame.WINDOW_HEIGHT - BOX_SIZE * ROWS) / 2;

        controller = new GameController(this);

        timer = new Timer(1000, this);
        timer.start();

        inputHandler = new InputHandler(this);

        inputHandler.bindKey("RIGHT", "moveRightAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryMove(piece, piece.getX() + 1,
                        piece.getY()));
        inputHandler.bindKey("D", "moveRightAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryMove(piece, piece.getX() + 1,
                        piece.getY()));

        inputHandler.bindKey("LEFT", "moveLeftAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryMove(piece, piece.getX() - 1,
                        piece.getY()));
        inputHandler.bindKey("A", "moveLeftAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryMove(piece, piece.getX() - 1,
                        piece.getY()));

        inputHandler.bindKey("DOWN", "moveDownAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryMove(piece, piece.getX(),
                        piece.getY() + 1));
        inputHandler.bindKey("S", "moveDownAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryMove(piece, piece.getX(),
                        piece.getY() + 1));

        inputHandler.bindKey("X", "rotateCwAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryRotate(piece, true));
        inputHandler.bindKey("Z", "rotateCcwAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.tryRotate(piece, false));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        TetrisPiece currentPiece = controller.getCurrentPiece();
        Board board = controller.getBoard();

        // Draw grid
        int y = topLeftY;
        for (int horz = 0; horz < ROWS; horz++) {
            int x = topLeftX;
            for (int vert = 0; vert < COLS; vert++) {
                g2d.drawRect(x, y, BOX_SIZE, BOX_SIZE);
                x += BOX_SIZE;
            }
            y += BOX_SIZE;
        }

        // Draw locked pieces
        Color[][] grid = board.getGrid();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] != null) {
                    int drawX = topLeftX + c * BOX_SIZE;
                    int drawY = topLeftY + r * BOX_SIZE;
                    g2d.setColor(grid[r][c]);
                    g2d.fillRect(drawX, drawY, BOX_SIZE, BOX_SIZE);
                }
            }
        }

        // Draw current falling piece
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
        controller.update();
        // Repaint the screen
        repaint();
    }

}
