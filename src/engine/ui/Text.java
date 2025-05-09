package engine.ui;

import engine.core.EngineObject;
import engine.util.FontManager;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Text extends EngineObject {
    private String text;
    private Font font;
    private final Color color;
    private final boolean centered;

    public Text(String text, int fontSize, Color color, Point2D.Double position) {
        super(position, 0, 0);
        this.text = text;
        this.color = color;
        this.centered = false;
        this.font = new Font("SansSerif", Font.PLAIN, fontSize);
        calculateSize();
    }

    public Text(String text, int fontSize, Color color, Point2D.Double position, boolean centered) {
        super(position, 0, 0);
        this.text = text;
        this.color = color;
        this.centered = centered;
        this.font = new Font("SansSerif", Font.PLAIN, fontSize);
        calculateSize();
    }

    private void calculateSize() {
        // Temporary image to get width and height
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tempImage.createGraphics();
        g2d.setFont(this.font);
        FontMetrics fm = g2d.getFontMetrics();

        width = fm.stringWidth(text);
        height = fm.getHeight();

        g2d.dispose();
    }

    public void setFont(String fontName, int fontSize) {
        font = FontManager.getFont(fontName, fontSize);
        calculateSize();
    }

    public void setText(String text) {
        this.text = text;
        calculateSize();
    }

    public String getText() {
        return text;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setFont(font);

        if (centered) {
            g2d.drawString(text, (int) (position.x - width / 2d), (int) (position.y - height / 2d));
        } else {
            g2d.drawString(text, (int) position.x, (int) position.y);
        }
        g2d.dispose();
    }
}

