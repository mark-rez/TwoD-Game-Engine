package engine.core;

import engine.Configuration;
import engine.graphics.EngineFrame;
import engine.input.MouseManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Engine extends JPanel implements Runnable {
    private boolean running = false;
    private final EngineFrame frame;

    // List to hold layers, each layer is a List of EngineObjects
    private final List<List<EngineObject>> layers = new ArrayList<>();

    public Engine() {
        this.setPreferredSize(new Dimension(Configuration.FRAME_WIDTH, Configuration.FRAME_HEIGHT));
        frame = new EngineFrame(this);
    }

    public void addObject(EngineObject obj, int layer) {
        // Ensure the layers list is large enough to hold the given layer
        while (layers.size() <= layer) {
            layers.add(new ArrayList<>());
        }
        layers.get(layer).add(obj);
    }

    public void addObject(EngineObject obj) {
        addObject(obj, 0); // default to layer 0
    }

    public void removeObject(EngineObject obj) {
        for (List<EngineObject> layer : layers) {
            layer.remove(obj);
        }
    }

    // Method to start the game loop
    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public abstract void update(double deltaTime);

    @Override
    public void run() {
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0; // seconds
            lastTime = now;

            update(deltaTime);
            repaint();

            try {
                Thread.sleep(Configuration.FRAME_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g); // clear background

        // Render objects layer by layer
        for (List<EngineObject> layer : layers) {
            for (EngineObject obj : layer) {
                obj.render(g);
            }
        }
    }

    public MouseManager getMouseManager() {
        return frame.getMouseManager();
    }
}
