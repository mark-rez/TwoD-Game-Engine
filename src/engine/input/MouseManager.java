package engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EnumMap;
import java.util.Map;

public class MouseManager implements MouseListener {

    private final Map<MouseButton, Boolean> buttonStates = new EnumMap<>(MouseButton.class);

    public MouseManager() {
        for (MouseButton button : MouseButton.values()) {
            buttonStates.put(button, false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MouseButton button = MouseButton.fromCode(e.getButton());
        buttonStates.put(button, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseButton button = MouseButton.fromCode(e.getButton());
        buttonStates.put(button, false);
    }

    public boolean isMouseButtonDown(MouseButton button) {
        return buttonStates.getOrDefault(button, false);
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
