import engine.core.Engine;
import engine.graphics.EngineFrame;
import game.core.Game;

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