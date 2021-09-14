import java.util.ArrayList;

public class Game {

    private Map gameMap;
    private Player player;
    private Enemy[] enemies;

    private final int ENEMY_COUNT = 15;

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {
        gameMap = new Map(10,10);
        player = new Player();

        enemies = new Enemy[ENEMY_COUNT];
    }
}
