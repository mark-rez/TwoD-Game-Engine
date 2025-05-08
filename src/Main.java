import engine.core.Engine;

public class Main {
    public static void main(String[] args) {
        Engine e = new Engine() {
            @Override
            public void update(double deltaTime) {

            }
        };
        e.start();
    }
}