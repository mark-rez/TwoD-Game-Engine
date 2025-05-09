package engine;

public class Configuration {
    public static final String FRAME_TITLE = "TwoD-Game-Engine";
    public static final int FRAME_WIDTH = 1270;
    public static final int FRAME_HEIGHT = 720;
    public static final boolean FRAME_RESIZABLE = false;
    public static final String FRAME_ICON_NAME = ""; // no image

    private static final int FPS = 60;
    public static final long FRAME_TIME = 1000 / FPS;

    public static final String PATH_SOUNDS = "assets/sounds";
    public static final String PATH_FONTS = "assets/fonts";
}
