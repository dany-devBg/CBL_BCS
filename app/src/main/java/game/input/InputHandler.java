package game.input;

import java.awt.event.ActionEvent;
import java.util.function.Supplier;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import game.ui.TetrisPiece;

public class InputHandler {

    private JComponent panel;

    public InputHandler(JComponent panel) {
        this.panel = panel;
    }

    public class MoveLeftAction extends AbstractAction {
        private Supplier<TetrisPiece> pieceSupplier;

        public MoveLeftAction(Supplier<TetrisPiece> pieceSupplier) {
            this.pieceSupplier = pieceSupplier;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TetrisPiece tetrisPiece = pieceSupplier.get();
            if (tetrisPiece != null) {
                tetrisPiece.moveLeft();
                panel.repaint();
            }
        }

    }

    public class MoveRightAction extends AbstractAction {
        private Supplier<TetrisPiece> pieceSupplier;

        public MoveRightAction(Supplier<TetrisPiece> pieceSupplier) {
            this.pieceSupplier = pieceSupplier;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TetrisPiece tetrisPiece = pieceSupplier.get();
            if (tetrisPiece != null) {
                tetrisPiece.moveRight();
                panel.repaint();
            }
        }

    }

    public class MoveDownAction extends AbstractAction {
        private Supplier<TetrisPiece> pieceSupplier;

        public MoveDownAction(Supplier<TetrisPiece> pieceSupplier) {
            this.pieceSupplier = pieceSupplier;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TetrisPiece tetrisPiece = pieceSupplier.get();
            if (tetrisPiece != null) {
                tetrisPiece.moveDown();
                panel.repaint();
            }
        }

    }

}
