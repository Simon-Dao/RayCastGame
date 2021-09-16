import org.newdawn.slick.*;

public class Game extends BasicGame {

    private Map gameMap;
    private Player player;
    private Enemy[] enemies;
    private int tileSize;
    private final int ENEMY_COUNT = 3;
    private long time = 0;
    public static float deltaTime;

    //todo temp variable
    private final int scaler = tileSize;

    private final int fov = 100;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameMap = new Map(Main.MAP_WIDTH,Main.MAP_HEIGHT);
        player = new Player(gameContainer.getInput(),250,250);
        enemies = new Enemy[ENEMY_COUNT];
        tileSize = Main.WINDOW_HEIGHT/Main.MAP_WIDTH;
        time = gameContainer.getTime();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        deltaTime = (float)Math.abs(gameContainer.getTime() - time)/(float)1000;

        time = gameContainer.getTime();

        graphics.clear();
        renderScene(graphics);
        draw2DMap(graphics);
        //drawPlayer(graphics);

        player.updatePlayerMovement();
    }

    public void renderScene(Graphics g) {

        float rayX, rayY, rayA, xo, yo, playerAngle = player.getFacingAngle(), px = player.getX(), py = player.getY();
        int mapX, mapY;
        rayA = playerAngle;
        float aTan = (float)Math.atan(rayA);

        //cardinal angles

        //horizontal line
        if(playerAngle < 180) {
            rayY = (int)py/tileSize;
            rayX = (py-rayY) * aTan;
            yo = -tileSize;
            xo = -yo*aTan;
        }

    }

    public Color getShade(float distance) {

        int shade = (int)((1 - (distance/Main.WINDOW_HEIGHT)) * 255) / 2;

        return new Color(shade,shade,shade);
    }

    public void draw2DMap(Graphics g) {
        int[][] map = gameMap.getGameMap();

        getRayLengths(g);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {

                switch (map[i][j]) {
                    case WallType.WALL:
                        g.setColor(Color.green);
                        g.fillRect(j*tileSize,i*tileSize,tileSize,tileSize);
                        break;
                }
            }
        }
    }


}
