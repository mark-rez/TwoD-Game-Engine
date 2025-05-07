package engine.core;

import engine.Configuration;

import java.awt.*;

public abstract class EngineObject {
    protected double x, y;
    protected int width, height;

    public EngineObject(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isOutOfBounds() {
        return x - width < 0 || x > Configuration.FRAME_WIDTH || y - height < 0 || y > Configuration.FRAME_HEIGHT;
    }

    public abstract void render(Graphics g);
}
