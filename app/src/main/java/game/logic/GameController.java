package game.logic;

import game.model.Board;
import game.model.TetrisPiece;
import game.ui.GamePanel;

public class GameController {
    private Board board;
    private TetrisPiece currentPiece;
    private GamePanel panel; // for repaint

    public GameController(GamePanel panel) {
        this.panel = panel;
        this.board = new Board();
        this.currentPiece = TetrisPiece.randomPiece();
    }

    public void tryMove(TetrisPiece piece, int newX, int newY) {
        if (board.isValidPosition(piece, newX, newY, piece.getShape())) {
            piece.setX(newX);
            piece.setY(newY);
            panel.repaint();
        }
    }

    public void tryRotate(TetrisPiece piece, boolean clockwise) {
        boolean[][] rotated = clockwise ? piece.getRotatedClockwise()
                : piece.getRotatedCounterClockwise();
        if (board.isValidPosition(piece, piece.getX(), piece.getY(), rotated)) {
            piece.setShape(rotated);
            panel.repaint();
        }
    }

    public void update() {
        int newY = currentPiece.getY() + 1;

        if (board.isValidPosition(currentPiece, currentPiece.getX(), newY,
                currentPiece.getShape())) {
            currentPiece.setY(currentPiece.getY() + 1);
        } else {
            board.placePiece(currentPiece);
            currentPiece = TetrisPiece.randomPiece();
        }
    }

    // Getters
    public TetrisPiece getCurrentPiece() {
        return currentPiece;
    }

    public Board getBoard() {
        return board;
    }
}
