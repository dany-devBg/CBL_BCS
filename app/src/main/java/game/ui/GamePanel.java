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
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    public static final int ROWS = 20;
    public static final int COLS = 10;
    public static final int BOX_SIZE = 40;
    private int topLeftX;
    private int topLeftY;

    private Timer timer;
    private InputHandler inputHandler;
    GameController controller;

    public GamePanel() {

        this.topLeftX = (GameFrame.WINDOW_WIDTH - BOX_SIZE * COLS) / 6;
        this.topLeftY = (GameFrame.WINDOW_HEIGHT - BOX_SIZE * ROWS) / 2;

        timer = new Timer(1000, this);

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

    public void startGame() {
        controller = new GameController(this);
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    private void drawCenteredPiece(boolean[][] shape, Graphics2D g2d, int previewBoxX, int previewBoxY, int previewBoxSize) {
        int minRow = 4, maxRow = -1, minCol = 4, maxCol = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (shape[i][j]) {
                    if (i < minRow) minRow = i;
                    if (i > maxRow) maxRow = i;
                    if (j < minCol) minCol = j;
                    if (j > maxCol) maxCol = j;
                }
            }
        }

        int shapeWidth = (maxCol - minCol + 1) * BOX_SIZE;
        int shapeHeight = (maxRow - minRow + 1) * BOX_SIZE;

        // --- Step 2: Center the shape within the preview box ---
        int offsetX = previewBoxX + (previewBoxSize - shapeWidth) / 2;
        int offsetY = previewBoxY + (previewBoxSize - shapeHeight) / 2;

        // --- Step 3: Draw each block of the shape ---
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (shape[i][j]) {
                    int drawX = offsetX + (j - minCol) * BOX_SIZE;
                    int drawY = offsetY + (i - minRow) * BOX_SIZE;
                    g2d.fillRect(drawX, drawY, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }

    private void drawNextPiece(Graphics2D g2d) {
        int previewBoxX = topLeftX + (COLS + 2) * BOX_SIZE;  // a bit to the right of the main grid
        int previewBoxY = topLeftY + 27;           // some padding from the top
        int previewBoxSize = BOX_SIZE * 4; // 4x4 preview grid

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
        for (int horz = 0; horz < ROWS; horz++) {
            int x = topLeftX;
            for (int vert = 0; vert < COLS; vert++) {
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
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        TetrisPiece currentPiece = controller.getCurrentPiece();
        Board board = controller.getBoard();

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
        // Repaint the screen
        repaint();
    }

}

