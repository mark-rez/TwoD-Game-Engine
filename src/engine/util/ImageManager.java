package engine.util;

import engine.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ImageManager {

    private static final HashMap<String, BufferedImage> images = new HashMap<>();

    static {
        File imageDir = new File(Configuration.PATH_IMAGES);
        if (imageDir.exists() && imageDir.isDirectory()) {
            for (File file : Objects.requireNonNull(imageDir.listFiles())) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
                    String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
                    try {
                        BufferedImage img = ImageIO.read(file);
                        if (img != null) {
                            images.put(name, img);
                            System.out.println(name);
                        } else {
                            System.err.println("Failed to load image: " + file.getPath());
                        }
                    } catch (IOException e) {
                        System.err.println("Error loading image: " + file.getPath());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.err.println("Image directory not found: " + imageDir.getPath());
        }
    }

    public static BufferedImage getImage(String name) {
        BufferedImage img = images.get(name);
        if (img == null) {
            System.err.println("Image not found: " + name);
        }
        return img;
    }
}
