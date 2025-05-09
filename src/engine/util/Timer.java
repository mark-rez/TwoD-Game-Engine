package engine.util;

public class Timer {
    private final long durationMillis;
    private long startTime;
    private boolean autoRepeat;

    public Timer(long durationMillis) {
        this.durationMillis = durationMillis;
        this.startTime = System.currentTimeMillis();
        this.autoRepeat = false;
    }

    public Timer(long durationMillis, boolean autoRepeat) {
        this.durationMillis = durationMillis;
        this.autoRepeat = autoRepeat;
        this.startTime = System.currentTimeMillis();
    }

    public boolean isDone() {
        long now = System.currentTimeMillis();
        if (now - startTime >= durationMillis) {
            if (autoRepeat) {
                startTime = now;
            }
            return true;
        }
        return false;
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    public void setAutoRepeat(boolean autoRepeat) {
        this.autoRepeat = autoRepeat;
    }

    public boolean isAutoRepeat() {
        return autoRepeat;
    }

    public long timeLeft() {
        long elapsed = System.currentTimeMillis() - startTime;
        return Math.max(durationMillis - elapsed, 0);
    }

    public long getDurationMillis() {
        return durationMillis;
    }
}
