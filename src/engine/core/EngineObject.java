package engine.core;

import engine.Configuration;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class EngineObject {
    protected Point2D.Double position;
    protected int width, height;

    public EngineObject(double x, double y, int width, int height) {
        position = new Point2D.Double(x, y);
        this.width = width;
        this.height = height;
    }

    public EngineObject(Point2D.Double position, int width, int height) {
        this.position = new Point2D.Double(position.x, position.y);
        this.width = width;
        this.height = height;
    }

    public boolean isOutOfBounds() {
        return position.x - width < 0 || position.x > Configuration.FRAME_WIDTH || position.y - height < 0 || position.y > Configuration.FRAME_HEIGHT;
    }

    public abstract void render(Graphics g);
}
