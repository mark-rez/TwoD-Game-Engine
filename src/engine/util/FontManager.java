package engine.util;

import engine.Configuration;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class FontManager {
	private static final HashMap<String, Font> fonts = new HashMap<>();

	static {
		File fontDir = new File(Configuration.PATH_FONTS);
		if (fontDir.exists() && fontDir.isDirectory()) {
			for (File file : Objects.requireNonNull(fontDir.listFiles())) {
				if (file.isFile() && file.getName().toLowerCase().endsWith(".ttf")) {
					String fileName = file.getName();
					String key = fileName.substring(0, fileName.lastIndexOf('.')); // without extension
					try {
						Font font = Font.createFont(Font.TRUETYPE_FONT, file);
						fonts.put(key, font);
					} catch (Exception e) {
						System.err.println("Failed to load font: " + file.getPath());
						e.printStackTrace();
					}
				}
			}
		} else {
			System.err.println("Font directory not found: " + fontDir.getPath());
		}
	}

	public static Font getFont(String fontName, float fontSize) {
		Font base = fonts.get(fontName);
		if (base == null) {
			System.err.println("Font not found: " + fontName);
			return new Font("SansSerif", Font.PLAIN, (int) fontSize);
		}
		return base.deriveFont(Font.PLAIN, fontSize);
	}
}
