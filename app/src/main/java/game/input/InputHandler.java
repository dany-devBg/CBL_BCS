package game.input;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import game.model.TetrisPiece;

public class InputHandler {

    private JComponent panel;

    public InputHandler(JComponent panel) {
        this.panel = panel;
    }

    public void bindKey(String keyStroke, String actionName, Supplier<TetrisPiece> pieceSupplier,
            Consumer<TetrisPiece> action) {
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(keyStroke), actionName);
        panel.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TetrisPiece tetrisPiece = pieceSupplier.get();
                if (tetrisPiece != null) {
                    action.accept(tetrisPiece);
                    panel.repaint();
                }
            }
        });
    }

}
