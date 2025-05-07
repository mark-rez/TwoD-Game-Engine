package engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {

    private boolean leftMouseButtonDown = false;
    private boolean rightMouseButtonDown = false;

    @Override
    public void mousePressed(MouseEvent e) {
        MouseButton button = MouseButton.fromCode(e.getButton());
        setIsMouseButtonDown(button, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseButton button = MouseButton.fromCode(e.getButton());
        setIsMouseButtonDown(button, false);
    }

    public boolean isMouseButtonDown(MouseButton button) {
        return switch (button) {
            case LEFT -> leftMouseButtonDown;
            case RIGHT -> rightMouseButtonDown;
        };
    }

    private void setIsMouseButtonDown(MouseButton button, boolean isDown) {
        switch (button) {
            case LEFT -> leftMouseButtonDown = isDown;
            case RIGHT -> rightMouseButtonDown = isDown;
        }
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
