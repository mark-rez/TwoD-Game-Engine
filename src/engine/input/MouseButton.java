package engine.input;

public enum MouseButton {
    LEFT(1),
    RIGHT(3);

    private final int code;

    MouseButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MouseButton fromCode(int code) {
        for (MouseButton btn : values()) {
            if (btn.code == code) return btn;
        }
        throw new IllegalArgumentException("Unsupported mouse button code: " + code);
    }
}
