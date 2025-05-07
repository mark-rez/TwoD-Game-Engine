package engine.core;

import engine.Configuration;
import engine.audio.SoundManager;
import engine.graphics.EngineFrame;
import engine.input.MouseManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Engine extends JPanel implements Runnable {
    private boolean running = false;
    private final EngineFrame frame;
    private List<EngineObject> objects = new ArrayList<>();

    public Engine() {
        this.setPreferredSize(new Dimension(Configuration.FRAME_WIDTH, Configuration.FRAME_HEIGHT));
        frame = new EngineFrame(this);
    }

    public void addObject(EngineObject obj) {
        objects.add(obj);
    }

    public void removeObject(EngineObject obj) {
        objects.remove(obj);
    }

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

    public void paint(Graphics g) {
        super.paintComponent(g); // clear background

        for (EngineObject obj : objects) {
            obj.render(g);
        }
    }

    public MouseManager getMouseManager() {
        return frame.getMouseManager();
    }
}
