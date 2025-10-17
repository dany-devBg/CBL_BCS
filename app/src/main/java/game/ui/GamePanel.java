package game.ui;

import game.input.InputHandler;
import game.logic.GameController;
import game.model.Board;
import game.model.TetrisPiece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
    public static final int ROWS = 20;
    public static final int COLS = 10;
    public static final int BOX_SIZE = 40;
    private int topLeftX;
    private int topLeftY;

    private InputHandler inputHandler;
    GameController controller;

    GameFrame frame;

    public GamePanel(GameFrame frame) {

        this.frame = frame;

        this.topLeftX = (GameFrame.WINDOW_WIDTH - BOX_SIZE * COLS) / 6;
        this.topLeftY = (GameFrame.WINDOW_HEIGHT - BOX_SIZE * ROWS) / 2;

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
        inputHandler.bindKey("SPACE", "hardDropAction",
                () -> controller.getCurrentPiece(),
                piece -> controller.hardDrop(piece));
    }

    public void startGame() {
        controller = new GameController(this);
        controller.startGame();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    private void drawCenteredPiece(boolean[][] shape, Graphics2D g2d, int previewBoxX, int previewBoxY,
            int previewBoxSize) {
        int minRow = 4;
        int maxRow = -1;
        int minCol = 4;
        int maxCol = -1;
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j]) {
                    if (i < minRow) {
                        minRow = i;
                    }
                    if (i > maxRow) {
                        maxRow = i;
                    }
                    if (j < minCol) {
                        minCol = j;
                    }
                    if (j > maxCol) {
                        maxCol = j;
                    }
                }
            }
        }

        int shapeWidth = (maxCol - minCol + 1) * BOX_SIZE;
        int shapeHeight = (maxRow - minRow + 1) * BOX_SIZE;

        // Center the shape within the preview box
        int offsetX = previewBoxX + (previewBoxSize - shapeWidth) / 2;
        int offsetY = previewBoxY + (previewBoxSize - shapeHeight) / 2;

        // Draw each block of the shape
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j]) {
                    int drawX = offsetX + (j - minCol) * BOX_SIZE;
                    int drawY = offsetY + (i - minRow) * BOX_SIZE;
                    g2d.fillRect(drawX, drawY, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }

    private void drawNextPiece(Graphics2D g2d) {
        int previewBoxX = topLeftX + (COLS + 2) * BOX_SIZE; // a bit to the right of the main grid
        int previewBoxY = topLeftY + 27; // some padding from the top

        g2d.setColor(Color.BLACK);
        Font originalFont = g2d.getFont();
        Font biggerFont = originalFont.deriveFont(Font.BOLD, 24); // 24-point bold font

        // Apply it
        g2d.setFont(biggerFont);
        g2d.drawString("Next:", previewBoxX, previewBoxY - 10);

        // Draw a small 4x4 box for preview
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(previewBoxX, previewBoxY, BOX_SIZE * 4 + 10, BOX_SIZE * 4 + 10);

        // Draw the next piece centered inside the preview box
        if (controller.getNextPiece() != null) {
            boolean[][] nextShape = controller.getNextPiece().getShape();
            g2d.setColor(controller.getNextPiece().getColor());

            drawCenteredPiece(nextShape, g2d, previewBoxX + 5, previewBoxY + 5, BOX_SIZE * 4);
        }
    }

    private void drawGrid(Graphics2D g2d) {
        int y = topLeftY;
        for (int row = 0; row < ROWS; row++) {
            int x = topLeftX;
            for (int col = 0; col < COLS; col++) {
                g2d.drawRect(x, y, BOX_SIZE, BOX_SIZE);
                x += BOX_SIZE;
            }
            y += BOX_SIZE;
        }
    }

    private void drawLockedPieces(Graphics2D g2d) {
        Color[][] grid = controller.getBoard().getGrid();
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
    }

    private void drawFallingPiece(Graphics2D g2d) {
        TetrisPiece currentPiece = controller.getCurrentPiece();
        for (int i = 0; i < currentPiece.getShape().length; i++) {
            for (int j = 0; j < currentPiece.getShape()[0].length; j++) {
                if (currentPiece.getShape()[i][j]) {
                    int pieceX = topLeftX + (currentPiece.getX() + j) * BOX_SIZE;
                    int pieceY = topLeftY + (currentPiece.getY() + i) * BOX_SIZE;

                    g2d.setColor(currentPiece.getColor());

                    g2d.fillRect(pieceX, pieceY, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw grid
        drawGrid(g2d);

        // Draw locked pieces
        drawLockedPieces(g2d);

        // Draw current falling piece
        drawFallingPiece(g2d);

        // Draw next piece preview
        drawNextPiece(g2d);

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update game state
        controller.update();
        if (controller.isGameOver()) {
            frame.showCard("GameOver");
            controller.resetGame();
        }
        // Repaint the screen
        repaint();
    }

}
