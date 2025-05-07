package engine.graphics;

import engine.Configuration;
import engine.input.MouseManager;

import javax.swing.*;

public class EngineFrame extends JFrame {
    private final MouseManager mouseManager;

    public EngineFrame(JPanel panel) {
        setSize(Configuration.FRAME_WIDTH, Configuration.FRAME_HEIGHT);
        setTitle(Configuration.FRAME_TITLE);
        setResizable(Configuration.FRAME_RESIZABLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panel);
        pack();

        setVisible(true);
        setLocationRelativeTo(null); // center

        mouseManager = new MouseManager();
        addMouseListener(mouseManager);
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
}
